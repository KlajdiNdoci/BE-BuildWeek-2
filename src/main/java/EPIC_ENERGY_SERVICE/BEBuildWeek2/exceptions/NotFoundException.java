package EPIC_ENERGY_SERVICE.BEBuildWeek2.exceptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException(long id) {
        super("Element with id " + id + " not found!");
    }
    public NotFoundException(String message) {
        super(message);
    }
}
