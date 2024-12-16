package pe.edu.cibertec.Crud_Restful.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.Crud_Restful.dto.carDetailDto;
import pe.edu.cibertec.Crud_Restful.dto.carDto;
import pe.edu.cibertec.Crud_Restful.entity.Car;
import pe.edu.cibertec.Crud_Restful.repository.carRepository;
import pe.edu.cibertec.Crud_Restful.service.ManageService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ManageServiceImpl implements ManageService {
    @Autowired
    carRepository CarRepository;

    @Override
    public List<carDto> getAllcars() throws Exception {
        List<carDto> cars = new ArrayList<carDto>();
        Iterable<Car> iterable = CarRepository.findAll();
        iterable.forEach(car -> {
            cars.add(new carDto(car.getCarId(),
                    car.getModel(),
                    car.getVin(),
                    car.getLicensePlate()));
        });
        return cars;

    }

    @Override
    public Optional<carDetailDto> getcarById(int id) throws Exception {


        Optional<Car> optional = CarRepository.findById(id);
        return optional.map(car -> new carDetailDto(car.getCarId(),
                car.getMake(),
                car.getModel(),
                car.getYear(),
                car.getVin(),
                car.getLicensePlate(),
                car.getOwnerName(),
                car.getOwnerContact(),
                car.getPurchaseDate(),
                car.getMileage(),
                car.getEngineType(),
                car.getColor(),
                car.getInsuranceCompany(),
                car.getInsurancePolicyNumber(),
                car.getRegistrationExpirationDate(),
                car.getServiceDueDate()));
    }

    @Override
    public boolean updateCar(carDto CarDto) throws Exception {

        Optional<Car> optional = CarRepository.findById(CarDto.carId());
        return optional.map(car -> {
            car.setModel(CarDto.model());
            car.setVin(CarDto.vin());
            car.setLicensePlate(CarDto.licensePlate());
            car.setPurchaseDate(new Date());
            CarRepository.save(car);
            return true;
        }).orElse(false);

    }

    @Override
    public boolean deleteCar(int id) throws Exception {
        Optional<Car> optional = CarRepository.findById(id);
        return optional.map(car -> {
            CarRepository.delete(car);
            return true;
        }).orElse(false);
    }

    @Override
    public boolean addCar(carDetailDto CarDetailDto) throws Exception {

        Optional<Car> optional = CarRepository.findById(CarDetailDto.carId());
        if (optional.isPresent()) {
            Car car = new Car();
            car.setCarId(CarDetailDto.carId());
            car.setMake(CarDetailDto.make());
            car.setModel(CarDetailDto.model());
            car.setYear(CarDetailDto.year());
            car.setVin(CarDetailDto.vin());
            car.setLicensePlate(CarDetailDto.licensePlate());
            car.setOwnerName(CarDetailDto.ownerName());
            car.setOwnerContact(CarDetailDto.ownerContact());
            car.setPurchaseDate(new Date());
            car.setMileage(CarDetailDto.mileage());
            car.setEngineType(CarDetailDto.engineType());
            car.setColor(CarDetailDto.color());
            car.setInsuranceCompany(CarDetailDto.insuranceCompany());
            car.setInsurancePolicyNumber(CarDetailDto.insurancePolicyNumber());
            car.setRegistrationExpirationDate(CarDetailDto.registrationExpirationDate());
            car.setServiceDueDate(CarDetailDto.serviceDueDate());
            CarRepository.save(car);
            return true;
        }
        return false;
    }
}

