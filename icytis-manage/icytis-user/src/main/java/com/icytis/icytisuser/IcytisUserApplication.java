package com.icytis.icytisuser;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.icytis.icytisuser.mapper")
public class IcytisUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(IcytisUserApplication.class, args);
    }
}
