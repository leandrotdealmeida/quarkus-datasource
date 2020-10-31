package devjapa.com.datasource.data;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Book extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    public String name;
    public String author;
    public Integer pages;

    public Book() {
    }

    public String getName() {
        return name.toLowerCase();
    }

//    public static List<Book> findAllBooks() {
//        return findAll().list();
//    }
//
//    public static List<Book> findBooksByName(String name) {
//        return find("name", name).list();
//    }

}
