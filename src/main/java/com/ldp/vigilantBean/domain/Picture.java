package com.ldp.vigilantBean.domain;

import lombok.Builder;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Picture implements Comparable<Picture> {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "picture_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pictureId;

    @Column(name = "name")
    private String name;

    @Column(name = "extension")
    private String extension;

    @Column(name = "relative_path")
    private String relativePath;

    @Builder
    public Picture(String name, String extension, String relativePath) {
        this.name = name;
        this.extension = extension;
        this.relativePath = relativePath;
    }

    public Picture() {
    }

    @Override
    public int compareTo(Picture o) {
        return this.name.compareTo(o.getName());
    }

    public String getFullName() {
        return this.name + "." + this.extension;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Picture picture = (Picture) o;
        return Objects.equals(pictureId, picture.pictureId) &&
                Objects.equals(name, picture.name) &&
                Objects.equals(extension, picture.extension)
                && Objects.equals(relativePath, picture.relativePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pictureId, name, extension, relativePath);
    }

    public Long getPictureId() {
        return pictureId;
    }

    public void setPictureId(Long pictureId) {
        this.pictureId = pictureId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getRelativePath() {
        return relativePath;
    }

    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "pictureId=" + pictureId +
                ", name='" + name + '\'' +
                ", extension='" + extension + '\'' +
                ", relativePath='" + relativePath + '\'' +
                '}';
    }
}
