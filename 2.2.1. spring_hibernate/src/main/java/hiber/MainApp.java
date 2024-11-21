package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Stream;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        CarService carService = context.getBean(CarService.class);

        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
        User user3 = new User("User3", "Lastname3", "user3@mail.ru");
        User user4 = new User("User4", "Lastname4", "user4@mail.ru");
        Stream.of(user1, user2, user3, user4).forEach(userService::add);

        Car car1 = new Car("BMV", 1, user1);
        Car car2 = new Car("Auds", 2, user2);
        Car car3 = new Car("Volkswagen", 3, user3);
        Car car4 = new Car("Vaz", 4, user4);
        Stream.of(car1, car2, car3, car4).forEach(carService::addCarForUser);

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }

        System.out.printf("Getted user by model: %s & series: %d - %s", "Auds", 2, carService.getUserByCarModelAndSeries("Auds", 2));
        context.close();
    }
}
