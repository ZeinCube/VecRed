package Figures;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import sample.Controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class Figure implements Serializable {
    public static GraphicsContext graphicsContext;
    public static double xOffSet, yOffSet;
    public boolean isFillingFigure;
    public Paint colorOfFilling;
    public Paint colorOfStroke;
    public boolean isFilled = false;
    public double sizeOfBrush;
    public Point start, end;
    public boolean isSelected;
    LinkedList<Point> points = new LinkedList<>();

    public Figure(Point start, Point end, double sizeOfBrush, Paint colorOfStroke) {
        this.start = start;
        this.end = end;
        this.sizeOfBrush = sizeOfBrush;
        this.colorOfStroke = colorOfStroke;
        draw(graphicsContext);
    }

    protected Figure() {
    }

    public static void setOffSet(double x, double y) {
        xOffSet = x;
        yOffSet = y;
    }

    public static Point canvasToScreen(Point p) {
        return new Point((p.x + Figure.xOffSet) / Controller.scaleSize, (p.y + Figure.yOffSet) / Controller.scaleSize);
    }

    public static List<String> parseString(String string) {
        List<String> params = new ArrayList<>();
        StringBuilder param = new StringBuilder();
        for (char c : string.toCharArray()) {
            if (c != ' ') {
                if (c != '[' && c != ']' && c != ',')
                    param.append(c);
            } else {
                params.add(param.toString());
                param = new StringBuilder();
            }
        }
        params.add(param.toString());
        return params;
    }

    public abstract boolean isContainPoint(Point p);

    public abstract boolean isIntersecting(Point starts, Point ends);

    public void setSelected(boolean condition) {
        isSelected = condition;
        graphicsContext.clearRect(0, 0, 1920, 1080);
        if (isSelected) {
            Controller.repaintCanvas();
            drawSelection();
        } else {
            Controller.repaintCanvas();
        }
    }

    public void drawSelection() {
        Point canvSt = inputToCanvas(start);
        Point canvEnd = inputToCanvas(end);
        graphicsContext.setLineWidth(3);
        graphicsContext.setStroke(Color.ROYALBLUE);
        graphicsContext.strokeRect(Math.min(canvSt.x, canvEnd.x) - (sizeOfBrush * Controller.scaleSize) / 2, Math.min(canvSt.y, canvEnd.y) - (sizeOfBrush * Controller.scaleSize) / 2, Math.abs(canvSt.x - canvEnd.x) + (sizeOfBrush * Controller.scaleSize), Math.abs(canvSt.y - canvEnd.y) + (sizeOfBrush * Controller.scaleSize));
    }

    public Point inputToCanvas(Point p) {
        return new Point(p.x * Controller.scaleSize - Figure.xOffSet, p.y * Controller.scaleSize - Figure.yOffSet);
    }

    public abstract void draw(GraphicsContext graphicsContext);

    @Override
    public abstract String toString();
}
