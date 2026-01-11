package com.curd.app.exception;

import java.time.LocalDateTime;
import java.util.Map;

public record ErrorResponse(
        String message,
        int statusCode,
        String path,
        LocalDateTime timeStamp,
        Map<String, String> filedErrors
) {}
