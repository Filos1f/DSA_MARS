package com;

/**
 * Created by Sultan on 10.11.2016.
 */
public class edge {

    private double weight;
    private vertex startVertex;
    private vertex targetVertex;

    public edge(double weight, vertex startVertex,vertex targetVertex){
        this.weight=weight;
        this.startVertex=startVertex;
        this.targetVertex=targetVertex;
    }

    public vertex getStartVertex() {
        return startVertex;
    }

    public void setStartVertex(vertex startVertex) {
        this.startVertex = startVertex;
    }

    public vertex getTargetVertex() {
        return targetVertex;
    }

    public void setTargetVertex(vertex targetVertex) {
        this.targetVertex = targetVertex;
    }



    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
