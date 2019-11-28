package com.pi.temperature;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class PiDegreeAdapterTest {

    private final DegreeAdapter degreeAdapter = new PiDegreeAdapter(buildStubCmdService());

    @Test
    void getDegrees() {

        assertThat(degreeAdapter.getDegrees().getDegrees())
                .isEqualTo(stubTemperature());

    }

    private CmdService buildStubCmdService() {
        final String commandOutput = "temp=" + stubTemperature() + "'C";
        final String expectedCommand = "vcgencmd measure_temp";
        return (cmd) -> {
            if (expectedCommand.equals(cmd)) {
                return commandOutput;
            }
            throw new RuntimeException("Expected command input for CmdService: " + expectedCommand);
        };
    }

    private double stubTemperature() {
        return 61.0;
    }
}