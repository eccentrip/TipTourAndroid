package mountainq.helloegg.tiptourguide.data;

/**
 * Created by dnay2 on 2016-11-28.
 */

public class BooleanData {
    private boolean result;

    public BooleanData(boolean result) {
        this.result = result;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "BooleanData{" +
                "result=" + result +
                '}';
    }
}
