package com.schening.phoenix.security.test;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class TestBCrypt {

    @Test
    public void test() {
        // 对原始密码进行加密
        String hashPw = BCrypt.hashpw("123", BCrypt.gensalt());
        System.out.println(hashPw);
        // 校验原始密码和BCrypt密码是否一致
        boolean checkPw = BCrypt.checkpw("123", "$2a$10$NlBC84MVb7F95EXYTXwLneXgCca6/GipyWR5NHm8K0203bSQMLpvm");
        System.out.println(checkPw);
    }
}
