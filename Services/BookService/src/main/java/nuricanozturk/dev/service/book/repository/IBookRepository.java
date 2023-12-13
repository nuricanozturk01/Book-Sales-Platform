package nuricanozturk.dev.service.book.repository;


import nuricanozturk.dev.service.book.entity.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IBookRepository extends CrudRepository<Book, UUID>
{
    @Query("SELECT b FROM Book b WHERE b.bookIsbn = :isbn")
    Iterable<Book> findAllByBookIsbn(UUID isbn);

    @Query("SELECT b FROM Book b WHERE b.bookPageCount = :bookPageCount")
    Iterable<Book> findAllByBookPageCount(@Param("bookPageCount") int bookPageCount);

    Optional<Book> findBooksByBookName(@Param("bookName") String bookName);

    Iterable<Book> findBooksByAuthorName(@Param("authorName") String authorName);

    Iterable<Book> findBooksByPublisherName(@Param("publisherName") String publisherName);

    @Query("SELECT b FROM Book b WHERE b.price = :price")
    Iterable<Book> findAllByPrice(double price);

    @Query("SELECT b FROM Book b WHERE b.price BETWEEN :minPrice AND :maxPrice")
    Iterable<Book> findAllByPriceBetween(double minPrice, double maxPrice);

    @Query("SELECT b FROM Book b WHERE b.price >= :price")
    Iterable<Book> findAllByPriceGreaterThanEqual(@Param("price") double minPrice);

    @Query("SELECT b FROM Book b WHERE b.price <= :price")
    Iterable<Book> findAllByPriceLessThanEqual(@Param("price") double maxPrice);
}
