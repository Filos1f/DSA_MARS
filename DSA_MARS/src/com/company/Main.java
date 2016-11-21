package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static Pixel[][] buildPixelsMatrix() throws IOException {
        File inputImage=new File("map.png");
        BufferedImage img = ImageIO.read(inputImage);;
        BufferedImage one = new BufferedImage(img.getWidth(),img.getHeight(),BufferedImage.TYPE_3BYTE_BGR);
        final int h = img.getHeight();
        final int w = img.getWidth();
        Pixel[][] matrix = new Pixel[img.getHeight()][img.getWidth()];
        for(int i = 0; i < h; ++i){
            for(int j = 0; j < w; ++j){
                final Color c = new Color(img.getRGB(j, i));
                matrix[i][j] = new Pixel(c.getRed(), c.getGreen(), c.getBlue(), j, i);;
            }
        }
        return matrix;
    }

    public static void main(String[] args) {
        try {
            int count=0;
            FileWriter Radiation = new FileWriter("RadiationMapNew.txt");
            int MaxE=0;
            int MinE=(int)10e8;
            final double radiationConst = Math.pow(10, 5);
            Color t = new Color(173, 203, 21);
            Pixel[][] matrix = buildPixelsMatrix();
            BufferedImage result = new BufferedImage(matrix.length,matrix[0].length,BufferedImage.TYPE_3BYTE_BGR);
            ArrayList<Pixel> vertexes = new ArrayList<Pixel>();
            ArrayList<Edge<Double> > edges = new ArrayList<Edge<Double> >();
            for(int i = 0; i < matrix.length; ++i){
                for(int j = 0; j < matrix[i].length; ++j) {
                    if (!matrix[i][j].isWhite())
                    {
                        Radiation.write(0.0 + " ");
                        count++;
                        continue;
                    }

                    double radiationE = 0.0;
                    for (int idx = 0; idx < matrix.length; ++idx) {
                        for (int jdx = 0; jdx < matrix[idx].length; ++jdx) {
                            if (matrix[idx][jdx].isWhite() || matrix[idx][jdx].isBlack())
                                continue;
                            final double catet1 = j - jdx;
                            final double catet2 = i - idx;
                            final double x = Math.sqrt(catet1 * catet1 + catet2 * catet2);
                            if (x == 0.0) continue;
                            radiationE += radiationConst / (x * x);
                        }
                    }
                    if(j==0){Radiation.write("\n");}
                    Radiation.write(radiationE+" ");count++;
                }
            }
            Radiation.close();
            System.out.println(MaxE+"<-----"+count+"-------->"+MinE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
