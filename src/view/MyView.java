package view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.*;

import java.util.List;

/**
 * Created by eugeny on 12.11.2015.
 */
public class MyView {

    private double f = 500;

    private double[][] m = {
            {1,0,0,0},{0,1,0,0},{0,0,1,0},{0,0,0,1}
    };

    public double xs(Point3D point) {
        return point.getX()*f/point.getZ();
    }

    public double ys(Point3D point) {
        return point.getY()*f/point.getZ();
    }

    public void drawFigure(Figure figure, GraphicsContext gc) {
        gc.setStroke(Color.BLUE);
        for (int i = 0; i < figure.facesCount(); i++) {
            Polygon3D face = figure.getFace(i);
            if (visible(face)) {
                gc.setFill(figure.getColor(i));
                Polygon2D face2D = makeProjection(face);
                gc.fillPolygon(face2D.xs(), face2D.ys(), face2D.size());
                gc.strokePolygon(face2D.xs(), face2D.ys(), face2D.size());
            }
        }
    }

    private boolean visible(Polygon3D face) {
        Point3D p0 = transform(face.getPoints().get(0));
        Point3D p1 = transform(face.getPoints().get(1));
        Point3D p2 = transform(face.getPoints().get(2));
        double xv = -p0.getX(), yv = -p0.getY(), zv = -p0.getZ(); // v

        double xa = p1.getX()-p0.getX();
        double ya = p1.getY()-p0.getY();
        double za = p1.getZ()-p0.getZ();
        double xb = p2.getX()-p0.getX();
        double yb = p2.getY()-p0.getY();
        double zb = p2.getZ()-p0.getZ();

        double xn = ya*zb-yb*za;
        double yn = za*xb-zb*xa;
        double zn = xa*yb-xb*ya;

        return xv * xn + yv * yn + zv * zn > 0;
    }

    private Point3D transform(Point3D point) {
        double x = point.getX()*m[0][0] + point.getY()*m[0][1] + point.getZ()*m[0][2] + m[0][3];
        double y = point.getX()*m[1][0] + point.getY()*m[1][1] + point.getZ()*m[1][2] + m[1][3];
        double z = point.getX()*m[2][0] + point.getY()*m[2][1] + point.getZ()*m[2][2] + m[2][3];
        return new Point3D(x,y,z);
    }

    private Point2D projection(Point3D point) {
        return new Point2D(xs(point), ys(point));
    }

    private Polygon2D makeProjection(Polygon3D face) {
        Point2D[] points = new Point2D[face.getPoints().size()];
        List<Point3D> points3D = face.getPoints(); // 1. Извлечь точки

        for (int i = 0; i < points3D.size(); i++) { // 2. Аффинное преобразование
            Point3D p3 = transform(points3D.get(i));
            points3D.set(i,p3);
        }

        for (int i = 0; i < points.length; i++) {    // 3. Построение проекции точек
            points[i] = projection(points3D.get(i));
        }

        return new Polygon2D(points);
    }

    public void move(double dx, double dy, double dz) {
        double[][] t = {
                {1,0,0,dx},{0,1,0,dy},{0,0,1,dz},{0,0,0,1}
        };
        m = multiply(t,m);
    }

    public void rotateZ(double alpha) {
        alpha = Math.toRadians(alpha);
        double sin = Math.sin(alpha);
        double cos = Math.cos(alpha);
        double[][] t = {
                {cos,-sin,0,0},
                {sin,cos,0,0},
                {0,0,1,0},
                {0,0,0,1}
        };
        move(-300,-300,-700);
        m = multiply(t,m);
        move(300,300,700);
    }

    private double[][] multiply(double[][] t, double[][] m) {
        double[][] result = new double[4][4];
        for (int i=0; i<4; i++) {
            for (int j=0; j<4; j++) {
                for (int k = 0; k<4; k++) {
                    result[i][j] += t[i][k] * m[k][j];
                }
            }
        }
        return result;
    }

    public void rotateX(double alpha) {
        alpha = Math.toRadians(alpha);
        double sin = Math.sin(alpha);
        double cos = Math.cos(alpha);
        double[][] t = {
                {1,  0,   0,0},
                {0,cos,-sin,0},
                {0,sin, cos,0},
                {0,  0,   0,1}
        };
        move(-300,-300,-700);
        m = multiply(t,m);
        move(300,300,700);
    }

    public void rotateY(double alpha) {
        alpha = Math.toRadians(alpha);
        double sin = Math.sin(alpha);
        double cos = Math.cos(alpha);
        double[][] t = {
                {cos, 0, sin, 0},
                {0,   1,   0, 0},
                {-sin,0, cos, 0},
                {0,   0,   0, 1}
        };
        move(-300,-300,-700);
        m = multiply(t,m);
        move(300,300,700);
    }
}
