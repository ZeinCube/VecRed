package Tools;

import Figures.Rect;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import sample.Controller;

public class RectTool extends Tool{

    public RectTool(Canvas canvas) {
        super(canvas);
        this.graphicsContext = canvas.getGraphicsContext2D();
    }

    @Override
    public void getOnMousePressed(MouseEvent event) {
        x0 = event.getX();
        y0 = event.getY();
    }

    @Override
    public void getOnMouseDragged(MouseEvent event) {
        graphicsContext.clearRect(0,0,1920,1080);
        Controller.repaintCanvas();
        x = Math.min(x0,event.getX());
        y = Math.min(y0,event.getY());
        width = Math.abs(event.getX() - x0);
        height = Math.abs(event.getY() - y0);
        graphicsContext.setStroke(color);
        graphicsContext.strokeRect(x,y,width,height);
    }

    @Override
    public void getOnMouseReleased(MouseEvent event) {
        Controller.repaintCanvas();
        Controller.addFigure(new Rect(x0,y0, event.getX(), event.getY() ,graphicsContext , color));
    }
}
