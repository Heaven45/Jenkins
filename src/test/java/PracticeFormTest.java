import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import java.util.Map;

public class PracticeFormTest {
    Faker faker = new Faker();

    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String fullName = String.format("%s %s", firstName, lastName);
    String email = faker.internet().emailAddress();
    String gender = "Male";
    String mobile = faker.phoneNumber().subscriberNumber(10);
    String day = "07";
    String month = "July";
    String year = "1984";
    String fullDate = "07 July,1984";
    String subjects = "Computer Science";
    String pathToImage = "images/test.png";
    String currentAddress = faker.address().fullAddress();
    String stateAndCity = "NCR Delhi";

    RegistrationPage registrationPage = new RegistrationPage();

    // Мапа для метода .checkResult
    public Map<String, String> valuesToCheck = Map.of(
            "Student Name", fullName,
            "Student Email", email,
            "Gender", gender,
            "Mobile", mobile,
            "Date of Birth", fullDate,
            "Subjects", subjects,
            "Hobbies", "Music",
            "Picture", "test.png",
            "Address", currentAddress,
            "State and City", stateAndCity);

    @BeforeAll
    static void beforeAll() {
        Configuration.startMaximized = true;
    }

    @Test
    void fillFormTest() {

        registrationPage
                .openPage()
                .typeFirstName(firstName)
                .typeLastName(lastName)
                .typeEmail(email)
                .choseGender()
                .typeNumber(mobile)
                .pickDate(day, month, year)
                .typeSubject(subjects)
                .choseHobbies()
                .uploadPicture(pathToImage)
                .typeAddress(currentAddress)
                .choseStateAndCity("NCR", "Delhi")
                .submit()
                .checkResult(valuesToCheck);
    }
}