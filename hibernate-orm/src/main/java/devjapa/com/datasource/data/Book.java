package devjapa.com.datasource.data;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;


@Entity
@Indexed
public class Book extends PanacheEntity {

    @FullTextField(analyzer = "english")
    public String title;

    @ManyToOne
    @JsonbTransient
    public Author author;

    public Book() {
    }

//    public String getName() {
//        return name.toLowerCase();
//    }

//    public static List<Book> findAllBooks() {
//        return findAll().list();
//    }
//
//    public static List<Book> findBooksByName(String name) {
//        return find("name", name).list();
//    }

}
