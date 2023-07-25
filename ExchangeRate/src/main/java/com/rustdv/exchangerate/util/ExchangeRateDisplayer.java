package com.rustdv.exchangerate.util;

import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.client5.http.fluent.Response;
import org.w3c.dom.Document;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExchangeRateDisplayer {


    public static String display(String date, String code) {

        String c = null;
        String name = null;
        String val = null;
        var url = UrlBuilder.buildUrlByDate(date);
        String result = null;
        try {
            Response response = Request.get(url).execute();
            result = response.returnContent().asString();

            Document doc = XmlParserBuilder.buildDocument(result);

            var list = doc.getElementsByTagName("Valute");


            for(int i = 0; i < list.getLength(); i++) {
                var childs = list.item(i).getChildNodes();

                for(int j = 0; j < childs.getLength(); j++) {
                    var node = childs.item(j);
                    var nodeName = node.getNodeName();
//                    while (nodeName.equals("CharCode") || nodeName.equals("Name") || nodeName.equals("Value")) {
//                        if(node.getTextContent().equals(code)) {
//                            c = code;
//                        }
//                    }

                    if(node.getNodeName().equals("CharCode") && node.getTextContent().equals(code)) {
                        j++;
                        while (!childs.item(j).getNodeName().equals("Name")) {

                        }
                    }

                    if(node.getNodeName().equals("Name")) {
                        name = node.getTextContent();
                    }
                    if(node.getNodeName().equals("Value")) {
                        val = node.getTextContent();
                    }

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return code + "( " + name.toUpperCase() + " )" + val;
    }
}
