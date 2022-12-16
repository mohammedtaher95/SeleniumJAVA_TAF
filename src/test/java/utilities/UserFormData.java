package utilities;

import com.github.javafaker.Faker;

public class UserFormData {

    private String fullName;
    private String email;
    private String friendEmail;
    private String message;
    private String FirstName;
    private String LastName;
    private String OldPassword;
    private String NewPassword;
    private String Address;
    private String City;
    private String PostalCode;
    private String PhoneNumber;

    public UserFormData() {
        Faker faker = new Faker();
        fullName = faker.name().fullName();
        email = faker.internet().safeEmailAddress();
        friendEmail = faker.internet().safeEmailAddress();
        message = faker.gameOfThrones().quote();
        FirstName = faker.name().firstName();
        LastName = faker.name().lastName();
        OldPassword = faker.number().digits(8).toString();
        NewPassword = faker.number().digits(9).toString();
        Address = faker.address().streetAddress();
        City = faker.address().city();
        PostalCode = faker.number().digits(5).toString();
        PhoneNumber = faker.phoneNumber().cellPhone();
    }

    public String getFullName() {
        return fullName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }
    public String getOldPassword() {
        return OldPassword;
    }
    public String getNewPassword() {
        return NewPassword;
    }

    public String getEmail() {
        return email;
    }
    public String getFriendEmail() {
        return friendEmail;
    }
    public String getMessage() {
        return message;
    }

    public String getAddress()
    {
        return Address;
    }

    public String getCity()
    {
        return City;
    }
    public String getPostalCode()
    {
        return PostalCode;
    }

    public String getPhoneNumber()
    {
        return PhoneNumber;
    }

}
