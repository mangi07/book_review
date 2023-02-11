package com.ben.reviews.book_search;

import com.ben.reviews.book_search.json_model.Book;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OpenBookApiTest {

    @Test
    public void assetRequestReturnsResult() throws Exception {

        OpenBookApi api = new OpenBookApi();
        String testIsbn = "0201558025";
        Book book = api.httpGetRequest(testIsbn);
        System.out.println(book);
        assertTrue(true);

    }

}
