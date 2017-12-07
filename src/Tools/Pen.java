package Tools;

import Figures.Line;
import javafx.scene.canvas.Canvas;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import sample.Controller;

import java.awt.*;

public class Pen extends Tool {

    public Pen(Canvas canvas) {
        super(canvas);
        this.graphicsContext = canvas.getGraphicsContext2D();
    }

    private void newLine(double size, double x0, double y0, double x1, double y1){
        Controller.figures.add(new Line(x0,y0,x1,y1,graphicsContext,size, color));
    }

    @Override
    public void getOnMousePressed(MouseEvent event) {
        x = event.getX()-size/2;
        y = event.getY()-size/2;
        x0 = x;
        y0 = y;
        Controller.figures.add(new Line(x,y,x,y,graphicsContext,size, color));
        graphicsContext.fillRoundRect(x, y, size, size, size, size);
    }

    @Override
    public void getOnMouseDragged(MouseEvent event) {
        x = (int) (event.getX()-size/2);
        y = event.getY()-size/2;
        newLine(size,x0, y0, x, y);
        x0 = x;
        y0 = y;
    }

    @Override
    public void getOnMouseReleased(MouseEvent event) {
        newLine(size,x0,y0,x,y);
    }
}
