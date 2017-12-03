package Tools;

import javafx.scene.canvas.GraphicsContext;

public class Line {
    double size;

    public Line(double size , double x0, double y0, double x1, double y1, GraphicsContext graphicsContext) {
        //Изменения координат
        double dx = (x1 > x0) ? (x1 - x0) : (x0 - x1);
        double dy = (y1 > y0) ? (y1 - y0) : (y0 - y1);
        //Направление приращения
        int sx = (x1 >= x0) ? (1) : (-1);
        int sy = (y1 >= y0) ? (1) : (-1);

        if (dy < dx)
        {
            double d = ((int)dy << 1) - dx;
            double d1 = (int)dy << 1;
            double d2 = (int)(dy - dx) << 1;
            graphicsContext.fillRoundRect(x0, y0, size, size, size, size);
            double x = x0 + sx;
            double y = y0;
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
            graphicsContext.fillRoundRect(x0, y0, size, size, size, size);
            double x = x0;
            double y = y0 + sy;
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
