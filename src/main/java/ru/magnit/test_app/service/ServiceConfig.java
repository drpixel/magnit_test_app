package ru.magnit.test_app.service;

import javax.ws.rs.Path;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.wadl.internal.WadlResource;
import org.springframework.stereotype.Component;
import ru.magnit.test_app.service.endpoint.ServiceEndpoint1;
import ru.magnit.test_app.service.endpoint.ServiceEndpoint2;

/**
 * Класс для конфигурации сервисов Jersey (регистрация эндпойнтов, фильтров,
 * пр.)
 *
 * @author Dima Pixel
 * @version 1.0
 */
@Path("/api")
@Component
public class ServiceConfig extends ResourceConfig {

    public ServiceConfig() {
        register(SecurityFilter.class);
        register(JacksonFeature.class);
        register(ServiceEndpoint1.class);
        register(ServiceEndpoint2.class);
        this.register(WadlResource.class);
    }
}
