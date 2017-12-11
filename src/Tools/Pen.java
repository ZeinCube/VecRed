package Tools;

import Figures.Figure;
import Figures.PolyLine;
import javafx.scene.canvas.Canvas;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import sample.Controller;

public class Pen extends Tool {

    public Pen(Canvas canvas) {
        super(canvas);
        this.graphicsContext = canvas.getGraphicsContext2D();
        button = new Button();
        button.setPrefHeight(70);
        button.setPrefWidth(75);
        button.setText("Pen");
    }

    private void newLine(double size, double x0, double y0, double x1, double y1){
        Controller.figures.add(new PolyLine(x0,y0,x1,y1,size,color));
    }

    @Override
    public void getOnMousePressed(MouseEvent event) {
        x = (event.getX()-size/2)+Figure.xOffSet;
        y = (event.getY()-size/2)+Figure.yOffSet;
        x0 = x;
        y0 = y;
        newLine(size,x,y,x,y);
    }

    @Override
    public void getOnMouseDragged(MouseEvent event) {
        x = (event.getX()-size/2)+Figure.xOffSet;
        y = (event.getY()-size/2)+Figure.yOffSet;
        newLine(size,x0, y0, x, y);
        x0 = x;
        y0 = y;
    }

    @Override
    public void getOnMouseReleased(MouseEvent event) {
        newLine(size,x0,y0,x,y);
    }
}
