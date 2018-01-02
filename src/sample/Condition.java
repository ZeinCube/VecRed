package sample;

import Figures.Figure;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Condition {

    private Set<Figure> figures;
    double xOffSet;
    double yOffset;
    double scaleSize;

    public Condition(List<Figure> figures, double xOffSet, double yOffset, double scaleSize) {
        this.figures = null;
        this.figures= new HashSet<>();
        this.figures.addAll(figures);
        this.xOffSet = xOffSet;
        this.yOffset = yOffset;
        this.scaleSize = scaleSize;
    }

    public Set<Figure> getFigures() {
        return figures;
    }
}
