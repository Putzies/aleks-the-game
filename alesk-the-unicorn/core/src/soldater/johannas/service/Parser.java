package soldater.johannas.service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import soldater.johannas.model.level.Level;
import soldater.johannas.view.Highscore;
import soldater.johannas.view.LevelInfo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    public static final String BASE_PATH = "levels/";

    private Gson gson = new GsonBuilder().create();

    public Level loadLevel(String name) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(BASE_PATH + name + ".json")));
            Level level =  gson.fromJson(content, Level.class);
            level.construct();
            return level;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void saveHighscore(int score, String level, String name) {
        try {
            LevelInfo levelInfo = loadLevelInfo(BASE_PATH + level + ".meta.json");
            levelInfo.getHighscores().add(new Highscore(score, name));
            String newMeta = gson.toJson(levelInfo);
            Files.write(Paths.get(BASE_PATH + level + ".meta.json"), newMeta.getBytes());

            System.out.println("Successfully saved highscores for " + name + " with score " + score);

        } catch (IOException e) {
            System.out.println("Unable to save highscore!");
            e.printStackTrace();
        }
    }

    public List<LevelInfo> loadLevels() {
        List<LevelInfo> levels = new ArrayList<>();

        FileHandle[] internalFiles = Gdx.files.local("levels/").list();

        for(FileHandle file : internalFiles) {
            if(file.nameWithoutExtension().endsWith(".meta")) {
                levels.add(loadLevelInfo(file.path()));
            }
        }

        return levels;
    }

    public LevelInfo loadLevelInfo(String name) {
        String content = null;

        if (!name.endsWith(".meta.json")) {
            name += ".meta.json";
        }

        if (!name.startsWith(BASE_PATH)) {
            name = BASE_PATH + name;
        }

        try {
            content = new String(Files.readAllBytes(Paths.get(name)));
        } catch (IOException e) {
            System.out.println("Unable to load " + name);
            e.printStackTrace();
        }
        return gson.fromJson(content, LevelInfo.class);
    }
}
