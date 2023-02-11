package com.ben.reviews.book_search;

import com.ben.reviews.book_search.json_model.Book;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;

@Service
public class OpenLibraryService {

    private OpenBookApi openBookApi;
    public OpenLibraryService(){
       this.openBookApi = new OpenBookApi();
    }

    public Book getBook (String isbn) throws URISyntaxException, IOException, InterruptedException {
        Book book = this.openBookApi.httpGetRequest(isbn);
        return book;
    }

}
