package Figures;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class RoundRect extends Figure {

    public RoundRect(double startingX, double startingY, double endX, double endY, double size, Paint color) {
        super(startingX, startingY, endX, endY, size, color);
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.setFill(color);
        graphicsContext.setStroke(color);
        graphicsContext.setLineWidth(size);
        graphicsContext.strokeRoundRect(Math.min(startingX,endX)-Figure.xOffSet,Math.min(startingY,endY)-Figure.yOffSet,Math.abs(startingX-endX),Math.abs(startingY-endY),size,size);
    }
}
