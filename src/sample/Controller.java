package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.paint.Color;


public class Controller {

    private GraphicsContext exampleBrush;

    @FXML
    private Canvas CanvasExample;

    @FXML
    private Label brushSizeLabel;

    private double size;

    @FXML
    private MenuBar menuBar;

    @FXML
    private Canvas canvas;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private Slider brushSize;

    @FXML
    private CheckBox eraser;

    private double x, y, x0, y0;

    public void onExit(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void getSize(){
        size = brushSize.getValue();
        brushSizeLabel.setText(String.valueOf(size));
        exampleBrush.clearRect(0, 0,105, 105);
        exampleBrush.fillRoundRect(52-size/2, 52-size/2, size,size,size, size);
    }

    private void draw() {
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        exampleBrush = CanvasExample.getGraphicsContext2D();

        canvas.setOnMousePressed(event -> {
            double x = event.getX()-size/2;
            double y = event.getY()-size/2;
            x0 = x;
            y0 = y;
            if(eraser.isSelected()){
                graphicsContext.clearRect(x, y ,size, size);
            }else {
                graphicsContext.setFill(colorPicker.getValue());
                graphicsContext.fillRoundRect(x, y, size, size, size, size);
            }
        });

        canvas.setOnMouseDragged(event -> {
            x = (int) (event.getX()-size/2);
            y = event.getY()-size/2;

            if(eraser.isSelected()){
                graphicsContext.clearRect(x, y ,size, size);
            }else {
                graphicsContext.setFill(colorPicker.getValue());
                line(x, y, x0, y0,graphicsContext);
                x0 = x;
                y0 = y;
            }
        });
    }
    private void line(double x0, double y0, double x1, double y1, GraphicsContext graphicsContext) {
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

    public void initialize(){
        colorPicker.setValue(Color.BLACK);
        draw();
        getSize();
    }

    public void setColor(ActionEvent actionEvent) {
        exampleBrush.setFill(colorPicker.getValue());
        canvas.getGraphicsContext2D().setFill(colorPicker.getValue());
    }
}
