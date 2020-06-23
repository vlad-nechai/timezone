package com.cellwerk.timezone;

import com.cellwerk.timezone.services.TimezoneException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class TimezoneApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test_default_time() throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String defaultTime = ZonedDateTime.now(ZoneId.systemDefault()).format(formatter);

        this.mockMvc.perform(get("/time"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(defaultTime));
    }

    @Test
    public void test_CET_time() throws Exception {
        String zoneId = "CET";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String cetTime = ZonedDateTime.now(ZoneId.of(zoneId)).format(formatter);

        this.mockMvc.perform(get("/time?zoneId=" + zoneId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(cetTime));
    }

    @Test
    public void test_invalid_zoneId() throws Exception {
        String zoneId = "INVALID_ZONE";

        this.mockMvc.perform(get("/time?zoneId=" + zoneId))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof TimezoneException));

    }
}
