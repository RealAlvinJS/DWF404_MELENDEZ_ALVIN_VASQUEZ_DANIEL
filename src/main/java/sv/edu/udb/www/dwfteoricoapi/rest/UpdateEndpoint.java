package sv.edu.udb.www.dwfteoricoapi.rest;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sv.edu.udb.www.dwfteoricoapi.dao.MedicamentoDAO;
import sv.edu.udb.www.dwfteoricoapi.models.Medicamento;
import sv.edu.udb.www.dwfteoricoapi.utils.Validations;

import java.sql.SQLException;
import java.util.Map;

@Path("/medicines/update")
public class UpdateEndpoint {
    private MedicamentoDAO medicamentoDAO = new MedicamentoDAO();

    @PUT
    public Response errorHandling() {
        return Response.status(Response.Status.NOT_FOUND).entity("{\"error\":\"Necesita ingresar el ID del Medicamento en la URL.\"}").build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateMedicine(@PathParam("id") String id, Medicamento medicamento) {
        if (id == null || id.trim().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\":\"ID del medicamento es requerido.\"}").build();
        }

        try {
            Medicamento existingMedicamento = medicamentoDAO.getById(id);
            if (existingMedicamento == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("{\"error\":\"Medicamento no encontrado.\"}").build();
            }

            Map<String, String> validationErrors = Validations.validateMedicamento(medicamento);
            if (!validationErrors.isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST).entity(validationErrors).build();
            }

            medicamentoDAO.update(id, medicamento);
            return Response.status(Response.Status.OK).entity("{\"message\":\"Medicamento actualizado exitosamente\"}").build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"error\":\"Error en la base de datos: " + e.getMessage() + "\"}").build();
        }
    }
}
