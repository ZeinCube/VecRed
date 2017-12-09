package Figures;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public abstract class Figure {
    double startingX,startingY,endX,endY;
    Paint color;
    double size;
    public static GraphicsContext graphicsContext;

    public Figure(double startingX, double startingY, double endX, double endY , double size, Paint color) {
        this.startingX = startingX;
        this.startingY = startingY;
        this.endX = endX;
        this.endY = endY;
        this.size = size;
        this.color = color;
        draw(graphicsContext);
    }

    public abstract void draw(GraphicsContext graphicsContext);
}
