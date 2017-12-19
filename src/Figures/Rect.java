package Figures;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
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
        graphicsContext.setLineJoin(StrokeLineJoin.MITER);
        Point point = new Point((Math.min(startingX,endX)+Figure.xOffSet),(Math.min(startingY,endY)+Figure.yOffSet));
        graphicsContext.strokeRect(point.x,point.y,Math.abs(startingX-endX),Math.abs(startingY-endY));
    }
}