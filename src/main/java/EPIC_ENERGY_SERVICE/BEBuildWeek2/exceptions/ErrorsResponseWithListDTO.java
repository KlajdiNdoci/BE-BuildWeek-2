package EPIC_ENERGY_SERVICE.BEBuildWeek2.exceptions;

import java.util.Date;
import java.util.List;

public record ErrorsResponseWithListDTO(String message, Date timestamp, List<String> errorsList) {}

