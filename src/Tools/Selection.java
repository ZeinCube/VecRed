package Tools;

import Figures.Figure;
import Figures.Point;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import sample.Controller;

public class Selection extends Tool {

    public Selection(Canvas canvas) {
        super(canvas);
        graphicsContext = canvas.getGraphicsContext2D();
        button = new Button();
        button.setPrefHeight(70);
        button.setPrefWidth(70);
        button.setText("Select");
    }

    @Override
    public void getOnMousePressed(MouseEvent event) {
        Point p  = new Point(event.getX(),event.getY());
        for (int i = 0 ; i<Controller.figures.size();i++){
            if(Controller.figures.get(Controller.figures.size()-i-1).isContainPoint(p)){
                Controller.figures.get(Controller.figures.size()-i-1).setSelected(true);
                break;
            }else{
                Controller.figures.get(Controller.figures.size()-i-1).setSelected(false);
            }
        }
    }

    @Override
    public void getOnMouseDragged(MouseEvent event) {

    }

    @Override
    public void getOnMouseReleased(MouseEvent event) {

    }
}
