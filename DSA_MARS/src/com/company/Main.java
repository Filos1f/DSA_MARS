package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static Pixel[][] buildPixelsMatrix() throws IOException {
        File inputImage=new File("Screenshot.jpg");
        BufferedImage img = ImageIO.read(inputImage);;
        BufferedImage one = new BufferedImage(img.getWidth(),img.getHeight(),BufferedImage.TYPE_3BYTE_BGR);
        final int h = img.getHeight();//rows foo.length
        final int w = img.getWidth();//columns foo[0].length
        Pixel[][] matrix = new Pixel[img.getHeight()][img.getWidth()];
        for(int i = 0; i < h; ++i){
            for(int j = 0; j < w; ++j){
                final Color c = new Color(img.getRGB(j,i));//getRGB(x,y) on a picture
                matrix[i][j] = new Pixel(c.getRed(), c.getGreen(), c.getBlue(), j, i);;//matrix[y][x]
            }
        }
        return matrix;
    }

    public static void main(String[] args) {
        try {
            final double radiationConst = Math.pow(10, 5);

            Pixel[][] matrix = buildPixelsMatrix();
            int X=matrix.length;
            int Y=matrix[0].length;
            ArrayList<Pixel> vertexes = new ArrayList<Pixel>();
            ArrayList<Edge<Double> > edges = new ArrayList<Edge<Double> >();
            for(int i = 0; i < X; ++i){
                for(int j = 0; j < Y; ++j){
                    System.out.println(matrix[i][j].coordX+"||"+matrix[i][j].coordY);
                    if(!matrix[i][j].isWhite())
                        continue;

                    double radiationE = 0.0;

                   for(int idx = 0; idx < X; ++idx) {
                        for(int jdy = 0; jdy < Y; ++jdy) {
                            if(matrix[idx][jdy].isWhite() || matrix [idx][jdy].isBlack())
                                continue;
                            final double catet1 = j - jdy;
                            final double catet2 = i - idx;
                            final double x = Math.sqrt(catet1 * catet1 + catet2 * catet2);
                            if(x == 0.0) continue;
                            radiationE += radiationConst / (x * x);
                        }
                    }
                    vertexes.add(matrix[i][j]);

                    if(i != 0) {
                        if (vertexes.contains(matrix[i - 1][j])) {
                            edges.add(new Edge<Double>(radiationE, matrix[i- 1][j], matrix[i][j]));

                        }
                        if(i<X-1&&j<Y-1&&j!=0) {
                            if (vertexes.contains(matrix[i - 1][j + 1])) {
                                edges.add(new Edge<Double>(radiationE, matrix[i + 1][j - 1], matrix[i][j]));
                            }
                        }
                        if(j != 0) {
                            if (vertexes.contains(matrix[i - 1][j - 1])) {
                                edges.add(new Edge<Double>(radiationE, matrix[i - 1][j - 1], matrix[i][j]));
                            }
                        }
                    }
                    //------------------------------------------
                    if(j != 0) {

                        if (vertexes.contains(matrix[i][j - 1])) {
                            edges.add(new Edge<Double>(radiationE, matrix[i][j - 1], matrix[i][j]));
                        }
                    }
                }
            }
           // System.out.println(vertexes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
