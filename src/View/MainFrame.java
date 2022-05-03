package View;

import Controller.StudentController;
import Database.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.regex.Pattern;

public class MainFrame extends JFrame {
    StudentController studentController = new StudentController();


    public MainFrame() throws IOException {
        initComponents();
        DisableAllBtn();
        returnStudentsToTable(studentController.readFile());
        studentController.readDataFromFile();
    }


    /*
     *
     * Button
     **/
    // Clear text inside the text field when implement function
    private void Clear() {
        idTextField.setText("");
        nameTextField.setText("");
        birthdayTextField.setText("");
        emailTextField.setText("");
        phoneNumberTextField.setText("");
    }
    private void clearBtn(ActionEvent e) {
        idTextField.setText("");
        nameTextField.setText("");
        birthdayTextField.setText("");
        emailTextField.setText("");
        phoneNumberTextField.setText("");
    }

    // Disable all button when compile project
    public void DisableAllBtn() {
        idTextField.setEnabled(false);
        nameTextField.setEnabled(false);
        addBtn.setEnabled(false);
        deleteBtn.setEnabled(false);
        updateBtn.setEnabled(false);
        tblStudent.setEnabled(false);
        femaleRadioButton.setEnabled(false);
        maleRadioButton.setEnabled(false);
        phoneNumberTextField.setEnabled(false);
        emailTextField.setEnabled(false);
        birthdayTextField.setEnabled(false);
        clearBtn.setEnabled(false);
    }


    // Choose add function
    private void saveFunctionRadioBtn(ActionEvent e) {
        // TODO add your code here
        tblStudent.setEnabled(false);
        deleteBtn.setEnabled(false);
        updateBtn.setEnabled(false);
        idTextField.setEnabled(true);
        nameTextField.setEnabled(true);
        addBtn.setEnabled(true);
        saveRadioButton.setSelected(true);
        saveRadioButton.setEnabled(false);
        updateRadioButton.setSelected(false);
        deleteRadioButton.setEnabled(true);
        deleteRadioButton.setSelected(true);
        updateRadioButton.setEnabled(true);
        femaleRadioButton.setEnabled(true);
        maleRadioButton.setEnabled(true);
        phoneNumberTextField.setEnabled(true);
        emailTextField.setEnabled(true);
        birthdayTextField.setEnabled(true);
        clearBtn.setEnabled(true);
    }

    // Choose delete function
    private void deleteRadioBtn(ActionEvent e) {
        // TODO add your code here
        idTextField.setEnabled(false);
        nameTextField.setEnabled(false);
        addBtn.setEnabled(false);
        updateBtn.setEnabled(false);
        deleteBtn.setEnabled(true);
        tblStudent.setEnabled(true);
        deleteRadioButton.setSelected(true);
        deleteRadioButton.setEnabled(false);
        saveRadioButton.setSelected(false);
        updateRadioButton.setSelected(false);
        saveRadioButton.setEnabled(true);
        updateRadioButton.setEnabled(true);
        femaleRadioButton.setEnabled(false);
        maleRadioButton.setEnabled(false);
        phoneNumberTextField.setEnabled(false);
        emailTextField.setEnabled(false);
        birthdayTextField.setEnabled(false);
        clearBtn.setEnabled(false);
    }

    // Choose update function
    private void updateRadioBtn(ActionEvent e) {
        // TODO add your code here
        addBtn.setEnabled(false);
        deleteBtn.setEnabled(false);
        updateBtn.setEnabled(true);
        tblStudent.setEnabled(true);
        nameTextField.setEnabled(true);
        updateBtn.setSelected(true);
        updateRadioButton.setEnabled(false);
        deleteRadioButton.setEnabled(true);
        saveRadioButton.setEnabled(true);
        idTextField.setEnabled(false);
        femaleRadioButton.setEnabled(true);
        maleRadioButton.setEnabled(true);
        phoneNumberTextField.setEnabled(true);
        emailTextField.setEnabled(true);
        birthdayTextField.setEnabled(true);
        clearBtn.setEnabled(false);
    }


