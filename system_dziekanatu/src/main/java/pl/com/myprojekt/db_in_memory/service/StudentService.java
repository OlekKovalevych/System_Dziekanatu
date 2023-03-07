package pl.com.myprojekt.db_in_memory.service;

import pl.com.myprojekt.db_in_memory.dao.StudentDao;
import pl.com.myprojekt.db_in_memory.entity.StudentUITM;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class StudentService {
    private static final int GOOD_MARK = 4;
    private final StudentDao studentDao;

    public StudentService(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public String create(StudentUITM studentUITM) {
        return studentDao.create(studentUITM);
    }

    public StudentUITM findById(String id) {
        return studentDao.findById(id);
    }

    public StudentUITM[] findAll() {
        return studentDao.findAll();
    }

    public String update(StudentUITM studentUITM) {
        return studentDao.update(studentUITM);
    }

    public String delete(String id) {
        return studentDao.delete(id);
    }


    public String deleteAll() {
        return studentDao.deleteAll();
    }


    public List<StudentUITM> nameStudentWithUserMark(int mark) {
        List<StudentUITM> student = Arrays.asList(studentDao.findAll());
        List<StudentUITM> result = new ArrayList<>();

        for (int i = 0; i < student.size(); i++) {
            if (ratingCalculation(student.get(i)) == mark) {
                result.add(student.get(i));
            }
        }
        return result;
    }

    public int numberStudentsWhitsMark(int mark) {
        StudentUITM[] array = studentDao.findAll();
        int result = 0;
        for (StudentUITM studentUITM : array) {
            if (ratingCalculation(studentUITM) == mark) {
                result++;
            }
        }
        return result;
    }

    public int studentsYear(StudentUITM studentUITM) {
        return Calendar.getInstance().get(Calendar.YEAR) - studentUITM.getYearOfBirth();
    }

    public StudentUITM[] highRatingOfRatings() {
        StudentUITM[] studentUITMS = studentDao.findAll();
        for (int i = 0; i < studentUITMS.length; i++) {
            if (ratingCalculation(studentUITMS[i]) < GOOD_MARK) {
                studentUITMS[i] = null;
            }
        }
        return studentUITMS;
    }

    public int ratingCalculation(StudentUITM studentUITM) {
        int result = 0;
        int[] bufferArray = studentUITM.getGradesForTheExam();
        for (int i = 0; i < StudentUITM.SIZE_ARRAY_GRADES; i++) {
            result = result + bufferArray[i];
        }
        return result / StudentUITM.SIZE_ARRAY_GRADES;
    }

    public StudentUITM[] studentSort() {
        StudentUITM[] studentsUITM = studentDao.findAll();
        for (int i = 0; i < studentsUITM.length; i++) {
            for (int j = 0; j < studentsUITM.length - 1; j++) {
                if (studentsYear(studentsUITM[j]) < studentsYear(studentsUITM[j + 1])) {
                    StudentUITM temp = studentsUITM[j];
                    studentsUITM[j] = studentsUITM[j + 1];
                    studentsUITM[j + 1] = temp;
                }
            }
        }
        return studentsUITM;
    }
}


