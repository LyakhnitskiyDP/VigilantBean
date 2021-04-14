package com.ldp.vigilantBean.repository.impl;

import com.ldp.vigilantBean.repository.Pagination;

public class DefaultPagination implements Pagination {

    private int firstResultIndex = 0;
    private int amountOfItemsPerFetch = 0;

    public DefaultPagination(int firstResultIndex,
                             int amountOfItemsPerFetch) {
        this.firstResultIndex = firstResultIndex;
        this.amountOfItemsPerFetch = amountOfItemsPerFetch;
    }


    @Override
    public int getFirstResultIndex() {
        return firstResultIndex;
    }

    @Override
    public int getMaxResults() {
        return amountOfItemsPerFetch;
    }
}
