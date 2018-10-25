package org.tyaa.springbookstore.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.tyaa.springbookstore.dao.interfaces.BookDAO;
import org.tyaa.springbookstore.entity.Author;
import org.tyaa.springbookstore.entity.Book;

import java.util.List;

/**
 * Created by Yurii on 14.10.2016.
 *
 * фасад, общий для всех реализаций DAO
 */

@Component("commonFacade")
@Scope("singleton")
public class CommonFacade {

    @Autowired
    private BookDAO mBookDAO;

    private List<Book> books;

    @Autowired
    private SearchCriteria mSearchCriteria;

    public List<Book> getBooks() {
        if (books == null){
            books = mBookDAO.getBooks();
        }
        return books;
    }

    public void searchBooksByLetter() {
        books = mBookDAO.getBooks(mSearchCriteria.getLetter());
    }

    public void searchBooksByGenre() {
        books = mBookDAO.getBooks(mSearchCriteria.getGenre());
    }

    public void searchBooksByText() {

        switch (mSearchCriteria.getSearchType()){
            case TITLE:
                books = mBookDAO.getBooks(mSearchCriteria.getText());
                break;
            case AUTHOR:
                books = mBookDAO.getBooks(new Author(mSearchCriteria.getText()));
                break;
        }

    }

    //метод получения объекта из поля «контент» таблицы «Книги»
    public byte[] getContent(Long _bookId){
        return (byte[])mBookDAO.getFieldValue(_bookId, "content");
    }
}
