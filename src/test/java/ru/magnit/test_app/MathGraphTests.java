package ru.magnit.test_app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.magnit.test_app.dao.oracle_1.PointGraphRepository;
import ru.magnit.test_app.math.TimeCalculator;
import ru.magnit.test_app.model.PointGraph;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@ActiveProfiles("test")
public class MathGraphTests {
    
    @Autowired
    PointGraphRepository pointGraphRepository;
    
    @Test
    public void valueGraphTest() {
        LogManager.getLogger().info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        LogManager.getLogger().info("valueGraphTest()");
        
        List<PointGraph> graph = (List<PointGraph>) pointGraphRepository.findAll();
        List<Integer> pointPath = new ArrayList<>(Arrays.asList(2, 5, 7, 1));

        Integer summDistance = TimeCalculator.calcTime(graph, pointPath);
        
        LogManager.getLogger().info("Distance between points: " + summDistance);
    }
    
}
