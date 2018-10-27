package org.tyaa.springbookstore.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.tyaa.springbookstore.dao.interfaces.BookDAO;
import org.tyaa.springbookstore.entity.Author;
import org.tyaa.springbookstore.entity.Book;
import org.tyaa.springbookstore.entity.Genre;

import java.util.List;

@Component
public class BookDAOImpl implements BookDAO {

    @Autowired
    private SessionFactory sessionFactory;

    //private List<Book> books;

    //поле хранения списка проекций для сущности "Книга"
    private ProjectionList bookProjection;

    //конструктор для инициализации списка проекций
    public BookDAOImpl() {
        bookProjection = Projections.projectionList();
        bookProjection.add(Projections.property("id"), "id");
        bookProjection.add(Projections.property("name"), "name");
        bookProjection.add(Projections.property("image"), "image");
        bookProjection.add(Projections.property("genre"), "genre");
        bookProjection.add(Projections.property("pageCount"), "pageCount");
        bookProjection.add(Projections.property("isbn"), "isbn");
        bookProjection.add(Projections.property("publisher"), "publisher");
        bookProjection.add(Projections.property("author"), "author");
        bookProjection.add(Projections.property("publishYear"), "publishYear");
        bookProjection.add(Projections.property("descr"), "descr");
    }

    @Transactional
    @Override
    public List<Book> getBooks() {

        /*полная загрузка данных*/
        /*books = (List<Book>) sessionFactory.getCurrentSession()
                .createCriteria(Book.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();*/
        /*загрузка данных, соответствующих проекциям*/
        /*загрузка данных, соответствующих проекциям*/
        //создание заготовки отсоедиенного объекта запроса
        DetachedCriteria bookListCriteria = DetachedCriteria.forClass(Book.class, "b");
        //вызываем собственнвй метод создания псевдонимов для сущностей
        createAliases(bookListCriteria);
        //получаем список книг, выполнив запрос, настроенный проекцией
        List<Book> books = createBookList(bookListCriteria);
        System.out.println(books.get(0));
        //возвращаем заполненный должным образом список книг
        return books;
    }

    /*
    * Методы получения списка книг по наличию искомой подстроки в названии
    * или в жанре или в ФИО автора, или по первой букве в названии
    */

    @Transactional
    @Override
    public List<Book> getBooks(Author author) {
        List books = createBookList(
                createBookCriteria()
                        .add(Restrictions.and(
                                Restrictions.ilike("author.fio", author.getFio(), MatchMode.ANYWHERE),
                                Restrictions.sqlRestriction("fio REGEXP ?", "[A-zА-я]{1,}", StringType.INSTANCE)))
        );
        return books;
    }

    @Transactional
    @Override
    public List<Book> getBooks(String bookName) {
        List<Book> books = createBookList(
                createBookCriteria()
                        .add(Restrictions
                                .ilike("b.name", bookName, MatchMode.ANYWHERE))
        );
        return books;
    }

    @Transactional
    @Override
    public List<Book> getBooks(Genre genre) {
        List<Book> books = createBookList(
                createBookCriteria()
                        .add(Restrictions
                                .eq("genre.id", genre.getId()))
        );
        return books;
    }

    @Transactional
    @Override
    public List<Book> getBooks(Character letter) {
        List<Book> books = createBookList(
                createBookCriteria()
                        .add(Restrictions
                                .ilike("b.name", letter.toString(), MatchMode.START))
        );
        return books;

    }

    //Вспомогательный метод создания отсоединенного объекта запроса
    private DetachedCriteria createBookCriteria() {
        DetachedCriteria bookListCriteria =
                DetachedCriteria.forClass(Book.class, "b");
        createAliases(bookListCriteria);
        return bookListCriteria;
    }

    //Вспомогательный метод создания псевдонимов для сущностей
    private void createAliases(DetachedCriteria criteria) {
        criteria.createAlias("b.author", "author");
        criteria.createAlias("b.genre", "genre");
        criteria.createAlias("b.publisher", "publisher");
    }

    //Вспомогательный метод заполнения списка сущностей "Книга"
    //на основе запроса, настроенного проекцией, выбирающей значения только нужных полей
    private List createBookList(DetachedCriteria bookListCriteria) {
        Criteria criteria =
                bookListCriteria.getExecutableCriteria(sessionFactory.getCurrentSession());
        criteria.addOrder(Order.asc("b.name"))
                .setProjection(bookProjection)
                .setResultTransformer(Transformers.aliasToBean(Book.class));
        System.out.println(bookProjection.getProjection(0));
        return criteria.list();
    }

    //метод получения значения из любого поля таблицы в виде объекта
    //_itemId - ID записи, _fieldName - название поля
    @Transactional
    @Override
    public Object getFieldValue(Long _bookId, String _fieldName) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Book.class);
        criteria.setProjection(Property.forName(_fieldName));
        criteria.add(Restrictions.eq("id", _bookId));
        return criteria.uniqueResult();
    }
}
