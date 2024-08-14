package utez.edu.mx.repaso.entity;

public class Employee {
    private long id;
    private String name, surname, lastname, department, createdAt;
    private boolean status;

    public Employee() {
    }

    public Employee(String name, String surname, String lastname, String department) {
        this.name = name;
        this.surname = surname;
        this.lastname = lastname;
        this.department = department;
    }

    public Employee(long id, String name, String surname, String lastname, String department) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.lastname = lastname;
        this.department = department;
    }

    public Employee(long id, String name, String surname, String lastname, String department, String createdAt, boolean status) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.lastname = lastname;
        this.department = department;
        this.createdAt = createdAt;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", department='" + department + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", status=" + status +
                '}';
    }
}
