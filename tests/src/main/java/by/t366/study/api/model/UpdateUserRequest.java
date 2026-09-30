package by.t366.study.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.params.provider.Arguments;

import java.util.UUID;
import java.util.stream.Stream;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateUserRequest {
    private String email;
    private String lastName;
    private String firstName;
    private String middleName;


    public static UpdateUserRequest user( String email, String lastName, String firstName, String middleName) {
        return UpdateUserRequest.builder()
                .email(email)
                .lastName(lastName)
                .firstName(firstName)
                .middleName(middleName)
                .build();
    }

    public static Stream<Arguments> positiveUserUpdate() {
        return Stream.of(
                Arguments.of(UpdateUserRequest.builder()
                        .email("john" + UUID.randomUUID() + "@test.com")
                        .lastName("Smith")
                        .build()),
                Arguments.of(UpdateUserRequest.builder()
                        .firstName("Anna")
                        .build())
        );
    }
}