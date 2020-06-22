package com.cellwerk.timezone.services;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
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

    public List<Map<String, String>> getTimezones() {
        // sort, filter and transform timezones
        LocalDateTime now = LocalDateTime.now();
        return ZoneId.getAvailableZoneIds()
                       .stream()
                       .filter(zoneId -> !zoneId.contains("System")) // exclude system time ids
                       .map(TimeZone::getTimeZone)
                       .sorted(new ZoneComparator())
                       .map(timezone -> new HashMap<String, String>() {{
                           put("id", timezone.getID());
                           put("name",  String.format("(%s%s) %s", "UTC", getOffset(now, timezone), timezone.getID()));
                       }})
                       .collect(Collectors.toList());
    }

    private String getOffset(LocalDateTime dateTime, TimeZone timeZone) {
        return dateTime
                       .atZone(ZoneId.of(timeZone.getID()))
                       .getOffset()
                       .getId()
                       .replace("Z", "+00:00");
    }

    private static class ZoneComparator implements Comparator<TimeZone> {
        @Override
        public int compare(TimeZone zoneId1, TimeZone zoneId2) {
            return zoneId2.getRawOffset() - zoneId1.getRawOffset();
        }
    }
}
