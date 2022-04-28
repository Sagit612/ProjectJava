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

public class CRUD extends JFrame {
    ArrayList<Student> _students = new ArrayList<>();
    String dbFile = "database.txt";
    public static void main(String[] args) throws IOException {
        CRUD crud = new CRUD();
        crud.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        crud.setVisible(true);
    }
    public CRUD() throws IOException {
        initComponents();
        DisableAllBtn();
        returnStudentsToTable(readFile());
        List<String> list = Files.readAllLines(new File(dbFile).toPath(), Charset.defaultCharset() );
        for(String line : list){
            String[] res = line.split(",");
            Student user = new Student(res[0], res[1], res[2], res[3], res[4], res[5]);
            _students.add(user);
        }

    }

    // Clear text inside the text field when implement function
    private void Clear(){
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
    private void DisableAllBtn() {
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
        saveRadioButton.setEnabled(false);
        deleteRadioButton.setEnabled(true);
        updateRadioButton.setEnabled(true);
        femaleRadioButton.setEnabled(true);
        maleRadioButton.setEnabled(true);
        phoneNumberTextField.setEnabled(true);
        emailTextField.setEnabled(true);
        birthdayTextField.setEnabled(true);
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
        deleteRadioButton.setEnabled(false);
        saveRadioButton.setEnabled(true);
        updateRadioButton.setEnabled(true);
        femaleRadioButton.setEnabled(false);
        maleRadioButton.setEnabled(false);
        phoneNumberTextField.setEnabled(false);
        emailTextField.setEnabled(false);
        birthdayTextField.setEnabled(false);
    }

    // Choose update function
    private void updateRadioBtn(ActionEvent e) {
        // TODO add your code here
        addBtn.setEnabled(false);
        deleteBtn.setEnabled(false);
        updateBtn.setEnabled(true);
        tblStudent.setEnabled(true);
        nameTextField.setEnabled(true);
        updateRadioButton.setEnabled(false);
        deleteRadioButton.setEnabled(true);
        saveRadioButton.setEnabled(true);
        idTextField.setEnabled(false);
        femaleRadioButton.setEnabled(true);
        maleRadioButton.setEnabled(true);
        phoneNumberTextField.setEnabled(true);
        emailTextField.setEnabled(true);
        birthdayTextField.setEnabled(true);
    }

    // add function
    private void addBtn(ActionEvent e) {
        // TODO add your code here
        String id = idTextField.getText().trim();
        String name = nameTextField.getText().trim();
        String gender;
        String birthday = birthdayTextField.getText().trim();
        String email = emailTextField.getText().trim();
        String phoneNumber = phoneNumberTextField.getText().trim();
        if (femaleRadioButton.isSelected()){
            gender = femaleRadioButton.getText().trim();
        }else if (maleRadioButton.isSelected()){
            gender = maleRadioButton.getText().trim();
        }else if (!femaleRadioButton.isSelected() && !maleRadioButton.isSelected()){
            gender = "";
        } else{
            gender = "";
        }

        if (!id.isEmpty() && !name.isEmpty() && gender != "" && !birthday.isEmpty() && !email.isEmpty() && !phoneNumber.isEmpty()){
            if (CheckId(id)){
                JOptionPane.showMessageDialog(null, "ID is duplicated", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else if (!CheckNameValid(name)){
                JOptionPane.showMessageDialog(null, "Name is invalid", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else if (!CheckIdValid(id)){
                JOptionPane.showMessageDialog(null, "ID is invalid", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else if (!CheckBirthday(birthday)){
                JOptionPane.showMessageDialog(null, "Birthday is invalid", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else if ((CheckEmail(email))){
                JOptionPane.showMessageDialog(null, "Email is duplicated", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }else if ((!CheckEmailValid(email))){
                JOptionPane.showMessageDialog(null, "Email is invalid", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }else if ((!CheckPhoneNumberValid(phoneNumber))){
                JOptionPane.showMessageDialog(null, "Email is invalid", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }else if ((CheckPhone(phoneNumber))){
                JOptionPane.showMessageDialog(null, "Email is invalid", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }


            _students.add(new Student(id, name, gender, birthday, email, phoneNumber));
            writeToFile(_students);
            Clear();
            clearTableContents();
            returnStudentsToTable(readFile());
        }else {
            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Fill ID Required", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }else if (name.isEmpty()){
                JOptionPane.showMessageDialog(null, "Fill Name Required", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }else if (gender == ""){
                JOptionPane.showMessageDialog(null, "Please choose gender", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }else if (birthday.isEmpty()){
                JOptionPane.showMessageDialog(null, "Fill Birthday Required", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }else if (!email.isEmpty()){
                JOptionPane.showMessageDialog(null, "Fill Email Required", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }else if (!phoneNumber.isEmpty()){
                JOptionPane.showMessageDialog(null, "Fill Phone number Required", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

        }
    }

    // delete function
    private void deleteBtn(ActionEvent e) throws Exception{
        // TODO add your code here
        String id = idTextField.getText().trim();
        for (Student student : _students) {
            if (student.getId().equals(id)) {
                _students.remove(student);
            }
        }
        writeToFile(_students);
        Clear();
        clearTableContents();
        returnStudentsToTable(readFile());
    }

    // update function
    private void updateBtn(ActionEvent e) {
        // TODO add your code here
        String id = idTextField.getText().trim();
        String name = nameTextField.getText().trim();
        String gender;
        String birthday = birthdayTextField.getText().trim();
        String email = emailTextField.getText().trim();
        String phoneNumber = phoneNumberTextField.getText().trim();
        if (femaleRadioButton.isSelected()){
            gender = femaleRadioButton.getText().trim();
        }else if (maleRadioButton.isSelected()){
            gender = maleRadioButton.getText().trim();
        }else if (!femaleRadioButton.isSelected() && !maleRadioButton.isSelected()){
            gender = "";
        } else{
            gender = "";
        }
        if (!id.isEmpty() && !name.isEmpty() && gender != "" && !birthday.isEmpty() && !email.isEmpty() && !phoneNumber.isEmpty()){
            if (CheckId(id)){
                JOptionPane.showMessageDialog(null, "ID is duplicated", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else if (!CheckNameValid(name)){
                JOptionPane.showMessageDialog(null, "Name is invalid", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else if (!CheckIdValid(id)){
                JOptionPane.showMessageDialog(null, "ID is invalid", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else if (!CheckBirthday(birthday)){
                JOptionPane.showMessageDialog(null, "Birthday is invalid", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else if ((CheckEmail(email))){
                JOptionPane.showMessageDialog(null, "Email is duplicated", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }else if ((!CheckEmailValid(email))){
                JOptionPane.showMessageDialog(null, "Email is invalid", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
        for (Student user : _students) {
            if (user.getId().equals(id)) {
                user.setName(name);
                user.setGender(gender);
            }
        }
        writeToFile(_students);
        Clear();
        clearTableContents();
        returnStudentsToTable(readFile());
        }else {
            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Fill ID Required", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }else if (name.isEmpty()){
                JOptionPane.showMessageDialog(null, "Fill Name Required", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }else if (gender == ""){
                JOptionPane.showMessageDialog(null, "Please choose gender", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }else if (birthday.isEmpty()){
                JOptionPane.showMessageDialog(null, "Fill Birthday Required", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }else if (!email.isEmpty()){
                JOptionPane.showMessageDialog(null, "Fill Email Required", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }else if (!phoneNumber.isEmpty()){
                JOptionPane.showMessageDialog(null, "Fill Phone number Required", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    }

    // search function
    private void searchBtn(ActionEvent e) {
        String id = searchIdTextField.getText().trim();
        String name = searchNameTextField.getText();
        clearTableContents();
        if (!id.isEmpty() && name.isEmpty()){
            for (Student student: _students) {
                if (student.getId().equals(id)) {
                    Student findedStudent = new Student(student.getId(), student.getName(), student.getGender(), student.getBirthday()
                            , student.getEmail(), student.getPhoneNumber());
                    clearTableContents();
                    returnFindedStudentsToTable(findedStudent);
                }else {
                    returnStudentsToTable(readFile());
                    JOptionPane.showMessageDialog(null, "There is no student having that ID", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        }else if (!id.isEmpty() && !name.isEmpty()){
            for (Student student: _students){
                if (student.getId().equals(id) && student.getName().contains(name)){
                    Student findedStudent = new Student(student.getId(), student.getName(), student.getGender(), student.getBirthday()
                            , student.getEmail(), student.getPhoneNumber());
                    returnFindedStudentsToTable(findedStudent);
                }else {
                    clearTableContents();
                    returnStudentsToTable(readFile());
                    JOptionPane.showMessageDialog(null, "There is no student having that ID and Name", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        }else if (id.isEmpty() && !name.isEmpty()){
            for (Student student: _students){
                if (student.getName().contains(name)){
                    Student findedStudent = new Student(student.getId(), student.getName(), student.getGender(), student.getBirthday()
                            , student.getEmail(), student.getPhoneNumber());
                    returnFindedStudentsToTable(findedStudent);
                } else {
                    returnStudentsToTable(readFile());
                    JOptionPane.showMessageDialog(null, "There is no student having that Name", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        }
        else{
            returnStudentsToTable(readFile());
            JOptionPane.showMessageDialog(null, "Filling Search Field Required", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    // sort ascending by ID function
    private void sortARadioBtn(ActionEvent e){
        sortDRadioBtn.setEnabled(true);
        sortARadioBtn.setEnabled(false);
        Collections.sort(_students, new Comparator<Student>() {
            public int compare(Student s1, Student s2) {
                return s1.getId().compareTo(s2.getId());
            }
        });
        writeToFile(_students);
        clearTableContents();
        returnStudentsToTable(readFile());
    }

    // sort descending by ID function
    private void sortDRadioBtn(ActionEvent e) {
        sortDRadioBtn.setEnabled(false);
        sortARadioBtn.setEnabled(true);
        Collections.sort(_students, new Comparator<Student>() {
            public int compare(Student s1, Student s2) {
                return s2.getId().compareTo(s1.getId());
            }
        });
        writeToFile(_students);
        clearTableContents();
        returnStudentsToTable(readFile());
    }

    // clear all the contents of table
    public void clearTableContents(){
        DefaultTableModel defaultTableModel = (DefaultTableModel) tblStudent.getModel();
        defaultTableModel.setRowCount(0);
    }

    // write from list to file
    public void writeToFile (ArrayList<Student> _users){
        try{
            FileWriter fw = new FileWriter(dbFile);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Student user: _users
            ) {
                bw.write(user.toString());
                bw.newLine();
            }
            bw.close();
            fw.close();

        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    // read file
    public Object[] readFile(){
        Object[] objects;
        try {
            FileReader fr = new FileReader(dbFile);
            BufferedReader bufferedReader = new BufferedReader(fr);
            // each lines to array
            objects = bufferedReader.lines().toArray();
            bufferedReader.close();
            return objects;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // return user by Object array
    public void returnStudentsToTable(Object[] objects){
        DefaultTableModel defaultTableModel = (DefaultTableModel) tblStudent.getModel();
        int i = 0;
        while(i < objects.length) {
            String row = objects[i].toString().trim();
            String[] rows = row.split(",");
            defaultTableModel.addRow(rows);
            i++;
        }
    }

    // return user by Object
    public void returnFindedStudentsToTable(Student user){
        DefaultTableModel defaultTableModel = (DefaultTableModel) tblStudent.getModel();
        String[] findedUser = user.toString().split(",");
        defaultTableModel.addRow(findedUser);
    }

    // Check Information of Student
    // Check ID
    public boolean CheckId(String id){
            for (Student user : _students) {
                if (user.getId().equals(id)) {
                    return true;
                }
        }return false;
    }
    // Check ID valid
    public boolean CheckIdValid(String id){
        String regexPattern = "[0-9]+";
        boolean validId = Pattern.compile(regexPattern)
                .matcher(id)
                .matches();
        if (validId){
            return true;
        }
        return false;
    }
    // Check name valid
    public boolean CheckNameValid(String name){
        String regexPattern = "[^0-9]+";
        boolean validName = Pattern.compile(regexPattern)
                .matcher(name)
                .matches();
        if(validName){
            return true;
        }
        return false;
    }
    // Check birthday
    public boolean CheckBirthday(String birthday){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);
        LocalDate date = null;
        try {
            date = LocalDate.parse(birthday, dtf);
            return true;
        } catch (DateTimeParseException e) {
        }
        return false;
    }
    // Check email valid
    public boolean CheckEmailValid(String email){
        String regexPattern = "^(.+)@(\\S+)$";
        boolean validEmail = Pattern.compile(regexPattern)
                .matcher(email)
                .matches();
        if (validEmail){
            return true;
        }
        return false;
    }
    // Check email duplicated
    public boolean CheckEmail(String email){

        for (Student user : _students) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }return false;
    }

    // Check phone number is duplicated
    public boolean CheckPhone(String phoneNumber){
        for (Student user : _students) {
            if (user.getEmail().equals(phoneNumber)) {
                return true;
            }
        }return false;
    }
    // Check phone number is valid
    public boolean CheckPhoneNumberValid(String phoneNumber){
        String regexPattern = "[0-9]{10,11}";
        boolean validEmail = Pattern.compile(regexPattern)
                .matcher(phoneNumber)
                .matches();
        if (validEmail){
            return true;
        }
        return false;
    }

    private void femaleRadioButton(ActionEvent e) {
        femaleRadioButton.setEnabled(false);
        maleRadioButton.setEnabled(true);
    }

    private void maleRadioButton(ActionEvent e) {
        maleRadioButton.setEnabled(false);
        femaleRadioButton.setEnabled(true);
    }

    // interface
    private void initComponents() {
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
                    EmptyBorder(0,0,0,0), "JF\u006frmDesi\u0067ner Ev\u0061luatio\u006e",javax.swing.border.TitledBorder.CENTER,javax.swing
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
                            false, false, false
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
                        int i = tblStudent.getSelectedRow();
                        TableModel model = tblStudent.getModel();
                        idTextField.setText(model.getValueAt(i, 0).toString());
                        nameTextField.setText(model.getValueAt(i, 1).toString());
                        birthdayTextField.setText(model.getValueAt(i, 2).toString());
                        emailTextField.setText(model.getValueAt(i, 3).toString());
                        phoneNumberTextField.setText(model.getValueAt(i, 4).toString());
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



