package com.pi.temperature;

import static java.lang.Double.parseDouble;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class PiDegreeAdapter implements DegreeAdapter {

    private final CmdService cmdService;

    @Override
    public Degree getDegrees() {
        final String EMPTY_STR = "";

        String measureTempOutput = cmdService.runCommand("vcgencmd measure_temp");
        String tempValue = measureTempOutput.trim()
                .replace("temp=", EMPTY_STR)
                .replace("'C", EMPTY_STR);

        return Degree.builder()
                .degrees(parseDouble(tempValue))
                .build();
    }


}
