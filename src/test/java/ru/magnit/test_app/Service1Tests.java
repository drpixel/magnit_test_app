package ru.magnit.test_app;

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

/**
 * Класс-тест для тестирования методов Сервиса 1
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@ActiveProfiles("test")
public class Service1Tests {

    private final String url = "http://localhost:8080/service1";

    private final TestRestTemplate restTemplate = new TestRestTemplate();

    /**
     * Метод тестирования получения списка связей между вершинами графа
     */
    @Test
    public void testGetGraph() {
        
        ResponseEntity<String> entity = this.restTemplate.getForEntity(this.url + "/graph", String.class);
        
        LogManager.getLogger().info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        LogManager.getLogger().info("testGetGraph(): " + entity.getBody());
        
        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }
    
    /**
     * Метод тестирования добавления маршрута с точками
     */
    @Test
    public void testAddRoute() {
        
        List<Integer> pointList = Arrays.asList(1, 2, 3);
        
        ResponseEntity<String> entity = this.restTemplate.postForEntity(this.url + "/add_route", pointList, String.class);
        
        LogManager.getLogger().info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        LogManager.getLogger().info("testAddRoute(): " + entity.getBody());
        
        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }
    
    /**
     * Метод тестирования получения списка маршрутов с точками
     */
    @Test
    public void testListRoutes() {
        
        ResponseEntity<String> entity = this.restTemplate.getForEntity(this.url + "/list_routes", String.class);
        
        LogManager.getLogger().info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        LogManager.getLogger().info("testListRoutes(): " + entity.getBody());
        
        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }
    
    /**
     * Метод тестирования получения маршрута по его идентификатору
     */
    @Test
    public void testGetRoute() {
        
        Integer routeId = 2;
        
        ResponseEntity<String> entity = this.restTemplate.postForEntity(this.url + "/get_route", routeId, String.class);
        
        LogManager.getLogger().info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        LogManager.getLogger().info("testGetRoute(): " + entity.getBody());
        
        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }

}
