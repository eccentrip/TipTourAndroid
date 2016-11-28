package mountainq.helloegg.tiptourguide.data;

/**
 * Created by dnay2 on 2016-11-25.
 */

public class LocationData {

    private double mapx;
    private double mapy;
    private String title;

    public LocationData() {
    }

    public LocationData(String mapx, String mapy, String title) {
        this.mapx = Double.parseDouble(mapx);
        this.mapy = Double.parseDouble(mapy);
        this.title = title;
    }
    public LocationData(double mapx, double mapy, String title){
        this.mapx = mapx;
        this.mapy = mapy;
        this.title = title;
    }

    public LocationData(LocationData locationData) {
        this.mapx = locationData.getMapx();
        this.mapy = locationData.getMapy();
        this.title = locationData.getTitle();
    }

    public double getMapx() {
        return mapx;
    }

    public void setMapx(String mapx) {
        this.mapx = Double.parseDouble(mapx);
    }

    public double getMapy() {
        return mapy;
    }

    public void setMapy(String mapy) {
        this.mapy = Double.parseDouble(mapy);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "SearchKeyword{" +
                "mapx='" + mapx + '\'' +
                ", mapy='" + mapy + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
