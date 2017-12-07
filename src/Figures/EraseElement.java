package Figures;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import sample.Controller;


public class EraseElement extends Figure {
    int size;

    public EraseElement(double startingX, double startingY, double endX, double endY, GraphicsContext graphicsContext, Paint color) {
        super(startingX, startingY, endX, endY, graphicsContext, color);
        this.size = Controller.size;
        draw();
    }

    @Override
    public void draw() {
        graphicsContext.clearRect((endX)-size/2,(endY)-size/2,size,size);
    }
}
