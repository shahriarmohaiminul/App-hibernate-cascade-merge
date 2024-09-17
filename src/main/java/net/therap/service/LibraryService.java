package net.therap.service;

import net.therap.entity.Author;
import net.therap.entity.Book;
import net.therap.entity.Library;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author shahriarmohaiminul
 * @since 9/7/24
 */
@Service
public class LibraryService {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private BookService bookService;

    @Transactional
    public Library save(Library library) {

        entityManager.persist(library);

        return library;
    }

    public Library getLibraryWithBookAndAuthor(int id) {

        Library library = findById(id);

        library.getBooks().forEach(
                book -> Hibernate.initialize(book.getAuthors())
        );

        return library;
    }


    public Library findById(int id) {

        return entityManager.find(Library.class, id);
    }

    public List<Library> getAllLibrary() {
        TypedQuery<Library> query = entityManager.createQuery("SELECT library FROM Library library ORDER BY library.updated desc", Library.class);

        return query.getResultList();
    }

    @Transactional
    public Library update(Library library) {

        library = prepareForUpdate(library);

        return entityManager.merge(library);
    }

    private Library prepareForUpdate(Library library) {

        library.setUpdated(new Date());
        library.setUpdatedBy("admin");
        library.increaseUpdateCount();

        for (Book book : getBooks(library)) {
            book.setUpdated(new Date());
            book.setUpdatedBy("book admin");
            book.increaseUpdateCount();
        }

        for (Book book : getBooks(library)) {

            for (Author author : getAuthors(book)) {
                author.setUpdatedBy("author admin");
                author.setUpdated(new Date());
                author.increaseUpdateCount();
            }
        }

        return library;
    }


    private List<Book> getBooks(Library library) {
        List<Book> books = new ArrayList<>();

        for (Book book : library.getBooks()) {
            books.add(book);
        }

        return books;
    }

    private List<Author> getAuthors(Book book) {
        List<Author> authors = new ArrayList<>();

        for (Author author : book.getAuthors()) {
            authors.add(author);
        }

        return authors;
    }
}

