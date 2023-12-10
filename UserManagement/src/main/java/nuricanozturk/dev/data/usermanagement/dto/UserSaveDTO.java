package nuricanozturk.dev.data.usermanagement.dto;

import nuricanozturk.dev.data.usermanagement.entity.Gender;

public record UserSaveDTO(
        String name,
        String surname,
        Gender gender,
        String username,
        String password,
        double budget
)
{

}
