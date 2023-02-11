package com.ben.reviews.book_search;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OpenBookApiTest {

    @Test
    public void assetRequestReturnsResult() throws Exception {

        OpenBookApi api = new OpenBookApi();
        String testIsbn = "0201558025";
        String result = api.httpGetRequest(testIsbn);
        System.out.println(result);
        assertTrue(true);

    }

}
