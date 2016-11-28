package mountainq.helloegg.tiptourguide.data;

/**
 * Created by dnay2 on 2016-11-25.
 */

public class User {

    private int id;
    private String name;
    private String password;
    private int gui;
    private float score;
    private String discription;
    private String deviceid;
    private String token;
    private int count;

    public User() {

    }

    public User(int id, String name, String password, int gui, float score, String discription, String deviceid, String token, int count) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.gui = gui;
        this.score = score;
        this.discription = discription;
        this.deviceid = deviceid;
        this.token = token;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getGui() {
        return gui;
    }

    public void setGui(int gui) {
        this.gui = gui;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
