/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class StudentsView extends JFrame {
    private JTable studentsTable;
    private DefaultTableModel tableModel;

    public StudentsView() {
        setTitle("Students Management");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Crear la tabla y el modelo de la tabla
        String[] columnNames = {"ID", "Names", "Last Names", "Document", "Average Grade"};
        tableModel = new DefaultTableModel(columnNames, 0);
        studentsTable = new JTable(tableModel);
        add(new JScrollPane(studentsTable), BorderLayout.CENTER);
    }  

    // Método para actualizar los datos de la tabla
    public void updateTable(Object[][] data) {
        tableModel.setRowCount(0); // Limpiar la tabla

        for (Object[] row : data) {
            tableModel.addRow(row);
        }
    }
}

