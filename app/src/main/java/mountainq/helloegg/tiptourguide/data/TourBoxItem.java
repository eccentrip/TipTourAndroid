package mountainq.helloegg.tiptourguide.data;

/**
 * Created by dnay2 on 2016-11-18.
 */

public class TourBoxItem {

    private String destination;
    private double distance;
    private double cost;
    private int time;

    public TourBoxItem(String destination, double distance, double cost, int time) {
        this.destination = destination;
        this.distance = distance;
        this.cost = cost;
        this.time = time;
    }

    public TourBoxItem(TourBoxItem item){
        this.destination = item.getDestination();
        this.distance = item.getDistance();
        this.cost = item.getCost();
        this.time = item.getTime();
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return destination + "까지 가는 여정";
    }
}
