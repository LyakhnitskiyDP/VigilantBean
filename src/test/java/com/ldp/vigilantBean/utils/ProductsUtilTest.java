package com.ldp.vigilantBean.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Collection;
import java.util.List;

public class ProductsUtilTest {


    @Nested
    @DisplayName("Testing getPageList method")
    class PageListMethod {

        @Nested
        @DisplayName("Testing with 7 products per page")
        class With7ProductsPerPage {

            @Test
            @DisplayName("Page list should be empty as 4 < 7")
            public void testCalculatingNumberOfPagesWith7ProductsPerPageAnd4Products() {
                int productsPerPage = 7;
                int amountOfProducts = 4;

                List<Integer> pageList =
                        ProductsUtil.getPageList(amountOfProducts, productsPerPage);

                Assertions.assertEquals(0, pageList.size());
            }

            @Test
            @DisplayName("Page list should be {1, 2} as ceil(13 / 7) == 2")
            public void testCalculatingNumberOfPagesWith7ProductsPerPageAnd13Products() {
                int productsPerPage = 7;
                int amountOfProducts = 13;

                List<Integer> pageList =
                        ProductsUtil.getPageList(amountOfProducts, productsPerPage);
                List<Integer> expectedList =
                        List.of(1, 2);

                assertDeepEquals(expectedList, pageList);
            }

            @Test
            @DisplayName("Page list should be {1, 2, 3, 4, 5} as ceil(35 / 7) == 5")
            public void testCalculatingNumberOfPagesWith7ProductsPerPageAnd35Products() {
                int productsPerPage = 7;
                int amountOfProducts = 35;

                List<Integer> pageList =
                        ProductsUtil.getPageList(amountOfProducts, productsPerPage);
                List<Integer> expectedList =
                        List.of(1, 2, 3, 4, 5);

                assertDeepEquals(expectedList, pageList);
            }
        }
    }

    private void assertDeepEquals(List expected, List actual) {

        Assertions.assertEquals(expected.size(), actual.size());

        for (int i = 0; i < expected.size(); i++)
            Assertions.assertEquals(expected.get(i), actual.get(i));

    }

    @Nested
    @DisplayName("Testing getFirstProductIndex method")
    class GetFirstProductIndexMethod {

        @Test
        @DisplayName("Should return 1 as the page is 1")
        public void testGettingProductIndexWithFirstPage() {

            int pageNumber = 1;
            int pageSize = 8;

            int firstProductIndex =
                    ProductsUtil.getFirstProductIndex(pageNumber, pageSize);

            Assertions.assertEquals(1, firstProductIndex);
        }

        @Test
        @DisplayName("Should return 8 as the page is 2 and pageSize is 7")
        public void testGettingProductIndexWithSecondPageAnd7ProductsPerPage() {

            int pageNumber = 2;
            int pageSize = 7;

            int firstProductIndex =
                    ProductsUtil.getFirstProductIndex(pageNumber, pageSize);

            Assertions.assertEquals(8, firstProductIndex);
        }


        @Test
        @DisplayName("Should return 13 as the page is 5 and pageSize is 3")
        public void testGettingProductIndexWithPage5And3ProductsPerPage() {

            int pageNumber = 5;
            int pageSize = 3;

            int firstProductIndex =
                    ProductsUtil.getFirstProductIndex(pageNumber, pageSize);

            Assertions.assertEquals(13, firstProductIndex);
        }
    }




}
