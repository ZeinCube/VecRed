package sample;

import Figures.Figure;
import Tools.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Controller {
    public static Label staticScaleSizeLabel;
    public static Slider staticSliderScale;
    public static Label staticPropertySize;
    public static ColorPicker staticParamColorOfStroke;
    public static CheckBox staticPropertyFilled;
    public static VBox staticBoxColorOfFiling;
    public static ColorPicker staticColorOfFillingParam;
    public static Slider staticSizeOfPropertyBrush;
    public static VBox staticParameters;
    public static Double scaleSize = 1.0;
    public static List<Figure> figures = new ArrayList<>();
    public static List<Tool> toolList = new ArrayList<>();
    public static int size;
    public static boolean isFilling = false;
    public static boolean isFillingParam;
    public static Paint colorOfFilling = Color.BLACK;
    public static int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    public static int widht = Toolkit.getDefaultToolkit().getScreenSize().width;
    public static boolean isSaved = false;
    public static String nameOfFile = "";
    @FXML
    public BorderPane borderPane;
    public javafx.scene.control.MenuBar MenuBar;
    public Label scaleSizeLabel;
    public Slider SliderScale;
    public ColorPicker colorOfFillingPicker;
    public CheckBox checkBox;
    public Label PropertySize;
    public ColorPicker paramColorOfStroke;
    public CheckBox PropertyFilled;
    public VBox BoxColorOfFiling;
    public Slider sizeOfPropertyBrush;
    public ColorPicker colorOfFillingParamPicker;
    public VBox Parameters;
    @FXML
    private Label brushSizeLabel;
    @FXML
    private Canvas canvas;
    @FXML
    private ToolBar toolBar;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private Slider brushSize;
    private GraphicsContext graphicsContext;
    private Tool currentTool;

    public static void repaintCanvas() {
        for (Figure figure : figures) {
            figure.draw(Figure.graphicsContext);
        }
    }

    public static void addFigure(Figure figure) {
        figures.add(figure);
    }

    public static void showParams() {
        isFillingParam = Selection.isFilled;
        staticParamColorOfStroke.setValue(Selection.colorOfShape);
        if (Selection.isSameSize) {
            staticPropertySize.setText(String.valueOf(Selection.sizeOfBrush));
            staticSizeOfPropertyBrush.setValue(Selection.sizeOfBrush);
        }
        staticBoxColorOfFiling.setVisible(Selection.isFilled);
        staticPropertyFilled.setSelected(Selection.isFilled);
        staticColorOfFillingParam.setValue(Selection.colorOfFilling);
        staticPropertyFilled.setVisible(Selection.isFillingFigure);
        staticParameters.setVisible(true);
    }

    public static void hideParams() {
        staticParameters.setVisible(false);
    }

    public void onExit() {
        Platform.exit();
    }

    public void getSize() {
        size = (int) brushSize.getValue();
        brushSizeLabel.setText(String.valueOf(size));
        currentTool.setSize(size);
    }

    public void initialize() {
        getGraphCont();
        colorPicker.setValue(Color.BLACK);
        colorOfFillingPicker.setValue(Color.BLACK);
        setFillingColor();
        colorOfFillingPicker.setVisible(false);
        checkBox.setVisible(false);
        currentTool = new Pen(canvas);
        Figure.graphicsContext = canvas.getGraphicsContext2D();
        new EllipseTool(canvas);
        new RectTool(canvas);
        new LineTool(canvas);
        new Hand(canvas);
        new RoundRectTool(canvas);
        new Selection(canvas);
        new MoveFigure(canvas);
        drawToolBut();
        getSize();
        setColor();
        canvas.toBack();
        initparams();
        History.init();
    }

    public void initparams() {
        staticColorOfFillingParam = colorOfFillingParamPicker;
        staticBoxColorOfFiling = BoxColorOfFiling;
        staticParamColorOfStroke = paramColorOfStroke;
        staticPropertySize = PropertySize;
        staticPropertyFilled = PropertyFilled;
        staticPropertySize = PropertySize;
        staticSizeOfPropertyBrush = sizeOfPropertyBrush;
        staticParameters = Parameters;
        staticSliderScale = SliderScale;
        staticScaleSizeLabel = scaleSizeLabel;
    }

    public void onScrollScale() {
        scaleSize = SliderScale.getValue();
        graphicsContext.clearRect(0, 0, widht, height);
        repaintCanvas();
        scaleSizeLabel.setText(String.valueOf(Math.round(SliderScale.getValue())));
    }

    private void drawToolBut() {
        for (Tool tool : toolList) {
            tool.button.setOnMouseClicked((MouseEvent event) -> {
                currentTool = tool;
                currentTool.setSize(size);
                currentTool.setColorOfStroke(colorPicker.getValue());
                checkBox.setVisible(tool.isFillingTool);
                colorOfFillingPicker.setVisible(tool.isFillingTool & isFilling);
            });
            toolBar.getItems().add(tool.button);
        }
    }

    private void getGraphCont() {
        Figure.graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext = canvas.getGraphicsContext2D();
    }

    public void setColor() {
        currentTool.setColorOfStroke(colorPicker.getValue());
        canvas.getGraphicsContext2D().setFill(colorPicker.getValue());
        canvas.getGraphicsContext2D().setStroke(colorPicker.getValue());
        getSize();
    }

    public void canvasOnMousePressed(MouseEvent event) {
        currentTool.getOnMousePressed(event);
    }

    public void canvasOnMouseDragged(MouseEvent event) {
        currentTool.getOnMouseDragged(event);
    }

    public void canvasOnMouseReleased(MouseEvent event) {
        currentTool.getOnMouseReleased(event);
        if (!currentTool.button.getText().equals("Select"))
            History.rememberCondition();
    }

    public void saveFile(ActionEvent actionEvent) {
        File directory = new File("C:/");
        FileChooser directoryChooser = new FileChooser();
        directoryChooser.setInitialDirectory(directory);
        directoryChooser.setTitle("Select Folder");
        directoryChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Vector image (*.vi)", "*.vi"));
        directory = directoryChooser.showSaveDialog(Main.getStage());
        DataOutputStream outputStream = null;
        if (directory != null) {
            try {
                outputStream = new DataOutputStream(new FileOutputStream(directory));
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                outputStream.writeUTF(SaveAndLoad.getFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void openFile(ActionEvent actionEvent) {
        File directory = new File("C:/");
        FileChooser directoryChooser = new FileChooser();
        directoryChooser.setInitialDirectory(directory);
        directoryChooser.setTitle("Select Folder");
        directoryChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Vector image (*.vi)", "*.vi"));
        directory = directoryChooser.showOpenDialog(Main.getStage());
        DataInputStream inputStream = null;
        if (directory != null) {
            try {
                inputStream = new DataInputStream(new FileInputStream(directory));
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    inputStream.close();
                } catch (IOException ignored) {
                }
            }
            try {
                SaveAndLoad.loadFile(inputStream.readUTF(), false);
            } catch (EOFException ignored) {
            } catch (IOException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            } finally {
                try {
                    inputStream.close();
                } catch (IOException ignored) {
                }
            }
            graphicsContext.clearRect(0, 0, widht, height);
            repaintCanvas();
        }
    }

    public void setFigureFilling() {
        if (isFilling) {
            isFilling = false;
            colorOfFillingPicker.setVisible(false);
        } else {
            isFilling = true;
            colorOfFillingPicker.setVisible(true);
        }
        History.rememberCondition();
    }

    public void setFillingColor() {
        colorOfFilling = colorOfFillingPicker.getValue();
    }

    public void copy() {
        StringBuilder str = new StringBuilder();
        for (Figure figure : figures) {
            if (figure.isSelected) {
                str.append(figure.toString());
            }
        }
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(str.toString()), null);
    }

    public void paste() throws IOException, UnsupportedFlavorException {
        try {
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            DataFlavor data = DataFlavor.stringFlavor;
            if (clipboard.isDataFlavorAvailable(data)) {
                SaveAndLoad.loadFile(String.valueOf(clipboard.getData(data)), true);
                History.rememberCondition();
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException ignored) {
        }
    }

    public void cut() {
        StringBuilder str = new StringBuilder();
        for (Figure figure : figures) {
            if (figure.isSelected) {
                str.append(figure.toString());
            }
        }
        deleteSelectedShape();
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(str.toString()), null);
        History.rememberCondition();
    }

    public void deleteSelectedShape() {
        for (int i = 0; i <= figures.size() - 1; i++) {
            if (figures.get(i).isSelected) {
                figures.remove(i);
                i--;
            }
            graphicsContext.clearRect(0, 0, widht, height);
            repaintCanvas();
        }
        hideParams();
        History.rememberCondition();
    }

    public void setColorOfShape() {
        for (Figure figure : figures) {
            if (figure.isSelected)
                figure.colorOfStroke = paramColorOfStroke.getValue();
        }
        graphicsContext.clearRect(0, 0, widht, height);
        repaintCanvas();
        History.rememberCondition();
    }

    public void setSizeOfBrush() {
        PropertySize.setText(String.valueOf((int) sizeOfPropertyBrush.getValue()));
        for (Figure figure : figures) {
            if (figure.isSelected)
                figure.sizeOfBrush = sizeOfPropertyBrush.getValue();
            graphicsContext.clearRect(0, 0, widht, height);
            repaintCanvas();
        }
    }

    public void setParamFilling() {
        isFillingParam = !isFillingParam;
        BoxColorOfFiling.setVisible(isFillingParam);
        for (Figure figure : figures) {
            if (figure.isSelected & figure.isFillingFigure) {
                figure.isFilled = isFillingParam;
                figure.colorOfFilling = colorOfFillingParamPicker.getValue();
            }
        }
        graphicsContext.clearRect(0, 0, widht, height);
        repaintCanvas();
        History.rememberCondition();
    }

    public void setColorOfFillingParam() {
        for (Figure figure : figures) {
            if (figure.isSelected & figure.isFillingFigure) {
                figure.colorOfFilling = colorOfFillingParamPicker.getValue();
            }
            graphicsContext.clearRect(0, 0, widht, height);
            repaintCanvas();
        }
        History.rememberCondition();
    }

    public void undo() {
        History.undo();
    }

    public void redo() {
        History.redo();
    }

    public void getFinal() {
        History.rememberCondition();
    }

    public void upLayer() {
        Figure rememberedFigure;
        Figure movingFigure;
        int i = 1;
        List<Figure> selected = new ArrayList<>();
        for (Figure figure : figures) {
            if (figure.isSelected)
                selected.add(figure);
        }
        if (selected.size() != figures.size()) {
            if (!selected.get(selected.size() - 1).equals(figures.get(figures.size() - 1))) {
                rememberedFigure = figures.get(figures.indexOf(selected.get(selected.size() - 1)) + 1);
                movingFigure = selected.get(selected.size() - 1);
                figures.set(figures.indexOf(rememberedFigure), movingFigure);
                figures.set(figures.indexOf(movingFigure), rememberedFigure);
            }
            for (; i != selected.size(); i++) {
                rememberedFigure = figures.get(figures.indexOf(selected.get(selected.size() - i)) - 1);
                movingFigure = selected.get(i);
                figures.set(figures.indexOf(rememberedFigure), movingFigure);
                figures.set(figures.indexOf(movingFigure), rememberedFigure);
            }
            canvas.getGraphicsContext2D().clearRect(0, 0, widht, height);
            repaintCanvas();
            History.rememberCondition();
        }
    }

    public void DownLayer() {
        Figure rememberedFigure;
        Figure movingFigure;
        int i = 1;
        List<Figure> selected = new ArrayList<>();
        for (Figure figure : figures) {
            if (figure.isSelected)
                selected.add(figure);
        }
        if (selected.size() != figures.size()) {
            if (!selected.get(0).equals(figures.get(0))) {
                rememberedFigure = figures.get(figures.indexOf(selected.get(0)) - 1);
                movingFigure = selected.get(0);
                figures.set(figures.indexOf(movingFigure), rememberedFigure);
                figures.set(figures.indexOf(rememberedFigure), movingFigure);
            }
            for (; i != selected.size(); i++) {
                rememberedFigure = figures.get(figures.indexOf(selected.get(i)) - 1);
                movingFigure = selected.get(i);
                figures.set(figures.indexOf(movingFigure), rememberedFigure);
                figures.set(figures.indexOf(rememberedFigure), movingFigure);
            }
            canvas.getGraphicsContext2D().clearRect(0, 0, widht, height);
            repaintCanvas();
            History.rememberCondition();
        }
    }
}