    private void femaleRadioButton(ActionEvent e) {
        femaleRadioButton.setSelected(true);
        femaleRadioButton.setEnabled(false);
        maleRadioButton.setEnabled(true);
    }


    private void maleRadioButton(ActionEvent e) {
        maleRadioButton.setSelected(true);
        maleRadioButton.setEnabled(false);
        femaleRadioButton.setEnabled(true);
    }

    // Add Button
    private void addBtn(ActionEvent e) {
        // TODO add your code here
        String id = idTextField.getText().trim();
        String name = nameTextField.getText().trim();
        String gender;
        String birthday = birthdayTextField.getText().trim();
        String email = emailTextField.getText().trim();
        String phoneNumber = phoneNumberTextField.getText().trim();
        if (femaleRadioButton.isSelected()) {
            gender = femaleRadioButton.getText().trim();
        } else if (maleRadioButton.isSelected()) {
            gender = maleRadioButton.getText().trim();
        } else if (!femaleRadioButton.isSelected() && !maleRadioButton.isSelected()) {
            gender = "";
        } else {
            gender = "";
        }

        if (!id.isEmpty() && !name.isEmpty() && gender != "" && !birthday.isEmpty() && !email.isEmpty() && !phoneNumber.isEmpty()) {
            if (!studentController.CheckId(id)) {
                JOptionPane.showMessageDialog(null, "ID is duplicated", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else if (!studentController.CheckNameValid(name)) {
                JOptionPane.showMessageDialog(null, "Name is invalid", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else if (!studentController.CheckIdValid(id)) {
                JOptionPane.showMessageDialog(null, "ID is invalid", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else if (!studentController.CheckBirthday(birthday)) {
                JOptionPane.showMessageDialog(null, "Birthday is invalid", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else if ((!studentController.CheckEmail(email))) {
                JOptionPane.showMessageDialog(null, "Email is duplicated", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else if ((!studentController.CheckEmailValid(email))) {
                JOptionPane.showMessageDialog(null, "Email is invalid", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else if ((!studentController.CheckPhoneNumberValid(phoneNumber))) {
                JOptionPane.showMessageDialog(null, "Phone number is invalid", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else if ((studentController.CheckPhone(phoneNumber))) {
                JOptionPane.showMessageDialog(null, "Phone is duplicated", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            Student student = new Student(id, name, gender, birthday,email, phoneNumber);
            studentController.AddStudent(student);
            Clear();
            clearTableContents();
            returnStudentsToTable(studentController.readFile());
            JOptionPane.showMessageDialog(null, "ADD SUCCESSFULL!!!", "Successfull",
                    JOptionPane.INFORMATION_MESSAGE);
            return;

        } else {
            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Fill ID Required", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else if (name.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Fill Name Required", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else if (gender == "") {
                JOptionPane.showMessageDialog(null, "Please choose gender", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else if (birthday.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Fill Birthday Required", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else if (!email.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Fill Email Required", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else if (!phoneNumber.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Fill Phone number Required", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

        }
    }

    // Delete Button
    private void deleteBtn(ActionEvent e) {
        // TODO add your code here
        String id = idTextField.getText().trim();
        if (!id.isEmpty()){
//            for (int i = 0; i < _students.size(); i++) {
//                if (_students.get(i).getId().equals(id)) {
//                    _students.remove(i);
//                }
//            }
            studentController.DeleteStudent(id);
            Clear();
            clearTableContents();
            returnStudentsToTable(studentController.readFile());
            JOptionPane.showMessageDialog(null, "DELETE SUCCESSFULL!!!", "Successfull",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        else{
            JOptionPane.showMessageDialog(null, "Please choose student from table below", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

    }

    // Update Button
    private void updateBtn(ActionEvent e) {
        // TODO add your code here
        String id = idTextField.getText().trim();
        String name = nameTextField.getText().trim();
        String gender;
        String birthday = birthdayTextField.getText().trim();
        String email = emailTextField.getText().trim();
        String phoneNumber = phoneNumberTextField.getText().trim();
        if (femaleRadioButton.isSelected()) {
            gender = femaleRadioButton.getText().trim();
        } else if (maleRadioButton.isSelected()) {
            gender = maleRadioButton.getText().trim();
        } else if (!femaleRadioButton.isSelected() && !maleRadioButton.isSelected()) {
            gender = "";
        } else {
            gender = "";
        }
        if (!id.isEmpty() && !name.isEmpty() && gender != "" && !birthday.isEmpty() && !email.isEmpty() && !phoneNumber.isEmpty()) {
            if (!studentController.CheckNameValid(name)) {
                JOptionPane.showMessageDialog(null, "Name is invalid", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else if (!studentController.CheckBirthday(birthday)) {
                JOptionPane.showMessageDialog(null, "Birthday is invalid", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else if (!studentController.CheckEmailValid(email)) {
                JOptionPane.showMessageDialog(null, "Email is invalid", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else if (!studentController.CheckPhoneNumberValid(phoneNumber)){
                JOptionPane.showMessageDialog(null, "Phone is invalid", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            StudentController.UpdateStudent(id, name, gender, birthday, email, phoneNumber);
            Clear();
            clearTableContents();
            returnStudentsToTable(studentController.readFile());
            JOptionPane.showMessageDialog(null, "UPDATE SUCCESSFULL!!!", "Successfull",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        } else {
            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Fill ID Required", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else if (name.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Fill Name Required", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else if (gender == "") {
                JOptionPane.showMessageDialog(null, "Please choose gender", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else if (birthday.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Fill Birthday Required", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else if (!email.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Fill Email Required", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else if (!phoneNumber.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Fill Phone number Required", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

    }

    // search Button
    private void searchBtn(ActionEvent e) {
        String id = searchIdTextField.getText().trim();
        String name = searchNameTextField.getText().trim();
        clearTableContents();
        if (!id.isEmpty() && name.isEmpty()){
            for (Student student : studentController._students) {
                if (student.getId().equals(id)) {
                    Student findedStudent = new Student(student.getId(), student.getName(), student.getGender(), student.getBirthday()
                            , student.getEmail(), student.getPhoneNumber());
                    clearTableContents();
                    returnFindedStudentsToTable(findedStudent);
                }
            }
        }else if (!id.isEmpty() && !name.isEmpty()){
            for (Student student : studentController._students) {
                if (student.getId().equals(id) && student.getName().contains(name)) {
                    Student findedStudent = new Student(student.getId(), student.getName(), student.getGender(), student.getBirthday()
                            , student.getEmail(), student.getPhoneNumber());
                    returnFindedStudentsToTable(findedStudent);
                }
            }
        }else if (id.isEmpty() && !name.isEmpty()){
            for (Student student: studentController._students){
                if (student.getName().contains(name)){
                    Student findedStudent = new Student(student.getId(), student.getName(), student.getGender(), student.getBirthday()
                            , student.getEmail(), student.getPhoneNumber());
                    returnFindedStudentsToTable(findedStudent);
                }
            }
        } else{
            returnStudentsToTable(studentController.readFile());
            JOptionPane.showMessageDialog(null, "Filling Search Field Required", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

    }

    // sort ascending by ID Button
    private void sortARadioBtn(ActionEvent e){
        sortARadioBtn.setSelected(true);
        sortDRadioBtn.setEnabled(true);
        sortARadioBtn.setEnabled(false);
        studentController.SortStudentAscendingById();
        clearTableContents();
        returnStudentsToTable(studentController.readFile());
    }

    // sort descending by ID Button
    private void sortDRadioBtn(ActionEvent e) {
        sortDRadioBtn.setSelected(true);
        sortDRadioBtn.setEnabled(false);
        sortARadioBtn.setEnabled(true);
        studentController.SortStudentDescendingById();
        clearTableContents();
        returnStudentsToTable(studentController.readFile());

    }

    // clear all the contents of table tutton
    public void clearTableContents(){
        DefaultTableModel defaultTableModel = (DefaultTableModel) tblStudent.getModel();
        defaultTableModel.setRowCount(0);
    }

    /*------ End button ------*/

    // return user by Object array
    public void returnStudentsToTable(Object[] objects){
        DefaultTableModel defaultTableModel = (DefaultTableModel) tblStudent.getModel();
        for (int i = 0; i < objects.length; i++) {
            String row = objects[i].toString().trim();
            String[] rows = row.split(",");
            defaultTableModel.addRow(rows);
        }
    }

    // return user by Object
    public void returnFindedStudentsToTable(Student student){
        DefaultTableModel defaultTableModel = (DefaultTableModel) tblStudent.getModel();
        String[] findedUser = student.toString().split(",");
        defaultTableModel.addRow(findedUser);
    }

    /*------ End Function ------*/


    // interface
    public void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Quoc
        panel4 = new JPanel();
        idTextField = new JTextField();
        label1 = new JLabel();
        label2 = new JLabel();
        nameTextField = new JTextField();
        birthdayTextField = new JTextField();
        label4 = new JLabel();
        label5 = new JLabel();
        emailTextField = new JTextField();
        addBtn = new JButton();
        clearBtn = new JButton();
        label6 = new JLabel();
        phoneNumberTextField = new JTextField();
        label7 = new JLabel();
        saveRadioButton = new JRadioButton();
        deleteBtn = new JButton();
        deleteRadioButton = new JRadioButton();
        updateBtn = new JButton();
        updateRadioButton = new JRadioButton();
        sortARadioBtn = new JRadioButton();
        sortDRadioBtn = new JRadioButton();
        searchIdTextField = new JTextField();
        label8 = new JLabel();
        searchNameTextField = new JTextField();
        label9 = new JLabel();
        searchBtn = new JButton();
        maleRadioButton = new JRadioButton();
        femaleRadioButton = new JRadioButton();
        panel1 = new JPanel();
        scrollPane1 = new JScrollPane();
        tblStudent = new JTable();

        //======== this ========
        var contentPane = getContentPane();

        //======== panel4 ========
        {
            panel4.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border.
                    EmptyBorder(0,0,0,0), "Student mananagement",javax.swing.border.TitledBorder.CENTER,javax.swing
                    .border.TitledBorder.BOTTOM,new java.awt.Font("Dialo\u0067",java.awt.Font.BOLD,12),
                    java.awt.Color.red),panel4. getBorder()));panel4. addPropertyChangeListener(new java.beans.PropertyChangeListener()
        {@Override public void propertyChange(java.beans.PropertyChangeEvent e){if("borde\u0072".equals(e.getPropertyName()))
            throw new RuntimeException();}});

            //---- label1 ----
            label1.setText("ID(Only Number):");

            //---- label2 ----
            label2.setText("Name:");

            //---- label4 ----
            label4.setText("Birthday(dd/mm/yyyy):");

            //---- label5 ----
            label5.setText("Email(a@example.com):");

            //---- addBtn ----
            addBtn.setText("Save");
            addBtn.addActionListener(e -> addBtn(e));

            //---- clearBtn ----
            clearBtn.setText("Clear");
            clearBtn.addActionListener(e->clearBtn(e));

            //---- label6 ----
            label6.setText("Phone Number(10 digits):");

            //---- label7 ----
            label7.setText("Gender:");

            //---- saveRadioButton ----
            saveRadioButton.setText("Add Function");
            saveRadioButton.addActionListener(e -> saveFunctionRadioBtn(e));

            //---- deleteBtn ----
            deleteBtn.setText("Delete");
            deleteBtn.addActionListener(e -> {
                try {
                    deleteBtn(e);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });

            //---- deleteRadioButton ----
            deleteRadioButton.setText("Delete Function");
            deleteRadioButton.addActionListener(e -> deleteRadioBtn(e));

            //---- updateBtn ----
            updateBtn.setText("Update");
            updateBtn.addActionListener(e -> updateBtn(e));

            //---- updateRadioButton ----
            updateRadioButton.setText("Update Function");
            updateRadioButton.addActionListener(e -> updateRadioBtn(e));

            //---- sortARadioBtn ----
            sortARadioBtn.setText("Sorting Asc");
            sortARadioBtn.addActionListener(e -> sortARadioBtn(e));

            //---- sortDRadioBtn ----
            sortDRadioBtn.setText("Sorting Desc");
            sortDRadioBtn.addActionListener(e -> sortDRadioBtn(e));

            //---- label8 ----
            label8.setText("Search ID:");

            //---- label9 ----
            label9.setText("Search Name:");

            //---- searchBtn ----
            searchBtn.setText("Search");
            searchBtn.addActionListener(e-> searchBtn(e));

            //---- maleRadioButton ----
            maleRadioButton.setText("Male");
            maleRadioButton.addActionListener(e -> maleRadioButton(e));

            //---- femaleRadioButton ----
            femaleRadioButton.setText("Female");
            femaleRadioButton.addActionListener(e-> femaleRadioButton(e));

            GroupLayout panel4Layout = new GroupLayout(panel4);
            panel4.setLayout(panel4Layout);
            panel4Layout.setHorizontalGroup(
                    panel4Layout.createParallelGroup()
                            .addGroup(panel4Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(panel4Layout.createParallelGroup()
                                            .addComponent(label2)
                                            .addComponent(label1)
                                            .addComponent(label8)
                                            .addComponent(label9)
                                            .addComponent(label7))
                                    .addGroup(panel4Layout.createParallelGroup()
                                            .addGroup(panel4Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                    .addGroup(panel4Layout.createSequentialGroup()
                                                            .addGroup(panel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                                    .addComponent(nameTextField, GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                                                                    .addComponent(idTextField))
                                                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addGroup(panel4Layout.createParallelGroup()
                                                                    .addGroup(panel4Layout.createSequentialGroup()
                                                                            .addGroup(panel4Layout.createParallelGroup()
                                                                                    .addComponent(label5)
                                                                                    .addComponent(label4))
                                                                            .addGap(22, 22, 22)
                                                                            .addGroup(panel4Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                                                    .addComponent(emailTextField, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
                                                                                    .addComponent(birthdayTextField, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)))
                                                                    .addGroup(panel4Layout.createSequentialGroup()
                                                                            .addComponent(label6)
                                                                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                            .addComponent(phoneNumberTextField, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE))))
                                                    .addGroup(GroupLayout.Alignment.LEADING, panel4Layout.createSequentialGroup()
                                                            .addGap(11, 11, 11)
                                                            .addComponent(searchIdTextField, GroupLayout.PREFERRED_SIZE, 335, GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(GroupLayout.Alignment.LEADING, panel4Layout.createSequentialGroup()
                                                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addGroup(panel4Layout.createParallelGroup()
                                                                    .addComponent(searchNameTextField, GroupLayout.PREFERRED_SIZE, 335, GroupLayout.PREFERRED_SIZE)
                                                                    .addGroup(panel4Layout.createSequentialGroup()
                                                                            .addComponent(searchBtn)
                                                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                            .addComponent(sortARadioBtn)
                                                                            .addGap(14, 14, 14)))))
                                            .addGroup(panel4Layout.createSequentialGroup()
                                                    .addComponent(maleRadioButton)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(femaleRadioButton)))
                                    .addGap(15, 15, 15)
                                    .addGroup(panel4Layout.createParallelGroup()
                                            .addGroup(panel4Layout.createSequentialGroup()
                                                    .addGroup(panel4Layout.createParallelGroup()
                                                            .addComponent(addBtn)
                                                            .addComponent(deleteBtn))
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addGroup(panel4Layout.createParallelGroup()
                                                            .addComponent(saveRadioButton)
                                                            .addComponent(deleteRadioButton)))
                                            .addGroup(panel4Layout.createSequentialGroup()
                                                    .addGroup(panel4Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                            .addComponent(clearBtn)
                                                            .addComponent(updateBtn))
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(updateRadioButton))
                                            .addGroup(panel4Layout.createSequentialGroup()
                                                    .addGap(1, 1, 1)
                                                    .addComponent(sortDRadioBtn)))
                                    .addContainerGap(43, Short.MAX_VALUE))
            );
            panel4Layout.setVerticalGroup(
                    panel4Layout.createParallelGroup()
                            .addGroup(panel4Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(panel4Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(label4)
                                            .addComponent(addBtn)
                                            .addComponent(birthdayTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(idTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(label1)
                                            .addComponent(saveRadioButton))
                                    .addGap(7, 7, 7)
                                    .addGroup(panel4Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(label5)
                                            .addComponent(emailTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(label2)
                                            .addComponent(nameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(deleteRadioButton)
                                            .addComponent(deleteBtn))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(panel4Layout.createParallelGroup()
                                            .addGroup(panel4Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                    .addComponent(label6)
                                                    .addComponent(phoneNumberTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(updateBtn)
                                                    .addComponent(updateRadioButton))
                                            .addGroup(GroupLayout.Alignment.TRAILING, panel4Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                    .addComponent(label7)
                                                    .addComponent(maleRadioButton)
                                                    .addComponent(femaleRadioButton)))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(panel4Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                            .addGroup(panel4Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                    .addComponent(sortARadioBtn)
                                                    .addComponent(sortDRadioBtn))
                                            .addGroup(panel4Layout.createSequentialGroup()
                                                    .addComponent(clearBtn)
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                    .addGroup(panel4Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                            .addComponent(label8)
                                                            .addComponent(searchIdTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                    .addGroup(panel4Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                            .addComponent(label9)
                                                            .addComponent(searchNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(searchBtn)))
                                    .addContainerGap(12, Short.MAX_VALUE))
            );
        }

        //======== panel1 ========
        {

            //======== scrollPane1 =
            // =======
            {
                String[] nameOfColumn= {"ID", "Name", "Gender", "Birthday", "Email", "Phone number"};
                tblStudent.setModel(new DefaultTableModel(
                        new Object[][]{},
                        nameOfColumn
                ){
                    boolean[] canEdit = new boolean [] {
                            false, false, false, false, false
                    };

                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit [columnIndex];
                    }
                });
                tblStudent.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        tblStudentMouseClicked(e);
                    }

                    private void tblStudentMouseClicked(MouseEvent e) {
                        if (deleteRadioButton.isSelected() || updateRadioButton.isSelected()) {
                            int i = tblStudent.getSelectedRow();
                            TableModel model = tblStudent.getModel();
                            idTextField.setText(model.getValueAt(i, 0).toString());
                            nameTextField.setText(model.getValueAt(i, 1).toString());
                            birthdayTextField.setText(model.getValueAt(i, 3).toString());
                            emailTextField.setText(model.getValueAt(i, 4).toString());
                            phoneNumberTextField.setText(model.getValueAt(i, 5).toString());
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Please choose delete radio button or update radio button to click row in table", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }

                });
                scrollPane1.setViewportView(tblStudent);
            }

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                    panel1Layout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 905, Short.MAX_VALUE))
            );
            panel1Layout.setVerticalGroup(
                    panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                                    .addContainerGap())
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(panel4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(panel4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Quoc
    private JPanel panel4;
    private JTextField idTextField;
    private JLabel label1;
    private JLabel label2;
    private JTextField nameTextField;
    private JTextField birthdayTextField;
    private JLabel label4;
    private JLabel label5;
    private JTextField emailTextField;
    private JButton addBtn;
    private JButton clearBtn;
    private JLabel label6;
    private JTextField phoneNumberTextField;
    private JLabel label7;
    private JRadioButton saveRadioButton;
    private JButton deleteBtn;
    private JRadioButton deleteRadioButton;
    private JButton updateBtn;
    private JRadioButton updateRadioButton;
    private JRadioButton sortARadioBtn;
    private JRadioButton sortDRadioBtn;
    private JTextField searchIdTextField;
    private JLabel label8;
    private JTextField searchNameTextField;
    private JLabel label9;
    private JButton searchBtn;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private JPanel panel1;
    private JScrollPane scrollPane1;
    private JTable tblStudent;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}



