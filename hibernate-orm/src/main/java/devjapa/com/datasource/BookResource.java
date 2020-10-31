package devjapa.com.datasource;

import devjapa.com.datasource.data.Book;
import devjapa.com.datasource.data.repository.BookRepository;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Path("/book")
public class BookResource {

    @Inject
    BookRepository bookRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public List<Book> hello() {
        // Book.update("name = 'Bob' where name = ?1", "DevJapa");
//        final Stream<Book> panacheEntitBookStream = Book.streamAll();
//        final List<Book> japa = panacheEntitBookStream.filter(it -> it.name.equals("japa")).collect(Collectors.toList());
//
//        final PanacheQuery<Book> query = Book.find("author", "japa");
//        query.page(Page.of(2, 50));
//        final List<Book> list = query.list();
//        query.nextPage();
//        query.pageCount();
//
//        Book.listAll(Sort.by("name").and("author"));

        return bookRepository.findAllBooks();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Book saveBook(Book book) {
        book.persist();
        return book;
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Book updateBook(@PathParam("id") Integer id, Book book) {
        System.out.println("LOG");

        return book;
    }

    @Path("/{id}")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public boolean deleteBook(@PathParam("id") Integer id) {
        bookRepository.deleteById(id);
        return true;
    }
}