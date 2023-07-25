package com.rustdv.exchangerate.util;

import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.client5.http.fluent.Response;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExchangeRateDisplayer {


    public static String display(String date, String code) {
        String name = null;
        String val = null;
        var url = UrlBuilder.buildUrlByDate(date);
        String result = null;
        try {
            Response response = Request.get(url).execute();
            result = response.returnContent().asString();
            Document doc = XmlParserBuilder.buildDocument(result);
            var list = doc.getElementsByTagName("Valute");
            var sublist = getNodeSublist(list, code);

            for(int i = 0; i < sublist.getLength(); i++) {
                var node = sublist.item(i);
                var nodeName = node.getNodeName();
                if(nodeName.equals("Name")) {
                    name = node.getTextContent();
                }
                if(nodeName.equals("Value")) {
                    val = node.getTextContent();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return code + " (" + name.toUpperCase() + "): " + val;
    }

    private static NodeList getNodeSublist(NodeList list, String code) {
        for (int i = 0; i < list.getLength(); i++) {
            var childs = list.item(i).getChildNodes();
            for (int j = 0; j < childs.getLength(); j++) {
                var node = childs.item(j);
                if (node.getTextContent().equals(code)) {
                    return childs;

                }


            }
        }
        return null;
    }
}
