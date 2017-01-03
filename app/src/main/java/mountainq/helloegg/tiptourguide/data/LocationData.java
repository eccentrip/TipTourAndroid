package mountainq.helloegg.tiptourguide.data;

/**
 * Created by dnay2 on 2016-11-25.
 */

public class LocationData {


    private double mapx;
    private double mapy;
    private String title;
    private String firstImgUrl;


    public LocationData() {
    }

    public LocationData(String mapx, String mapy, String title, String firstImgUrl) {
        this.mapx = Double.parseDouble(mapx);
        this.mapy = Double.parseDouble(mapy);
        this.title = title;
        this.firstImgUrl = firstImgUrl;
    }
    public LocationData(double mapx, double mapy, String title, String firstImgUrl){
        this.mapx = mapx;
        this.mapy = mapy;
        this.title = title;
        this.firstImgUrl = firstImgUrl;
    }

    public LocationData(LocationData locationData) {
        this.mapx = locationData.getMapx();
        this.mapy = locationData.getMapy();
        this.title = locationData.getTitle();
        this.firstImgUrl = locationData.getFirstImgUrl();
    }

    public double getMapx() {
        return mapx;
    }

    public void setMapx(String mapx) {
        this.mapx = Double.parseDouble(mapx);
    }

    public void setMapx(double mapx){
        this.mapx = mapx;
    }

    public double getMapy() {
        return mapy;
    }

    public void setMapy(String mapy) {
        this.mapy = Double.parseDouble(mapy);
    }

    public void setMapy(double mapy){
        this.mapy = mapy;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstImgUrl() {
        return firstImgUrl;
    }

    public void setFirstImgUrl(String firstImgUrl) {
        this.firstImgUrl = firstImgUrl;
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
