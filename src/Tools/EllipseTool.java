package Tools;
import Figures.Ellipse;
import Figures.Figure;
import Figures.Point;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import sample.Controller;

public class EllipseTool extends Tool{
    public EllipseTool(Canvas canvas) {
        super(canvas);
        this.graphicsContext = canvas.getGraphicsContext2D();
        button = new Button();
        button.setPrefHeight(70);
        button.setPrefWidth(70);
        button.setText("Oval");
        isFillingTool = true;
    }

    @Override
    public void getOnMousePressed(MouseEvent event) {
        start = Figure.canvasToScreen(new Point(event.getX(),event.getY()));
    }

    @Override
    public void getOnMouseDragged(MouseEvent event) {
        graphicsContext.clearRect(0,0,1920,1080);
        Controller.repaintCanvas();
        end = Figure.canvasToScreen(new Point(event.getX(),event.getY()));
        new Ellipse(start,end,size, colorOfStroke,Controller.colorOfFilling,Controller.isFilling);
    }

    @Override
    public void getOnMouseReleased(MouseEvent event) {
        end = Figure.canvasToScreen(new Point(event.getX(),event.getY()));
        Controller.addFigure(new Ellipse(start,end,size, colorOfStroke,Controller.colorOfFilling,Controller.isFilling));
        Controller.repaintCanvas();
    }
}