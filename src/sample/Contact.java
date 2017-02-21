package sample;

/**
 * Created by souporman on 2/20/17.
 */
public class Contact {

    private String fullName;
    private String phoneNumber;
    private String email;

    public Contact(String fullName, String phoneNumber, String email) {
        this.setFullName(fullName);
        this.setPhoneNumber(phoneNumber);
        this.setEmail(email);
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString(){
        return String.format("%s, %s, %s",getFullName(),getPhoneNumber(),getEmail());
    }
}
