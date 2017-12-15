package Figures;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

import java.io.Serializable;

public abstract class Figure implements Serializable {
    double[] x;
    double[] y;
    int n;
    double startingX,startingY,endX,endY;
    String color;
    double size;
    public static GraphicsContext graphicsContext;
    public static double xOffSet , yOffSet;

    public Figure(double startingX, double startingY, double endX, double endY , double size, Paint color) {
        this.startingX = startingX;
        this.startingY = startingY;
        this.endX = endX;
        this.endY = endY;
        this.size = size;
        this.color = color.toString();
        draw(graphicsContext);
    }

    protected Figure() {
    }

    public static void setOffSet(double x,double y){
        xOffSet = x;
        yOffSet = y;
    }

    public abstract void draw(GraphicsContext graphicsContext);
}
