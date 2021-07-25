package Figures;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import sample.Controller;

import java.util.List;

public class RoundRect extends Figure {
    double sizeOfArc;

    public RoundRect(Point start, Point end, double size, Paint colorOfStroke, Paint colorOfFilling, boolean isFilled) {
        super(start, end, size, colorOfStroke);
        this.isFilled = isFilled;
        isFillingFigure = true;
        this.colorOfFilling = colorOfFilling;
    }

    public static Figure valueOf(String string) {
        List<String> params = parseString(string);
        return new RoundRect(new Point(Double.parseDouble(params.get(0)), Double.parseDouble(params.get(1))), new Point(Double.parseDouble(params.get(2)), Double.parseDouble(params.get(3))), Double.parseDouble(params.get(4)), Color.valueOf(params.get(5)), Color.valueOf(params.get(7)), Boolean.parseBoolean(params.get(6)));
    }

    @Override
    public boolean isContainPoint(Point p) {
        Point canvSt = inputToCanvas(start);
        Point canvEnd = inputToCanvas(end);
        Rectangle shape = new javafx.scene.shape.Rectangle(Math.min(canvSt.x, canvEnd.x) - (sizeOfBrush * Controller.scaleSize / 2) / 2, Math.min(canvSt.y, canvEnd.y) - (sizeOfBrush * Controller.scaleSize / 2) / 2, Math.abs((canvSt).x - (canvEnd).x) + (sizeOfBrush * Controller.scaleSize / 2), Math.abs((canvSt).y - (canvEnd).y) + (sizeOfBrush * Controller.scaleSize / 2));
        shape.setArcHeight(sizeOfBrush);
        shape.setArcWidth(sizeOfBrush);
        return shape.contains(new Point2D(p.x, p.y));
    }

    @Override
    public boolean isIntersecting(Point starts, Point ends) {
        Point canvSt = inputToCanvas(start);
        Point canvEnd = inputToCanvas(end);
        starts = inputToCanvas(starts);
        ends = inputToCanvas(ends);
        Rectangle shape = new javafx.scene.shape.Rectangle(Math.min(canvSt.x, canvEnd.x) - (sizeOfBrush * Controller.scaleSize / 2) / 2, Math.min(canvSt.y, canvEnd.y) - (sizeOfBrush * Controller.scaleSize / 2) / 2, Math.abs((canvSt).x - (canvEnd).x) + (sizeOfBrush * Controller.scaleSize / 2), Math.abs((canvSt).y - (canvEnd).y) + (sizeOfBrush * Controller.scaleSize / 2));
        shape.setArcHeight(sizeOfBrush);
        shape.setArcWidth(sizeOfBrush);
        return shape.intersects(starts.x, starts.y, Math.abs(starts.x - ends.x), Math.abs(starts.y - ends.y));
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.setFill(colorOfFilling);
        graphicsContext.setStroke(colorOfStroke);
        Point canvSt = inputToCanvas(start);
        Point canvEnd = inputToCanvas(end);
        graphicsContext.setLineWidth(sizeOfBrush * Controller.scaleSize / 2);
        if (!isFilled) {
            graphicsContext.strokeRoundRect(Math.min(canvSt.x, canvEnd.x), Math.min(canvSt.y, canvEnd.y), Math.abs(canvSt.x - canvEnd.x), Math.abs(canvSt.y - canvEnd.y), sizeOfBrush, sizeOfBrush);
        } else {
            graphicsContext.fillRoundRect(Math.min(canvSt.x, canvEnd.x), Math.min(canvSt.y, canvEnd.y), Math.abs(canvSt.x - canvEnd.x), Math.abs(canvSt.y - canvEnd.y), sizeOfBrush, sizeOfBrush);
            graphicsContext.strokeRoundRect(Math.min(canvSt.x, canvEnd.x), Math.min(canvSt.y, canvEnd.y), Math.abs(canvSt.x - canvEnd.x), Math.abs(canvSt.y - canvEnd.y), sizeOfBrush, sizeOfBrush);
        }
        if (isSelected) {
            drawSelection();
        }
    }

    @Override
    public String toString() {
        return "RoundR " + start.toString() + ' ' + end.toString() + ' ' + sizeOfBrush + ' ' + colorOfStroke.toString() + ' ' + isFilled + ' ' + colorOfFilling.toString() + '/';
    }
}