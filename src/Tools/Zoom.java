package Tools;

import Figures.Figure;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import sample.Controller;

public class Zoom extends Tool {
    public Zoom(Canvas canvas) {
        super(canvas);
        graphicsContext = canvas.getGraphicsContext2D();
        button = new Button();
        button.setPrefHeight(70);
        button.setPrefWidth(75);
        button.setText("Zoom");
    }

    @Override
    public void getOnMousePressed(MouseEvent event) {
        Figure.setOffSet(Figure.xOffSet+event.getX()-(1920-Figure.xOffSet)/2,Figure.yOffSet+event.getY()-(1080-Figure.yOffSet)/2);
        graphicsContext.clearRect(0,0,1920,1080);
        Controller.repaintCanvas();
    }

    @Override
    public void getOnMouseDragged(MouseEvent event) {

    }

    @Override
    public void getOnMouseReleased(MouseEvent event) {

    }
}
