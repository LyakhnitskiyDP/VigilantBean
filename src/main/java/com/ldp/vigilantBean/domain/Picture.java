package com.ldp.vigilantBean.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Picture {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "picture_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pictureId;

    @Column(name = "name")
    private String name;

    @Column(name = "extension")
    private String extension;

    @Column(name = "relative_path")
    private String relativePath;

    public String getFullName() {
        return this.name + "." + this.extension;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Picture picture = (Picture) o;
        return Objects.equals(pictureId, picture.pictureId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pictureId);
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
}
