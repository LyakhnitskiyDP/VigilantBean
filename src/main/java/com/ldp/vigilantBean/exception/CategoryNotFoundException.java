package com.ldp.vigilantBean.exception;

public class CategoryNotFoundException extends ItemNotFoundException {

    private String categoryName;

    public CategoryNotFoundException(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

}
