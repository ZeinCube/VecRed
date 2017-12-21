package Tools;

import Figures.Figure;
import Figures.Rect;
import Figures.RoundRect;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import sample.Controller;

public class RoundRectTool extends Tool {
    public RoundRectTool(Canvas canvas) {
        super(canvas);
        graphicsContext = canvas.getGraphicsContext2D();
        button = new Button();
        button.setPrefHeight(70);
        button.setPrefWidth(110);
        button.setText("RoundedRect");
    }

    @Override
    public void getOnMousePressed(MouseEvent event) {
        x0 = (event.getX()+Figure.xOffSet)/Controller.scaleSize;
        y0 = (event.getY()+Figure.yOffSet)/Controller.scaleSize;
    }

    @Override
    public void getOnMouseDragged(MouseEvent event) {
        graphicsContext.clearRect(0,0,1920,1080);
        Controller.repaintCanvas();
        new RoundRect(x0,y0,(event.getX()+Figure.xOffSet)/Controller.scaleSize,(event.getY()+Figure.yOffSet)/Controller.scaleSize,size,colorOfStroke,Controller.colorOfFilling,Controller.isFilling);
    }

    @Override
    public void getOnMouseReleased(MouseEvent event) {
        Controller.addFigure(new RoundRect(x0,y0,(event.getX()+Figure.xOffSet)/Controller.scaleSize,(event.getY()+Figure.yOffSet)/Controller.scaleSize,size,colorOfStroke,Controller.colorOfFilling,Controller.isFilling));
        Controller.repaintCanvas();
    }
}