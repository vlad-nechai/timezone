package com.cellwerk.timezone.controllers;

import com.cellwerk.timezone.services.TimezoneService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping
@AllArgsConstructor
public class TimezoneController {

    private final TimezoneService service;

    @GetMapping("/time")
    public String getCurrentTime(@RequestParam(required = false, defaultValue = "CET") String zoneId) {
        return service.getDefaultTime(zoneId);
    }

    @GetMapping("/zones")
    public Set<String> getTimezones() {
        return service.getTimezones();
    }
}
