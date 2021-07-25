package Tools;

import Figures.Figure;
import Figures.Point;
import Figures.Rect;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import sample.Controller;

import static sample.Controller.height;
import static sample.Controller.widht;

public class Selection extends Tool {
    private boolean isSelectedByRect = false;
    public static boolean isFillingFigure = true;
    public static boolean isFilled = true;
    public static Color colorOfShape;
    public static double sizeOfBrush;
    public static Color colorOfFilling;
    private static boolean isSameColorOfShape = true;
    public static boolean isSameSize = true;
    private static boolean isSameColorOfFilling = true;
    private static boolean isRememberedParams = false;
    private static boolean haveSelectedFigure = false;
    public static Button movingTool;

    public Selection(Canvas canvas) {
        super(canvas);
        button = new Button();
        button.setPrefHeight(70);
        button.setPrefWidth(70);
        button.setText("Select");
    }

    @Override
    public void getOnMousePressed(MouseEvent event) {
        start = Figure.canvasToScreen(new Point(event.getX(), event.getY()));
    }

    @Override
    public void getOnMouseDragged(MouseEvent event) {
        isSelectedByRect = true;
        end = Figure.canvasToScreen(new Point(event.getX(), event.getY()));
        graphicsContext.clearRect(0, 0, widht, height);
        Controller.repaintCanvas();
        new Rect(start, end, 3, Color.RED, Controller.colorOfFilling, false);
    }

    private static void rememberParams(int index) {
        isFillingFigure = isFillingFigure & Controller.figures.get(index).isFillingFigure;
        isFilled = isFilled & Controller.figures.get(index).isFilled;
        isSameColorOfShape = isSameColorOfShape & colorOfShape.equals(Controller.figures.get(index).colorOfStroke);
        if (!isSameColorOfShape) {
            colorOfShape = Color.BLACK;
        }
        isSameColorOfFilling = isSameColorOfFilling & colorOfFilling.equals(Controller.figures.get(index).colorOfFilling);
        if (!isSameColorOfFilling) {
            colorOfFilling = Color.BLACK;
        }
        isSameSize = isSameSize & Controller.figures.get(index).sizeOfBrush == sizeOfBrush;
        if (!isSameSize)
            sizeOfBrush = 15;
    }

    private static void rememberFirstParams(Color colorOfShapeI, boolean isFillingFigureI, boolean isFilledI, Color colorOfFillingI, double sizeOfBrushI) {
        colorOfShape = colorOfShapeI;
        isFillingFigure = isFillingFigureI;
        isFilled = isFilledI;
        colorOfFilling = colorOfFillingI;
        sizeOfBrush = sizeOfBrushI;
        isRememberedParams = true;
        haveSelectedFigure = true;
    }

    @Override
    public void getOnMouseReleased(MouseEvent event) {
        Controller.repaintCanvas();
        if (!isSelectedByRect) {
            Point p = new Point(event.getX(), event.getY());
            for (int i = 0; i < Controller.figures.size(); i++) {
                if (Controller.figures.get(Controller.figures.size() - i - 1).isContainPoint(p)) {
                    Controller.figures.get(Controller.figures.size() - i - 1).setSelected(true);
                    if (!isRememberedParams) {
                        Figure currentFigure = Controller.figures.get(Controller.figures.size() - i - 1);
                        rememberFirstParams(Color.valueOf(currentFigure.colorOfStroke.toString()), currentFigure.isFillingFigure, currentFigure.isFilled, Color.valueOf(currentFigure.colorOfFilling.toString()), currentFigure.sizeOfBrush);
                    }
                    i++;
                    for (int i1 = i; i1 < Controller.figures.size(); i1++)
                        Controller.figures.get(Controller.figures.size() - i1 - 1).setSelected(false);
                    break;
                } else {
                    Controller.figures.get(Controller.figures.size() - i - 1).setSelected(false);
                }
            }
        } else {
            for (int i = 0; i < Controller.figures.size(); i++) {
                if (Controller.figures.get(Controller.figures.size() - i - 1).isIntersecting(new Point(Math.min(start.x, end.x), Math.min(start.y, end.y)), new Point(Math.max(start.x, end.x), Math.max(start.y, end.y)))) {
                    Controller.figures.get(Controller.figures.size() - i - 1).setSelected(true);
                    if (!isRememberedParams) {
                        Figure currentFigure = Controller.figures.get(Controller.figures.size() - i - 1);
                        rememberFirstParams(Color.valueOf(currentFigure.colorOfStroke.toString()), currentFigure.isFillingFigure, currentFigure.isFilled, Color.valueOf(currentFigure.colorOfFilling.toString()), currentFigure.sizeOfBrush);
                    } else {
                        rememberParams(Controller.figures.size() - i - 1);
                    }
                } else {
                    Controller.figures.get(Controller.figures.size() - i - 1).setSelected(false);
                }
            }
        }
        isSelectedByRect = false;
        movingTool.setDisable(!haveSelectedFigure);
        if (haveSelectedFigure) {
            Controller.showParams();
        } else {
            Controller.hideParams();
        }
        graphicsContext.clearRect(0, 0, widht, height);
        Controller.repaintCanvas();
        haveSelectedFigure = false;
        isRememberedParams = false;
        isSameColorOfShape = true;
        isSameSize = true;
        isSameColorOfFilling = true;
        haveSelectedFigure = false;
    }
}
