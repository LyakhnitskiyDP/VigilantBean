package com.ldp.vigilantBean.domain.product;

import com.ldp.vigilantBean.domain.Picture;
import com.ldp.vigilantBean.domain.category.Category;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

@Entity
@NamedQueries({
        @NamedQuery(
                name = Product.GET_ALL_PRODUCTS,
                query = "from Product p"
        ),
        @NamedQuery(
                name = Product.GET_PRODUCTS_BY_CATEGORY,
                query = "from Product p " +
                        "join fetch p.categories c " +
                        "where c.shortName = :category"
        ),
        @NamedQuery(
                name = Product.GET_NUMBER_OF_PRODUCTS,
                query = "select count(p) from Product p"
        ),
        @NamedQuery(
                name = Product.GET_NUMBER_OF_PRODUCTS_BY_CATEGORY,
                query = "select count(distinct p) from Product p " +
                        "join p.categories c " +
                        "where c.shortName = :category"
        ),
        @NamedQuery(
                name = Product.GET_PRODUCT_BY_ID,
                query = "from Product p where p.productId = :productId"
        )
})
public class Product {

    private static final long serialVersionUID = 1L;

    public static final String GET_ALL_PRODUCTS = "Product.getAllProducts";
    public static final String GET_NUMBER_OF_PRODUCTS = "Product.getNumberOfProducts";
    public static final String GET_PRODUCTS_BY_CATEGORY = "Product.getProductsByCategory";
    public static final String GET_NUMBER_OF_PRODUCTS_BY_CATEGORY = "Product.getNumberOfProductsByCategory";

    public static final String GET_PRODUCT_BY_ID = "Product.getProductById";

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "quantity_per_unit")
    private int quantityPerUnit;

    @Column(name = "unit_weight")
    private long unitWeight;

    @ManyToMany
    @JoinTable(name = "product_category",
               joinColumns = @JoinColumn(name = "product_id"),
               inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories;

    @OneToMany(fetch = FetchType.EAGER,
               cascade = CascadeType.ALL,
               orphanRemoval = true)
    @JoinTable(name = "product_picture",
               joinColumns = @JoinColumn(name = "product_id"),
               inverseJoinColumns = @JoinColumn(name = "picture_id"))
    private Set<Picture> pictures;

    @Column(name = "units_in_stock")
    private long unitsInStock;

    @Column(name = "units_in_order")
    private long unitsInOrder;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @Column(name = "origins")
    private String origins;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "ingredients")
    private String ingredients;

    @Column(name = "allergy_information")
    private String allergyInformation;

    public Product() {

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productId == product.productId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                '}';
    }

    public Picture getMainPicture() {

        return this.pictures.stream()
                            .sorted(
                                    (p1, p2) -> p1.getName().compareTo(p2.getName())
                            )
                            .findFirst()
                            .get();
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantityPerUnit() {
        return quantityPerUnit;
    }

    public void setQuantityPerUnit(int quantityPerUnit) {
        this.quantityPerUnit = quantityPerUnit;
    }

    public long getUnitWeight() {
        return unitWeight;
    }

    public void setUnitWeight(long unitWeight) {
        this.unitWeight = unitWeight;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(Set<Picture> pictures) {
        this.pictures = pictures;
    }

    public long getUnitsInStock() {
        return unitsInStock;
    }

    public void setUnitsInStock(long unitsInStock) {
        this.unitsInStock = unitsInStock;
    }

    public long getUnitsInOrder() {
        return unitsInOrder;
    }

    public void setUnitsInOrder(long unitsInOrder) {
        this.unitsInOrder = unitsInOrder;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getOrigins() {
        return origins;
    }

    public void setOrigins(String origins) {
        this.origins = origins;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getAllergyInformation() {
        return allergyInformation;
    }

    public void setAllergyInformation(String allergy_information) {
        this.allergyInformation = allergy_information;
    }
}
