package com.wiki.utils;

import java.util.ArrayList;

public class Utils {

    public static ArrayList<String> sortByAscendingListWithStringsAndInt(ArrayList<String> list) {
        list
            .sort(
                (a, b) -> {
                    try {
//                        check if value starts with number
                        return Long.valueOf(a.split(" ")[0])
                            .compareTo(Long.valueOf(b.split(" ")[0]));
                    } catch (NumberFormatException nfe) {
                        return a.toLowerCase().compareTo(b.toLowerCase());
                    }
                }
            );
        return list;
    }

}
