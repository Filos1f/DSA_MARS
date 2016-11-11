package com;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

	// write your code here
        pixel[][] matrix;
        int h,w;
        File inputImage=new File("E:\\Image\\map.png");
        BufferedImage img=null;
        try {
            img= ImageIO.read(inputImage);
            System.out.print(img.getHeight()+img.getWidth());
            BufferedImage one=new BufferedImage(img.getWidth(),img.getHeight(),BufferedImage.TYPE_3BYTE_BGR);
            h=img.getHeight();
            w=img.getWidth();
              matrix=new pixel[img.getHeight()][img.getWidth()];

            for(int i=0;i<h;i++){
                for(int j=0;j<w;j++){
                     Color c=new Color(img.getRGB(i,j));
                     pixel temp = new pixel(c.getRed(),c.getGreen(),c.getBlue());
                     matrix[i][j]=temp;
                        //temp.printPixel();


                }
            }



            List<vertex> vertexes=new ArrayList<>();

            List<edge> edges=new ArrayList<>();


            for(int i=0;i<h;i++){
                for(int j=0;j<w;j++){//if color is white
                    if(matrix[i][j].r==255&&matrix[i][j].g==255&&matrix[i][j].b==255){
                        vertexes.add(new vertex(i+""+j));
                    }
                }
            }



        } catch (IOException e) {
            e.printStackTrace();

        }






    }
}
