package sv.edu.udb.www.dwfteoricoapi.utils;

import sv.edu.udb.www.dwfteoricoapi.models.Medicamento;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Validations {

    public static Map<String, String> validateMedicamento(Medicamento medicamento) {
        Map<String, String> errors = new HashMap<>();

        if (medicamento == null) {
            errors.put("error", "El objeto medicamento no puede ser nulo.");
            return errors;
        }

        if (medicamento.getNombre() == null || medicamento.getNombre().trim().isEmpty()) {
            errors.put("nombre", "El nombre del medicamento no puede estar vacío.");
        }

        if (medicamento.getCantidad() < 0) {
            errors.put("cantidad", "La cantidad del medicamento no puede ser negativa.");
        }

        if (medicamento.getCantidad() == 0) {
            errors.put("cantidad", "La cantidad debe estar presente o no puede ser 0");
        }

        if (medicamento.getPrecio() == null) {
            errors.put("precio", "El precio del medicamento es requerido.");
        } else if (medicamento.getPrecio().compareTo(BigDecimal.ZERO) < 0) {
            errors.put("precio", "El precio del medicamento no puede ser negativo.");
        }

        return errors;
    }
}
