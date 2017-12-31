package sample;

import Figures.Figure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Condition {
    public Condition() {
    }

    private List<Figure> figures;
    double xOffSet;
    double yOffset;
    double scaleSize;

    public Condition(List<Figure> figures, double xOffSet, double yOffset, double scaleSize) {
        this.figures= new ArrayList<>(figures);
        Collections.copy(this.figures,figures);
        this.xOffSet = xOffSet;
        this.yOffset = yOffset;
        this.scaleSize = scaleSize;
    }

    public List<Figure> getFigures() {
        return figures;
    }
}
