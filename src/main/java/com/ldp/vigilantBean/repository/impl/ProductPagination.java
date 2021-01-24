package com.ldp.vigilantBean.repository.impl;

import com.ldp.vigilantBean.repository.Pagination;

public class ProductPagination implements Pagination {

    private int firstResultIndex = 0;
    private int amountOfProductsPerFetch = 0;

    public ProductPagination(int firstResultIndex,
                             int amountOfProductsPerFetch) {
        this.firstResultIndex = firstResultIndex;
        this.amountOfProductsPerFetch = amountOfProductsPerFetch;
    }


    @Override
    public int getFirstResultIndex() {
        return firstResultIndex;
    }

    @Override
    public int getMaxResults() {
        return amountOfProductsPerFetch;
    }
}
