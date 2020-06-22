package com.cellwerk.timezone.controllers;

import com.cellwerk.timezone.services.TimezoneException;
import com.cellwerk.timezone.services.TimezoneService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@Tag(name = "Timezones", description = "Timezones Controller")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600) // for running frontend and backend separately on local machine
@Slf4j
@AllArgsConstructor
public class TimezoneController {

    private final TimezoneService service;

    @Operation(summary = "Get current time", description = "Get current time for given timezone, if not specified system default time", tags = { "Timezone" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid timezone provided")
    })
    @GetMapping(value = "/time", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getCurrentTime(@RequestParam(required = false, defaultValue = "CET") String zoneId) {
        String time = service.getDefaultTime(zoneId);

        return ResponseEntity.ok(time);
    }

    @Operation(summary = "Get available timezones", description = "Get normalized set with available timezones", tags = { "Timezone" })
    @ApiResponses(value = @ApiResponse(responseCode = "200", description = "successful operation"))
    @GetMapping(value = "/zones", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<String>> getTimezones() {
        return ResponseEntity.ok(service.getTimezones());
    }

    @ExceptionHandler(TimezoneException.class)
    public ResponseEntity<String> handleException(TimezoneException exception) {
        log.warn("TimezoneException occurred for zone: {} with a message: {}",
                 exception.getZone(), exception.getMessage());

        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
