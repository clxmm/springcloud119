package org.clxmm.sentinel;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import javax.xml.crypto.Data;
import java.util.Date;

@SpringBootTest
class SentinelApplicationTests {

    @Test
    void contextLoads() {
        RestTemplate restTemplate = new RestTemplate();


        for (int i = 0; i < 15; i++) {

            String s = restTemplate.getForObject("http://localhost:8081/test", String.class);
            System.out.println(s + new Date());
        }


    }

}
