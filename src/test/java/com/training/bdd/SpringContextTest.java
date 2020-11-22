package com.training.bdd;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BddApplication.class)
public class SpringContextTest {

    @Test
    public void testSpringContext() {
        // Check if spring context is booted without any exception.
    }
}
