package pl.com.myprojekt.db_in_memory.dao;

import pl.com.myprojekt.db_in_memory.db.StudentDB;
import pl.com.myprojekt.db_in_memory.entity.StudentUITM;

public class StudentDao {

    private final StudentDB db;

    public StudentDao(StudentDB db) {
        this.db = db;
    }

    public String create(StudentUITM studentUITM) {return db.create(studentUITM);}

    public StudentUITM findById(String id) {
        return db.findById(id);
    }

    public StudentUITM[] findAll() {return db.findAll();}

    public String update(StudentUITM studentUITM) {
        return db.update(studentUITM);
    }

    public String delete(String id) {
        return db.delete(id);
    }

    public String deleteAll() {return db.deleteAll();}

}
