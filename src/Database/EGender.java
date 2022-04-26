package Database;

public class EGender {
    public enum Gender{
        Male("Male"),
        Female("Female");
        String gender;

        Gender(String gender) {
            this.gender = gender;
        }

        @Override
        public String toString() {
            return gender;
        }
    }

}
