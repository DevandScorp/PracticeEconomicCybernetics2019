package lab3;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        int width = 600;
        int height = 600;
        frame.setSize(width, height);
        final JPanel panel = new JPanel() {
            public void paint(Graphics g) {

                int centerX = getWidth() / 2;
                int centerY = getHeight() / 2;
                Graphics2D g2 = (Graphics2D) g;
                g2.setColor(Color.LIGHT_GRAY);
                g2.fillRect(0, 0, getWidth(), getHeight());

                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setStroke(new BasicStroke(4));
                g2.setColor(Color.gray);
                g2.setColor(Color.RED);
                g2.setStroke(new MStoke(2));
                int param = 250;
                g2.draw(new Cardioid(centerX, centerY, param));
            }
        };
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
