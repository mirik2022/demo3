package com.example;

import java.sql.*;
import org.hibernate.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Mysql{

    public void sql(){

        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Product.class);
        configuration.configure();

        //Meth meth1 = new Meth();
        //meth1.elementsWb("https://www.kufar.by/l/naushniki?cnd=1&sort=lst.d");

        try{
            //for(String k : newList) {
            //    System.out.println(k);
            //}

            
        } catch (Exception e) {
            
        }
    }
}





    
