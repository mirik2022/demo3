package com.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Searchkuf {

    public void searchkuff(String name) {
        try {
            String url2 = "https://www.kufar.by/l?cnd=2&query=" + name + "&sort=lst.d";

            Document doc2 = Jsoup.connect(url2).get();

            List<String> newList2 = new ArrayList<String>();
            List<Object> newList3 = new ArrayList<Object>();

            Elements price2 = doc2.select("p[class=styles_price__G3lbO]");
            Elements description2 = doc2.select("h3[class=styles_title__F3uIe]");

            if(price2.isEmpty() || description2.isEmpty()) {
                System.out.println("На куфаре товар не найден");
            }

            else{
                String fpr = price2.get(0).text();
                String desc = description2.get(0).text();

                System.out.println(fpr);
                System.out.println(desc);
            }
        
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
    }
    
}
