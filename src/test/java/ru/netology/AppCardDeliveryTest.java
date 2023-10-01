package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class AppCardDeliveryTest {

        public String generateDate( int days) {
            return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        }

    @Test
    void correctDataTest() {

        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id='date'] input").doubleClick().sendKeys(generateDate(8));
        $("[data-test-id='name'] input").setValue("Римский-Корсаков Иван");
        $("[data-test-id='phone'] input").setValue("+79259876543");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='notification']").shouldHave(Condition.text("Встреча успешно забронирована на " + generateDate(8)), Duration.ofSeconds(15)).should(visible);
    }
}
