package cit.backen.library.management.system.exceptions.security;



import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;

import java.io.IOException;

public final class ErrorResponseWriter {

    private ErrorResponseWriter() {}

    public static void write(HttpServletResponse response, HttpStatus status, String message)
            throws IOException {
        response.setStatus(status.value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(
                "{\"status\":\"" + status.name() + "\","
                        + "\"message\":\"" + message + "\","
                        + "\"data\":null}");
    }
}
