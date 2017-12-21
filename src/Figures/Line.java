package Figures;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.StrokeLineCap;
import sample.Controller;

public class Line extends Figure {
    public Line(double startingX, double startingY, double endX, double endY, double size, Paint color) {
        super(startingX, startingY, endX, endY, size, color);
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.setLineWidth(size*Controller.scaleSize);
        graphicsContext.setStroke(Paint.valueOf(color));
        graphicsContext.setLineCap(StrokeLineCap.ROUND);
        graphicsContext.strokeLine(startingX*Controller.scaleSize-Figure.xOffSet,startingY*Controller.scaleSize-Figure.yOffSet,endX*Controller.scaleSize-Figure.xOffSet,endY*Controller.scaleSize-Figure.yOffSet);
    }
}