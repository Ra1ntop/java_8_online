package ua.com.alevel.depends;

import org.apache.commons.text.RandomStringGenerator;
import org.apache.commons.lang3.Validate;
public class random {

    public void console(String msg) {
        RandomStringGenerator generator = new RandomStringGenerator.Builder()
                .withinRange('a', 'z')
                .build();
        String randomString = generator.generate(10);
        System.out.println("Случайная строка: " + randomString);
    }

}
