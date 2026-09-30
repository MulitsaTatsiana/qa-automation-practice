package by.t366.study.api;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class InzhenerkaLoginTest {

    @Test
    void loginAndLogoutTest() {
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";

        // 1. Открыть страницу
        open("http://qa-stand-login.inzhenerka.tech/login");

        // 2. Вводим логин и пароль (данные берем из документации)
        $("#username").setValue("testuser");
        $(By.name("password")).setValue("password123"); // <-- Проверь пароль в документации!

        // 3. Нажимаем "Вход"
        $("button[type='submit']").click();

        // 4. ПРОВЕРКА (Assertion)
        // Мы ищем элемент body или заголовок, который содержит приветствие
        $("body").shouldHave(text("Привет, testuser"));

        // 5. Выход из системы
        // Используем поиск по тексту "Выйти" — это надежнее, чем искать по классам стилей (.mb-3)
        $(byText("Выйти")).click();

        // 6. Проверка выхода (снова видна кнопка "Вход")
        $("button[type='submit']").shouldBe(visible);
    }
}
