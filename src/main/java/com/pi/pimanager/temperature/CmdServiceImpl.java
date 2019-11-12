package com.pi.pimanager.temperature;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public class CmdServiceImpl implements CmdService {

    @Override
    @SneakyThrows
    public String runCommand(String cmd) {
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec(cmd);
        process.waitFor();
        BufferedReader buf = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder commandOutput = new StringBuilder();
        String bufferLine;
        while ((bufferLine = buf.readLine()) != null) {
            commandOutput.append(bufferLine);
        }
        return commandOutput.toString();
    }
}
