package nuricanozturk.dev.service.book.mapper;


import nuricanozturk.dev.service.book.dto.BookSaveDTO;
import nuricanozturk.dev.service.book.dto.BookViewDTO;
import nuricanozturk.dev.service.book.dto.BooksViewDTO;
import nuricanozturk.dev.service.book.entity.Book;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(implementationName = "BookMapperImpl", componentModel = "spring")
public interface IBookMapper
{

    BookViewDTO toBookDTO(Book book);

    Book toBook(BookSaveDTO bookSaveDTO);

    default List<Book> toBookList(List<BookSaveDTO> bookSaveDTOs)
    {
        return bookSaveDTOs.stream().map(this::toBook).toList();
    }

    default BooksViewDTO toBooksViewDTO(List<BookViewDTO> bookViewDTOs)
    {
        return new BooksViewDTO(bookViewDTOs);
    }
}
