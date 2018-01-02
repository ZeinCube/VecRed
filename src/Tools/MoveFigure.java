package Tools;

import Figures.Figure;
import Figures.Point;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import sample.Controller;

public class MoveFigure extends Tool {
    Figure movingFigure;
    Point starting;
    double remXS,remYS,remXE,remYE;

    public MoveFigure(Canvas canvas) {
        super(canvas);
        button = new Button();
        button.setPrefHeight(70);
        button.setPrefWidth(70);
        button.setText("Move");
        button.setDisable(true);
        Selection.movingTool = button;
    }

    @Override
    public void getOnMousePressed(MouseEvent event) {
        starting = new Point(event.getX(),event.getY());
        for (int i = 0; i< Controller.figures.size(); i++){
            if(Controller.figures.get(Controller.figures.size()-i-1).isContainPoint(starting)&&Controller.figures.get(Controller.figures.size()-i-1).isSelected) {
                movingFigure = Controller.figures.get(Controller.figures.size()-i-1);
                break;
            }
        }
        remXS = movingFigure.start.x;
        remYS = movingFigure.start.y;
        remXE = movingFigure.end.x;
        remYE = movingFigure.end.y;
    }

    @Override
    public void getOnMouseDragged(MouseEvent event) {
        if(movingFigure!=null) {
            movingFigure.start = new Point(remXS + (event.getX() - starting.x), remYS + (event.getY() - starting.y));
            movingFigure.end = new Point(remXE + (event.getX() - starting.x),remYE +(event.getY() - starting.y));
            graphicsContext.clearRect(0, 0, Controller.widht, Controller.height);
            Controller.repaintCanvas();
        }
    }

    @Override
    public void getOnMouseReleased(MouseEvent event) {

    }
}
