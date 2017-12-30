package Figures;


import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import sample.Controller;
import java.util.LinkedList;


public class PolyLine extends Figure{
    private LinkedList<Point> points = new LinkedList<>();

    public PolyLine(LinkedList<Point> points , Paint color,double size){
        this.colorOfStroke = color;
        this.points = points;
        this.size = size;
        draw(graphicsContext);
    }

    public void addPoint(Point point){
        points.add(point);
        draw(graphicsContext);
    }

    @Override
    public boolean isContainPoint(Point p){
        boolean res = false;
        for(Point point : points){
            if(point.x==p.x&point.y==p.y){
                res = true;
                System.out.println("true");
                break;
            }
        }
        return res;
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
    }

}