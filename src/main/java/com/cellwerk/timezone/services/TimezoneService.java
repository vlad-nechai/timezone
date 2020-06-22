package com.cellwerk.timezone.services;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TimezoneService {

    public String getDefaultTime(String zoneId) {
        // default zone is system default
        try {
            ZoneId zone = zoneId != null ? ZoneId.of(zoneId) : ZoneId.systemDefault();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            return ZonedDateTime.now(zone).format(formatter);
        } catch (Exception e) {
            throw new TimezoneException(zoneId, "Timezone doesnt exist");
        }
    }

    public List<String> getTimezones() {
        // sort and filter timezones
        LocalDateTime now = LocalDateTime.now();
        return ZoneId.getAvailableZoneIds()
                       .stream()
                       .filter(zoneId -> !zoneId.contains("System")) // exclude system time ids
                       .map(TimeZone::getTimeZone)
                       .sorted(new ZoneComparator())
                       .map(TimeZone::getID)
                       .collect(Collectors.toList());
    }

    private static class ZoneComparator implements Comparator<TimeZone> {
        @Override
        public int compare(TimeZone zoneId1, TimeZone zoneId2) {
            return zoneId2.getRawOffset() - zoneId1.getRawOffset();
        }
    }
}
