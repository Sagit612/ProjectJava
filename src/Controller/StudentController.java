package Controller;

import Database.Student;
import View.MainFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.regex.Pattern;

public class StudentController {
    public static ArrayList<Student> _students = new ArrayList<>();
    public static String dbFile = "database.txt";
    public static String findedStudentFile = "database2.txt";

    public StudentController() throws IOException {

    }



    public void readDataFromFile() throws IOException {
        List<String> list = Files.readAllLines(new File(dbFile).toPath(), Charset.defaultCharset());
        for (String line : list) {
            String[] res = line.split(",");
            Student user = new Student(res[0], res[1], res[2], res[3], res[4], res[5]);
            _students.add(user);
        }
    }

    public void AddStudent(Student student) {
        _students.add(student);
        writeToFile(_students);

    }
    public void DeleteStudent(String id){
            for (int i = 0; i < _students.size(); i++) {
                if (_students.get(i).getId().equals(id)) {
                    _students.remove(i);
                }
            }
            writeToFile(_students);
    }
    public static void UpdateStudent(String id, String name, String gender, String birthday, String email, String phoneNumber){
        for (Student student : _students) {
            if (student.getId().equals(id)) {
                student.setName(name);
                student.setGender(gender);
                student.setBirthday(birthday);
                student.setEmail(email);
                student.setPhoneNumber(phoneNumber);
            }
        }
        writeToFile(_students);
    }
//    public void SearchStudent(String id, String name){
//        for (Student student : _students) {
//            if ((student.getId().equals(id) || student.getName().contains(name))
//                    || (student.getId().equals(id) && student.getName().contains(name))) {
//                Student findedStudent = new Student(student.getId(), student.getName(), student.getGender(), student.getBirthday()
//                        , student.getEmail(), student.getPhoneNumber());
//                mainFrame.returnFindedStudentsToTable(findedStudent);
//            }
//        }
//
//    }
    public void SortStudentAscendingById(){
        Collections.sort(_students, new Comparator<Student>() {
            public int compare(Student s1, Student s2) {
                int student1 = Integer.parseInt(s1.getId());
                int student2 = Integer.parseInt(s2.getId());
                return student1 - student2;
            }
        });
        writeToFile(_students);
    }
    public void SortStudentDescendingById(){
        Collections.sort(_students, new Comparator<Student>() {
            public int compare(Student s1, Student s2) {
                int student1 = Integer.parseInt(s1.getId());
                int student2 = Integer.parseInt(s2.getId());
                return student2 - student1;
            }
        });
        writeToFile(_students);
    }

    // Check Information of Student
    // Check ID
    public boolean CheckId(String id){
        for (Student user : StudentController._students) {
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
        } catch (DateTimeParseException e) {}
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

        for (Student user : StudentController._students) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }return false;
    }

    // Check phone number is duplicated
    public boolean CheckPhone(String phoneNumber){
        for (Student user : StudentController._students) {
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

    /*------ End Check Information ------*/









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

    // write from list to file
    public static void writeToFile(ArrayList<Student> _users){
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
}

