package com.ldp.vigilantBean.utils;


import java.util.ArrayList;
import java.util.List;

public class ProductsUtil {

    public static List<Integer> getPageList(Integer numberOfItems,
                                             Integer numberOfItemsPerPage) {

        int numberOfPages = getNumberOfPages(numberOfItems, numberOfItemsPerPage);

        List<Integer> listOfPages = new ArrayList<>();

        for (int i = 1; i <= numberOfPages; i++)
           listOfPages.add(i);

        if (listOfPages.size() < 2)
            return new ArrayList<>();
        else
            return listOfPages;

    }

    private static int getNumberOfPages(int numberOfItems,
                                 int numberOfItemsPerPage) {

        return (int) Math.ceil((double) numberOfItems / (double) numberOfItemsPerPage);
    }

    //Returns index of a first product on a given page
    public static int getFirstProductIndex(int pageNumber,
                                           int pageSize) {

        return (pageNumber - 1) * pageSize + 1;

    }

}
