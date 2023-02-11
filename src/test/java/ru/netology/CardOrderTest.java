package ru.netology;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardOrderTest {
    @Test
    void shouldSubmitForm(){
        open("http://localhost:7777");
        $("[data-test-id=name] input").setValue("Казимир Алмазов");
        $("[data-test-id=phone] input").setValue("+79876543211");
        $("[data-test-id=agreement]").click();
        $("button[type=button]").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldHaveNameFieldFilled(){
        open("http://localhost:7777");
        $("[data-test-id=phone] input").setValue("+79876543211");
        $("[data-test-id=agreement]").click();
        $("button[type=button]").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldHavePhoneFieldFilled(){
        open("http://localhost:7777");
        $("[data-test-id=name] input").setValue("Казимир Алмазов");
        $("[data-test-id=agreement]").click();
        $("button[type=button]").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldHavePhoneFieldRightFilled(){
        open("http://localhost:7777");
        $("[data-test-id=name] input").setValue("Казимир Алмазов");
        $("[data-test-id=phone] input").setValue("+7987654321");
        $("[data-test-id=agreement]").click();
        $("button[type=button]").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldHaveCyrillicName(){
        open("http://localhost:7777");
        $("[data-test-id=name] input").setValue("Kazimir Almazov");
        $("[data-test-id=phone] input").setValue("+79876543211");
        $("[data-test-id=agreement]").click();
        $("button[type=button]").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldHaveCheckBoxPressed(){
        open("http://localhost:7777");
        $("[data-test-id=name] input").setValue("Казимир Алмазов");
        $("[data-test-id=phone] input").setValue("+79876543211");
        $("button[type=button]").click();
        $("[data-test-id=agreement]").shouldHave(cssClass("input_invalid"));
    }
}
