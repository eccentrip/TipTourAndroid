package mountainq.helloegg.tiptourguide.data;



import java.util.ArrayList;

/**
 * Created by dnay2 on 2016-11-20.
 */

public class Body
{

    private ArrayList<SearchKeyword> items;

    private int numOfRows;
    private int pageNo;
    private int totalCount;

    public Body(ArrayList<SearchKeyword> items, int numOfRows, int pageNo, int totalCount) {
        this.items = items;
        this.numOfRows = numOfRows;
        this.pageNo = pageNo;
        this.totalCount = totalCount;
    }

    public ArrayList<SearchKeyword> getItems() {
        return items;
    }

    public void setItems(ArrayList<SearchKeyword> items) {
        this.items = items;
    }

    public int getNumOfRows() {
        return numOfRows;
    }

    public void setNumOfRows(int numOfRows) {
        this.numOfRows = numOfRows;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
