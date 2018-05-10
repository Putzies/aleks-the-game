package soldater.johannas.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.Align;

public class MenuItem {
    public enum Alignment {
        LEFT,
        CENTER
    };

    private Texture texture;

    private GlyphLayout layout;
    private BitmapFont font;
    private final int width, height;
    private float originalScale;


    private Alignment alignment;



    public MenuItem(String text, float scale, Color color) {
        this(text, scale, Alignment.CENTER, color);
    }

    public MenuItem(String text, float scale, Alignment alignment, Color color) {
        this.alignment = alignment;
        font = new BitmapFont();
        originalScale = scale;
        font.getData().setScale(scale);
        layout = new GlyphLayout(font, text, color, 0, Align.bottomLeft, true);
        width = (int)layout.width;
        height = (int)layout.height;
    }

    public MenuItem(String texture) {
        this(texture, Alignment.CENTER);
    }

    public MenuItem(String texture, Alignment alignment) {
        this.texture = new Texture(texture);
        this.alignment = alignment;
        width = this.texture.getWidth();
        height = this.texture.getHeight();
    }

    public void draw(Batch batch, int x, int y) {
        draw(batch, x, y, originalScale);
    }

    public void draw(Batch batch, int x, int y, float scale) {
        if (texture == null) {

            if (font.getData().scaleX != scale) {
                font.getData().setScale(scale);
            }

            if (alignment == Alignment.LEFT) {
                font.draw(batch, layout, x, y);
            } else if (alignment == Alignment.CENTER) {
                font.draw(batch, layout, x - width / 2, y);
            }

        } else {

            if (alignment == Alignment.LEFT) {
                batch.draw(texture, x, y);
            } else if (alignment == Alignment.CENTER) {
                batch.draw(texture, x - width / 2, y);
            }

        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
