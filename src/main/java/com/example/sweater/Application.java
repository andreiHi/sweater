package com.example.sweater;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 10.08.2018.
 * @version $Id$.
 * @since 0.1.
 */
@SpringBootApplication
@EnableTransactionManagement
public class Application {
    private static final Logger LOG = LogManager.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
