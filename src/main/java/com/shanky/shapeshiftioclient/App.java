package com.shanky.shapeshiftioclient;

import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.IOException;

public class App 
{
    public static void main( String[] args ) throws IOException, UnirestException
    {  
        Shapeshift s = new Shapeshift();
        System.out.println(s.getRate("btc_ltc"));
    }
}
