package pe.edu.cibertec.Crud_Restful.repository;

import org.springframework.data.repository.CrudRepository;
import pe.edu.cibertec.Crud_Restful.entity.Car;

public interface carRepository extends CrudRepository<Car, Integer> {

}
