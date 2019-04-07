package bsu.fpmi.education_practice;

import java.applet.Applet;
import java.awt.*;



public class TestApplet extends Applet{
    private Rectangle1 rectangle;

    @Override
    public void init() {
        rectangle = new Rectangle1(500, 500);
    }

    @Override
    public void paint(Graphics g) {
        setBackground(Color.WHITE);
        rectangle.paint(g);
    }

}

