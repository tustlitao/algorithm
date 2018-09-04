package algorithm.sort;

public class Student implements Comparable<Student>{
    private int id;

    public Student() {
    }

    public Student(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                '}';
    }

    @Override
    public int compareTo(Student o) {
        if (id < o.getId()) return -1;
        else if(id == o.getId()) return 0;
        else return 1;
    }
}
