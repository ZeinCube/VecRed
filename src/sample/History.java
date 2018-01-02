package sample;

import Figures.Figure;
import Tools.Selection;
import Tools.Tool;

import java.util.*;

public class History {

    private static List<Condition> history = new ArrayList<>();
    private static int currentCondition = 0;

    public static void rememberCondition(){
        if(currentCondition!=history.size()-1) {
            while(history.size()>currentCondition+1){
                history.remove(history.size()-1);
            }
        }
        history.add(new Condition(Controller.figures, Figure.xOffSet, Figure.yOffSet, Controller.scaleSize));
        currentCondition = history.size()-1;
    }

    public static void undo(){
        if(currentCondition>0) {
            currentCondition--;
            applyCondition();
        }
    }

    static void init(){
        history.add(new Condition(Controller.figures, Figure.xOffSet, Figure.yOffSet, Controller.scaleSize));
    }

    private static void applyCondition(){
        Condition condition = history.get(currentCondition);
        Controller.figures = new ArrayList<>();
        Controller.figures.addAll(condition.getFigures());
        Figure.setOffSet(condition.xOffSet, condition.yOffset);
        Controller.scaleSize = condition.scaleSize;
        Tool.canvas.getGraphicsContext2D().clearRect(0, 0, Controller.widht, Controller.height);
        Controller.staticSliderScale.setValue(condition.scaleSize);
        Controller.staticScaleSizeLabel.setText(String.valueOf((int)condition.scaleSize));
        for (Figure figure : Controller.figures)
            figure.isSelected = false;
        Selection.movingTool.setDisable(true);
        Controller.hideParams();
        Controller.repaintCanvas();
    }

    public static void redo(){
        if(currentCondition<history.size()-1) {
            currentCondition++;
            applyCondition();
        }
    }
}
