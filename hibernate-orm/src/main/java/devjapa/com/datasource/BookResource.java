package devjapa.com.datasource;

import devjapa.com.datasource.data.Author;
import devjapa.com.datasource.data.Book;
import devjapa.com.datasource.data.repository.BookRepository;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import io.quarkus.runtime.StartupEvent;
import org.hibernate.search.mapper.orm.Search;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Path("/book")
public class BookResource {

    @Inject
    BookRepository bookRepository;

    @Inject
    EntityManager entityManager;

    public void onStart(@Observes StartupEvent startupEvent) throws InterruptedException {
        if(Book.count() > 0) {
            Search.session(entityManager)
                    .massIndexer()
                    .startAndWait();
        }

    }

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

    @Path("/author/search")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public List<Author> searchForAuthor(@QueryParam("pattern") String pattern) {

        return Search.session(entityManager)
                .search(Author.class)
                .predicate(pred ->
                        pattern == null || pattern.trim().isEmpty()
                                ? pred.matchAll()
                                : pred.simpleQueryString().fields("firstName", "lastName", "books.title").matching(pattern))
                .sort(sort -> sort.field("firstName_sort").then().field("lastName_sort"))
                .fetchAll()
                .getHits();
        }



}