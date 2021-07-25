package Figures;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import sample.Controller;

import java.util.LinkedList;
import java.util.List;


public class PolyLine extends Figure {

    public PolyLine(LinkedList<Point> points, Paint color, double size) {
        isFillingFigure = false;
        this.colorOfStroke = color;
        this.points = points;
        this.sizeOfBrush = size;
        this.colorOfFilling = colorOfStroke;
        draw(graphicsContext);
    }

    public static Figure valueOf(String string) {
        LinkedList<Point> points = new LinkedList<>();
        List<String> params = parseString(string);
        int i = 0;
        for (; !params.get(i).contains("x"); i += 1) {
            points.add(new Point(Double.parseDouble(params.get(i)), Double.parseDouble(params.get(++i))));
        }
        return new PolyLine(points, Paint.valueOf(params.get(i)), Double.parseDouble(params.get(i + 1)));
    }

    public void addPoint(Point point) {
        points.add(point);
        draw(graphicsContext);
    }

    @Override
    public boolean isContainPoint(Point p) {
        Polyline polyline = new Polyline();
        polyline.getPoints().addAll(returnPoints());
        polyline.setStrokeWidth(sizeOfBrush * Controller.scaleSize);
        return polyline.contains(new Point2D(p.x, p.y));
    }

    @Override
    public boolean isIntersecting(Point starts, Point ends) {
        Polyline polyline = new Polyline();
        polyline.getPoints().addAll(returnPoints());
        polyline.setStrokeWidth(sizeOfBrush * Controller.scaleSize);
        starts = inputToCanvas(starts);
        ends = inputToCanvas(ends);
        return polyline.intersects(starts.x, starts.y, Math.abs(starts.x - ends.x), Math.abs(starts.y - ends.y));
    }

    private Double[] returnPoints() {
        Double[] pointsArr = new Double[points.size() * 2];
        for (int i = 0; i < points.size() * 2; i += 2) {
            pointsArr[i] = inputToCanvas(points.get(i / 2)).x;
            pointsArr[i + 1] = inputToCanvas(points.get(i / 2)).y;
        }
        return pointsArr;
    }

    @Override
    public void drawSelection() {
        Point max = new Point(0, 0);
        Point min = new Point(1920, 1080);
        for (Point p : points) {
            min = new Point(Math.min(p.x, min.x), Math.min(p.y, min.y));
            max = new Point(Math.max(p.x, max.x), Math.max(p.y, max.y));
        }
        max = inputToCanvas(max);
        min = inputToCanvas(min);
        graphicsContext.setLineWidth(3);
        graphicsContext.setStroke(Color.ROYALBLUE);
        graphicsContext.strokeRect(min.x - sizeOfBrush * Controller.scaleSize, min.y - sizeOfBrush * Controller.scaleSize, max.x - min.x + sizeOfBrush * Controller.scaleSize * 2, max.y - min.y + sizeOfBrush * Controller.scaleSize * 2);
    }

    public void draw(GraphicsContext graphicsContext) {
        double[] x;
        double[] y;
        x = new double[points.size()];
        y = new double[points.size()];
        for (Point p : points) {
            x[points.indexOf(p)] = inputToCanvas(p).x;
            y[points.indexOf(p)] = inputToCanvas(p).y;
        }
        graphicsContext.setLineCap(StrokeLineCap.ROUND);
        graphicsContext.setLineJoin(StrokeLineJoin.ROUND);
        graphicsContext.setStroke(colorOfStroke);
        graphicsContext.setLineWidth(sizeOfBrush * Controller.scaleSize);
        graphicsContext.strokePolyline(x, y, points.size());
        if (isSelected) {
            drawSelection();
        }
    }

    @Override
    public String toString() {
        return "PolyLine " + points + ' ' + colorOfStroke.toString() + ' ' + sizeOfBrush + '/';
    }

}