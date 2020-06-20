package com.cellwerk.timezone.services;

import org.springframework.stereotype.Service;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@Service
public class TimezoneService {

    public String getDefaultTime(String zoneId) {
        // default zone is system default
        ZoneId zone = zoneId != null ? ZoneId.of(zoneId) : ZoneId.systemDefault() ;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return ZonedDateTime.now(zone).format(formatter);
    }

    public Set<String> getTimezones() {
        return ZoneId.getAvailableZoneIds();
    }
}
