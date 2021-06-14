package ru.javamentor;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;
import ru.javamentor.configuration.MyConfig;
import ru.javamentor.entity.User;

import java.util.Objects;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        Communication communication = context.getBean("communication", Communication.class);
        HttpHeaders headers = new HttpHeaders();
        headers.put("Cookie", Objects.requireNonNull(communication.getAllUser().getHeaders().get("Set-Cookie")));
        User user = new User((long)3,"James", "Brown", (byte) 21);
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        System.out.println(communication.createUser(entity));
        user.setName("Thomas");
        user.setLastName("Shelby");
        System.out.println(communication.UpdateUser(entity));
        System.out.println(communication.DeleteUser(entity));

    }

}
