package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.repository.ICarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarDeleteServiceImpl implements CarDeleteService {

    @Autowired
    ICarRepository carRepository;

    @Override
    public void deleteCarById(String carId) {
        carRepository.delete(carId);
    }
}
