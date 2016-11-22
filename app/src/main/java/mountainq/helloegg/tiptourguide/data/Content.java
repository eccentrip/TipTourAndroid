package mountainq.helloegg.tiptourguide.data;

/**
 * Created by cholmink on 16. 11. 15..
 */
public class Content {
    public int user_idx;
    public String name;
    public String password;
    public String token;
    public String deviceid;

    public void setName(String name){
        this.name = name;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }
}
