package soldater.johannas.util;

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

    public void saveHighscore(int score, String level) {
        try {
            LevelInfo levelInfo = loadLevelInfo(BASE_PATH + level + ".meta.json");
            levelInfo.getHighscores().add(new Highscore(score, "Anonymous"));
            String newMeta = gson.toJson(levelInfo);
            Files.write(Paths.get(BASE_PATH + level + ".meta.json"), newMeta.getBytes());

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
                try {
                    levels.add(loadLevelInfo(file.path()));
                } catch (IOException e) {
                    System.out.println("Unable to load file " + file.path());
                    e.printStackTrace();
                }
            }
        }

        return levels;
    }

    private LevelInfo loadLevelInfo(String name) throws IOException{
        String content = new String(Files.readAllBytes(Paths.get(name)));
        return gson.fromJson(content, LevelInfo.class);
    }
}
