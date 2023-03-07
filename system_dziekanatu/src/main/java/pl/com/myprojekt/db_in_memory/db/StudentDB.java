package pl.com.myprojekt.db_in_memory.db;

import pl.com.myprojekt.db_in_memory.entity.StudentUITM;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

public class StudentDB {

    private static final int START_ARRAY_SIZE = 30;
    private static final int MINIMAL_STUDENT_YEAR = 2004;
    private static final int MAX_STUDENT_YEAR = 1920;
    private static final int MAX_MARK = 5;
    private static final int MIN_MARK = 1;
    private static StudentUITM[] students = new StudentUITM[START_ARRAY_SIZE];
    private static StudentDB instance;

    private StudentDB() {
    }

    public static StudentDB getInstance() {
        if (instance == null) {
            instance = new StudentDB();
        }
        return instance;
    }

    public String create(final StudentUITM studentUITM) {
        studentUITM.setId(generateId());
        if (!isYearStudent(studentUITM.getYearOfBirth())) {
            return "Student can`t have this year of birth";
        }
        if (!checkMarks(studentUITM)) {
            return "Student cant have this marks";
        }
        boolean dataRecordingCapability = false;
        for (int i = 0; i < students.length; i++) {
            if (students[i] == null) {
                students[i] = studentUITM;
                dataRecordingCapability = true;
                break;
            }
        }
        if (!dataRecordingCapability) {
            this.increasingArray(studentUITM);
        }
        return "Student create";
    }

    private boolean checkMarks(StudentUITM studentUITM) {
        int[] array = studentUITM.getGradesForTheExam();
        for (int j : array) {
            if (j > MAX_MARK || j < MIN_MARK) {
                return false;
            }
        }
        return true;
    }

    private void increasingArray(StudentUITM studentUITM) {
        final StudentUITM[] newArray = new StudentUITM[students.length + START_ARRAY_SIZE];
        System.arraycopy(students, 0, newArray, 0, students.length);
        students = newArray;
        students[students.length] = studentUITM;
    }

    public String deleteAll() {
        Arrays.fill(students, null);
        return "All Student were delete";
    }

    public String update(StudentUITM studentUITM) {//worker
        StudentUITM current = this.findById(studentUITM.getId());
        if (current != null) {
            current.setName(studentUITM.getName());
            current.setSurname(studentUITM.getSurname());
            current.setYearOfBirth(studentUITM.getYearOfBirth());
            current.setGradesForTheExam(studentUITM.getGradesForTheExam());
            return "Student was update";
        } else {
            return "we dont have student with this id";
        }
    }

    private Boolean isYearStudent(int year) {
        return year <= MINIMAL_STUDENT_YEAR && year >= MAX_STUDENT_YEAR;
    }

    public StudentUITM findById(final String id) {
        int i;
        for (i = 0; i < students.length; i++) {
            if (students[i] == null) {
                break;
            }
            if (Objects.equals(students[i].getId(), id)) {
                return students[i];
            }
        }
        return null;
    }

    public StudentUITM[] findAll() {
        int sizeResultArray = 0;
        for (int i = 0; i < students.length; i++) {
            if (students[i] == null) {
                sizeResultArray = i;
                break;
            }
        }
        final StudentUITM[] newResultArray = new StudentUITM[sizeResultArray];
        System.arraycopy(students, 0, newResultArray, 0, sizeResultArray);
        return newResultArray;
    }


    public String delete(String id) {
        int studentDeletePoint = 0;
        boolean studentExist = false;
        for (int i = 0; i < students.length; i++) {
            if (null != students[i] && id.equals(students[i].getId())) {
                students[i] = null;
                studentDeletePoint = i;
                studentExist = true;
            }
        }
        if (studentExist) {
            final StudentUITM[] newArray = new StudentUITM[students.length];
            System.arraycopy(students, 0, newArray, 0, studentDeletePoint);
            System.arraycopy(students, studentDeletePoint + 1, newArray, studentDeletePoint, students.length - (studentDeletePoint + 1));
            students = newArray;
            return "Student was delete";

        }
        return "we dont have student with this id";
    }


    private String generateId() {
        String id;
        do {
            id = this.generateStringUUID();
        } while (this.existId(id));
        return id;
    }

    private boolean existId(final String id) {

        for (final StudentUITM studentUITM : students) {
            if (null == studentUITM) break;
            if (id.equals(studentUITM.getId())) {
                return true;
            }
        }
        return false;
    }

    private String generateStringUUID() {
        return String.copyValueOf(UUID.randomUUID().toString().toCharArray(), 0, 6);
    }

}
