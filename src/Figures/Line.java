package Figures;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.shape.StrokeLineCap;
import sample.Controller;

public class Line extends Figure {
    public Line(Point start,Point end, double size, Paint colorOfStroke) {
        super(start,end, size,colorOfStroke);
        isFillingFigure = false;
        colorOfFilling = colorOfStroke;
    }

    @Override
    public boolean isContainPoint(Point p) {
        Point canvSt = inputToCanvas(start);
        Point canvEnd = inputToCanvas(end);
        javafx.scene.shape.Line line = new javafx.scene.shape.Line(canvSt.x,canvSt.y,canvEnd.x,canvEnd.y);
        line.setStrokeWidth(sizeOfBrush *Controller.scaleSize);
        return line.contains(new Point2D(p.x,p.y));
    }

    @Override
    public boolean isIntersecting(Point starts, Point ends) {
        Point canvSt = inputToCanvas(start);
        Point canvEnd = inputToCanvas(end);
        starts = inputToCanvas(starts);
        ends = inputToCanvas(ends);
        javafx.scene.shape.Line line = new javafx.scene.shape.Line(canvSt.x,canvSt.y,canvEnd.x,canvEnd.y);
        line.setStrokeWidth(sizeOfBrush *Controller.scaleSize);
        return line.intersects(starts.x, starts.y,Math.abs(starts.x- ends.x),Math.abs(starts.y- ends.y));
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.setLineWidth(sizeOfBrush *Controller.scaleSize);
        graphicsContext.setStroke(colorOfStroke);
        graphicsContext.setLineCap(StrokeLineCap.ROUND);
        Point canvSt = inputToCanvas(start);
        Point canvEnd = inputToCanvas(end);
        graphicsContext.strokeLine(canvSt.x,canvSt.y,canvEnd.x,canvEnd.y);
        if(isSelected){
            drawSelection();
        }
    }
}