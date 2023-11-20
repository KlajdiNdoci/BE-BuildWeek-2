package EPIC_ENERGY_SERVICE.BEBuildWeek2.exceptions;

import org.springframework.validation.ObjectError;

import java.util.List;

public class ErrorList extends RuntimeException {

    private List<ObjectError> errorList;

    public ErrorList(String message) {
        super(message);
    }

    public ErrorList(List<ObjectError> objList) {
        this.errorList = objList;
    }
}
