package bsu.fpmi.education_practice;

import java.awt.*;

public class Rectangle1 extends Canvas {
    private int width;
    private int height;

    private int x = 10;
    private int y = 10;


    public Rectangle1(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setWidth(int width){
        this.width=width;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    public void setHeight(int Height){
        this.height=Height;
    }

    public Rectangle1() {
        width = 100;
        height = 50;
    }

    public void setStartPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor (Color.gray);
        g.fill3DRect (100, 100, 200, 100, true);
    }
}