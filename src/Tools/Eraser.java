package Tools;

import Figures.EraseElement;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import sample.Controller;

public class Eraser extends Tool {

    public Eraser(Canvas canvas, double size) {
        super(canvas);
        graphicsContext = canvas.getGraphicsContext2D();
        this.size = size;
    }

    @Override
    public void getOnMousePressed(MouseEvent event) {
        size = Controller.size;
        Controller.addFigure(new EraseElement(event.getX()-size/2,event.getY()-size/2,event.getX(),event.getY(),graphicsContext,color));
    }

    @Override
    public void getOnMouseDragged(MouseEvent event) {
        Controller.addFigure(new EraseElement(event.getX()-size/2,event.getY()-size/2,event.getX(),event.getY(),graphicsContext,color));
    }

    @Override
    public void getOnMouseReleased(MouseEvent event) {

    }
}
