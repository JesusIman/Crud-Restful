package pe.edu.cibertec.Crud_Restful.response;

import pe.edu.cibertec.Crud_Restful.dto.carDetailDto;

public record FindCarResponce(String code,
                              String error,
                              carDetailDto car) {
}
