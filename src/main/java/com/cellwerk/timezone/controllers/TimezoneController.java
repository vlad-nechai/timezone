package com.cellwerk.timezone.controllers;

import com.cellwerk.timezone.services.TimezoneService;
import lombok.AllArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Set;

@RestController
@RequestMapping
@AllArgsConstructor
public class TimezoneController {

    private final TimezoneService service;

    @GetMapping
    public String getCurrentTime(@RequestParam @Nullable String zoneId) {
        return service.getDefaultTime(zoneId);
    }

    @GetMapping("/zones")
    public Set<String> getTimezones() {
        return service.getTimezones();
    }
}
