package ru.magnit.test_app.service.endpoint;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.magnit.test_app.dao.oracle_1.PointGraphRepository;
import ru.magnit.test_app.dao.oracle_1.RouteRepository;
import ru.magnit.test_app.math.TimeCalculator;
import ru.magnit.test_app.model.Point;
import ru.magnit.test_app.model.PointGraph;
import ru.magnit.test_app.model.Route;

/**
 * Класс-сервис 1 (Jersey). Получение списка точек графа, добавление маршрута,
 * список маршрутов, маршрут.
 *
 * @author Dima Pixel
 * @version 1.0
 */
@Component
@Api(value = "Service 1 API")
@Path("/service1")
public class ServiceEndpoint1 {

    // связываемся с репозиторием Route, с квалификатором для oracle1
    @Autowired
    @Qualifier("oracle1RR")
    RouteRepository routeRepository;

    // связываемся с репозиторием PointGraph, с квалификатором для oracle1
    @Autowired
    @Qualifier("oracle1PGR")
    PointGraphRepository pointGraphRepository;

    /**
     * Метод получения списка связей между точками графа (файл data.csv)
     *
     * @return List<PointGrap> список связей между точками графа
     */
    @GET
    @ApiOperation(value = "Метод получения списка связей между точками графа (файл data.csv)", response = List.class)
    @PermitAll
    @Path("/graph")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<PointGraph> listGraphPoints() {

        LogManager.getLogger().info("Calling /service1/graph");

        List<PointGraph> graph = (List<PointGraph>) pointGraphRepository.findAll();
        LogManager.getLogger().info("Graph count: " + graph.size());
        return graph;
    }

    /**
     * Метод добавления маршута
     *
     * @param points Список точек маршрута
     * @return Integer Идентификатор добавленного маршрута
     */
    @POST
    @ApiOperation(value = "Метод добавления маршута", response = Integer.class)
    @PermitAll
    @Path("/add_route")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Integer addRoute(List<Integer> points) {

        LogManager.getLogger().info("Calling /service1/add_route");
        LogManager.getLogger().info("Points count: " + points.size());

        List<PointGraph> graph = (List<PointGraph>) pointGraphRepository.findAll();
        Integer calculatedTime = TimeCalculator.calcTime(graph, points);

        Route route = new Route();
        if (!calculatedTime.equals(-1)) {
            route.setIsReady('Y');
        } else {
            route.setIsReady('N');
        }
        route.setTime(BigInteger.valueOf(calculatedTime));

        List<Point> pointList = new ArrayList<>();
        for (Integer p : points) {
            Point point = new Point();
            point.setRoutePointId(BigInteger.valueOf(p));
            point.setRouteId(route);
            pointList.add(point);
        }

        route.setPointList(pointList);

        routeRepository.save(route);

        LogManager.getLogger().info("Create route: " + route.toString());

        return route.getId().intValue();
    }

    /**
     * Метод получения списка маршрутов (с точками маршрута)
     *
     * @return List<Route>
     */
    @GET
    @ApiOperation(value = "Метод получения списка маршрутов (с точками маршрута)", response = List.class)
    @PermitAll
    @Path("/list_routes")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Route> getRoutes() {

        LogManager.getLogger().info("Calling /service1/list_routes");

        List<Route> routes = (List<Route>) routeRepository.findAll();
        LogManager.getLogger().info("Routes count: " + routes.size());
        return routes;
    }

    /**
     * Метод получения маршрута
     *
     * @param routeId Идентификатор маршрута
     * @return Route Маршрут
     */
    @POST
    @ApiOperation(value = "Метод получения маршрута", response = Route.class)
    @PermitAll
    @Path("/get_route")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Route getRoute(Integer routeId) {

        LogManager.getLogger().info("Calling /service1/get_route");

        Optional<Route> route = routeRepository.findById(BigInteger.valueOf(routeId));
        if (route.isPresent()) {
            return route.get();
        } else {
            return null;
        }
    }

}
