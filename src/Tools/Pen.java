package Tools;

import Figures.Figure;
import Figures.PolyLine;
import javafx.scene.canvas.Canvas;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.StrokeLineCap;
import sample.Controller;

import java.util.ArrayList;
import java.util.List;

public class Pen extends Tool {
    List<Double> xs;
    List<Double> ys;
    static int n;
    public Pen(Canvas canvas) {
        super(canvas);
        this.graphicsContext = canvas.getGraphicsContext2D();
        button = new Button();
        button.setPrefHeight(70);
        button.setPrefWidth(75);
        button.setText("Pen");
    }

    private void newLine(double[] xs, double[] ys, double size, Paint color){
        Controller.figures.add(new PolyLine(xs,ys,size,color,n));
    }

    @Override
    public void getOnMousePressed(MouseEvent event) {
        n = 1;
        xs = new ArrayList<>();
        ys = new ArrayList<>();
        x0 = (event.getX()+Figure.xOffSet)/Controller.scaleSize;
        xs.add(x0);
        y0 = (event.getY()+Figure.yOffSet)/Controller.scaleSize;
        ys.add(y0);
        new PolyLine(x0,y0,(event.getX()+Figure.xOffSet)/Controller.scaleSize,(event.getY()+Figure.yOffSet)/Controller.scaleSize,size,color);
    }

    @Override
    public void getOnMouseDragged(MouseEvent event) {
        n++;
        x = (event.getX()+Figure.xOffSet)/Controller.scaleSize;
        xs.add(x);
        y = (event.getY()+Figure.yOffSet)/Controller.scaleSize;
        ys.add(y);
        new PolyLine(x0,y0,(event.getX()+Figure.xOffSet)/Controller.scaleSize,(event.getY()+Figure.yOffSet)/Controller.scaleSize,size,color);
        x0 = x;
        y0 = y;
    }

    @Override
    public void getOnMouseReleased(MouseEvent event) {
        double xa[] = new double[xs.size()];
        double ya[] = new double[ys.size()];
        int i = 0;
        for(double x : xs){
            xa[i] = x;
            i++;
        }
        i = 0;
        for (double y : ys){
            ya[i] = y;
            i++;
        }
        newLine(xa,ya,size,color);
    }
}