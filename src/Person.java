import java.util.List;

public class Person {
    public String name;
    public Integer height;
    public Integer weight;
    public Integer age;
    public Boolean sex;
    public List courses;

    public Person(String name, Integer height, Integer weight, Integer age, Boolean sex, List courses) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.sex = sex;
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Person{" +
            "name='" + name + '\'' +
            ", height=" + height +
            ", weight=" + weight +
            ", age=" + age +
            ", sex=" + sex +
            ", courses=" + courses +
            '}';
    }
}
