package Figures;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.StrokeLineCap;
import sample.Controller;

public class PolyLine extends Figure{

    public PolyLine(double x0 , double y0 , double x1 , double y1 ,double size, Paint color) {
        super(x0,y0,x1,y1,size,color);
        n = 2;
        x = new double[]{startingX*Controller.scaleSize-Figure.xOffSet,endX*Controller.scaleSize-Figure.xOffSet};
        y = new double[]{startingY*Controller.scaleSize-Figure.yOffSet,endY*Controller.scaleSize-Figure.yOffSet};
        draw(graphicsContext);
    }

    public PolyLine(double[] xs , double[] ys , double sizeOf , Paint color,int points){
        this.color = color.toString();
        size = sizeOf;
        n = points;
        x = xs;
        y = ys;
        graphicsContext.setLineCap(StrokeLineCap.ROUND);
        graphicsContext.setStroke(Paint.valueOf(this.color));
        graphicsContext.setLineWidth(size*Controller.scaleSize);
        graphicsContext.strokePolyline(x,y,n);
    }

    public void draw(GraphicsContext graphicsContext){
        graphicsContext.setLineCap(StrokeLineCap.ROUND);
        graphicsContext.setStroke(Color.BLACK);
        graphicsContext.setLineWidth(size*Controller.scaleSize);
        System.out.println(size);
        graphicsContext.strokePolyline(x, y,n);
    }

}