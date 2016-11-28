package mountainq.helloegg.tiptourguide.data;

/**
 * Created by dnay2 on 2016-11-20.
 */
public class SearchKeyword extends LocationData{

    public SearchKeyword() {
    }

    public SearchKeyword(String mapx, String mapy, String title) {
        super(mapx, mapy, title);
    }

    public SearchKeyword(double mapx, double mapy, String title) {
        super(mapx, mapy, title);
    }

    public SearchKeyword(LocationData locationData) {
        super(locationData);
    }
}
