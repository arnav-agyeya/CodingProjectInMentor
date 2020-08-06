package com.dependencyinjection;

import java.io.IOException;
import java.io.OutputStream;

public class LoggingHelper {

    public static boolean close(OutputStream stream) {
        try {
            stream.flush();
            stream.close();
            return true;
        } catch (IOException e) {
            throw new RuntimeException("Logging close Error");
        }
    }
}
