import Controller.StudentController;
import View.MainFrame;

import javax.swing.*;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        StudentController studentController = new StudentController();
        MainFrame crud = new MainFrame();

        crud.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        crud.setVisible(true);
    }
}
