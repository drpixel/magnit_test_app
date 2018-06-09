package ru.magnit.test_app.math;

import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraphBuilder;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import ru.magnit.test_app.model.PointGraph;

public class TimeCalculator {

    public static Integer calcTime(List<PointGraph> pointGraph, List<Integer> points) {

        // sort points
        Collections.sort(points, (Integer o1, Integer o2) -> o2.compareTo(o1));

        // build and fill graph structure
        MutableValueGraph<Integer, Integer> weightedGraph = ValueGraphBuilder.directed().build();
        pointGraph.forEach((pg) -> {
            weightedGraph.putEdgeValue(pg.getIdPointOne().intValue(), pg.getIdPointTwo().intValue(), pg.getTimeInfo().intValue());
        });

        // print edges points
        weightedGraph.edges().forEach((ep) -> {
            LogManager.getLogger().info("EP Graph: " + ep.toString());
        });
        
        // calculate distances summ between points
        boolean isFirstPoint = true;
        Integer prevPoint = null;
        Integer summDistance = 0;
        for (Integer p : points) {
            if (isFirstPoint) {
                isFirstPoint = false;
            } else {
                Optional<Integer> val = weightedGraph.edgeValue(prevPoint, p);
                if (val.isPresent()) {
                    LogManager.getLogger().info("Distance (time) between p1(" + prevPoint + ") and p2(" + p + "): " + val.get());
                    summDistance += val.get();
                } else {
                    LogManager.getLogger().info("Distance (time) between p1(" + prevPoint + ") and p2(" + p + ") not present...");
                    summDistance = -1;
                    break;
                }
            }
            prevPoint = p;
        }
        
        return summDistance;
    }
}
