package Figures;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.StrokeLineJoin;
import sample.Controller;

import java.util.List;

public class Rect extends Figure {

    public Rect(Point start, Point end, double size, Paint colorOfStroke, Paint colorOfFilling, boolean isFilled) {
        super(start, end, size, colorOfStroke);
        isFillingFigure = true;
        this.isFilled = isFilled;
        this.colorOfFilling = colorOfFilling;
    }

    public static Figure valueOf(String string) {
        List<String> params = parseString(string);
        for (String s : params)
            System.out.println(s);
        return new Rect(new Point(Double.parseDouble(params.get(0)), Double.parseDouble(params.get(1))), new Point(Double.parseDouble(params.get(2)), Double.parseDouble(params.get(3))), Double.parseDouble(params.get(4)), Color.valueOf(params.get(5)), Color.valueOf(params.get(7)), Boolean.parseBoolean(params.get(6)));
    }

    @Override
    public boolean isContainPoint(Point p) {
        Point canvSt = inputToCanvas(start);
        Point canvEnd = inputToCanvas(end);
        return new javafx.scene.shape.Rectangle(Math.min(canvSt.x, canvEnd.x) - (sizeOfBrush * Controller.scaleSize / 2) / 2, Math.min(canvSt.y, canvEnd.y) - (sizeOfBrush * Controller.scaleSize / 2) / 2, Math.abs((canvSt).x - (canvEnd).x) + (sizeOfBrush * Controller.scaleSize / 2), Math.abs((canvSt).y - (canvEnd).y) + (sizeOfBrush * Controller.scaleSize / 2)).contains(new Point2D(p.x, p.y));
    }

    @Override
    public boolean isIntersecting(Point starts, Point ends) {
        Point canvSt = inputToCanvas(start);
        Point canvEnd = inputToCanvas(end);
        starts = inputToCanvas(starts);
        ends = inputToCanvas(ends);
        return new javafx.scene.shape.Rectangle(Math.min(canvSt.x, canvEnd.x) - (sizeOfBrush * Controller.scaleSize / 2) / 2, Math.min(canvSt.y, canvEnd.y) - (sizeOfBrush * Controller.scaleSize / 2) / 2, Math.abs((canvSt).x - (canvEnd).x) + (sizeOfBrush * Controller.scaleSize / 2), Math.abs((canvSt).y - (canvEnd).y) + (sizeOfBrush * Controller.scaleSize / 2)).intersects(starts.x, starts.y, Math.abs(starts.x - ends.x), Math.abs(starts.y - ends.y));
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.setFill(colorOfFilling);
        graphicsContext.setStroke(colorOfStroke);
        Point canvSt = inputToCanvas(start);
        Point canvEnd = inputToCanvas(end);
        graphicsContext.setLineWidth(sizeOfBrush * Controller.scaleSize / 2);
        graphicsContext.setLineJoin(StrokeLineJoin.MITER);
        if (!isFilled) {
            graphicsContext.strokeRect(Math.min(canvSt.x, canvEnd.x), Math.min(canvSt.y, canvEnd.y), Math.abs(canvSt.x - canvEnd.x), Math.abs(canvSt.y - canvEnd.y));
        } else {
            graphicsContext.fillRect(Math.min(canvSt.x, canvEnd.x), Math.min(canvSt.y, canvEnd.y), Math.abs(canvSt.x - canvEnd.x), Math.abs(canvSt.y - canvEnd.y));
            graphicsContext.strokeRect(Math.min(canvSt.x, canvEnd.x), Math.min(canvSt.y, canvEnd.y), Math.abs(canvSt.x - canvEnd.x), Math.abs(canvSt.y - canvEnd.y));
        }
        if (isSelected) {
            drawSelection();
        }
    }

    @Override
    public String toString() {
        return "Rect " + start.toString() + ' ' + end.toString() + ' ' + sizeOfBrush + ' ' + colorOfStroke.toString() + ' ' + isFilled + ' ' + colorOfFilling.toString() + '/';
    }
}