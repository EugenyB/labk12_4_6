package model;

import java.util.Arrays;
import java.util.List;

/**
 * Created by eugeny on 12.11.2015.
 */
public class Polygon3D {
    private List<Point3D> points;

    public Polygon3D(List<Point3D> points) {
        this.points = points;
    }

    public Polygon3D(Point3D... points) {
        this(Arrays.asList(points));
    }

    public List<Point3D> getPoints() {
        return points;
    }
}
