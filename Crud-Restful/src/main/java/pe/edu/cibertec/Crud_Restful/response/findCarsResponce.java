package pe.edu.cibertec.Crud_Restful.response;

import pe.edu.cibertec.Crud_Restful.dto.carDto;

public record findCarsResponce(String code,
                               String error,
                               Iterable<carDto> cars) {
}
