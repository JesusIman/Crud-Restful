package pe.edu.cibertec.Crud_Restful.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.Crud_Restful.dto.carDetailDto;
import pe.edu.cibertec.Crud_Restful.dto.carDto;
import pe.edu.cibertec.Crud_Restful.response.*;
import pe.edu.cibertec.Crud_Restful.service.ManageService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("manage-car")
public class ManageCarApi {

    @Autowired
    ManageService manageService;

    @GetMapping("/all")
    public findCarsResponce findCars() {

        try {
            List<carDto> cars = manageService.getAllcars();
            if (!cars.isEmpty())
                return new findCarsResponce("01", null, cars);
            else
                return new findCarsResponce("02", "Cars not found", null);

        } catch (Exception e) {
            e.printStackTrace();
            return new findCarsResponce("99", "An error ocurred, please try again", null);
        }

    }

    @GetMapping("/detail")
    public FindCarResponce findCars(@RequestParam(value = "id", defaultValue = "0") String id) {
        try {
            Optional<carDetailDto> optional = manageService.getcarById(Integer.parseInt(id));
            return optional.map(car ->
                    new FindCarResponce("01", null, car)
            ).orElse(
                    new FindCarResponce("02", "Car not found", null)
            );
        } catch (Exception e) {
            e.printStackTrace();
            return new FindCarResponce("99", "An error ocurred, please try again", null);
        }
    }

    @GetMapping("/update")
    public UpdateCarResponce updateCar(@RequestBody carDto CarDto) {

        try {
            if (manageService.updateCar(CarDto))
                return new UpdateCarResponce("01", null);
            else
                return new UpdateCarResponce("02", "Car not found");
        } catch (Exception e) {
            e.printStackTrace();
            return new UpdateCarResponce("99", "An error ocurred, please try again");
        }
    }

    @DeleteMapping("/delete/{id}")
    public DeleteCarResponce deleteCar(@PathVariable String id) {

        try {
            if (manageService.deleteCar(Integer.parseInt(id)))
                return new DeleteCarResponce("01", null);
            else
                return new DeleteCarResponce("02", "Car not found");
        } catch (Exception e) {
            e.printStackTrace();
            return new DeleteCarResponce("99", "An error ocurred, please try again");

        }
    }

    @PostMapping("/create")
    public CreateCarResponce createCar(@RequestBody carDetailDto CarDetailDto) {

        try {
            if (manageService.addCar(CarDetailDto))
                return new CreateCarResponce("01", null);
            else
                return new CreateCarResponce("02", "Car already exists");
        } catch (Exception e) {
            e.printStackTrace();
            return new CreateCarResponce("99", "An error ocurred, please try again");
        }
    }

}
