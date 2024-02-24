package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/car")
public class CarController {

    @Autowired
    CarCreationService carCreationService;

    @Autowired
    CarDeleteService carDeleteService;

    @Autowired
    CarRetrievalService carRetrievalService;

    @Autowired
    CarUpdateService carUpdateService;

    @GetMapping("/create")
    public String createCarPage(Model model) {
        Car car = new Car();
        model.addAttribute("car", car);
        return "createcar";
    }

    @PostMapping("/create")
    public String createCarPost(@ModelAttribute Car car, Model model) {
        carCreationService.create(car);
        return "redirect:list";
    }

    @GetMapping("/list")
    public String carListPage(Model model) {
        List<Car> allCars = carRetrievalService.findAll();
        model.addAttribute("cars", allCars);
        return "carlist";
    }

    @GetMapping("/edit/{carId}")
    public String editCarPage(@PathVariable String carId, Model model) {
        Car car = carRetrievalService.findById(carId);
        model.addAttribute("car", car);
        return "editcar";
    }

    @PostMapping("/edit")
    public String editCarPost(@ModelAttribute Car updatedCar, Model model) {
        System.out.println(updatedCar.getCarId());
        carUpdateService.update(updatedCar.getCarId(),  updatedCar);
        return "redirect:/car/list";
    }

    @PostMapping("/delete")
    public String deleteCar(@RequestParam("carId") String carId) {
        carDeleteService.deleteCarById(carId);
        return "redirect:/car/list";
    }
}
