package ru.magnit.test_app.service;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

/**
 * Класс-фильтр для организации доступа по ролям (basic auth) к аннотированным
 * методам сервиса "javax.annotation.security"
 *
 * @author Dima Pixel
 * @version 1.0
 */
@Provider
public class SecurityFilter implements ContainerRequestFilter {

    @Context
    private ResourceInfo resourceInfo;

    private static final Response ACCESS_DENIED = Response.status(Response.Status.UNAUTHORIZED).build();
    private static final Response ACCESS_FORBIDDEN = Response.status(Response.Status.FORBIDDEN).build();
    private static final Response SERVER_ERROR = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();

    /**
     * Класс-заглушка с наименованием роли и информацией о пользователе для
     * базовой авторизации
     *
     * @author Dima Pixel
     * @version 1.0
     */
    public static class AllowedUser {

        public static String roleName = "SERVICE";
        public static String userLogin = "service";
        public static String userPassword = "service";
    }

    /**
     * Метод для проверки роли пользователя
     *
     * @param requestContext Контекс запроса к методу сервиса
     * @throws java.io.IOException
     */
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        Method method = resourceInfo.getResourceMethod();

        // если метод аннотирован не доступным для всех
        if (!method.isAnnotationPresent(PermitAll.class)) {

            // если метод аннотирован недоступным ни для кого - ACCESS_FORBIDDEN
            if (method.isAnnotationPresent(DenyAll.class)) {
                requestContext.abortWith(ACCESS_FORBIDDEN);
                return;
            }

            // пробуем получить заголовок авторизации
            final MultivaluedMap<String, String> headers = requestContext.getHeaders();
            final List<String> authorization = headers.get("Authorization");

            if (authorization == null || authorization.isEmpty()) {
                requestContext.abortWith(ACCESS_DENIED);
                return;
            }

            // пробуем получить информацию из базовой авторизации (base64)
            final String encodedUserPassword = authorization.get(0).replaceFirst("Basic ", "");
            String usernameAndPassword = null;
            try {
                usernameAndPassword = new String(Base64.getDecoder().decode(encodedUserPassword));
            } catch (Exception ex) {
                requestContext.abortWith(SERVER_ERROR);
                return;
            }

            final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
            final String username = tokenizer.nextToken();
            final String password = tokenizer.nextToken();

            // если метод аннотирован конкретными ролями - проверяем роли
            if (method.isAnnotationPresent(RolesAllowed.class)) {
                RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
                Set<String> rolesSet = new HashSet<>(Arrays.asList(rolesAnnotation.value()));

                if (!isUserAllowed(username, password, rolesSet)) {
                    requestContext.abortWith(ACCESS_DENIED);
                    return;
                }
            }
        }
    }

    /**
     * Метод для проверки роли пользователя
     *
     * @param username Имя пользователя
     * @param password Пароль
     * @param rolesSet Множество ролей
     * @return boolean Авторизован?
     */
    private boolean isUserAllowed(String username, String password, Set<String> rolesSet) {
        boolean isAllowed = false;

        if (rolesSet.contains(AllowedUser.roleName)
                && (username.equalsIgnoreCase(AllowedUser.userLogin) && password.equalsIgnoreCase(AllowedUser.userPassword))) {
            isAllowed = true;
        }
        return isAllowed;
    }

}
