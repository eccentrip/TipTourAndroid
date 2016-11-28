package mountainq.helloegg.tiptourguide.parsers;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

import mountainq.helloegg.tiptourguide.data.SearchKeyword;

/**
 * Created by dnay2 on 2016-11-20.
 */

public class SearchKeywordHandler extends DefaultHandler {

    private ArrayList<SearchKeyword> items = new ArrayList<>();
    private SearchKeyword item;
    private String tagName = "";


    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String data = new String(ch, start, length);
        switch (tagName){
            case "mapx":
                item.setMapx(data);
                break;
            case "mapy":
                item.setMapy(data);
                break;
            case "title":
                item.setTitle(data);
                break;
        }
        tagName = "";
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (localName){
            case "item":
                items.add(item);
                break;
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (localName){
            case "item":
                item = new SearchKeyword();
                break;
        }
        tagName = localName;
    }

    public ArrayList<SearchKeyword> getItems() {
        return items;
    }
}
