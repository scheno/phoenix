package com.schening.phoenix.security.po;

/**
 * @author schening
 * @date 2020/7/13
 */
public class OrderPO {

    private String no;

    private String name;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "OrderPO{" +
                "no='" + no + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
