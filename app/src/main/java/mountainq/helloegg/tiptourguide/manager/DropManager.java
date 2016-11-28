package mountainq.helloegg.tiptourguide.manager;

/**
 * Created by dnay2 on 2016-11-26.
 */

public class DropManager {

    public static String calCost(double distance){
        String sum = "";
        if(distance > 3000){
            sum += "5000원";
        }else if(distance > 2000){
            sum+= "4000원";
        }else if(distance > 1000){
            sum += "3000원";
        }else{
            sum += "2000원";
        }
        return sum;
    }

    public static String calTime(double distance){
        String sum = "";
        if(distance > 3000){
            sum += "1시간 이상";
        }else if(distance > 2000){
            sum+= "45분이상";
        }else if(distance > 1000){
            sum += "30분이상";
        }else{
            sum += "15분이상";
        }
        return sum;
    }

    public static String calDistance(double distance){
        LOG.DEBUG("distance : " + distance + " <-- 이걸 출력할 수 있당가");
        String sum = "";
        int dist = (int)distance;
        if(distance > 1000){
            sum += dist/1000 + " km";
        }else if(distance >0){
            sum += dist + " m";
        }
        else sum += "알수없음";

        return sum;
    }



}
