package ru.netology;

import com.codeborne.selenide.Condition;
import ru.netology.data.DataGen;
import ru.netology.data.PersonLoc;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class AppDeliveryTest {


    @Test
    public void ShouldSubmitRequest() {
        PersonLoc persona = DataGen.PersonGen.personLocal("ru");
        String dateMeetin = DataGen.PersonGen.generateDate(3);
        open("http://localhost:9999/");
        $("input[type='text']").setValue("Архангельск");
        $("input[type='tel']").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME));
        $("input[type='tel']").sendKeys(Keys.BACK_SPACE);
        $("input[type='tel']").setValue(dateMeetin);
        $("input[type='text'][name='name']").setValue(persona.getName());
        $("input[name='phone']").setValue(persona.getCity());
        $("label[data-test-id='agreement']").click();
        $(withText("Запланировать")).click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(5));
        $(".notification__content").shouldHave(Condition.text("Встреча успешно запланирована на " + dateMeetin));
        dateMeetin = DataGen.PersonGen.generateDate(5);
        $("input[type='tel']").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME));
        $("input[type='tel']").sendKeys(Keys.BACK_SPACE);
        $("input[type='tel']").setValue(dateMeetin);
        $x("/html/body/div[1]/div/form/fieldset/div[6]/div[2]/div/button").click();
        $(withText("Необходимо подтверждение")).shouldBe(visible);
        $(byText("У вас уже запланирована встреча на другую дату. Перепланировать?")).shouldBe(visible);
        $(withText("Запланировать")).click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $(".notification__content").shouldHave(Condition.text("Встреча успешно запланирована на " + dateMeetin));

    }

}
