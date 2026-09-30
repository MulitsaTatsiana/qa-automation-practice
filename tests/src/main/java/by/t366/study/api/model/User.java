package by.t366.study.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Модель пользователя - вложенный объект user в ответе сервера.

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String id;
    private String email;
    private String lastName;
    private String firstName;
    private String middleName;
}
