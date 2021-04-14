package com.ldp.vigilantBean.repository;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

public class PaginatedEntities<E> {

    private List<E> entities;

    private List<Integer> pages;

    public PaginatedEntities(List<E> entities, List<Integer> pages) {
        this.entities = entities;
        this.pages = pages;
    }

    public List<E> getEntities() {
        return entities;
    }

    public void setEntities(List<E> entities) {
        this.entities = entities;
    }

    public List<Integer> getPages() {
        return pages;
    }

    public void setPages(List<Integer> pages) {
        this.pages = pages;
    }
}
