package Figures;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import sample.Controller;

public class Rect extends Figure {
    boolean isFilled;
    Paint colorOfFilling;

    public Rect(double startingX, double startingY, double endX, double endY, double size, Paint colorOfStroke,Paint colorOfFilling, boolean isFilled) {
        super(startingX,startingY,endX,endY, size,colorOfStroke);
        this.isFilled = isFilled;
        this.colorOfFilling = colorOfFilling;
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.setFill(colorOfFilling);
        graphicsContext.setStroke(colorOfStroke);
        graphicsContext.setLineWidth(size* Controller.scaleSize);
        if(!isFilled) {
            graphicsContext.strokeRect(Math.min(startingX, endX) * Controller.scaleSize - Figure.xOffSet, Math.min(startingY, endY) * Controller.scaleSize - Figure.yOffSet, Math.abs(startingX - endX) * Controller.scaleSize, Math.abs(startingY - endY) * Controller.scaleSize);
        }else{
            graphicsContext.strokeRect(Math.min(startingX, endX) * Controller.scaleSize - Figure.xOffSet, Math.min(startingY, endY) * Controller.scaleSize - Figure.yOffSet, Math.abs(startingX - endX) * Controller.scaleSize, Math.abs(startingY - endY) * Controller.scaleSize);
            graphicsContext.fillRect(Math.min(startingX, endX) * Controller.scaleSize - Figure.xOffSet, Math.min(startingY, endY) * Controller.scaleSize - Figure.yOffSet, Math.abs(startingX - endX) * Controller.scaleSize, Math.abs(startingY - endY) * Controller.scaleSize);
        }
    }
}