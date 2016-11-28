package mountainq.helloegg.tiptourguide.data;

/**
 * Created by dnay2 on 2016-11-27.
 */

public class PushDatas {

    private int id;
    private double startPointX;
    private double startPointY;
    private double endPointX;
    private double endPointY;
    private String startTitle;
    private String endTitle;
    private int touristid;

    public PushDatas(int id, double startPointX, double startPointY, double endPointX, double endPointY, String startTitle, String endTitle, int touristid) {
        this.id = id;
        this.startPointX = startPointX;
        this.startPointY = startPointY;
        this.endPointX = endPointX;
        this.endPointY = endPointY;
        this.startTitle = startTitle;
        this.endTitle = endTitle;
        this.touristid = touristid;
    }

    public PushDatas(PushDatas pushDatas) {
        this.id = pushDatas.getId();
        this.startPointX = pushDatas.getStartPointX();
        this.startPointY = pushDatas.getStartPointY();
        this.endPointX = pushDatas.getEndPointX();
        this.endPointY = pushDatas.getEndPointY();
        this.startTitle = pushDatas.getStartTitle();
        this.endTitle = pushDatas.getEndTitle();
        this.touristid = pushDatas.getTouristid();
    }

    public double getStartPointX() {
        return startPointX;
    }

    public void setStartPointX(double startPointX) {
        this.startPointX = startPointX;
    }

    public double getStartPointY() {
        return startPointY;
    }

    public void setStartPointY(double startPointY) {
        this.startPointY = startPointY;
    }

    public double getEndPointX() {
        return endPointX;
    }

    public void setEndPointX(double endPointX) {
        this.endPointX = endPointX;
    }

    public double getEndPointY() {
        return endPointY;
    }

    public void setEndPointY(double endPointY) {
        this.endPointY = endPointY;
    }

    public String getStartTitle() {
        return startTitle;
    }

    public void setStartTitle(String startTitle) {
        this.startTitle = startTitle;
    }

    public String getEndTitle() {
        return endTitle;
    }

    public void setEndTitle(String endTitle) {
        this.endTitle = endTitle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTouristid() {
        return touristid;
    }

    public void setTouristid(int touristid) {
        this.touristid = touristid;
    }

    @Override
    public String toString() {
        return "PushDatas{" +
                "id=" + id +
                ", startPointX=" + startPointX +
                ", startPointY=" + startPointY +
                ", endPointX=" + endPointX +
                ", endPointY=" + endPointY +
                ", startTitle='" + startTitle + '\'' +
                ", endTitle='" + endTitle + '\'' +
                ", touristid=" + touristid +
                '}';
    }
}
