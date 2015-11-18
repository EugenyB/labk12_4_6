package model;

import java.util.Arrays;
import java.util.List;

/**
 * Created by eugeny on 12.11.2015.
 */
public class Polygon2D {
    private List<Point2D> points;

    public Polygon2D(List<Point2D> points) {
        this.points = points;
    }

    public Polygon2D(Point2D... points) {
        this(Arrays.asList(points));
    }

    public List<Point2D> getPoints() {
        return points;
    }

    public int size() {
        return points.size();
    }

    public double[] xs() {
        double[] x = new double[points.size()];
        for (int i = 0; i < points.size(); i++) {
            x[i] = points.get(i).getX();
        }
        return x;
    }

    public double[] ys() {
        double[] y = new double[points.size()];
        for (int i = 0; i < points.size(); i++) {
            y[i] = points.get(i).getY();
        }
        return y;
    }
}
