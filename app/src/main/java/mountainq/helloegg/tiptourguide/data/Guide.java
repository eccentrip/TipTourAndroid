package mountainq.helloegg.tiptourguide.data;

/**
 * Created by dnay2 on 2016-11-26.
 */

public class Guide {

    private int id;
    private String name;
    private float score;
    private String description;
    private int count;

    public Guide() {
        id = 0;
        name ="";
        score = 0.0f;
        description = "";
        count = 0;
    }

    public Guide(int id, String name, float score, String description, int count) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.description = description;
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

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
