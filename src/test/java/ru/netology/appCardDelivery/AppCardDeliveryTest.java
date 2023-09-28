package ru.netology.appCardDelivery;

import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class AppCardDeliveryTest {

    private String testDate(int addDays) {

        Calendar catDay = Calendar.getInstance();
        catDay.roll(Calendar.DAY_OF_MONTH, +addDays);
        catDay.roll(Calendar.MONTH, +1);
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy ");
        String testDate = df.format(catDay.getTime());
        return testDate;
    }

    @Test
    void correctDataTest() {

        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id='date'] input").doubleClick().sendKeys(testDate(8));
        $("[data-test-id='name'] input").setValue("Римский-Корсаков Иван");
        $("[data-test-id='phone'] input").setValue("+79259876543");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='notification']").should(visible, Duration.ofSeconds(12));
    }
}
