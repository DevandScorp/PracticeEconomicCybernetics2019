package Graphic;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;


public class Main extends JComponent implements ActionListener {

    private Color borderColor = Color.gray;
    private Color backgroundColor = Color.lightGray;
    private RectangleAffine rectangleAffine;

    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private double theta = 0;
    private static final float BORDER = 4;

    private Timer timer;

    private final double ROTATE_ANGLE = Math.PI / 180;

    private Main(int delay) {
        timer = new Timer(delay, this);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    private void start() {
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setStroke(new BasicStroke(BORDER));
        rectangleAffine = new RectangleAffine(
                WIDTH / 2 - 200,
                HEIGHT / 2 - 100,
                200,
                100,
                theta,
                borderColor,
                backgroundColor);
        rectangleAffine.paint(graphics2D);
        theta += ROTATE_ANGLE;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Rectangle");
            JPanel panel = new JPanel();
            Main movingRectangle = new Main(20);
            panel.add(movingRectangle);
            frame.getContentPane().add(panel);
            movingRectangle.start();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(WIDTH, HEIGHT);
            frame.setVisible(true);
        });
    }

}
