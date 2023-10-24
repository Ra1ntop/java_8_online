package ua.com.alevel.entity;

public class Neighbor {
    private int neighborIndex;
    private int cost;

    public int getNeighborIndex() {
        return neighborIndex;
    }

    public void setNeighborIndex(int neighborIndex) {
        this.neighborIndex = neighborIndex;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Neighbor(int neighborIndex, int cost) {
        setNeighborIndex(neighborIndex);
        setCost(cost);
    }
}
