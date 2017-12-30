package Figures;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import sample.Controller;

import java.util.List;

public class Rect extends Figure {
    boolean isFilled;
    Paint colorOfFilling;

    public Rect(Point start , Point end, double size, Paint colorOfStroke,Paint colorOfFilling, boolean isFilled) {
        super(start,end, size,colorOfStroke);
        this.isFilled = isFilled;
        this.colorOfFilling = colorOfFilling;
    }

    @Override
    public boolean isContainPoint(Point p) {
        Point canvSt = inputToCanvas(start);
        Point canvEnd = inputToCanvas(end);
        return new javafx.scene.shape.Rectangle(Math.min(canvSt.x,canvEnd.x)-size/2,Math.min(canvSt.y,canvEnd.y)-size/2,Math.abs((canvSt).x-(canvEnd).x)+size,Math.abs((canvSt).y-(canvEnd).y)+size).contains(new Point2D(p.x,p.y));
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.setFill(colorOfFilling);
        graphicsContext.setStroke(colorOfStroke);
        Point canvSt = inputToCanvas(start);
        Point canvEnd = inputToCanvas(end);
        graphicsContext.setLineWidth(size*Controller.scaleSize/2);
        if(!isFilled){
            graphicsContext.strokeRect(Math.min(canvSt.x,canvEnd.x),Math.min(canvSt.y,canvEnd.y),Math.abs(canvSt.x-canvEnd.x),Math.abs(canvSt.y-canvEnd.y));
        }else{
            graphicsContext.fillRect(Math.min(canvSt.x,canvEnd.x),Math.min(canvSt.y,canvEnd.y),Math.abs(canvSt.x-canvEnd.x),Math.abs(canvSt.y-canvEnd.y));
            graphicsContext.strokeRect(Math.min(canvSt.x,canvEnd.x),Math.min(canvSt.y,canvEnd.y),Math.abs(canvSt.x-canvEnd.x),Math.abs(canvSt.y-canvEnd.y));
        }
    }
}