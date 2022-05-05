package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@UtilityClass
public class DataGen {

    @UtilityClass
    public static class PersonGen {
        public static PersonLoc personLocal(String locale) {
            Faker faker = new Faker(new Locale(locale));
            return new PersonLoc(faker.address().toString(),
                    faker.name().fullName(),
                    faker.phoneNumber().phoneNumber());
        }

        public String generateDate(int days) {
            return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        }
    }
}
