package Figures;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import sample.Controller;

import java.io.Serializable;

public abstract class Figure implements Serializable {
    Paint colorOfStroke;
    double size;
    public static GraphicsContext graphicsContext;
    Point start , end;
    public boolean isSelected;
    public static double xOffSet , yOffSet;

    public Figure(Point start , Point end, double size, Paint colorOfStroke) {
        this.start = start;
        this.end = end;
        this.size = size;
        this.colorOfStroke = colorOfStroke;
        draw(graphicsContext);
    }

    protected Figure() {
    }

    public abstract boolean isContainPoint(Point p);

    public static void setOffSet(double x,double y){
        xOffSet = x;
        yOffSet = y;
    }

    public void setSelected(boolean condition){
        isSelected = condition;
        graphicsContext.clearRect(0,0,1920,1080);
        if(isSelected){
            Controller.repaintCanvas();
            drawSelection();
        }else{
            Controller.repaintCanvas();
        }
    }

    public void drawSelection() {
        Point canvSt = inputToCanvas(start);
        Point canvEnd = inputToCanvas(end);
        graphicsContext.setLineWidth(1);
        graphicsContext.setStroke(Color.BLUE);
        graphicsContext.strokeRect(Math.min(canvSt.x,canvEnd.x)-size/2,Math.min(canvSt.y,canvEnd.y)-size/2,Math.abs(canvSt.x-canvEnd.x)+(size*Controller.scaleSize),Math.abs(canvSt.y-canvEnd.y)+(size*Controller.scaleSize));
    }

    public Point inputToCanvas(Point p){
        return new Point(p.x*Controller.scaleSize - Figure.xOffSet,p.y*Controller.scaleSize - Figure.yOffSet);

    }

    public static Point canvasToScreen(Point p){
        return new Point((p.x+Figure.xOffSet)/Controller.scaleSize,(p.y+Figure.yOffSet)/Controller.scaleSize);
    }

    public abstract void draw(GraphicsContext graphicsContext);
}
