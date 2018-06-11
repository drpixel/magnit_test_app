package ru.magnit.test_app.service;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import javax.ws.rs.Path;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.wadl.internal.WadlResource;
import org.glassfish.jersey.servlet.ServletProperties;
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
        // jersey
        register(SecurityFilter.class);
        register(JacksonFeature.class);
        register(WadlResource.class);
        
        // endpoints
        register(ServiceEndpoint1.class);
        register(ServiceEndpoint2.class);

        // swagger
        this.register(ApiListingResource.class);
        this.register(SwaggerSerializers.class);
        BeanConfig config = new BeanConfig();
        config.setConfigId("magint-test-app-config");
        config.setTitle("Manit test Application API");
        config.setVersion("1.0.0");
        config.setBasePath("/api");
        config.setResourcePackage("ru.magnit.test_app");
        config.setScan(true);
    }

}
