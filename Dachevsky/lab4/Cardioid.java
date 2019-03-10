package lab4;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Cardioid implements Shape {
    private int centerX;
    private int centerY;
    private int a;

    public Cardioid(int centerX, int centerY, int param) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.a = param;
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }

    @Override
    public Rectangle2D getBounds2D() {
        return null;
    }

    @Override
    public boolean contains(double x, double y) {
        return false;
    }

    @Override
    public boolean contains(Point2D p) {
        return false;
    }

    @Override
    public boolean intersects(double x, double y, double w, double h) {
        return false;
    }

    @Override
    public boolean intersects(Rectangle2D r) {
        return false;
    }

    @Override
    public boolean contains(double x, double y, double w, double h) {
        return false;
    }

    @Override
    public boolean contains(Rectangle2D r) {
        return false;
    }

    @Override
    public PathIterator getPathIterator(AffineTransform at) {
        return new ShapeIterator();
    }

    @Override
    public PathIterator getPathIterator(AffineTransform at, double flatness) {
        return new ShapeIterator();
    }

    class ShapeIterator implements PathIterator {
        boolean done = false;
        double h = Math.PI / 500;
        boolean start = true;
        double t = 0;

        ShapeIterator() {
        }

        @Override
        public int getWindingRule() {
            return 0;
        }

        @Override
        public boolean isDone() {
            return done;
        }

        @Override
        public void next() {
            t += h;
        }

        @Override
        public int currentSegment(float[] coordinate) {
            if (start) {
                coordinate[0] = (float) (2 * a * Math.cos(t) - a * Math.cos(2 * t)) + centerX;
                coordinate[1] = (float) (2 * a * Math.sin(t) - a * Math.sin(2 * t)) + centerY;
                start = false;
                return SEG_MOVETO;
            }
            if (t >= Math.PI * 2) {
                done = true;
                return SEG_CLOSE;
            }
            coordinate[0] = (float) (2 * a * Math.cos(t) - a * Math.cos(2 * t)) + centerX;
            coordinate[1] = (float) (2 * a * Math.sin(t) - a * Math.sin(2 * t)) + centerY;
            return SEG_LINETO;
        }

        @Override
        public int currentSegment(double[] coordinate) {
            if (start) {
                coordinate[0] = (2 * a * Math.cos(t) - a * Math.cos(2 * t)) + centerX;
                coordinate[1] = (2 * a * Math.sin(t) - a * Math.sin(2 * t)) + centerY;
                start = false;
                return SEG_MOVETO;
            }
            if (t >= Math.PI * 2) {
                done = true;
                return SEG_CLOSE;
            }
            coordinate[0] = (2 * a * Math.cos(t) - a * Math.cos(2 * t)) + centerX;
            coordinate[1] = (2 * a * Math.sin(t) - a * Math.sin(2 * t)) + centerY;
            return SEG_LINETO;
        }
    }
}