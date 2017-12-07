package Figures;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class Rect extends Figure {

    public Rect(double startingX, double startingY, double endX, double endY, GraphicsContext graphicsContext, Paint color) {
        super(startingX, startingY, endX, endY, graphicsContext, color);
        draw();
    }

    @Override
    public void draw() {
        graphicsContext.setFill(color);
        graphicsContext.setStroke(color);
        graphicsContext.strokeRect(Math.min(startingX,endX),Math.min(startingY,endY),Math.abs(startingX-endX),Math.abs(startingY-endY));
    }
}
