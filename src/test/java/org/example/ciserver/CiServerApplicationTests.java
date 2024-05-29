package org.example.ciserver;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CiServerApplicationTests {

    @Value("${spring.datasource.username}")
    String username;

    @Test
    void contextLoads() {
    }

    @Test
    void dbUsernameIsSa(){
        Assertions.assertEquals("sa",username);
    }

}
