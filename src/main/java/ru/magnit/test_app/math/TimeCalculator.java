package ru.magnit.test_app.math;

import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraphBuilder;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import ru.magnit.test_app.model.PointGraph;

/**
 * Класс для расчета расстояний между вершинами в графе
 *
 * @author Dima Pixel
 * @version 1.0
 */
public class TimeCalculator {

    /**
     * Метод расчета суммы времени между списком вершин графа
     *
     * @param pointGraph Список связей между вершинами графа (точками) и
     * информацией о времени
     * @param points Список точек для расчета времени
     * @return Integer Время на маршрут (-1 если не достижимо)
     */
    public static Integer calcTime(List<PointGraph> pointGraph, List<Integer> points) {

        // сортируем точки перед расчетом (от меньшего к большему)
        Collections.sort(points, Comparator.reverseOrder());
        
        // удаляем дубликаты
        List<Integer> dedupedPoints = points.stream().distinct().collect(Collectors.toList());

        // создаем граф и заполняем его структуру информацией о времени между точками
        MutableValueGraph<Integer, Integer> weightedGraph = ValueGraphBuilder.directed().build();
        pointGraph.forEach((pg) -> {
            weightedGraph.putEdgeValue(pg.getIdPointOne().intValue(), pg.getIdPointTwo().intValue(), pg.getTimeInfo().intValue());
        });

        // распечатаем заполненнй граф
        weightedGraph.edges().forEach((ep) -> {
            LogManager.getLogger().info("EP Graph: " + ep.toString());
        });

        // рассчитаем время
        boolean isFirstPoint = true;
        Integer prevPoint = null;
        Integer summDistance = 0;
        for (Integer p : dedupedPoints) {
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
