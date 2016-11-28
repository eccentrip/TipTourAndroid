package mountainq.helloegg.tiptourguide.data;

/**
 * Created by dnay2 on 2016-11-28.
 */

public class PushGuideToTourist {

    private int touristid;
    private int responseCode;
    private int tourId;

    public PushGuideToTourist(int touristid, int responseCode, int tourId) {
        this.touristid = touristid;
        this.responseCode = responseCode;
        this.tourId = tourId;
    }

    public PushGuideToTourist(PushGuideToTourist item) {
        this.touristid = item.getTouristid();
        this.responseCode = item.getResponseCode();
        this.tourId = item.getTourId();
    }

    public int getTouristid() {
        return touristid;
    }

    public void setTouristid(int touristid) {
        this.touristid = touristid;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public int getTourId() {
        return tourId;
    }

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }

    @Override
    public String toString() {
        return "PushGuideToTourist{" +
                "touristid=" + touristid +
                ", responseCode=" + responseCode +
                ", tourId=" + tourId +
                '}';
    }

}
