package soldater.johannas.view;

import com.badlogic.gdx.files.FileHandle;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class LevelInfo {
    public String getName() {
        return name;
    }

    public List<Highscore> getHighscores() {
        return highScores;
    }

    private String name;
    private List<Highscore> highScores;
    private String fileName;



    public LevelInfo(FileHandle fh) {
        Gson gson = new GsonBuilder().create();

        try {
            String content = new String(Files.readAllBytes(Paths.get(fh.path())));
            LevelInfo li = gson.fromJson(content, LevelInfo.class);
            this.name = li.name;
            this.highScores = li.highScores;
            this.fileName = fh.name().replace(".meta", "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getFileName() {
        return fileName;
    }
}
