package mountainq.helloegg.tiptourguide.data;

/**
 * Created by dnay2 on 2016-11-20.
 */
public class SearchKeyword {

    private double mapx;
    private double mapy;
    private String title;

    public SearchKeyword() {
    }

    public SearchKeyword(String mapx, String mapy, String title) {
        this.mapx = Double.parseDouble(mapx);
        this.mapy = Double.parseDouble(mapy);
        this.title = title;
    }

    public SearchKeyword(SearchKeyword searchKeyword) {
        this.mapx = searchKeyword.getMapx();
        this.mapy = searchKeyword.getMapy();
        this.title = searchKeyword.getTitle();
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
