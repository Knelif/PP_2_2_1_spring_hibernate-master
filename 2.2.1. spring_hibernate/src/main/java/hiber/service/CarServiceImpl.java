package hiber.service;

import hiber.dao.CarDao;
import hiber.dao.CarDaoImpl;
import hiber.model.Car;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CarServiceImpl implements CarService {

    private final CarDao carDao;

    @Autowired
    public CarServiceImpl(CarDaoImpl carDao) {
        this.carDao = carDao;
    }

    @Transactional
    @Override
    public void addCarForUser(Car car) {
        carDao.addCarForUser(car);
    }

}
