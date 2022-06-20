package com.dimka.javapro.interceptor;

import com.dimka.javapro.exception.PermissionDeniedException;
import com.dimka.javapro.model.Permissions;
import com.dimka.javapro.model.User;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Optional;

@Component
public class PermissionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod method = (HandlerMethod) handler;
        boolean allowedAccess = true;
        if (method.hasMethodAnnotation(Permissions.class)) {
            User user = Optional.ofNullable(request.getSession(false))
                    .map(session -> (User) session.getAttribute("user"))
                    .orElseGet(User::new);
            allowedAccess =  Arrays.stream(method.getMethodAnnotation(Permissions.class).value())
                    .anyMatch(permission -> user.getPermissions().contains(permission));
        }
        if (!allowedAccess) {
            throw new PermissionDeniedException();
        }
        return true;
    }
}
