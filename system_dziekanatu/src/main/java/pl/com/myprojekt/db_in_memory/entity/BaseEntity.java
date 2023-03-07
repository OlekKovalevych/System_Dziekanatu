package pl.com.myprojekt.db_in_memory.entity;

public abstract class BaseEntity {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
