package com.ldp.vigilantBean.domain;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@NamedQueries({
        @NamedQuery(
                name = Category.GET_ALL_CATEGORIES,
                query = "from Category c"
        ),
        @NamedQuery(
                name = Category.GET_CATEGORY_BY_NAME,
                query = "from Category c " +
                        "where c.shortName = :categoryName"
        )
})
public class Category {

    public static final String GET_ALL_CATEGORIES = "Category.getAllCategories";
    public static final String GET_CATEGORY_BY_NAME = "Category.getCategoryByName";

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long categoryId;

    @Column(name = "name")
    private String name;

    @Column(name = "short_name")
    private String shortName;

    @Column(name = "description")
    private String description;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "category_picture",
               joinColumns = @JoinColumn(name = "category_id"),
               inverseJoinColumns = @JoinColumn(name = "picture_id")
    )
    private Picture picture;

    public Category() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return categoryId == category.categoryId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId);
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", name='" + name + '\'' +
                '}';
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}