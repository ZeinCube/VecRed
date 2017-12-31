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


public class PolyLine extends Figure{
    private LinkedList<Point> points = new LinkedList<>();

    public PolyLine(LinkedList<Point> points , Paint color,double size){
        isFillingFigure = false;
        this.colorOfStroke = color;
        this.points = points;
        this.size = size;
        this.colorOfFilling = colorOfStroke;
        draw(graphicsContext);
    }

    public void addPoint(Point point){
        points.add(point);
        draw(graphicsContext);
    }

    @Override
    public boolean isContainPoint(Point p){
        Polyline polyline = new Polyline();
        polyline.getPoints().addAll(returnPoints());
        polyline.setStrokeWidth(size*Controller.scaleSize);
        return polyline.contains(new Point2D(p.x,p.y));
    }

    @Override
    public boolean isIntersecting(Point starts, Point ends) {
        Polyline polyline = new Polyline();
        polyline.getPoints().addAll(returnPoints());
        polyline.setStrokeWidth(size*Controller.scaleSize);
        starts = inputToCanvas(starts);
        ends = inputToCanvas(ends);
        return polyline.intersects(starts.x, starts.y,Math.abs(starts.x- ends.x),Math.abs(starts.y- ends.y));
    }

    private Double[] returnPoints(){
        Double[] pointsArr = new Double[points.size()*2];
        for(int i = 0;i<points.size()*2;i+=2){
            pointsArr[i] = inputToCanvas(points.get(i/2)).x;
            pointsArr[i+1] = inputToCanvas(points.get(i/2)).y;
        }
        return pointsArr;
    }

    @Override
    public void drawSelection() {
        Point max = new Point(0 , 0);
        Point min = new Point(1920,1080);
        for(Point p : points){
            min = new Point(Math.min(p.x,min.x),Math.min(p.y,min.y));
            max = new Point(Math.max(p.x,max.x),Math.max(p.y,max.y));
        }
        max = inputToCanvas(max);
        min = inputToCanvas(min);
        graphicsContext.setLineWidth(3);
        graphicsContext.setStroke(Color.ROYALBLUE);
        graphicsContext.strokeRect(min.x-size*Controller.scaleSize,min.y-size*Controller.scaleSize,max.x-min.x+size*Controller.scaleSize*2,max.y-min.y+size*Controller.scaleSize*2);
    }

    public void draw(GraphicsContext graphicsContext){
        double[] x;
        double[] y;
        x = new double[points.size()];
        y = new double[points.size()];
        for(Point p : points){
            x[points.indexOf(p)] = inputToCanvas(p).x;
            y[points.indexOf(p)] = inputToCanvas(p).y;
        }
        graphicsContext.setLineCap(StrokeLineCap.ROUND);
        graphicsContext.setLineJoin(StrokeLineJoin.ROUND);
        graphicsContext.setStroke(colorOfStroke);
        graphicsContext.setLineWidth(size*Controller.scaleSize);
        graphicsContext.strokePolyline(x, y,points.size());
        if(isSelected){
            drawSelection();
        }
    }

}