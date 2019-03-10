package lab4;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        int width = 900;
        int height = 900;
        frame.setSize(width, height);
        final JPanel panel = new JPanel() {
            public void paint(Graphics g) {
                setSize(900, 900);
                int centerX = getWidth() / 2 + 35;
                int centerY = getHeight() / 2;
                Graphics2D g2D = (Graphics2D) g;
                g2D.setColor(Color.WHITE);
                g2D.fillRect(0, 0, getWidth(), getHeight());
                g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2D.setStroke(new BasicStroke(3));

                g2D.setColor(new Color(0, 0 , 0));
                g2D.setStroke(new CardioidStroke(3));
                int param = 70;
                g2D.draw(new Cardioid(centerX, centerY, param));
            }
        };
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
