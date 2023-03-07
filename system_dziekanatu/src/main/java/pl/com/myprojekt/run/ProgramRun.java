package pl.com.myprojekt.run;

import pl.com.myprojekt.db_in_memory.controller.StudentController;

import javax.swing.*;

public class ProgramRun extends JFrame {

    public void run() {
        StudentController studentController = new StudentController("Dean 's Office UITM");
        studentController.setVisible(true);
        StudentController.startCreate();
    }
}
