package com.pi.temperature.api;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.pi.temperature.model.Degree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class ApiDegreeTest {

    private MockMvc mockMvc;

    private final DegreeAdapter degreeAdapter = () -> Degree.builder().degrees(60.0).build();
    private final ApiDegree apiDegree = new ApiDegree(degreeAdapter);

    @BeforeEach
    void beforeTest() {
        mockMvc = MockMvcBuilders.standaloneSetup(apiDegree)
                                 .build();
    }

    @Test
    void degree_whenGet_shouldReturnActualTemperature() throws Exception {
        mockMvc.perform(get("/api/degree"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.degrees", is(60.0)));
    }

}