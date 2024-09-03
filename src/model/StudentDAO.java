
package model;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    
    private DBConnection dbConnection;

    public StudentDAO() {
        dbConnection = new DBConnection();
    }
    
    public List<Students> getAllStudents(){
        List<Students> students = new ArrayList<>();
        String query = "SELECT * FROM students";
        Connection connection = dbConnection.getConnection();
        try {          
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next()){
                Students student = new Students();
                student.setId(rs.getInt(1));
                student.setNames(rs.getString(2));
                student.setLastnames(rs.getString(3));
                student.setDocument(rs.getString(4));
                student.setAverageGrade(rs.getDouble(5));
                students.add(student);
            }   
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        
        return students;
    }
    
    public boolean addStudent(Students student){     
        try {
            String query = "INSERT INTO students(names, lastnames, document, average_grade) VALUES(?,?,?,?)";
            Connection connection = dbConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, student.getNames());
            ps.setString(2, student.getLastnames());
            ps.setString(3, student.getDocument());
            ps.setDouble(4, student.getAverageGrade());
            
            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();  
            return false;
        }      
    }
    
    public boolean updateStudent(Students student) {
        String query = "UPDATE students SET names = ?, lastnames = ?, document = ?, average_grade = ? WHERE id = ?";
        Connection connection = dbConnection.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, student.getNames());
            stmt.setString(2, student.getLastnames());
            stmt.setString(3, student.getDocument());
            stmt.setDouble(4, student.getAverageGrade());
            stmt.setInt(5, student.getId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public boolean deleteStudent(int id) {
        String query = "DELETE FROM students WHERE id = ?";
        Connection connection = dbConnection.getConnection();
        try ( PreparedStatement stmt = connection.prepareStatement(query)){
            // Establecer el ID en el query
            stmt.setInt(1, id);

            // Ejecutar la consulta
            int rowsAffected = stmt.executeUpdate();

            // Si se afectaron filas, significa que la eliminación fue exitosa
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public boolean studentExists(String document) {
        boolean exists = false;
        String query = "SELECT COUNT(*) FROM students WHERE document = ?";
        Connection connection = dbConnection.getConnection();

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, document);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                exists = rs.getInt(1) > 0;  // Si COUNT(*) > 0, el documento ya existe
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exists;
    }




    
}
    
