package Figures;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public abstract class Figure {
    double startingX,startingY,endX,endY;
    Paint color;
    double size;
    public static GraphicsContext graphicsContext;
    public static double xOffSet , yOffSet;

    public Figure(double startingX, double startingY, double endX, double endY , double size, Paint color) {
        this.startingX = startingX;
        this.startingY = startingY;
        this.endX = endX;
        this.endY = endY;
        this.size = size;
        this.color = color;
        draw(graphicsContext);
    }

    public static void setOffSet(double x,double y){
        xOffSet = x;
        yOffSet = y;
    }

    public abstract void draw(GraphicsContext graphicsContext);
}
