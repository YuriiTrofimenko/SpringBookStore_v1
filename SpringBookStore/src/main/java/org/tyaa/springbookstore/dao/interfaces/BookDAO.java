package org.tyaa.springbookstore.dao.interfaces;

import org.tyaa.springbookstore.entity.Author;
import org.tyaa.springbookstore.entity.Book;
import org.tyaa.springbookstore.entity.Genre;

import java.util.List;

public interface BookDAO {
    List<Book> getBooks();
    List<Book> getBooks(Author author);
    List<Book> getBooks(String bookName);
    List<Book> getBooks(Genre genre);
    List<Book> getBooks(Character letter);
    Object getFieldValue(Long bookId, String fieldName);
}
