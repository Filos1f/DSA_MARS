package com.company;

/**
 * Created by Pavel on 13.11.2016.
 */
public class Edge<T> {
    private T radiationValue;
    private Pixel startVertex;
    private Pixel targetVertex;

    public Edge(T radiation, Pixel startVertex, Pixel targetVertex){
        this.radiationValue = radiation;
        this.startVertex = startVertex;
        this.targetVertex = targetVertex;
    }

    public Pixel getStartVertex() {
        return startVertex;
    }

    public void setStartVertex(Pixel startVertex) {
        this.startVertex = startVertex;
    }

    public Pixel getTargetVertex() {
        return targetVertex;
    }

    public void setTargetVertex(Pixel targetVertex) {
        this.targetVertex = targetVertex;
    }

    public T getRadiation() {
        return radiationValue;
    }

    public void setWeight(T radiation) {
        this.radiationValue = radiation;
    }
}
