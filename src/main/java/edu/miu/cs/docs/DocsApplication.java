package edu.miu.cs.docs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DocsApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(DocsApplication.class, args);
        } catch (Throwable t) {
            // Print full stacktrace to help diagnose BeanCreationException on startup
            t.printStackTrace(System.err);
            throw t;
        }
    }

}
