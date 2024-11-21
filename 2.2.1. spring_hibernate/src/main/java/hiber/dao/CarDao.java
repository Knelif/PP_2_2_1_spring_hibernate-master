package hiber.dao;

import hiber.model.Car;
import hiber.model.User;

public interface CarDao {
    void addCarForUser (Car car);
    User getUserByCarModelAndSeries (String model, Integer series);
}
