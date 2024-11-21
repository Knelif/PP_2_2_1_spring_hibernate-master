package hiber.service;

import hiber.model.Car;
import hiber.model.User;

public interface CarService {
    void addCarForUser (Car car);
    User getUserByCarModelAndSeries (String model, Integer series);
}
