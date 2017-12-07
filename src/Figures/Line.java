package Figures;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class Line extends Figure{
    private double size;

    public Line(double startingX, double startingY, double endX, double endY, GraphicsContext graphicsContext , double size , Paint color) {
        super(startingX,startingY,endX,endY ,graphicsContext , color);
        this.size = size;
        this.color = color;
        draw();
    }

    public void draw(){
        //Изменения координат
        graphicsContext.setFill(color);
        double dx = (endX > startingX) ? (endX - startingX) : (startingX - endX);
        double dy = (endY > startingY) ? (endY - startingY) : (startingY - endY);
        //Направление приращения
        int sx = (endX >= startingX) ? (1) : (-1);
        int sy = (endY >= startingY) ? (1) : (-1);

        if (dy < dx)
        {
            double d = ((int)dy << 1) - dx;
            double d1 = (int)dy << 1;
            double d2 = (int)(dy - dx) << 1;
            graphicsContext.fillRoundRect(startingX, startingY, size, size, size, size);
            double x = startingX + sx;
            double y = startingY;
            for (int i = 1; i <= dx; i++)
            {
                if (d > 0)
                {
                    d += d2;
                    y += sy;
                }
                else
                    d += d1;
                graphicsContext.fillRoundRect(x, y, size, size, size, size);
                x+=sx;
            }
        }
        else
        {
            double d =  ((int)dx << 1) - dy;
            double d1 = (int) dx << 1;
            double d2 = (int) (dx - dy) << 1;
            graphicsContext.fillRoundRect(startingX, startingY, size, size, size, size);
            double x = startingX;
            double y = startingY + sy;
            for (int i = 1; i <= dy; i++)
            {
                if (d > 0)
                {
                    d += d2;
                    x += sx;
                }
                else
                    d += d1;
                graphicsContext.fillRoundRect(x, y, size, size, size, size);
                y+=sy;
            }
        }
    }

}
