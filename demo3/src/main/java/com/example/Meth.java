package com.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

//import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Meth extends Search21v {
    
    public void elementsWb(String url) throws InterruptedException {
        
        try {
            
            //WebDriver driver = new ChromeDriver();
            //driver.get(url);

            //By accept = By.xpath("//*[@id=\"__next\"]/div[2]/div/div[2]/button");
            //By nextPage = By.xpath("//div[@lsaa='styles_links__inner__g3xjS']//a[@class='styles_link__8m3I9.styles_arrow__LNoLG']");

            //driver.findElement(accept).click();
            //driver.findElement(nextPage).click();

            Configuration configuration = new Configuration();
            configuration.addAnnotatedClass(Product.class);
            configuration.configure();

            Document doc = Jsoup.connect(url).get();

            List<String> newList1 = new ArrayList<String>();
            List<String> newList = new ArrayList<String>();
            List<Object> newList2 = new ArrayList<Object>();
            
            Elements price = doc.select("p[class=styles_price__G3lbO]");
            Elements description = doc.select("h3[class=styles_title__F3uIe]"); 

            for(Element x : description) {
                newList.add(x.text().replaceAll(",", ""));
            }
            
            for(Element y : price) {
                newList1.add(y.text().replaceAll(" р.",""));
            }

            System.out.println(newList);
            System.out.println(newList);

            newList1.addAll(newList);
            System.out.println(newList1);


            //Searchkuf searchkuf = new Searchkuf();
            //Search21v search21v = new Search21v();
            List<String> newList3 = new ArrayList<String>();

            for(String j : newList) {
                //searchkuf.searchkuff(j);
                //search21v.search21vv(j);
                String url1 = "https://www.21vek.by/search/?sa=&term=" + j + "&searchId=66d31c671ffb465c850ed631c53d27e3";

                String url2 = "https://www.kufar.by/l?cnd=2&query=" + j + "&sort=lst.d";

                Document doc1 = Jsoup.connect(url1).get();

                Document doc2 = Jsoup.connect(url2).get();
            
                Elements price1 = doc1.select("span[class=result__name]");
                Elements description1 = doc1.select("span[class=g-item-data j-item-data j-item-data8121411 j-item-data-real8121411 ]");

                Elements price2 = doc2.select("p[class=styles_price__G3lbO]");
                Elements description2 = doc2.select("h3[class=styles_title__F3uIe]");

                if(price1.isEmpty() || description1.isEmpty()) {
                    newList4.add("в 21 веке товар не найден");
                    System.out.println("в 21 веке товар не найден");
                }
                else{
                    String fpr1 = price1.get(0).text();
                    String desc1 = description1.get(0).text();
                    System.out.println(fpr1);
                    System.out.println(desc1);
                
                    newList4.add(fpr1+"#"+desc1+"#"+url1);
                }
                if(price2.isEmpty() || description2.isEmpty()){
                    System.out.println("на куфаре товар не найден");
                    newList3.add("на куфаре товар не найден");
                }
                else{
                    String fpr2 = price2.get(0).text();
                    String desc2 = description2.get(0).text();
                    System.out.println(fpr2);
                    System.out.println(desc2);
                    
                    newList3.add(fpr2+"#"+desc2+"#"+url2);
                }

                Thread.sleep(5000);
            }

            for(int h = 0; h<price.size();h++) {
                SessionFactory sessionFactory = configuration.buildSessionFactory();
                Session session = sessionFactory.openSession();
                session.beginTransaction();

                System.out.println(newList4);
                String i = newList4.get(h);

                System.out.println(newList3);
                String m = newList3.get(h);

                if (i.equals("в 21 веке товар не найден" ) && m.equals("на куфаре товар не найден")) {
                    Product product = Product.builder().price(newList1.get(h)).namee(newList1.get(h+price.size())).namee21v(newList4.get(h)).nameeKuf(newList3.get(h)).build();
                    session.persist(product);
                    session.getTransaction().commit();
                }
                else if (i.equals("в 21 веке товар не найден" )) {

                    String[] sub2 = newList3.get(h).split("#");

                    Product product = Product.builder().price(newList1.get(h)).namee(newList1.get(h+price.size())).namee21v(newList4.get(h)).nameeKuf(sub2[0]).priceKuf(sub2[1]).linkKuf(sub2[2]).build();
                    session.persist(product);
                    session.getTransaction().commit();
                }
                else if (m.equals("на куфаре товар не найден")) {

                    String[] sub1 = newList4.get(h).split("#");

                    Product product = Product.builder().price(newList1.get(h)).namee(newList1.get(h+price.size())).namee21v(sub1[0]).price21v(sub1[1]).link21v(sub1[2]).nameeKuf(newList3.get(h)).build();
                    session.persist(product);
                    session.getTransaction().commit();
                }
                else{
                    String[] sub1 = newList4.get(h).split("#");
                    String[] sub2 = newList3.get(h).split("#");

                    Product product = Product.builder().price(newList1.get(h)).namee(newList1.get(h+price.size())).namee21v(sub1[0]).price21v(sub1[1]).link21v(sub1[2]).nameeKuf(sub2[0]).priceKuf(sub2[1]).linkKuf(sub2[2]).build();
                    session.persist(product);
                    session.getTransaction().commit();
                }
                
            }
            

            //try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/kufar?" +
            //"user=root&password=89Monkey89")) {

                /*String sqlUpdate = " INSERT INTO subjkuf(numb) " + " VALUES( ? ) ";
                sqlUpdate1 = " UPDATE subjkuf " + " SET namee = ? " + " WHERE numb = ? ";
                String sqlUpdate2 = " UPDATE subjkuf " + " SET price = ? " + " WHERE numb = ? ";


                PreparedStatement pstmt = conn.prepareStatement(sqlUpdate);
                PreparedStatement pstmt1 = conn.prepareStatement(sqlUpdate1);
                PreparedStatement pstmt2 = conn.prepareStatement(sqlUpdate2);

                for(int f = 0; f<newList.size(); f++) {
                    pstmt.setInt(1, f);
                    int row = pstmt.executeUpdate();
                }
                System.out.println(newList.size());

                for(int h=0; h<newList.size(); h++) {
                    pstmt1.setString(1, newList.get(h));
                    pstmt1.setInt(2, h);
                    pstmt2.setObject(1, newList1.get(h));
                    pstmt2.setInt(2, h);
                }

                Searchkuf searchkuf = new Searchkuf();
                Search21v search21v = new Search21v();

                //for(String j : newList) {
                    //searchkuf.searchkuff(j);
                    //search21v.search21vv(j);

                   // Thread.sleep(9000);
               // }*/

                

            //} catch (Exception e) {
            //    System.out.println(e);
           // }


            

            //driver.quit();


            //String t = price.replaceAll(" ", "");
            //String[] pric = t.split("\\р.");

            
        } catch (IOException e) {

            e.printStackTrace();

        }
    }
}
