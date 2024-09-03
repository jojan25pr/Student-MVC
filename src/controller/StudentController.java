package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import model.Students;
import model.StudentDAO;
import view.StudentViewCRUD;

public class StudentController {

    private StudentViewCRUD studentViewCRUD;
    private StudentDAO model;

    public StudentController(StudentViewCRUD studentViewCRUD, StudentDAO model) {
        this.studentViewCRUD = studentViewCRUD;
        this.model = model;
               
        studentViewCRUD.getBtnInsertStudent().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent(studentViewCRUD); 
                loadStudentsData();
            }
        });
        
        studentViewCRUD.getBtnUpdateStudent().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateStudent(studentViewCRUD); 
                loadStudentsData();
            }
        });
        
        studentViewCRUD.getBtnDeleteStudent().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteStudent(studentViewCRUD); 
                loadStudentsData();
            }
        });
        
        studentViewCRUD.getBtnClearForm().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm(studentViewCRUD); 
            }
        });
        
        //addStudentForm.getBtnAddStudent().addActionListener(new ActionListener() {
            //@Override
            //public void actionPerformed(ActionEvent e) {
                // Llamar a la función para guardar el estudiante
                //addStudent(addStudentForm);
                //addStudentForm.dispose();
                //loadStudentsData();
            //}
        //});
        
        loadStudentsData();
    }
    
    public void loadStudentsData() {
        List<Students> studentsList = model.getAllStudents();

        // Convertir la lista de estudiantes en unsarreglo de Object[][]
        Object[][] data = new Object[studentsList.size()][5];

        for (int i = 0; i < studentsList.size(); i++) {
            Students student = studentsList.get(i);
            data[i][0] = student.getId();
            data[i][1] = student.getNames();
            data[i][2] = student.getLastnames();
            data[i][3] = student.getDocument();
            data[i][4] = student.getAverageGrade();
        }

        studentViewCRUD.updateTable(data);
    }

    public void addStudent(StudentViewCRUD insertStudentForm) {
       
        String names = insertStudentForm.getTxtFieldNames();
        String lastnames = insertStudentForm.getTxtFieldLastnames();
        String document = insertStudentForm.getTxtFieldDocument();
        double averageGrade;

        int id = insertStudentForm.getSelectedStudentId();
            
        // Clear the form before instert
        if (id != 0) {
            JOptionPane.showMessageDialog(insertStudentForm, "Clear the form to insert a new student");
            return;
        }
        
        // Verify if the document is already registered
        if (this.model.studentExists(document)) {
            JOptionPane.showMessageDialog(insertStudentForm, "A student with this document already exists");
            return;
        }
            
        // Validate empty fields
        if (names.isEmpty() || lastnames.isEmpty() || document.isEmpty()) {
            JOptionPane.showMessageDialog(insertStudentForm, "All fields must be filled");
            return;
        }
        
        // If the average is not a valid number
        try {
            averageGrade = Double.parseDouble(insertStudentForm.getTxtFieldAvgGrade());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(insertStudentForm, "The average must be a valid number");
            return;
        }
            
        Students student = new Students();
        student.setNames(names);
        student.setLastnames(lastnames);
        student.setDocument(document);
        student.setAverageGrade(averageGrade);
   
        if (this.model.addStudent(student)) {
            JOptionPane.showMessageDialog(insertStudentForm, "Student added successfully!");
            clearForm(insertStudentForm);  // Clear form
        } else {
            JOptionPane.showMessageDialog(insertStudentForm, "Failed to add student.");
        }
    }
    
    public void updateStudent(StudentViewCRUD studentForm){
        
        int id = studentForm.getSelectedStudentId();

        // Verify if the student to update is selected
        if (id == 0) {
            JOptionPane.showMessageDialog(studentForm, "Please, select the student you want to update");
            return;
        }
        
        String names = studentForm.getTxtFieldNames();
        String lastnames = studentForm.getTxtFieldLastnames();
        String document = studentForm.getTxtFieldDocument();
        double averageGrade;
        
        if (this.model.studentExists(document)) {
            JOptionPane.showMessageDialog(studentForm, "A student with this document already exists");
            return;
        }

        // Validate empty fields
        if (names.isEmpty() || lastnames.isEmpty() || document.isEmpty()) {
            JOptionPane.showMessageDialog(studentForm, "All fields must be filled to update");
            return;
        }

        try {
            averageGrade = Double.parseDouble(studentForm.getTxtFieldAvgGrade());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(studentForm, "The average must be a valid number");
            return;
        }

        // Call the model to update data
        Students updatedStudent = new Students(studentForm.getSelectedStudentId(), names, lastnames, document, averageGrade);

        boolean success = model.updateStudent(updatedStudent);
        
        if (success) {
            JOptionPane.showMessageDialog(studentForm, "Information updated");
            clearForm(studentViewCRUD);
        } else {
            JOptionPane.showMessageDialog(studentForm, "Something was wrong");
        }
    }
    
    public void deleteStudent(StudentViewCRUD view){
        int id = view.getSelectedStudentId();

        // Verify if the student to delete is selected
        if (id == 0) {
            JOptionPane.showMessageDialog(view, "Select the student you want to delete");
            return;
        }

        // Confirmar deletion
        int confirm = JOptionPane.showConfirmDialog(view, "Are you sure you want to delete this student?", "Confirm deletion", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            // Call the model to delete the student
            boolean success = model.deleteStudent(id);

            if (success) {          
                JOptionPane.showMessageDialog(view, "Student deleted succesfully");
                clearForm(view);
            } else {
                JOptionPane.showMessageDialog(view, "Something was wrong");
            }
        }
    }
    
    public void clearForm(StudentViewCRUD studentForm){
        studentForm.setTxtFieldId("");
        studentForm.setTxtFieldNames("");
        studentForm.setTxtFieldLastnames("");
        studentForm.setTxtFieldDocument("");
        studentForm.setTxtFieldAvgGrade("");
        studentForm.setSelectedStudentId(0);
    }
}


