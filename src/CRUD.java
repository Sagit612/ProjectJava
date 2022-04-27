import Database.EGender;
import Database.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.*;

public class CRUD extends JFrame {
    ArrayList<User> _users = new ArrayList<>();
    String dbFile = "database.txt";
    public static void main(String[] args) throws IOException {
        CRUD crud = new CRUD();
        crud.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        crud.setVisible(true);
    }
    public CRUD() throws IOException {
        initComponents();
        DisableAllBtn();
        returnUserToTable(readFile());
        List<String> list = Files.readAllLines(new File(dbFile).toPath(), Charset.defaultCharset() );
        for(String line : list){
            String [] res = line.split(",");
            int res2 = Integer.parseInt(res[2]);
//            EGender.Gender eGender = EGender.Gender.valueOf(res[3]);
            User user = new User(res[0], res[1], res2 /*eGender*/);
            _users.add(user);
        }

    }

    // Clear text inside the text field when implement function
    private void Clear(){
        idTextField.setText("");
        nameTextField.setText("");
    }

    // Disable all button when compile project
    private void DisableAllBtn() {
        idTextField.setEnabled(false);
        nameTextField.setEnabled(false);
        addBtn.setEnabled(false);
        deleteBtn.setEnabled(false);
        updateBtn.setEnabled(false);
        tblStudent.setEnabled(false);
        ageSpinner.setEnabled(false);
    }

    // Choose add function
    private void saveFunctionRadioBtn(ActionEvent e) {
        // TODO add your code here
        tblStudent.setEnabled(false);
        deleteBtn.setEnabled(false);
        updateBtn.setEnabled(false);
        idTextField.setEnabled(true);
        nameTextField.setEnabled(true);
        ageSpinner.setEnabled(true);
        addBtn.setEnabled(true);
        saveRadioButton.setEnabled(false);
        deleteRadioButton.setEnabled(true);
        updateRadioButton.setEnabled(true);
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
        ageSpinner.setEnabled(false);
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
        ageSpinner.setEnabled(true);
        idTextField.setEnabled(false);
    }

