## Spring Security OAuth2单点登录

#### 问题

* 多个系统多套用户表，认证工作交由认证中心负责，不同系统负责不同的角色权限
* 需要可扩展，例如第三方验证，可通过数据库设计解决，参考（https://www.liaoxuefeng.com/article/1029274073038464）
* 

用户认证由认证中心统一负责，用户鉴权由各应用独立完成。

统一认证服务可配置，每个应用可以自由选择认证方式。

对外提供多种系统认证方式(authorization_code、password、client_credentials、refresh_token)等。

可接入第三方应用进行登录。

支持用户管理功能。

每个应用负责自身权限配置工作。



#### 目标

* 支持单点登录。
* 支持用户管理功能。
* 自定义权限访问控制。
* 登录方式可扩展（账户、微信、微博）。





#### 参考

https://www.liaoxuefeng.com/article/1078848528483840

https://www.cnblogs.com/xifengxiaoma/p/10043173.html

数据库脚本

```mysql
CREATE TABLE user(
    id varchar(32) PRIMARY KEY,
    name varchar(32) not null,
    birth varchar(32)
) DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

CREATE TABLE local_auth(
    id varchar(32) PRIMARY KEY,
    user_id varchar(32) NOT NULL,
    username varchar(32) NOT NULL,
    password varchar(32) NOT NULL
) DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

CREATE TABLE o_auth(
    id varchar(32) PRIMARY KEY,
    user_id varchar(32) NOT NULL,
    oauth_name varchar(32) NOT NULL,
    oauth_access_token varchar(512) NOT NULL,
    oauth_expires tinyint(1) NOT NULL

) DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

CREATE TABLE api_auth(
    id varchar(32) PRIMARY KEY,
    user_id varchar
) DEFAULT CHARSET utf8 COLLATE utf8_general_ci;
```

确认用户身份，我们需要一个统一的`Authenticator`接口。以Java为例，该接口看起来如下：

```java
public interface Authenticator {
    // 认证成功返回User，认证失败抛出异常，无认证信息返回null:
    User authenticate(HttpServletRequest request, HttpServletResponse response) throws AuthenticateException;
}
```

接下来，对于每一种类型的认证，我们都编写一个对应的`Authenticator`的实现类。例如，针对表单登录后的Cookie，需要一个`LocalCookieAuthenticator`：

```java
public LocalCookieAuthenticator implements Authenticator {
    public User authenticate(HttpServletRequest request, HttpServletResponse response) {
        String cookie = getCookieFromRequest(request, 'cookieName');
        if (cookie == null) {
            return null;
        }
        return getUserByCookie(cookie);
    }
}
```

对于直接用Basic认证的`Authorization` Header，我们需要一个`BasicAuthenticator`：

```java
public BasicAuthenticator implements Authenticator {
    public User authenticate(HttpServletRequest request, HttpServletResponse response) {
        String auth = getHeaderFromRequest(request, "Authorization");
        if (auth == null) {
            return null;
        }
        String username = parseUsernameFromAuthorizationHeader(auth);
        String password = parsePasswordFromAuthorizationHeader(auth);
        return authenticateUserByPassword(username, password);
    }
}
```

对于用API Token认证的方式，同样编写一个`APIAuthenticator`：

```java
public APIAuthenticator implements Authenticator {
    public User authenticate(HttpServletRequest request, HttpServletResponse response) {
        String token = getHeaderFromRequest(request, "X-API-Token");
        if (token == null) {
            return null;
        }
        return authenticateUserByAPIToken(token);
    }
}
```

然后在一个统一的入口处，例如`Filter`里面，把这些`Authenticator`全部串起来，让它们依次自己去尝试认证：

```java
public class GlobalFilter implements Filter {
    // 所有的Authenticator都在这里:
    Authenticator[] authenticators = initAuthenticators();

    // 每个页面都会执行的代码:
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        User user = null;
        for (Authenticator auth : this.authenticators) {
            user = auth.authenticate(request, response);
            if (user != null) {
                break;
            }
        }
        // user放哪？
        chain.doFilter(request, response);
    }
}
```

现在，一个可扩展的认证体系在Web层就基本搭建完成了，我们可以随意组合各种`Authenticator`，优先级高的放前面。一旦某个`Authenticator`成功地认证了用户，后面的`Authenticator`就不执行了。

最后只剩一个问题：认证成功后的`User`对象放哪？

放session里？NO，我们在前面已经拒绝了使用服务器提供的session。放`request`里？也不好，因为HTTP级别的对象太低级，很难传到业务层里。

> 那你说应该放哪？

当然是放到一个与业务逻辑相关的地方了，比如`UserContext`中。把`Filter`代码改写如下：

```
public class GlobalFilter implements Filter {
    Authenticator[] authenticators = initAuthenticators();

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        // 链式认证获得User:
        User user = tryGetAuthenticatedUser(request, response);
        // 把User绑定到UserContext中:
        try (UserContext ctx = new UserContext(user)) {
            chain.doFilter(request, response);
        }
    }
}
```

这样一来，任何地方需要获得当前`User`时，只需要写：

```java
User user = UserContext.getCurrentUser();
```

