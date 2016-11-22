package mountainq.helloegg.tiptourguide.data;

/**
 * Created by dnay2 on 2016-11-18.
 */
public class TourItem {

    private Dummy header;

    private Body body;

    public TourItem(Dummy header, Body body) {
        this.header = header;
        this.body = body;
    }

    public Dummy getHeader() {
        return header;
    }

    public void setHeader(Dummy header) {
        this.header = header;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }
}
