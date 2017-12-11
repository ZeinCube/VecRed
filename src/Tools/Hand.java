package Tools;

import Figures.Figure;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import sample.Controller;

public class Hand extends Tool {
    public Hand(Canvas canvas) {
        super(canvas);
        graphicsContext = canvas.getGraphicsContext2D();
        button = new Button();
        button.setPrefHeight(70);
        button.setPrefWidth(75);
        button.setText("Hand");
    }

    @Override
    public void getOnMousePressed(MouseEvent event) {
        x0 = Figure.xOffSet+event.getX();
        y0 = Figure.yOffSet+event.getY();
    }

    @Override
    public void getOnMouseDragged(MouseEvent event) {
        Figure.xOffSet = x0-event.getX();
        Figure.yOffSet = y0-event.getY();
        graphicsContext.clearRect(0,0,1920,1080);
        Controller.repaintCanvas();
    }

    @Override
    public void getOnMouseReleased(MouseEvent event) {

    }
}
