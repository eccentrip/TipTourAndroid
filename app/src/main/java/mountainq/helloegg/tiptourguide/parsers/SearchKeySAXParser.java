package mountainq.helloegg.tiptourguide.parsers;

import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import mountainq.helloegg.tiptourguide.data.SearchKeyword;

/**
 * Created by dnay2 on 2016-11-20.
 */

public class SearchKeySAXParser {

    public ArrayList<SearchKeyword> parse(InputStream s){
        ArrayList<SearchKeyword> items = null;
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = null;
        SearchKeywordHandler handler = new SearchKeywordHandler();
        try {
            saxParser = factory.newSAXParser();
            saxParser.parse(s, handler);
            items = handler.getItems();

        }catch (Exception e){
            e.printStackTrace();
        }
        return items;
    }

    public ArrayList<SearchKeyword> parseList(String s){
        ArrayList<SearchKeyword> list = new ArrayList<>();

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = null;
        SearchKeywordHandler handler = new SearchKeywordHandler();
        try {
            saxParser = factory.newSAXParser();
            saxParser.parse(s, handler);
            list = handler.getItems();

        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
