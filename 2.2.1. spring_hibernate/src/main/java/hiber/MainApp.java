package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);
        CarService carService = context.getBean(CarService.class);
        List<User> userList = Arrays.asList(
                new User("User1", "Lastname1", "user1@mail.ru"),
                new User("User2", "Lastname2", "user2@mail.ru"),
                new User("User3", "Lastname3", "user3@mail.ru"),
                new User("User4", "Lastname4", "user4@mail.ru")
        );
        userList.forEach(userService::add);

        Stream.of(
                new Car("BMV", 1, userList.get(0)),
                new Car("Auds", 2, userList.get(1)),
                new Car("Volkswagen", 3, userList.get(2)),
                new Car("Vaz", 4, userList.get(3))
        ).forEach(carService::addCarForUser);

        userService.getAllUsers().forEach(System.out::println);

        System.out.printf("Getted user by model: %s & series: %d - %s", "Auds", 2, userService.findUserByCarDetails("Auds", 2));
        context.close();
    }
}
