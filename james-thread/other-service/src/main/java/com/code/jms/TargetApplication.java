package com.code.jms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
* springboot程序启动入口，启动时包括了内置的tomcat容器
* @author jms
* @throws Exception
*/
@SpringBootApplication
public class TargetApplication  {

    public static void main(String[] args) {
        SpringApplication.run(TargetApplication.class, args);
    }

}
