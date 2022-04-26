package Database;

public class User {
    private String id;
    private String name;
    private int age;
//    private EGender.Gender gender;

    public User() {
    }

    public User(String id, String name, int age /*EGender.Gender eGender*/) {
        this.id = id;
        this.name = name;
        this.age = age;
//        this.gender = gender;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

//    public EGender.Gender getGender() {
//        return gender;
//    }
//
//    public void setGender(EGender.Gender gender) {
//        this.gender = gender;
//    }

    @Override
    public String toString() {
        return id + "," + name + "," + age /*"," + gender.toString()*/;
    }
}
