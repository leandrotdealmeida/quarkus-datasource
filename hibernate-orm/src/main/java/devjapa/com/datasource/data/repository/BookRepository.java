package devjapa.com.datasource.data.repository;


import devjapa.com.datasource.data.Book;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class BookRepository implements PanacheRepositoryBase<Book, Integer> {

    public List<Book> findAllBooks() {
        return findAll().list();
    }

    public  List<Book> findBooksByName(String name) {
        return find("name", name).list();
    }

}
