package com.pi.temperature.api;

import com.pi.temperature.model.Degree;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ApiDegree {

    private final DegreeAdapter degreeAdapter;

    @GetMapping("degree")
    public Degree getDegrees () {
        return degreeAdapter.getDegrees();
    }
}
