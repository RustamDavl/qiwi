package com.rustdv.exchangerate;

import com.rustdv.exchangerate.util.ExchangeRateDisplayer;


public class ApplicationRunner {
    public static void main(String[] args) {
        var date = System.getProperty("date");
        var code = System.getProperty("code");

        System.out.println(ExchangeRateDisplayer.display(date, code));
    }


}

