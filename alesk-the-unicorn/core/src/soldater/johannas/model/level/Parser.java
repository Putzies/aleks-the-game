package soldater.johannas.model.level;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Parser {
    public static final String BASE_PATH = "levels/";

    private Gson gson = new Gson();

    public Level loadLevel(String name) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(BASE_PATH + name)));
            return gson.fromJson(content, Level.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
