package com.unukovich;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringFactory {

    private static final ApplicationContext springApplicationContext;

    static {
        try {
            springApplicationContext = new ClassPathXmlApplicationContext("spring.cfg.xml");
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial Spring application context creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static ApplicationContext getspringApplicationContext() {
        return springApplicationContext;
    }

}
