
package studentmvc;

import controller.StudentController;
import model.StudentDAO;
import view.StudentViewCRUD;

public class StudentMVC {

    public static void main(String[] args) {
        StudentViewCRUD studentViewCRUD = new StudentViewCRUD();
        StudentDAO model = new StudentDAO();
        
        StudentController controller = new StudentController(studentViewCRUD, model);
        
        studentViewCRUD.setVisible(true);
        
        //Historial de transacciones
    }
}


