package com.ldp.vigilantBean.domain.category;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ldp.vigilantBean.utils.StringUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CategoryDTO {

    @Length(min = 2, max = 16, message = "validation.newCategory.nameOutOfRange")
    private String name;

    private String shortName;

    @Length(min = 10, max = 200, message = "validation.newCategory.descriptionOutOfRange")
    private String description;

    @NotNull(message = "validation.newCategory.photoNotPresent")
    @JsonIgnore
    private MultipartFile picture;

    public void initShortName() {

        if (this.name != null)
            this.shortName = StringUtil.getSmallerVersion(name);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryDTO that = (CategoryDTO) o;
        return  Objects.equals(name, that.name) &&
                Objects.equals(shortName, that.shortName) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, shortName, description);
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile getPicture() {
        return picture;
    }

    public void setPicture(MultipartFile picture) {
        this.picture = picture;
    }

}
