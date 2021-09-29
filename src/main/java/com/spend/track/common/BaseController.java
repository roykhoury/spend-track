package com.spend.track.common;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {
    @ExceptionHandler()
    public ResponseEntity<String> onError(Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
