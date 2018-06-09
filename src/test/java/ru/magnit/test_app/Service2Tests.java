package ru.magnit.test_app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.magnit.test_app.service.SecurityFilter;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@ActiveProfiles("test")
public class Service2Tests {

    private final String url = "http://localhost:8080/service2";
    
    @Test
    public void testGetRouteTimeAuth() {
        
        List<Integer> points = new ArrayList<>(Arrays.asList(2, 5, 7, 1));

        TestRestTemplate restTemplate = new TestRestTemplate(SecurityFilter.AllowedUser.userLogin, SecurityFilter.AllowedUser.userPassword);
        ResponseEntity<String> entity = restTemplate.postForEntity(this.url + "/get_points_time", points, String.class);
        
        LogManager.getLogger().info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        LogManager.getLogger().info("testGetRouteTimeAuth(): " + entity.getBody());
        
        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }
    
    @Test
    public void testGetRouteTimeWithoutAuth() {
        
        List<Integer> points = new ArrayList<>(Arrays.asList(2, 5, 7, 1));

        TestRestTemplate restTemplate = new TestRestTemplate();
        ResponseEntity<String> entity = restTemplate.postForEntity(this.url + "/get_points_time", points, String.class);
        
        LogManager.getLogger().info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        LogManager.getLogger().info("testGetRouteTimeWithoutAuth(): " + entity.getBody());
        
        assertEquals(HttpStatus.NOT_FOUND, entity.getStatusCode());
    }

}
