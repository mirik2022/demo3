package com.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Search21v {

    ArrayList<String> newList4 = new ArrayList<String>();

    public ArrayList<String> search21vv(String name) {
        try { 
            String url2 = "https://www.21vek.by/search/?sa=&term=" + name + "&searchId=66d31c671ffb465c850ed631c53d27e3";

            Document doc2 = Jsoup.connect(url2).get();
            
            Elements price1 = doc2.select("span[class=result__name]");
            Elements description1 = doc2.select("span[class=g-item-data j-item-data j-item-data8121411 j-item-data-real8121411 ]");

            if(price1.isEmpty() || description1.isEmpty()) {
                newList4.add("в 21 веке товар не найден");
            }
            else{
                String fpr1 = price1.get(0).text();
                String desc1 = description1.get(0).text();
                System.out.println(fpr1);
                System.out.println(desc1);
                
                //newList5.add(fpr1);
                //newList5.add(desc1);
                newList4.add(fpr1+"#"+desc1);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(newList4);
        return newList4;
    }
    
}
