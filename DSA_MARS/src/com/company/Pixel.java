package com.company;

/**
 * Created by Pavel on 13.11.2016.
 */
public class Pixel {

    public Pixel( int r,int g, int b, int x, int y) {
        this.red = r;
        this.green = g;
        this.blue = b;
        this.coordX = x;
        this.coordY = y;
    }

    private int red;
    private int green;
    private int blue;

    private int coordX;
    private int coordY;

    public boolean isWhite () {
        return red == 255 && green == 255 && blue == 255;
    }

    public boolean isBlack () {
        return red == 0 && green == 0 && blue == 0;
    }

    public void printPixel(){
        System.out.println(this.red+"|"+this.green+"|"+this.blue);
    }
}
