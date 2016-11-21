package com.company.Dijkstra;

/**
 * Created by Sultan on 18.11.2016.
 */
        import java.awt.*;
        import java.awt.image.BufferedImage;
        import java.io.File;
        import java.io.FileReader;
        import java.io.IOException;
        import java.util.ArrayList;
        import java.util.LinkedList;
        import java.util.List;

        import com.company.Pixel;
        import org.junit.Test;

        import javax.imageio.ImageIO;

        import static org.junit.Assert.assertNotNull;
        import static org.junit.Assert.assertTrue;

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
            ArrayList<Vertex> vertexNew=new ArrayList<>();
            ArrayList<Edge> EdgesNew=new ArrayList<>();

            int[] f0,f1;
            Pixel[][] matrix = buildPixelsMatrix();
            BufferedImage result = new BufferedImage(matrix.length,matrix[0].length,BufferedImage.TYPE_3BYTE_BGR);
            ArrayList<Pixel> vertexes = new ArrayList<Pixel>();
            ArrayList<com.company.Edge<Integer>> graph = new ArrayList<com.company.Edge<Integer>>();

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
            System.out.println(" ");
            int r1,r2,r3,r4,r5,r6,r7,r8,r0,r0P;
            int c=0,c1=0;
            int focus=0,start=0,startP=0;
            for(int i=2;i<100;++i)
            {
                for(int j=2;j<100;++j){
                    r0P=Rad[i-1][j-1];
                    r0=Rad[i][j];
                    r1=Rad[i-1][j-1];
                    r2=Rad[i-1][j];
                    r3=Rad[i-1][j+1];
                    r4=Rad[i][j-1];
                    r5=Rad[i][j+1];
                    r6=Rad[i+1][j-1];
                    r7=Rad[i+1][j];
                    r8=Rad[i+1][j+1];
                    if(r0>0) {
                        if(i>2&&j>2) {
                            Vertex locationP = new Vertex((i - 1) + ":" + (j - 1), (i - 1) + ":" + (j - 1));
                            vertexNew.add(locationP);
                            startP=vertexNew.lastIndexOf(locationP);

                        }
                        Vertex location = new Vertex(i + ":" + j, i + ":" + j);
                        vertexNew.add(location);
                         start=vertexNew.lastIndexOf(location);
                        Edge line1=new Edge((i-1)+ "" + (j-1)+"<-->"+i+""+j,vertexNew.get(startP),vertexNew.get(start),r0P+r1);
                        Edge line3=new Edge(i+""+j+"<-->"+(i-1)+ "" + (j-1),vertexNew.get(start),vertexNew.get(startP),r0P+r1);
                        EdgesNew.add(line1);EdgesNew.add(line3);

                            System.out.println(vertexNew.size()+"|"+EdgesNew.size());
                        /*
                        if (r1 > 0) {
                            Vertex location1 = new Vertex(i+ ":" + (j-1),i+ ":" + (j-1));
                            if(!vertexNew.contains(location1)){

                                    vertexNew.add(location1);
                                     focus=vertexNew.lastIndexOf(location1);

                                Edge line=new Edge(i+ "" + (j-1)+"<-->"+i+""+j,vertexNew.get(start),vertexNew.get(focus),r1);
                                Edge line2=new Edge(i+""+j+"<-->"+i+ "" + (j-1),vertexNew.get(focus),vertexNew.get(start),r1);
                            EdgesNew.add(line);EdgesNew.add(line2);}


                            f0 = RGB(r0);
                            f1 = RGB(r1);
                            result.setRGB(j, i, new Color(f0[0], f0[1], f0[2]).getRGB());
                            result.setRGB(j - 1, i - 1, new Color(f1[0], f1[1], f1[2]).getRGB());

                        }
                        if (r2 > 0) {
                            Vertex location1 = new Vertex((i - 1) + ":" + j, (i - 1) + ":" + j);
                            if (!vertexNew.contains(location1)) {
                                vertexNew.add(location1);
                                focus = vertexNew.lastIndexOf(location1);

                            Edge line = new Edge((i - 1) + "" + j + "<-->" + i + "" + j, vertexNew.get(start), vertexNew.get(focus), r2);
                                Edge line2 = new Edge(  i + "" + j+ "<-->" +(i - 1) + "" + j,vertexNew.get(focus), vertexNew.get(start),  r2);
                            EdgesNew.add(line);EdgesNew.add(line2);
                        }

                            f0 = RGB(r0);
                            f1 = RGB(r2);
                            result.setRGB(j, i, new Color(f0[0], f0[1], f0[2]).getRGB());
                            result.setRGB(j, i - 1, new Color(f1[0], f1[1], f1[2]).getRGB());

                        }
                        if (r3 > 0) {
                            Vertex location1 = new Vertex((i-1)+ ":" + (j+1),(i-1)+ ":" + (j+1));
                            if(!vertexNew.contains(location1)){
                            vertexNew.add(location1);focus=vertexNew.lastIndexOf(location1);


                                Edge line=new Edge((i-1)+ "" + (j+1)+"<-->"+i+""+j,vertexNew.get(start),vertexNew.get(focus),r3);
                                Edge line2=new Edge(i+""+j+"<-->"+(i-1)+ "" + (j+1),vertexNew.get(focus),vertexNew.get(start),r3);
                            EdgesNew.add(line);EdgesNew.add(line2);}

                            f0 = RGB(r0);
                            f1 = RGB(r3);
                            result.setRGB(j, i, new Color(f0[0], f0[1], f0[2]).getRGB());
                            result.setRGB(j + 1, i - 1, new Color(f1[0], f1[1], f1[2]).getRGB());

                        }
                        if (r4 > 0) {
                            Vertex location1 = new Vertex(i + ":" + (j - 1), i + ":" + (j - 1));
                            if (!vertexNew.contains(location1)) {
                                vertexNew.add(location1);
                                focus = vertexNew.lastIndexOf(location1);

                                Edge line = new Edge(i + "" + (j - 1) + "<-->" + i + "" + j, vertexNew.get(start), vertexNew.get(focus), r4);
                                Edge line2 = new Edge(  i + "" + j+ "<-->" +i + "" + (j - 1), vertexNew.get(focus),vertexNew.get(start),r4);
                            EdgesNew.add(line);EdgesNew.add(line2);
                        }


                            f0 = RGB(r0);
                            f1 = RGB(r4);
                            result.setRGB(j, i, new Color(f0[0], f0[1], f0[2]).getRGB());
                            result.setRGB(j - 1, i, new Color(f1[0], f1[1], f1[2]).getRGB());

                        }
                        if (r5 > 0) {
                            Vertex location1 = new Vertex(i + ":" + (j + 1), i + ":" + (j + 1));
                            if (!vertexNew.contains(location1)) {
                                vertexNew.add(location1);
                                focus = vertexNew.lastIndexOf(location1);

                                Edge line = new Edge(i + "" + (j + 1) + "<-->" + i + "" + j, vertexNew.get(start), vertexNew.get(focus), r5);
                                Edge line2 = new Edge(  i + "" + j+ "<-->" +i + "" + (j + 1), vertexNew.get(focus),vertexNew.get(start),  r5);
                            EdgesNew.add(line);EdgesNew.add(line2);
                        }
                            /*
                            f0 = RGB(r0);
                            f1 = RGB(r5);
                            result.setRGB(j, i, new Color(f0[0], f0[1], f0[2]).getRGB());
                            result.setRGB(j + 1, i, new Color(f1[0], f1[1], f1[2]).getRGB());

                        }
                        if (r6 > 0) {
                            Vertex location1 = new Vertex((i + 1) + ":" + (j - 1), (i + 1) + ":" + (j - 1));
                            if (!vertexNew.contains(location1)) {
                                vertexNew.add(location1);
                                focus = vertexNew.lastIndexOf(location1);

                                Edge line = new Edge((i + 1) + "" + (j - 1) + "<-->" + i + "" + j, vertexNew.get(start), vertexNew.get(focus), r6);
                                Edge line2 = new Edge(  i + "" + j+ "<-->" +(i + 1) + "" + (j - 1), vertexNew.get(focus), vertexNew.get(start), r6);
                            EdgesNew.add(line);EdgesNew.add(line2);
                        }

                            f0 = RGB(r0);
                            f1 = RGB(r6);
                            result.setRGB(j, i, new Color(f0[0], f0[1], f0[2]).getRGB());
                            result.setRGB(j - 1, i + 1, new Color(f1[0], f1[1], f1[2]).getRGB());

                        }
                        if (r7 > 0) {
                            Vertex location1 = new Vertex((i + 1) + ":" + j, (i + 1) + ":" + j);
                            if (!vertexNew.contains(location1)) {
                                vertexNew.add(location1);
                                focus = vertexNew.lastIndexOf(location1);

                                Edge line = new Edge((i + 1) + "" + j + "<-->" + i + "" + j, vertexNew.get(start), vertexNew.get(focus), r7);
                                Edge line2 = new Edge(  i + "" + j+ "<-->" +(i + 1) + "" + j, vertexNew.get(focus),vertexNew.get(start),  r7);
                            EdgesNew.add(line);EdgesNew.add(line2);
                        }

                            f0 = RGB(r0);
                            f1 = RGB(r7);
                            result.setRGB(j, i, new Color(f0[0], f0[1], f0[2]).getRGB());
                            result.setRGB(j, i + 1, new Color(f1[0], f1[1], f1[2]).getRGB());

                        }
                        if (r8 > 0) {
                            Vertex location1 = new Vertex((i+1)+ ":" + (j+1),(i+1)+ ":" + (j+1));
                            if(!vertexNew.contains(location1)) {
                                vertexNew.add(location1);
                                focus = vertexNew.lastIndexOf(location1);
                                Edge line = new Edge((i + 1) + "" + (j + 1) + "<-->" + i + "" + j, vertexNew.get(start), vertexNew.get(focus), r8);
                                Edge line2 = new Edge(  i + "" + j+ "<-->" +(i + 1) + "" + (j + 1), vertexNew.get(focus), vertexNew.get(start), r8);
                                EdgesNew.add(line);EdgesNew.add(line2);
                            }


                            f0 = RGB(r0);
                            f1 = RGB(r8);
                            result.setRGB(j, i, new Color(f0[0], f0[1], f0[2]).getRGB());
                            result.setRGB(j + 1, i + 1, new Color(f1[0], f1[1], f1[2]).getRGB());
                            */
                        }

                    }


                }


            Graph graphNew = new Graph(vertexNew, EdgesNew);
            ShortestPath dijkstra = new ShortestPath(graphNew);
            dijkstra.execute(vertexNew.get(0));
            LinkedList<Vertex> path = dijkstra.getPath(vertexNew.get(22));

            assertNotNull(path);
            assertTrue(path.size() > 0);

            for (Vertex vertex : path) {
                System.out.println(vertex.getId());
            }


            ImageIO.write(result, "png", new File("graph.png"));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


