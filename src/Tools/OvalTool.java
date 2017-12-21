package Tools;
import Figures.Ellipse;
import Figures.Figure;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import sample.Controller;

public class OvalTool extends Tool{
    public OvalTool(Canvas canvas) {
        super(canvas);
        this.graphicsContext = canvas.getGraphicsContext2D();
        button = new Button();
        button.setPrefHeight(70);
        button.setPrefWidth(70);
        button.setText("Oval");
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
        new Ellipse(x0,y0,(event.getX()+Figure.xOffSet)/Controller.scaleSize,(event.getY()+Figure.yOffSet)/Controller.scaleSize,size, colorOfStroke,Controller.colorOfFilling,Controller.isFilling);
    }

    @Override
    public void getOnMouseReleased(MouseEvent event) {
        Controller.addFigure(new Ellipse(x0,y0,(event.getX()+Figure.xOffSet)/Controller.scaleSize,(event.getY()+Figure.yOffSet)/Controller.scaleSize,size, colorOfStroke,Controller.colorOfFilling,Controller.isFilling));
        Controller.repaintCanvas();
    }
}