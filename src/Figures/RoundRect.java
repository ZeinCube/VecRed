package Figures;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import sample.Controller;

import java.util.List;

public class RoundRect extends Figure {
    boolean isFilled;
    Paint colorOfFilling;
    double sizeOfArc;
    public RoundRect(Point start , Point end, double size, Paint colorOfStroke,Paint colorOfFilling, boolean isFilled) {
        super(start,end, size,colorOfStroke);
        this.isFilled = isFilled;
        this.colorOfFilling = colorOfFilling;
    }

    @Override
    public boolean isContainPoint(Point p) {
        Point canvSt = inputToCanvas(start);
        Point canvEnd = inputToCanvas(end);
        Rectangle shape  = new javafx.scene.shape.Rectangle(Math.min(canvSt.x,canvEnd.x),Math.min(canvSt.y,canvEnd.y),Math.abs(inputToCanvas(canvSt).x-inputToCanvas(canvEnd).x)+size,Math.abs(inputToCanvas(canvSt).y-inputToCanvas(canvEnd).y)+size);
        shape.setArcHeight(size);
        shape.setArcWidth(size);
        return shape.contains(new Point2D(p.x,p.y));
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.setFill(colorOfFilling);
        graphicsContext.setStroke(colorOfStroke);
        Point canvSt = inputToCanvas(start);
        Point canvEnd = inputToCanvas(end);
        graphicsContext.setLineWidth(size*Controller.scaleSize/2);
        if(!isFilled){
            graphicsContext.strokeRoundRect(Math.min(canvSt.x,canvEnd.x),Math.min(canvSt.y,canvEnd.y),Math.abs(canvSt.x-canvEnd.x),Math.abs(canvSt.y-canvEnd.y),size*Controller.scaleSize,size*Controller.scaleSize);
        }else{
            graphicsContext.fillRoundRect(Math.min(canvSt.x,canvEnd.x),Math.min(canvSt.y,canvEnd.y),Math.abs(canvSt.x-canvEnd.x),Math.abs(canvSt.y-canvEnd.y),size*Controller.scaleSize,size*Controller.scaleSize);
            graphicsContext.strokeRoundRect(Math.min(canvSt.x,canvEnd.x),Math.min(canvSt.y,canvEnd.y),Math.abs(canvSt.x-canvEnd.x),Math.abs(canvSt.y-canvEnd.y),size*Controller.scaleSize,size*Controller.scaleSize);
        }
    }
}