package ru.magnit.test_app.service;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;
import ru.magnit.test_app.service.endpoint.ServiceEndpoint1;
import ru.magnit.test_app.service.endpoint.ServiceEndpoint2;

@Component
public class ServiceConfig extends ResourceConfig {

    public ServiceConfig() {
        register(SecurityFilter.class);
        register(ServiceEndpoint1.class);
        register(ServiceEndpoint2.class);
    }
    
}
