package com;

/**
 * Created by Sultan on 11.11.2016.
 */
public class pixel {
    public pixel( int r,int g, int b) {

        this.r = r;
        this.g = g;
        this.b = b;
    }

    int r,g,b;

    public void printPixel(){
        System.out.println(this.r+"|"+this.g+"|"+this.b);
    }
}
