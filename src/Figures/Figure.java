package Figures;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public abstract class Figure {
    double startingX,startingY,endX,endY;
    GraphicsContext graphicsContext;
    Paint color;

    public Figure(double startingX, double startingY, double endX, double endY , GraphicsContext graphicsContext , Paint color) {
        this.startingX = startingX;
        this.startingY = startingY;
        this.endX = endX;
        this.endY = endY;
        this.graphicsContext = graphicsContext;
        this.color = color;
    }

    public abstract void draw();
}
