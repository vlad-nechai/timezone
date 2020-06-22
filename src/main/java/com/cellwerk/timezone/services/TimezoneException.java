package com.cellwerk.timezone.services;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class TimezoneException extends RuntimeException {
    private String zone;
    private String message;

    public TimezoneException(String zone, String message) {
        super(message);
        this.message = message;
        this.zone = zone;
    }
}