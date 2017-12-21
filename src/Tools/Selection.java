package Tools;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class Selection extends Tool {

    public Selection(Canvas canvas) {
        super(canvas);
        graphicsContext = canvas.getGraphicsContext2D();
        button = new Button();
        button.setPrefHeight(70);
        button.setPrefWidth(75);
        button.setText("Select");
    }

    @Override
    public void getOnMousePressed(MouseEvent event) {

    }

    @Override
    public void getOnMouseDragged(MouseEvent event) {

    }

    @Override
    public void getOnMouseReleased(MouseEvent event) {

    }
}
