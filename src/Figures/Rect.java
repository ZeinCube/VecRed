package Figures;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import sample.Controller;

public class Rect extends Figure {

    public Rect(double startingX, double startingY, double endX, double endY, double size, Paint color) {
        super(startingX,startingY,endX,endY, size, color);
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Paint.valueOf(color));
        graphicsContext.setStroke(Paint.valueOf(color));
        graphicsContext.setLineWidth(size* Controller.scaleSize);
        graphicsContext.strokeRect(Math.min(startingX,endX)* Controller.scaleSize-Figure.xOffSet,Math.min(startingY,endY)*Controller.scaleSize-Figure.yOffSet,Math.abs(startingX-endX)*Controller.scaleSize,Math.abs(startingY-endY)*Controller.scaleSize);
    }
}