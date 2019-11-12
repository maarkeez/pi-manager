package com.pi.pimanager.temperature;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DegreeService {

    private final DegreeAdapter degreeAdapter;

    @GetMapping("degree")
    public Degree getDegrees() {
        return degreeAdapter.getDegrees();
    }
}
