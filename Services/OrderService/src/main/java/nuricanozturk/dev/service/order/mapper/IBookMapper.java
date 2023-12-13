package nuricanozturk.dev.service.order.mapper;

import nuricanozturk.dev.service.order.config.listenerdto.BookInfo;
import nuricanozturk.dev.service.order.entity.Book;
import org.mapstruct.Mapper;

@Mapper(implementationName = "BookMapperImpl", componentModel = "spring")
public interface IBookMapper
{
    Book toBook(BookInfo bookInfo);
}
