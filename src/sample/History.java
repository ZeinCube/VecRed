package sample;

import Figures.Figure;
import Tools.Tool;

import java.util.*;

public class History {

    public static List<Condition> history = new ArrayList<>();
    private static int currentCondition = 0;

    public static void rememberCondition(){
        if(currentCondition!=history.size()-1) {
            for (int i = history.size() - currentCondition; i > 0; i--) {
                history.remove(history.size() - i);
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

    public static void init(){
        history.add(new Condition(Controller.figures, Figure.xOffSet, Figure.yOffSet, Controller.scaleSize));
    }

    public static void applyCondition(){
        Condition condition = history.get(currentCondition);
        Controller.figures = condition.getFigures();
        Figure.setOffSet(condition.xOffSet, condition.yOffset);
        Controller.scaleSize = condition.scaleSize;
        Tool.canvas.getGraphicsContext2D().clearRect(0, 0, Controller.widht, Controller.height);
        Controller.repaintCanvas();
    }

    public static void redo(){
        if(currentCondition<history.size()-1) {
            currentCondition++;
            applyCondition();
        }
    }
}