/*
    private List<Vertex> nodes;
    private List<Edge> edges;

    @Test
   public void testExcute() {
        nodes = new ArrayList<Vertex>();
        edges = new ArrayList<Edge>();
        for (int i = 0; i < 11; i++) {
            Vertex location = new Vertex("Node_" + i, "Node_" + i);
            nodes.add(location);
        }
/*
        addLane("Edge_0", 0, 1, 85);
        addLane("Edge_1", 0, 2, 217);
        addLane("Edge_2", 0, 4, 173);
        addLane("Edge_3", 2, 6, 186);
        addLane("Edge_4", 2, 7, 103);
        addLane("Edge_5", 3, 7, 183);
        addLane("Edge_6", 5, 8, 250);
        addLane("Edge_7", 8, 9, 84);
        addLane("Edge_8", 7, 9, 167);
        addLane("Edge_9", 4, 9, 502);
        addLane("Edge_10", 9, 10, 40);
        addLane("Edge_11", 1, 10, 600);

        // Lets check from location Loc_1 to Loc_10
        /*
        Graph graph = new Graph(nodes, edges);
        ShortestPath dijkstra = new ShortestPath(graph);
        dijkstra.execute(nodes.get(0));
        LinkedList<Vertex> path = dijkstra.getPath(nodes.get(10));

        assertNotNull(path);
        assertTrue(path.size() > 0);

        for (Vertex vertex : path) {
            System.out.println(vertex);
        }

    }


    private void addLane(String laneId, int sourceLocNo, int destLocNo,
                         int duration) {
        Edge lane = new Edge(laneId,nodes.get(sourceLocNo), nodes.get(destLocNo), duration );
        edges.add(lane);
    }*/
}