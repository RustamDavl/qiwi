package com.rustdv.exchangerate;

import com.rustdv.exchangerate.util.ExchangeRateDisplayer;
import com.rustdv.exchangerate.util.UrlBuilder;
import com.rustdv.exchangerate.util.XmlParserBuilder;
import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.client5.http.fluent.Response;
import org.w3c.dom.Document;


import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;


public class ApplicationRunner {
    public static void main(String[] args) throws ParserConfigurationException {

        System.out.println(ExchangeRateDisplayer.display("2022-10-08", "USD"));
    }


    }

