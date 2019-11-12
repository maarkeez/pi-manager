package com.pi.pimanager.temperature;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DegreeServiceTest {

    private MockMvc mockMvc;

    private final DegreeAdapter degreeAdapter = () -> Degree.builder().degrees(60.0).build();
    private final DegreeService degreeService = new DegreeService(degreeAdapter);

    @BeforeEach
    void beforeTest() {
        mockMvc = MockMvcBuilders.standaloneSetup(degreeService).build();
    }

    @Test
    void degree_whenGet_shouldReturnActualTemperature() throws Exception {
        mockMvc.perform(get("/api/degree"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.degrees", is(60.0)));
    }

}