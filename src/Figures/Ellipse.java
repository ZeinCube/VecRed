package Figures;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import sample.Controller;

import java.util.List;

public class Ellipse extends Figure {

    public Ellipse(Point start, Point end, double size, Paint colorOfStroke, Paint colorOfFilling, boolean isFilled) {
        super(start, end, size, colorOfStroke);
        this.isFillingFigure = true;
        this.isFilled = isFilled;
        this.colorOfFilling = colorOfFilling;
    }

    public static Figure valueOf(String string) {
        List<String> params = parseString(string);
        return new Ellipse(new Point(Double.parseDouble(params.get(0)), Double.parseDouble(params.get(1))), new Point(Double.parseDouble(params.get(2)), Double.parseDouble(params.get(3))), Double.parseDouble(params.get(4)), Color.valueOf(params.get(5)), Color.valueOf(params.get(7)), Boolean.parseBoolean(params.get(6)));
    }

    @Override
    public boolean isContainPoint(Point p) {
        Point canvSt = inputToCanvas(start);
        Point canvEnd = inputToCanvas(end);
        double cx = ((canvSt).x + (canvEnd).x) / 2;
        double cy = ((canvSt).y + (canvEnd).y) / 2;
        return new javafx.scene.shape.Ellipse(cx, cy, Math.abs(canvSt.x - canvEnd.x) / 2 + (sizeOfBrush * Controller.scaleSize / 2) / 2, Math.abs((canvSt).y - (canvEnd).y) / 2 + (sizeOfBrush * Controller.scaleSize / 2) / 2).contains(new Point2D(p.x, p.y));
    }

    @Override
    public boolean isIntersecting(Point starts, Point ends) {
        Point canvSt = inputToCanvas(start);
        Point canvEnd = inputToCanvas(end);
        starts = inputToCanvas(starts);
        ends = inputToCanvas(ends);
        double cx = ((canvSt).x + (canvEnd).x) / 2;
        double cy = ((canvSt).y + (canvEnd).y) / 2;
        return new javafx.scene.shape.Ellipse(cx, cy, Math.abs(canvSt.x - canvEnd.x) / 2 + (sizeOfBrush * Controller.scaleSize / 2) / 2, Math.abs((canvSt).y - (canvEnd).y) / 2 + (sizeOfBrush * Controller.scaleSize / 2) / 2).intersects(starts.x, starts.y, Math.abs(starts.x - ends.x), Math.abs(starts.y - ends.y));
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        Point canvSt = inputToCanvas(start);
        Point canvEnd = inputToCanvas(end);
        graphicsContext.setFill(colorOfFilling);
        graphicsContext.setStroke(colorOfStroke);
        graphicsContext.setLineWidth(sizeOfBrush * Controller.scaleSize / 2);
        if (!isFilled) {
            graphicsContext.strokeOval(Math.min(canvSt.x, canvEnd.x), Math.min(canvSt.y, canvEnd.y), Math.abs(canvSt.x - canvEnd.x), Math.abs(canvSt.y - canvEnd.y));
        } else {
            graphicsContext.fillOval(Math.min(canvSt.x, canvEnd.x), Math.min(canvSt.y, canvEnd.y), Math.abs(canvSt.x - canvEnd.x), Math.abs(canvSt.y - canvEnd.y));
            graphicsContext.strokeOval(Math.min(canvSt.x, canvEnd.x), Math.min(canvSt.y, canvEnd.y), Math.abs(canvSt.x - canvEnd.x), Math.abs(canvSt.y - canvEnd.y));
        }
        if (isSelected) {
            drawSelection();
        }
    }

    @Override
    public String toString() {
        return "Ellipse " + start.toString() + ' ' + end.toString() + ' ' + sizeOfBrush + ' ' + colorOfStroke.toString() + ' ' + isFilled + ' ' + colorOfFilling.toString() + '/';
    }
}