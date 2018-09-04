package algorithm.sort;

public class Test {
    public static void main(String[] args) {
        Student[] students = new Student[10];
        for(int i = 0; i < 10; i++) {
            students[i] = new Student((int)(Math.random()*1000+1));
        }


        SortUtils.print(students);

        HeapSort.sort(students);

        SortUtils.print(students);
    }

}
