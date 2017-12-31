package Figures;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import sample.Controller;

public class Ellipse extends Figure {

    public Ellipse(Point start , Point end, double size, Paint colorOfStroke,Paint colorOfFilling, boolean isFilled) {
        super(start,end, size,colorOfStroke);
        this.isFillingFigure = true;
        this.isFilled = isFilled;
        this.colorOfFilling = colorOfFilling;
    }

    @Override
    public boolean isContainPoint(Point p) {
        Point canvSt = inputToCanvas(start);
        Point canvEnd = inputToCanvas(end);
        double cx = ((canvSt).x+(canvEnd).x)/2;
        double cy = ((canvSt).y+(canvEnd).y)/2;
        return new javafx.scene.shape.Ellipse(cx,cy,Math.abs(canvSt.x-canvEnd.x)/2+(size*Controller.scaleSize/2)/2,Math.abs((canvSt).y-(canvEnd).y)/2+(size*Controller.scaleSize/2)/2).contains(new Point2D(p.x,p.y));
    }

    @Override
    public boolean isIntersecting(Point starts, Point ends) {
        Point canvSt = inputToCanvas(start);
        Point canvEnd = inputToCanvas(end);
        starts = inputToCanvas(starts);
        ends = inputToCanvas(ends);
        double cx = ((canvSt).x+(canvEnd).x)/2;
        double cy = ((canvSt).y+(canvEnd).y)/2;
        return new javafx.scene.shape.Ellipse(cx,cy,Math.abs(canvSt.x-canvEnd.x)/2+(size*Controller.scaleSize/2)/2,Math.abs((canvSt).y-(canvEnd).y)/2+(size*Controller.scaleSize/2)/2).intersects(starts.x,starts.y,Math.abs(starts.x-ends.x),Math.abs(starts.y-ends.y));
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        Point canvSt = inputToCanvas(start);
        Point canvEnd = inputToCanvas(end);
        graphicsContext.setFill(colorOfFilling);
        graphicsContext.setStroke(colorOfStroke);
        graphicsContext.setLineWidth(size*Controller.scaleSize/2);
        if(!isFilled) {
            graphicsContext.strokeOval(Math.min(canvSt.x,canvEnd.x),Math.min(canvSt.y,canvEnd.y),Math.abs(canvSt.x-canvEnd.x),Math.abs(canvSt.y-canvEnd.y));
        }else{
            graphicsContext.fillOval(Math.min(canvSt.x,canvEnd.x),Math.min(canvSt.y,canvEnd.y),Math.abs(canvSt.x-canvEnd.x),Math.abs(canvSt.y-canvEnd.y));
            graphicsContext.strokeOval(Math.min(canvSt.x,canvEnd.x),Math.min(canvSt.y,canvEnd.y),Math.abs(canvSt.x-canvEnd.x),Math.abs(canvSt.y-canvEnd.y));
        }
        if(isSelected){
            drawSelection();
        }
    }
}