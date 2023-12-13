package nuricanozturk.dev.service.book.controller;

import nuricanozturk.dev.service.book.dto.BookSaveDTO;
import nuricanozturk.dev.service.book.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/book")
public class BookController
{
    private final BookService bookService;

    public BookController(BookService bookService)
    {
        this.bookService = bookService;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody BookSaveDTO bookSaveDTO)
    {
        return ResponseEntity.ok(bookService.saveBook(bookSaveDTO));
    }
}
