package com.example.assignment3.batch;

import com.example.assignment3.model.Book;
import org.springframework.batch.item.ItemProcessor;

public class BookProcessor implements ItemProcessor<Book,Book> {

    @Override
    public Book process(Book book) {
//        book.setBookId(null);
        return book;
    }
}
