package com;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sultan on 11.11.2016.
 */
public class vertex {
    private double minDistance=Double.MAX_VALUE;//Setting initial distance values to infinity
    private vertex previousVertex;//predecessor
    private String name;



    public void setPreviousVertex(vertex previousVertex) {
        this.previousVertex = previousVertex;
    }


    private List<edge> adjacientslist;


    public double getMinDistance() {
        return minDistance;
    }


    public vertex getPreviousVertex() {
        return previousVertex;
    }
    public void setMinDistance(double minDistance) {
        this.minDistance = minDistance;
    }
    public vertex(String name){
        this.name=name;
        this.adjacientslist= new ArrayList<>();
    }
    public void addEdge(edge edge){
        this.adjacientslist.add(edge);
    }
    public String toString(){
        return this.name;
    }
}
