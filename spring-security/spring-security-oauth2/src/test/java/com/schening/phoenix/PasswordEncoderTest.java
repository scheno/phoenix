package com.schening.phoenix;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderTest {

    @Test
    public void test() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode("123456");
        boolean result = passwordEncoder.matches("123456", "$2a$10$Po.MKB34mOvSzH1Xdl253elqqvLuYbfiCY9LgE51T4s68U14Krc7a");
        System.out.println(password);
        System.out.println(result);
    }
}
