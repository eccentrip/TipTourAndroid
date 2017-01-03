package mountainq.helloegg.tiptourguide.data;

/**
 * Created by dnay2 on 2016-11-20.
 */
public class SearchKeyword extends LocationData{

    public SearchKeyword() {
    }

    public SearchKeyword(String mapx, String mapy, String title, String firstImgUrl) {
        super(mapx, mapy, title, firstImgUrl);
    }

    public SearchKeyword(double mapx, double mapy, String title, String firstImgUrl) {
        super(mapx, mapy, title, firstImgUrl);
    }

    public SearchKeyword(LocationData locationData) {
        super(locationData);
    }

    public SearchKeyword(double mapx, double mapy){
        this.setMapx(mapx);
        this.setMapy(mapy);
    }
}
