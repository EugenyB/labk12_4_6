package model;

import javafx.scene.paint.Color;

/**
 * Created by eugeny on 12.11.2015.
 */
public class Figure {
    double[] x ={200, 400, 400, 200, 200, 400, 400, 200};
    double[] y ={400, 400, 200, 200, 400, 400, 200, 200};
    double[] z ={600, 600, 600, 600, 800, 800, 800, 800};

    int[][] p ={
            {0, 1, 2, 3},
            {1, 5, 6, 2},
            {3, 2, 6, 7},
            {0, 4, 5, 1},
            {0, 3, 7, 4},
            {4, 7, 6, 5}
    };

    Color[] colors = {
            Color.RED,
            Color.LIGHTBLUE,
            Color.YELLOW,
            Color.GREEN,
            Color.ORANGE,
            Color.WHITE
    };

    public Color getColor(int i) {
        return colors[i];
    }

    public int facesCount() {
        return p.length;
    }

    public Polygon3D getFace(int i) {
        int n = p[i].length;
        Point3D[] points = new Point3D[n];
        for (int j = 0; j < n; j++) {
            points[j] = new Point3D(x[p[i][j]], y[p[i][j]], z[p[i][j]]);
        }
        return new Polygon3D(points);
    }

}
