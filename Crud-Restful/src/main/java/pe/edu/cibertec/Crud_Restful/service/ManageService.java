package pe.edu.cibertec.Crud_Restful.service;

import pe.edu.cibertec.Crud_Restful.dto.carDetailDto;
import pe.edu.cibertec.Crud_Restful.dto.carDto;

import java.util.List;
import java.util.Optional;

public interface ManageService {

    List<carDto> getAllcars() throws Exception;

    Optional<carDetailDto> getcarById(int id) throws Exception;

    boolean updateCar(carDto CarDto) throws Exception;

    boolean deleteCar(int id) throws Exception;

    boolean addCar(carDetailDto CarDetailDto) throws Exception;
}
