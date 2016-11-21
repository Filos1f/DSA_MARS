package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class Experiment {

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
    public static int[] RGB(int rad){//23947 min <-----> 2328813 max
        int[] rgb=new int[3];
        if(rad<100000){rgb[0]=0;rgb[1]=255;rgb[2]=255;}
        else if(rad>=100000&&rad<300000){rgb[0]=0;rgb[1]=255;rgb[2]=0;}
        else if(rad>=300000&&rad<500000){rgb[0]=191;rgb[1]=255;rgb[2]=0;}
        else if(rad>=500000&&rad<800000){rgb[0]=255;rgb[1]=150;rgb[2]=0;}
        else if(rad>=800000&&rad<1000000){rgb[0]=255;rgb[1]=128;rgb[2]=0;}

        else if(rad>=1000000&&rad<1400000){rgb[0]=255;rgb[1]=0;rgb[2]=0;}

        else if(rad>=1400000&&rad<1700000){rgb[0]=0;rgb[1]=128;rgb[2]=255;}
        else if(rad>=1700000&&rad<1900000){rgb[0]=0;rgb[1]=64;rgb[2]=255;}
        else if(rad>=1900000&&rad<2000000){rgb[0]=0;rgb[1]=0;rgb[2]=255;}
        else if(rad>=2000000&&rad<2100000){rgb[0]=255;rgb[1]=255;rgb[2]=0;}
        else if(rad>=2100000&&rad<2150000){rgb[0]=255;rgb[1]=191;rgb[2]=0;}
        else if(rad>=2150000&&rad<2200000){rgb[0]=255;rgb[1]=128;rgb[2]=0;}
        else if(rad>=2200000&&rad<2300000){rgb[0]=255;rgb[1]=64;rgb[2]=0;}
        else if(rad>=2300000){rgb[0]=255;rgb[1]=0;rgb[2]=0;}

        return rgb;
    }

    public static void main(String[] args) {
        try {

            int[] f0,f1;
            Pixel[][] matrix = buildPixelsMatrix();
            BufferedImage result = new BufferedImage(matrix.length,matrix[0].length,BufferedImage.TYPE_3BYTE_BGR);
            ArrayList<Pixel> vertexes = new ArrayList<Pixel>();
            ArrayList<Edge<Integer>> graph = new ArrayList<Edge<Integer> >();

            File file = new File("RadiationMapNew.txt");
            FileReader reader = new FileReader(file);
            char[] buffer = new char[(int)file.length()];
            reader.read(buffer);
            String s = new String(buffer);
            String[] radiations = s.split(" ");
            int[][] Rad=new int[700][700];
            int MaxRad=0;
            int t=0;
            for(int g=0;g<700;g++){
                for(int r=0;r<700;r++){
                    Rad[g][r]=(int) Double.parseDouble(radiations[t]);
                    if(Rad[g][r]>MaxRad){MaxRad=Rad[g][r];}
                t++;
                }
            }
            System.out.print(MaxRad);
            int l = radiations.length;
            double d = Double.parseDouble(radiations[radiations.length-1]);
            System.out.print(" ");
    int r1,r2,r3,r4,r5,r6,r7,r8,r0;
            int c=0,c1=0;
            for(int i=1;i<699;i+=2)
            {
                for(int j=1;j<699;j+=2){
                    r0=Rad[i][j];
                    r1=Rad[i-1][j-1];
                    r2=Rad[i-1][j];
                    r3=Rad[i-1][j+1];
                    r4=Rad[i][j-1];
                    r5=Rad[i][j+1];
                    r6=Rad[i+1][j-1];
                    r7=Rad[i+1][j];
                    r8=Rad[i+1][j+1];
                    if(r1>0){
                        graph.add(new Edge<Integer>(r1,matrix[i-1][j-1],matrix[i][j]));
                        f0=RGB(r0);
                        f1=RGB(r1);
                        result.setRGB(j, i, new Color(f0[0], f0[1], f0[2]).getRGB());
                        result.setRGB(j-1, i-1, new Color(f1[0], f1[1], f1[2]).getRGB());
                    }
                    if(r2>0){
                        graph.add(new Edge<Integer>(r1,matrix[i-1][j],matrix[i][j]));
                        f0=RGB(r0);
                        f1=RGB(r2);
                        result.setRGB(j, i, new Color(f0[0], f0[1], f0[2]).getRGB());
                        result.setRGB(j, i-1, new Color(f1[0], f1[1], f1[2]).getRGB());
                    }
                    if(r3>0){
                        graph.add(new Edge<Integer>(r1,matrix[i-1][j+1],matrix[i][j]));
                        f0=RGB(r0);
                        f1=RGB(r3);
                        result.setRGB(j, i, new Color(f0[0], f0[1], f0[2]).getRGB());
                        result.setRGB(j+1, i-1, new Color(f1[0], f1[1], f1[2]).getRGB());
                    }
                    if(r4>0){
                        graph.add(new Edge<Integer>(r1,matrix[i][j-1],matrix[i][j]));
                        f0=RGB(r0);
                        f1=RGB(r4);
                        result.setRGB(j, i, new Color(f0[0], f0[1], f0[2]).getRGB());
                        result.setRGB(j-1, i, new Color(f1[0], f1[1], f1[2]).getRGB());
                    }
                    if(r5>0){
                        graph.add(new Edge<Integer>(r1,matrix[i][j+1],matrix[i][j]));
                        f0=RGB(r0);
                        f1=RGB(r5);
                        result.setRGB(j, i, new Color(f0[0], f0[1], f0[2]).getRGB());
                        result.setRGB(j+1, i, new Color(f1[0], f1[1], f1[2]).getRGB());
                    }
                    if(r6>0){
                        graph.add(new Edge<Integer>(r1,matrix[i+1][j-1],matrix[i][j]));
                        f0=RGB(r0);
                        f1=RGB(r6);
                        result.setRGB(j, i, new Color(f0[0], f0[1], f0[2]).getRGB());
                        result.setRGB(j-1, i+1, new Color(f1[0], f1[1], f1[2]).getRGB());
                    }
                    if(r7>0){
                        graph.add(new Edge<Integer>(r1,matrix[i+1][j],matrix[i][j]));
                        f0=RGB(r0);
                        f1=RGB(r7);
                        result.setRGB(j, i, new Color(f0[0], f0[1], f0[2]).getRGB());
                        result.setRGB(j, i+1, new Color(f1[0], f1[1], f1[2]).getRGB());
                    }
                    if(r8>0){
                        graph.add(new Edge<Integer>(r1,matrix[i+1][j+1],matrix[i][j]));
                        f0=RGB(r0);
                        f1=RGB(r8);
                        result.setRGB(j, i, new Color(f0[0], f0[1], f0[2]).getRGB());
                        result.setRGB(j+1, i+1, new Color(f1[0], f1[1], f1[2]).getRGB());
                    }


                        }
                }
            ImageIO.write(result, "png", new File("graph.png"));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}