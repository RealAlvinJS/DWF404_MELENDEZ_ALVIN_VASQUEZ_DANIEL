package sv.edu.udb.www.dwfteoricoapi.rest;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sv.edu.udb.www.dwfteoricoapi.dao.MedicamentoDAO;
import sv.edu.udb.www.dwfteoricoapi.models.Medicamento;

import java.sql.SQLException;
import java.util.List;

@Path("/medicines")
public class GetEndpoint {

    private MedicamentoDAO medicamentoDAO = new MedicamentoDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllMedicines() {
        try {
            List<Medicamento> medicamentos = medicamentoDAO.getAll();
            if (medicamentos.isEmpty()) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"error\":\"no se encontraron medicamentos\"}").build();
            }
            return Response.ok(medicamentos).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"error\":\"Error en la base de datos\"}").build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMedicineById(@PathParam("id") String id) {
        try {
            Medicamento medicamento = medicamentoDAO.getById(id);
            if (medicamento == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("{\"error\":\"Medicamento no Encontrado\"}").build();
            }
            return Response.ok(medicamento).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"error\":\"Error en la base de datos\"}").build();
        }
    }
}
