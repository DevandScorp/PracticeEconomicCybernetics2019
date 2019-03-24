package lab6;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.Serializable;

public class Cardioid implements Shape, Serializable, Cloneable, Transferable {
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
        return new Rectangle(centerX - 4*a, centerY -3*a, 5 * a, 6 * a);
    }
    @Override
    public Rectangle2D getBounds2D() {
        return new Rectangle2D.Double(centerX - 4*a, centerY -3*a, 5 * a, 6 * a);
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
        return getBounds().intersects(x, y, w, h);
    }
    @Override
    public boolean intersects(Rectangle2D r) {
        return getBounds().intersects(r.getX(), r.getY(), r.getWidth(), r.getHeight());
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
        ShapeIterator() { }
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
                coordinate[0] =  (2 * a * Math.cos(t) - a * Math.cos(2 * t)) + centerX;
                coordinate[1] =  (2 * a * Math.sin(t) - a * Math.sin(2 * t)) + centerY;
                start = false;
                return SEG_MOVETO;
            }
            if (t >= Math.PI * 2) {
                done = true;
                return SEG_CLOSE;
            }
            coordinate[0] =  (2 * a * Math.cos(t) - a * Math.cos(2 * t)) + centerX;
            coordinate[1] =  (2 * a * Math.sin(t) - a * Math.sin(2 * t)) + centerY;
            return SEG_LINETO;
        }
    }



    static DataFlavor decDataFlavor = new DataFlavor(Cardioid.class, "Cardioid");

    private static DataFlavor[] supportedFlavors = {decDataFlavor, DataFlavor.stringFlavor};


    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return supportedFlavors.clone();
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor dataFlavor) {
        return (dataFlavor.equals(decDataFlavor) || dataFlavor.equals(DataFlavor.stringFlavor));
    }

    @Override
    public Object getTransferData(DataFlavor dataFlavor) throws UnsupportedFlavorException, IOException {
        if (dataFlavor.equals(decDataFlavor)) {
            return this;
        } else if (dataFlavor.equals(DataFlavor.stringFlavor)) {
            return toString();
        } else
            throw new UnsupportedFlavorException(dataFlavor);
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) { // This should never happen
            return this;
        }
    }


    @Override
    public String toString() {
        return a + " " + centerX + " " + centerY ;
    }


    static Cardioid getFromString(String line) {
        String[] arr = line.split(" ");
        return new Cardioid(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]),
                Integer.parseInt(arr[2]));
    }

    void translate(int x, int y) {
        centerX += x;
        centerY += y;
    }
}
