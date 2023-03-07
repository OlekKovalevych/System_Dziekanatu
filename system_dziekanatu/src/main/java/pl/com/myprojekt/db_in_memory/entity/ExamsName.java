package pl.com.myprojekt.db_in_memory.entity;

public class ExamsName {

    private static String firstExam = "Math";
    private static String secondExam = "English";
    private static String threadExam = "Programing";
    private static String forthExam = "History";
    private static String fifthExam = "Physics";

    public static String getFirstExam() {
        return firstExam;
    }

    public static String getSecondExam() {
        return secondExam;
    }

    public static String getThreadExam() {
        return threadExam;
    }

    public static String getForthExam() {
        return forthExam;
    }

    public static String getFifthExam() {
        return fifthExam;
    }
}
