package it.unibo.mvc;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ConfigurationLoader {
    //private final static String file = "src/main/resources/config.yml";
    private final int max; 
    private final int min;
    private final int attempts;

    public ConfigurationLoader (String file){
        final Path filePath = FileSystems.getDefault().getPath(file);
        Map<String, String> map = new HashMap<>();
        InputStream is;
        try {
            is = new FileInputStream(filePath.toFile());
            BufferedReader in = new BufferedReader(new InputStreamReader(is));
            map = in.lines().map(l -> l.split(":")).collect(Collectors.toMap(l->l[0], l->l[1]));
            in.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }

        this.min = Integer.parseInt(map.get("minimum"));
        this.max = Integer.parseInt(map.get("maximum"));
        this.attempts = Integer.parseInt(map.get("attempts"));
    }

    public int getMax() {
        return max;
    }

    public int getMin() {
        return min;
    }

    public int getAttempts() {
        return attempts;
    }
}
