package com.cellwerk.timezone.controllers;

import com.cellwerk.timezone.services.TimezoneService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping
@AllArgsConstructor
public class TimezoneController {

    private final TimezoneService service;

    @GetMapping("/time")
    public ResponseEntity<String> getCurrentTime(@RequestParam(required = false, defaultValue = "CET") String zoneId) {
        String time = service.getDefaultTime(zoneId);

        return ResponseEntity.ok(time);
    }

    @GetMapping("/zones")
    public Set<String> getTimezones() {
        return service.getTimezones();
    }
}
