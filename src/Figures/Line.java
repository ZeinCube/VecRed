package Figures;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.shape.StrokeLineCap;
import sample.Controller;

public class Line extends Figure {
    public Line(double startingX, double startingY, double endX, double endY, double size, Paint colorOfStroke) {
        super(startingX,startingY,endX,endY, size,colorOfStroke);
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.setLineWidth(size*Controller.scaleSize);
        graphicsContext.setStroke(colorOfStroke);
        graphicsContext.setLineCap(StrokeLineCap.ROUND);
        graphicsContext.strokeLine(startingX*Controller.scaleSize-Figure.xOffSet,startingY*Controller.scaleSize-Figure.yOffSet,endX*Controller.scaleSize-Figure.xOffSet,endY*Controller.scaleSize-Figure.yOffSet);
    }
}