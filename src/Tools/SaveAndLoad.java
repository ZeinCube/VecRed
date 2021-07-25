package Tools;

import Figures.*;
import sample.Controller;

public class SaveAndLoad {
    public static String getFile() {
        StringBuilder res = new StringBuilder();
        for (Figure figure : Controller.figures) {
            res.append(figure.toString());
        }
        return res.toString();
    }

    public static void loadFile(String string, boolean add) throws IllegalAccessException, InstantiationException {
        String figure;
        if (!add) {
            Controller.figures.clear();
        }
        StringBuilder parameters = new StringBuilder();
        char[] chars = string.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        int index = 0;
        for (; index < chars.length; index++) {
            if (chars[index] != ' ') {
                stringBuilder.append(chars[index]);
            } else {
                index++;
                while (chars[index] != '/') {
                    parameters.append(chars[index]);
                    index++;
                }
                figure = stringBuilder.toString();
                stringBuilder = new StringBuilder();
                switch (figure) {
                    case "Ellipse":
                        Controller.figures.add(Ellipse.valueOf(parameters.toString()));
                        parameters = new StringBuilder();
                        break;
                    case "Line":
                        Controller.figures.add(Line.valueOf(parameters.toString()));
                        parameters = new StringBuilder();
                        break;
                    case "PolyLine":
                        Controller.figures.add(PolyLine.valueOf(parameters.toString()));
                        parameters = new StringBuilder();
                        break;
                    case "Rect":
                        Controller.figures.add(Rect.valueOf(parameters.toString()));
                        parameters = new StringBuilder();
                        break;
                    case "RoundR":
                        Controller.figures.add(RoundRect.valueOf(parameters.toString()));
                        parameters = new StringBuilder();
                        break;
                }
            }
        }
        Controller.repaintCanvas();
    }
}