    // add function
    private void addBtn(ActionEvent e) {
        // TODO add your code here
        String id = idTextField.getText().trim();
        String name = nameTextField.getText().trim();
        int age = Integer.parseInt(ageSpinner.getValue().toString().trim());
//        EGender.Gender eGender = EGender.Gender.valueOf(genderComboBox.getSelectedItem().toString().trim());
        if (!id.isEmpty() && !name.isEmpty()){
            if (CheckId(id) && CheckAge(age)){
                JOptionPane.showMessageDialog(null, "ID is duplicated or Age is not in range from 1 to 100", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            _users.add(new User(id, name, age /*eGender*/));
            writeToFile(_users);
            Clear();
            clearTableContents();
            returnUserToTable(readFile());
        }else {
            JOptionPane.showMessageDialog(null, "Fill Required", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    // delete function
    private void deleteBtn(ActionEvent e) throws Exception{
        // TODO add your code here
        String id = idTextField.getText().trim();
        for (User user : _users) {
            if (user.getId().equals(id)) {
                _users.remove(user);
            }
        }
        writeToFile(_users);
        Clear();
        clearTableContents();
        returnUserToTable(readFile());
    }

    // update function
    private void updateBtn(ActionEvent e) {
        // TODO add your code here
        String id = idTextField.getText().trim();
        String name = nameTextField.getText().trim();
        int age = Integer.parseInt(ageSpinner.getValue().toString().trim());
        if (!id.isEmpty() && !name.isEmpty()){
//        EGender.Gender eGender = EGender.Gender.valueOf(genderComboBox.getSelectedItem().toString().trim());
        for (User user : _users) {
            if (user.getId().equals(id)) {
                user.setName(name);
                user.setAge(age);
//                user.setGender(eGender);
            }
        }
        writeToFile(_users);
        Clear();
        clearTableContents();
        returnUserToTable(readFile());
        }else {
            JOptionPane.showMessageDialog(null, "Fill Required", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    // search function
    private void searchBtn(ActionEvent e) {
        String id = searchTextField.getText().trim();
        if (!id.isEmpty()){
            for (User user: _users) {
                if (user.getId().equals(id)) {
                    User findedUser = new User(user.getId(), user.getName(), user.getAge());
                    clearTableContents();
                    returnFindedUserToTable(findedUser);
                }
            }

        }
        else{
            JOptionPane.showMessageDialog(null, "Filling Search Field Required", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    // sort ascending by ID function
    private void sortARadioBtn(ActionEvent e){
        sortDRadioBtn.setEnabled(true);
        sortARadioBtn.setEnabled(false);
        Collections.sort(_users, new Comparator<User>() {
            public int compare(User s1, User s2) {
                return s1.getId().compareTo(s2.getId());
            }
        });
        writeToFile(_users);
        clearTableContents();
        returnUserToTable(readFile());
    }

    // sort descending by ID function
    private void sortDRadioBtn(ActionEvent e) {
        sortDRadioBtn.setEnabled(false);
        sortARadioBtn.setEnabled(true);
        Collections.sort(_users, new Comparator<User>() {
            public int compare(User s1, User s2) {
                return s2.getId().compareTo(s1.getId());
            }
        });
        writeToFile(_users);
        clearTableContents();
        returnUserToTable(readFile());
    }

    // clear all the contents of table
    public void clearTableContents(){
        DefaultTableModel defaultTableModel = (DefaultTableModel) tblStudent.getModel();
        defaultTableModel.setRowCount(0);
    }

    // write from list to file
    public void writeToFile (ArrayList<User> _users){
        try{
            FileWriter fw = new FileWriter(dbFile);
            BufferedWriter bw = new BufferedWriter(fw);
            for (User user: _users
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
    public void returnUserToTable(Object[] objects){
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
    public void returnFindedUserToTable(User user){
        DefaultTableModel defaultTableModel = (DefaultTableModel) tblStudent.getModel();
        String[] findedUser = user.toString().split(",");
        defaultTableModel.addRow(findedUser);
    }

    // Check id of Student
    public boolean CheckId(String id){
        for (User user : _users) {
            if (user.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    // Check age of Student
    public boolean CheckAge(int age){
        if (age <= 0 && age >=100){
            return true;
        }
        return false;
    }

    // interface
    private void initComponents() {
    // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
    // Generated using JFormDesigner Evaluation license - Quoc
    label1 = new JLabel();
    label2 = new JLabel();
    idTextField = new JTextField();
    nameTextField = new JTextField();
    scrollPane1 = new JScrollPane();
    tblStudent = new JTable();
    label4 = new JLabel();
    ageSpinner = new JSpinner();
    hSpacer1 = new JPanel(null);
    panel1 = new JPanel();
    updateRadioButton = new JRadioButton();
    saveRadioButton = new JRadioButton();
    addBtn = new JButton();
    updateBtn = new JButton();
    deleteBtn = new JButton();
    deleteRadioButton = new JRadioButton();
    label3 = new JLabel();
    searchTextField = new JTextField();
    searchBtn = new JButton();
    sortARadioBtn = new JRadioButton();
    sortDRadioBtn = new JRadioButton();

    //======== this ========
    var contentPane = getContentPane();

    //---- label1 ----
    label1.setText("ID");

    //---- label2 ----
    label2.setText("Name");

    //======== scrollPane1 ========
    {

        //---- tblStudent ----
        String[] nameOfColumn= {"ID", "Name", "Age"};
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
            }

        });
        scrollPane1.setViewportView(tblStudent);
    }

    //---- label4 ----
    label4.setText("Age:");

    //======== panel1 ========
    {
        panel1.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder (
                new javax . swing. border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frmDes\u0069gner \u0045valua\u0074ion"
                , javax. swing .border . TitledBorder. CENTER ,javax . swing. border .TitledBorder . BOTTOM
                , new java. awt .Font ( "D\u0069alog", java .awt . Font. BOLD ,12 )
                ,java . awt. Color .red ) ,panel1. getBorder () ) ); panel1. addPropertyChangeListener(
            new java. beans .PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e
            ) { if( "\u0062order" .equals ( e. getPropertyName () ) )throw new RuntimeException( )
                    ;} } );

        //---- updateRadioButton ----
        updateRadioButton.setText("Update Function");
        updateRadioButton.addActionListener(e -> updateRadioBtn(e));

        //---- saveRadioButton ----
        saveRadioButton.setText("Save function");
        saveRadioButton.addActionListener(e -> saveFunctionRadioBtn(e));

        //---- addBtn ----
        addBtn.setText("Save");
        addBtn.addActionListener(e -> addBtn(e));

        //---- updateBtn ----
        updateBtn.setText("Update");
        updateBtn.addActionListener(e -> updateBtn(e));

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
        deleteRadioButton.setText("Delete function");
        deleteRadioButton.addActionListener(e -> deleteRadioBtn(e));

        //---- label3 ----
        label3.setText("Search ID:");

        //---- searchBtn ----
        searchBtn.setText("Seach");
        searchBtn.addActionListener(e -> searchBtn(e));

        //---- sortARadioBtn ----
        sortARadioBtn.setText("Sort ID acending");
        sortARadioBtn.addActionListener(e -> sortARadioBtn(e));

        //---- sortDRadioBtn ----
        sortDRadioBtn.setText("Sort ID descending");
        sortDRadioBtn.addActionListener(e -> sortDRadioBtn(e));

        GroupLayout panel1Layout = new GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                        .addGroup(panel1Layout.createSequentialGroup()
                                .addGroup(panel1Layout.createParallelGroup()
                                        .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(panel1Layout.createParallelGroup()
                                                        .addComponent(updateRadioButton)
                                                        .addComponent(deleteRadioButton))
                                                .addGap(18, 18, 18)
                                                .addGroup(panel1Layout.createParallelGroup()
                                                        .addGroup(panel1Layout.createSequentialGroup()
                                                                .addComponent(deleteBtn)
                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                        .addGroup(panel1Layout.createSequentialGroup()
                                                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(updateBtn)
                                                                        .addComponent(addBtn))
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                        .addGroup(panel1Layout.createSequentialGroup()
                                                .addGroup(panel1Layout.createParallelGroup()
                                                        .addGroup(panel1Layout.createSequentialGroup()
                                                                .addComponent(label3, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(searchTextField, GroupLayout.PREFERRED_SIZE, 351, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(panel1Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(saveRadioButton)))
                                                .addGap(18, 18, Short.MAX_VALUE)))
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(searchBtn)
                                        .addComponent(sortDRadioBtn, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                                        .addComponent(sortARadioBtn, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
                                .addGap(80, 80, 80))
        );
        panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(saveRadioButton, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(addBtn)
                                        .addComponent(sortARadioBtn))
                                .addGap(12, 12, 12)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(updateRadioButton)
                                        .addComponent(updateBtn))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(deleteRadioButton)
                                        .addComponent(deleteBtn)
                                        .addComponent(sortDRadioBtn))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(searchBtn)
                                        .addComponent(searchTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(25, 25, 25))
        );
    }

    GroupLayout contentPaneLayout = new GroupLayout(contentPane);
    contentPane.setLayout(contentPaneLayout);
    contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                    .addGroup(contentPaneLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(contentPaneLayout.createParallelGroup()
                                    .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 716, Short.MAX_VALUE)
                                    .addGroup(contentPaneLayout.createSequentialGroup()
                                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(hSpacer1, GroupLayout.PREFERRED_SIZE, 388, GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(contentPaneLayout.createSequentialGroup()
                                                            .addComponent(label1, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(idTextField, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addComponent(label2)
                                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(nameTextField, GroupLayout.PREFERRED_SIZE, 377, GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(label4)
                                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(ageSpinner, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE))
                                                    .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGap(0, 24, Short.MAX_VALUE)))
                            .addContainerGap())
    );
    contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                    .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(14, 14, 14)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label1)
                                    .addComponent(label2)
                                    .addComponent(idTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label4)
                                    .addComponent(ageSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(panel1, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                    .addComponent(hSpacer1, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(scrollPane1, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 298, GroupLayout.PREFERRED_SIZE))
                            .addGap(534, 534, 534))
    );
    pack();
    setLocationRelativeTo(getOwner());
    // JFormDesigner - End of component initialization  //GEN-END:initComponents
}



    // JFormDesigner - Start of variables declaration  //GEN-END:variables
    private JLabel label1;
    private JLabel label2;
    private JTextField idTextField;
    private JTextField nameTextField;
    private JScrollPane scrollPane1;
    private JTable tblStudent;
    private JLabel label4;
    private JSpinner ageSpinner;
    private JPanel hSpacer1;
    private JPanel panel1;
    private JRadioButton updateRadioButton;
    private JRadioButton saveRadioButton;
    private JButton addBtn;
    private JButton updateBtn;
    private JButton deleteBtn;
    private JRadioButton deleteRadioButton;
    private JLabel label3;
    private JTextField searchTextField;
    private JButton searchBtn;
    private JRadioButton sortARadioBtn;
    private JRadioButton sortDRadioBtn;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}



