package com.ldp.vigilantBean.domain;

import org.apache.logging.log4j.core.util.JsonUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.constraints.Digits;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

public class PictureTest {

    @Test
    @DisplayName("Should sort pictures by name")
    public void shouldSortPicturesByName() {

        List<Picture> picList = new ArrayList<>();
        IntStream.of(0, 1, 2)
                 .forEach((i) -> {
                     picList.add(i, new Picture());
                     picList.get(i).setName("1_" + String.valueOf(i + 1));
                 });

        Collections.shuffle(picList);
        Collections.sort(picList);

        IntStream.of(0, 1, 2)
                 .forEach((i) -> {
                     Assertions.assertEquals(picList.get(i).getName(), "1_" + String.valueOf(i + 1));
                 });
    }

    @Test
    @DisplayName("Should return valid full name")
    public void shouldReturnValidFullName() {

        List<Picture> pictures = List.of(
                new Picture(),
                new Picture(),
                new Picture()
        );
        String[] picNames = new String[]{
                "picName1",
                "myPicture",
                "special_Picture33"
        };
        String[] picExtensions = new String[]{
                "jpg",
                "png",
                "gif"
        };
        IntStream.of(0, 1, 2)
                 .forEach((i) -> {
                     pictures.get(i).setName(picNames[i]);
                     pictures.get(i).setExtension(picExtensions[i]);
                 });

        String[] expectedNames = new String[]{
            "picName1.jpg",
            "myPicture.png",
            "special_Picture33.gif"
        };

        IntStream.of(0, 1, 2)
                 .forEach((i) -> {
                    Assertions.assertEquals(expectedNames[i], pictures.get(i).getFullName());
                 });
    }

}
