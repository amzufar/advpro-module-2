package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.ICarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarUpdateServiceImpl implements CarUpdateService {

    @Autowired
    ICarRepository carRepository;

    @Override
    public void update(String carId, Car car) {
        carRepository.update(carId, car);
    }
}
