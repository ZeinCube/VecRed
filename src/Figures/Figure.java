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
    public boolean isFillingFigure;
    public Paint colorOfFilling;
    public Paint colorOfStroke;
    public boolean isFilled = false;
    public double sizeOfBrush;
    public static GraphicsContext graphicsContext;
    public Point start , end;
    LinkedList<Point> points = new LinkedList<>();
    public boolean isSelected;
    public static double xOffSet , yOffSet;

    public Figure(Point start , Point end, double sizeOfBrush, Paint colorOfStroke) {
        this.start = start;
        this.end = end;
        this.sizeOfBrush = sizeOfBrush;
        this.colorOfStroke = colorOfStroke;
        draw(graphicsContext);
    }

    protected Figure() {
    }

    public abstract boolean isContainPoint(Point p);

    public abstract boolean isIntersecting(Point starts, Point ends);

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
        graphicsContext.setLineWidth(3);
        graphicsContext.setStroke(Color.ROYALBLUE);
        graphicsContext.strokeRect(Math.min(canvSt.x,canvEnd.x)-(sizeOfBrush *Controller.scaleSize)/2,Math.min(canvSt.y,canvEnd.y)-(sizeOfBrush *Controller.scaleSize)/2,Math.abs(canvSt.x-canvEnd.x)+(sizeOfBrush *Controller.scaleSize),Math.abs(canvSt.y-canvEnd.y)+(sizeOfBrush *Controller.scaleSize));
    }

    public Point inputToCanvas(Point p){
        return new Point(p.x*Controller.scaleSize - Figure.xOffSet,p.y*Controller.scaleSize - Figure.yOffSet);
    }

    public static Point canvasToScreen(Point p){
        return new Point((p.x+Figure.xOffSet)/Controller.scaleSize,(p.y+Figure.yOffSet)/Controller.scaleSize);
    }

    public abstract void draw(GraphicsContext graphicsContext);

    public static List<String> parseString(String string) {
        List<String> params = new ArrayList<>();
        StringBuilder param = new StringBuilder();
        for (char c : string.toCharArray()) {
            if (c != ' ') {
                if(c!='[' && c!=']'&&c!=',')
                param.append(c);
            } else {
                params.add(param.toString());
                param = new StringBuilder();
            }
        }
        params.add(param.toString());
        return params;
    }

    @Override
    public abstract String toString();
}
