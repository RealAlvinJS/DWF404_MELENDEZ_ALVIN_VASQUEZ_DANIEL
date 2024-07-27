package sv.edu.udb.www.dwfteoricoapi.rest;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sv.edu.udb.www.dwfteoricoapi.dao.MedicamentoDAO;
import sv.edu.udb.www.dwfteoricoapi.models.Medicamento;

import java.sql.SQLException;

@Path("/medicines/delete")
public class DeleteEndpoint {
    private MedicamentoDAO medicamentoDAO = new MedicamentoDAO();

    @DELETE
    public Response errorHandling() {
        return Response.status(Response.Status.OK).entity("{\"error\":\"Necesita ingresar el ID del Medicamento en la URL.\"}").build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteMedicine(@PathParam("id") String id) {
        if (id == null || id.trim().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\":\"ID del medicamento es requerido.\"}").build();
        }

        try {
            Medicamento existingMedicamento = medicamentoDAO.getById(id);
            if (existingMedicamento == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("{\"error\":\"Medicamento no encontrado.\"}").build();
            }

            medicamentoDAO.delete(id);
            return Response.status(Response.Status.OK).entity("{\"message\":\"Delete ejecutado correctamente.\"}").build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"error\":\"Error en la base de datos: " + e.getMessage() + "\"}").build();
        }
    }
}
