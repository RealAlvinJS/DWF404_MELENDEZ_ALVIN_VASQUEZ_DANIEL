package sv.edu.udb.www.dwfteoricoapi.dao;

import sv.edu.udb.www.dwfteoricoapi.database.DatabaseConnection;
import sv.edu.udb.www.dwfteoricoapi.models.Medicamento;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicamentoDAO {

    // Consultas SQL
    private static final String INSERTAR_MEDICAMENTO_SQL = "INSERT INTO medicamento (nombre, cantidad, precio) VALUES (?, ?, ?)";
    private static final String OBTENER_MEDICAMENTO_POR_ID_SQL = "SELECT * FROM medicamento WHERE id = ?";
    private static final String UPDATE_MEDICAMENTO_SQL = "UPDATE medicamento SET nombre = ?, cantidad = ?, precio = ? WHERE id = ?";
    private static final String ELIMINAR_MEDICAMENTO_POR_ID_SQL = "DELETE FROM medicamento WHERE id = ?";
    private static final String OBTENER_TODOS_LOS_MEDICAMENTOS_SQL = "SELECT * FROM medicamento";

    // Método para obtener todos los medicamentos
    public List<Medicamento> getAll() throws SQLException {
        List<Medicamento> medicamentos = new ArrayList<>();
        try (PreparedStatement pstmt = DatabaseConnection.getConnection().prepareStatement(OBTENER_TODOS_LOS_MEDICAMENTOS_SQL);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Medicamento medicamento = new Medicamento(
                        rs.getString("id"),
                        rs.getString("nombre"),
                        rs.getInt("cantidad"),
                        rs.getBigDecimal("precio")
                );
                medicamentos.add(medicamento);
            }
        }
        return medicamentos;
    }

    // Método para obtener un medicamento por ID
    public Medicamento getById(String id) throws SQLException {
        try (PreparedStatement pstmt = DatabaseConnection.getConnection().prepareStatement(OBTENER_MEDICAMENTO_POR_ID_SQL)) {
            pstmt.setString(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Medicamento(
                            rs.getString("id"),
                            rs.getString("nombre"),
                            rs.getInt("cantidad"),
                            rs.getBigDecimal("precio")
                    );
                }
            }
        }
        return null; // O lanzar una excepción si el medicamento no se encuentra
    }

    // Método para crear un nuevo medicamento
    public void create(Medicamento medicamento) throws SQLException {
        try ( PreparedStatement pstmt = DatabaseConnection.getConnection().prepareStatement(INSERTAR_MEDICAMENTO_SQL)) {
            pstmt.setString(1, medicamento.getNombre());
            pstmt.setInt(2, medicamento.getCantidad());
            pstmt.setBigDecimal(3, medicamento.getPrecio());
            pstmt.executeUpdate();
        }
    }

    // Método para actualizar un medicamento
    public void update(String id, Medicamento medicamento) throws SQLException {
        try (PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(UPDATE_MEDICAMENTO_SQL)) {

            stmt.setString(1, medicamento.getNombre());
            stmt.setInt(2, medicamento.getCantidad());
            stmt.setBigDecimal(3, medicamento.getPrecio());
            stmt.setString(4, id);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No se pudo actualizar el medicamento con ID: " + id);
            }
        }
    }

    // Método para eliminar un medicamento por ID
    public void delete(String id) throws SQLException {
        try (PreparedStatement pstmt = DatabaseConnection.getConnection().prepareStatement(ELIMINAR_MEDICAMENTO_POR_ID_SQL)) {
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        }
    }
}
