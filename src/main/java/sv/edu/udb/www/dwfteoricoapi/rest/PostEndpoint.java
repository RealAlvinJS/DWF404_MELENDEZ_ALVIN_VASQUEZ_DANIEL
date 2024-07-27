package sv.edu.udb.www.dwfteoricoapi.rest;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sv.edu.udb.www.dwfteoricoapi.dao.MedicamentoDAO;
import sv.edu.udb.www.dwfteoricoapi.models.Medicamento;
import sv.edu.udb.www.dwfteoricoapi.utils.Validations;

import java.sql.SQLException;
import java.util.Map;

@Path("/medicines/create")
public class PostEndpoint {
    private MedicamentoDAO medicamentoDAO = new MedicamentoDAO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createMedicine(Medicamento medicamento) {
        Map<String, String> validationErrors = Validations.validateMedicamento(medicamento);

        if (!validationErrors.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity(validationErrors).build();
        }

        try {
            medicamentoDAO.create(medicamento);
            return Response.status(Response.Status.CREATED).entity("{\"message\":\"Medicamento creado exitosamente\"}").build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(createErrorResponse("error de base de datos")).build();
        }
    }

    private String createErrorResponse(String message) {
        return String.format("{\"error\":\"%s\"}", message);
    }
}
