package Figures;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.shape.StrokeLineCap;

public class PolyLine extends Figure{

    public PolyLine(double startingX, double startingY, double endX, double endY, double size , Paint color) {
        super(startingX,startingY,endX,endY, size, color);
    }

    public void draw(GraphicsContext graphicsContext){
        double[] x = new double[]{
                (startingX+size/2)-Figure.xOffSet,(endX+size/2)-Figure.xOffSet
        };
        double[] y = new double[]{
                (startingY+size/2)-Figure.yOffSet,(endY+size/2)-Figure.yOffSet
        };
        graphicsContext.setStroke(color);
        graphicsContext.setLineWidth(size);
        graphicsContext.setLineCap(StrokeLineCap.ROUND);
        graphicsContext.strokePolyline(x,y,2);
    }

}
