package nuricanozturk.dev.service.order.mapper;

import nuricanozturk.dev.service.order.config.listenerdto.UserInfo;
import nuricanozturk.dev.service.order.entity.User;
import org.mapstruct.Mapper;

@Mapper(implementationName = "UserMapperImpl", componentModel = "spring")
public interface IUserMapper
{
    User toUser(UserInfo userInfo);
}
