package Tools;
import Figures.Figure;
import Figures.Ellipse;
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
        button.setPrefWidth(75);
        button.setText("Ellipse");
    }

    @Override
    public void getOnMousePressed(MouseEvent event) {
        x0 = (event.getX()-Figure.xOffSet)/Controller.scaleSize;
        y0 = (event.getY()-Figure.yOffSet)/Controller.scaleSize;
    }

    @Override
    public void getOnMouseDragged(MouseEvent event) {
        graphicsContext.clearRect(0,0,1920,1080);
        Controller.repaintCanvas();
        new Ellipse(x0,y0,(event.getX()-Figure.xOffSet)/Controller.scaleSize,(event.getY()-Figure.yOffSet)/Controller.scaleSize,size,color);
    }

    @Override
    public void getOnMouseReleased(MouseEvent event) {
        Controller.repaintCanvas();
        Controller.addFigure(new Ellipse(x0,y0,(event.getX()-Figure.xOffSet)/Controller.scaleSize,(event.getY()-Figure.yOffSet)/Controller.scaleSize,size,color));
    }
}