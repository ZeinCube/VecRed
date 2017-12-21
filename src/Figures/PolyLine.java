package Figures;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import sample.Controller;
import java.util.LinkedList;

public class PolyLine extends Figure{
    LinkedList<Point> points = new LinkedList<>();
    double[] x;
    double[] y;
    int n;

    public PolyLine(LinkedList<Point> points , Paint color,double size){
        this.colorOfStroke = color;
        this.points = points;
        this.size = size;
        x = new double[points.size()];
        y = new double[points.size()];
        n = points.size();
        draw(graphicsContext);
    }

    public void draw(GraphicsContext graphicsContext){
        for(Point p : points){
            x[points.indexOf(p)] = p.x*Controller.scaleSize-Figure.xOffSet;
            y[points.indexOf(p)] = p.y*Controller.scaleSize-Figure.yOffSet;
        }
        graphicsContext.setLineCap(StrokeLineCap.ROUND);
        graphicsContext.setLineJoin(StrokeLineJoin.ROUND);
        graphicsContext.setStroke(colorOfStroke);
        graphicsContext.setLineWidth(size*Controller.scaleSize);
        graphicsContext.strokePolyline(x, y,n);
    }

}