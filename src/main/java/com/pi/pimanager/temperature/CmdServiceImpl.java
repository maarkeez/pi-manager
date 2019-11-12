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
        Runtime run = Runtime.getRuntime();
        Process pr = run.exec(cmd);
        pr.waitFor();
        BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line = "";
        while ((line = buf.readLine()) != null) {
            line += line;
        }
        return line;
    }
}
