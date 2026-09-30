package by.t366.study.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.params.provider.Arguments;

import java.util.UUID;
import java.util.stream.Stream;

// Модель тела запроса POST /api/v1/users.
// Поля соответствуют JSON-схеме CreateUserRequest из Swagger.
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {

    private String email;
    private String password;
    private String lastName;
    private String firstName;
    private String middleName;


    public static CreateUserRequest newUser() {
        String email = "john" + UUID.randomUUID() + "@test.com";

        return CreateUserRequest.builder()
                .email(email)
                .password("12341")
                .lastName("John")
                .firstName("Ronin")
                .middleName("Vladimirovich")
                .build();
    }

    public static CreateUserRequest createUserWithInvalidEmailTest(String email) {
        CreateUserRequest request = CreateUserRequest
                .builder()
                .email(email)
                .password("12341")
                .lastName("")
                .firstName("")
                .middleName("")
                .build();
        return request;
    }

    public static CreateUserRequest userEmailLength251(String email) {
        return CreateUserRequest
                .builder()
                .email(email)
                .password("12341")
                .lastName("John")
                .firstName("Ronin")
                .middleName("Vladimirovich")
                .build();
    }

    public static CreateUserRequest defaultUser() {
        return CreateUserRequest
                .builder()
                .email("j" + UUID.randomUUID() + "@test.com")
                .password("J")
                .lastName("B")
                .firstName("J")
                .middleName("U")
                .build();
    }

    public static CreateUserRequest defaultUser2() {
        return CreateUserRequest
                .builder()
                .email("j2" + UUID.randomUUID() + "@test.com")
                .password("J2")
                .lastName("B2")
                .firstName("J2")
                .middleName("U2")
                .build();
    }

    public static CreateUserRequest defaultUser3() {
        return CreateUserRequest
                .builder()
                .email("j".repeat(249 - 36 - 9) + UUID.randomUUID() + "@test.com")
                .password("J".repeat(49))
                .lastName("B".repeat(49))
                .firstName("J".repeat(49))
                .middleName("U".repeat(49))
                .build();
    }

    public static CreateUserRequest defaultUser4() {
        return CreateUserRequest
                .builder()
                .email("j".repeat(250 - 36 - 9) + UUID.randomUUID() + "@test.com")
                .password("J".repeat(50))
                .lastName("B".repeat(50))
                .firstName("J".repeat(50))
                .middleName("U".repeat(50))
                .build();
    }

    public static CreateUserRequest defaultUser5() {
        return CreateUserRequest
                .builder()
                .email("j".repeat(40) + UUID.randomUUID() + "@test.com")
                .password("J".repeat(20))
                .lastName("B".repeat(20))
                .firstName("J".repeat(20))
                .middleName("U".repeat(20))
                .build();
    }

    public static CreateUserRequest defaultUser6() {
        return CreateUserRequest
                .builder()
                .email("")
                .password("J".repeat(20))
                .lastName("B".repeat(20))
                .firstName("J".repeat(20))
                .middleName("U".repeat(20))
                .build();
    }

    public static CreateUserRequest defaultUser10(String email, String password, String lastName, String firstName, String middleName) {
        return CreateUserRequest
                .builder()
                .email(email)
                .password(password)
                .lastName(lastName)
                .firstName(firstName)
                .middleName(middleName)
                .build();
    }

    public static CreateUserRequest userCsv(String email, String password, String lastName, String firstName, String middleName) {
        return CreateUserRequest
                .builder()
                .email(email)
                .password(password)
                .lastName(lastName)
                .firstName(firstName)
                .middleName(middleName)
                .build();
    }

    public static Stream<Arguments> positiveUserMethodSource() {
        return Stream.of(

                Arguments.of("john" + UUID.randomUUID() + "@test.com", "ghhh", "fcrdy", "fcdff", "hggg"),

                Arguments.of("j".repeat(1) + UUID.randomUUID() + "@test.com", "pass2", "smith", "ann", "jane"),
                Arguments.of("joh" + UUID.randomUUID() + "@test.com", "1", "pass2", "smith", "ann"),
                Arguments.of("ohn" + UUID.randomUUID() + "@test.com", "fr", "2", "smith", "ann"),
                Arguments.of("john" + UUID.randomUUID() + "@test.com", "smith", "pass2", "3", "ann"),
                Arguments.of("john" + UUID.randomUUID() + "@test.com", "smith", "pass2", "smith", "4"),

                Arguments.of("po".repeat(2) + "@test.com", "pass2", "smith", "ann", "jane"),
                Arguments.of("joh" + UUID.randomUUID() + "@test.com", "12", "pass2", "smith", "ann"),
                Arguments.of("ohn" + UUID.randomUUID() + "@test.com", "fr", "22", "smith", "ann"),
                Arguments.of("john" + UUID.randomUUID() + "@test.com", "smith", "pass2", "32", "ann"),
                Arguments.of("john" + UUID.randomUUID() + "@test.com", "smith", "pass2", "smith", "42"),

                Arguments.of("u".repeat(249 - 36 - 9) + UUID.randomUUID() + "@test.com", "pass2", "smith", "ann", "jane"),
                Arguments.of("joh" + UUID.randomUUID() + "@test.com", "e".repeat(49), "pass2", "smith", "ann"),
                Arguments.of("ohn" + UUID.randomUUID() + "@test.com", "fr", "2".repeat(49), "smith", "ann"),
                Arguments.of("john" + UUID.randomUUID() + "@test.com", "smith", "pass2", "3".repeat(49), "ann"),
                Arguments.of("john" + UUID.randomUUID() + "@test.com", "smith", "pass2", "smith", "4".repeat(49)),

                Arguments.of("q".repeat(250 - 36 - 9) + UUID.randomUUID() + "@test.com", "pass2", "smith", "ann", "jane"),
                Arguments.of("joh" + UUID.randomUUID() + "@test.com", "e".repeat(50), "pass2", "smith", "ann"),
                Arguments.of("ohn" + UUID.randomUUID() + "@test.com", "fr", "2".repeat(50), "smith", "ann"),
                Arguments.of("john" + UUID.randomUUID() + "@test.com", "smith", "pass2", "3".repeat(50), "ann"),
                Arguments.of("john" + UUID.randomUUID() + "@test.com", "smith", "pass2", "smith", "4".repeat(50)),

                Arguments.of("john" + UUID.randomUUID() + "@test.com", ".", "|", "\uD83D\uDE09\"", "Ролл")
        );
    }

    public static Stream<Arguments> negativeUserMethodSource() {
        return Stream.of(

                Arguments.of(null, "pass2", "smith", "ann", "jane"),
                Arguments.of("john" + UUID.randomUUID() + "@test.com", null, "pass2", "smith", "ann"),
                Arguments.of("john" + UUID.randomUUID() + "@test.com", "fr", null, "smith", "ann"),
                Arguments.of("john" + UUID.randomUUID() + "@test.com", "smith", "pass2", null, "ann"),
                Arguments.of("john" + UUID.randomUUID() + "@test.com", "smith", "pass2", "smith", null),

                Arguments.of("", "@test.com", "pass2", "smith", "ann"),
                Arguments.of("john" + UUID.randomUUID() + "@test.com", "", "pass2", "smith", "ann"),
                Arguments.of("john" + UUID.randomUUID() + "@test.com", "fr", "", "smith", "ann"),
                Arguments.of("john" + UUID.randomUUID() + "@test.com", "smith", "pass2", "", "ann"),
                Arguments.of("john" + UUID.randomUUID() + "@test.com", "smith", "pass2", "smith", ""),

                Arguments.of("qq".repeat(251 - 36) + UUID.randomUUID() + "@test.com", "pass2", "smith", "ann", "jane"),
                Arguments.of("ohn" + UUID.randomUUID() + "@test.com", "fr", "2".repeat(51), "smith", "ann"),
                Arguments.of("john" + UUID.randomUUID() + "@test.com", "smith", "pass2", "3".repeat(51), "ann"),
                Arguments.of("john" + UUID.randomUUID() + "@test.com", "smith", "pass2", "smith", "4".repeat(51)),

                Arguments.of("john" + UUID.randomUUID() + "test.com", "ghhh", "fcrdy", "fcdff", "hggg"),
                Arguments.of("john" + UUID.randomUUID() + "@", "ghhh", "fcrdy", "fcdff", "hggg")

        );
    }


}
