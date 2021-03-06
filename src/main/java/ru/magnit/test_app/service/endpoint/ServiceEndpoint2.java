package ru.magnit.test_app.service.endpoint;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.BasicAuthDefinition;
import io.swagger.annotations.SecurityDefinition;
import io.swagger.annotations.SwaggerDefinition;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.magnit.test_app.dao.oracle_2.PointGraphRepository;
import ru.magnit.test_app.math.TimeCalculator;
import ru.magnit.test_app.model.PointGraph;

/**
 * Класс-сервис 2 (Jersey). Расчет времени между точками
 *
 * @author Dima Pixel
 * @version 1.0
 */
@SwaggerDefinition(
        securityDefinition = @SecurityDefinition(
                basicAuthDefinitions = {
                    @BasicAuthDefinition(key = "basicAuth")}
        )
)
@Component
@Api(value = "Service 2 API")
@Path("/service2")
public class ServiceEndpoint2 {

    // связываемся с репозиторием PointGraph, с квалификатором для oracle2
    @Autowired
    @Qualifier("oracle2PGR")
    PointGraphRepository pointGraphRepository;

    /**
     * Метод получения суммы времени между точками
     *
     * @param points Список точек
     * @return Integer Сумма времени между точками (-1 если не достижимо)
     */
    @POST
    @ApiOperation(value = "Метод получения суммы времени между точками", response = Integer.class, authorizations = {@Authorization(value = "basicAuth")})
    @RolesAllowed("SERVICE")
    @Path("/get_points_time")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Integer getPointsTime(@ApiParam(value = "Список точек маршрута", required = true) List<Integer> points) {

        LogManager.getLogger().info("Calling /service2/get_points_time");

        List<PointGraph> graph = (List<PointGraph>) pointGraphRepository.findAll();
        Integer calculatedTime = TimeCalculator.calcTime(graph, points);

        return calculatedTime;
    }

}
