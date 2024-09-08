package net.therap.service;

import net.therap.entity.Book;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author shahriarmohaiminul
 * @since 9/8/24
 */
@Service
public class BookService {

    @PersistenceContext
    EntityManager entityManager;

    public Book findById(int id) {
        return entityManager.find(Book.class, id);
    }
}
