package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;

import java.util.Map;
import java.util.Set;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationPage {

    CalendarComponent calendar = new CalendarComponent();

    private final String FORM_TITLE = "Student Registration Form";

    private SelenideElement
            formTitle = $(".practice-form-wrapper"),
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            emailInput = $("#userEmail"),
            genderRadioButton = $("[for=gender-radio-1]"),
            userNumber = $("#userNumber"),
            subjectsInput = $("#subjectsInput"),
            hobbiesCheckbox = $("[for=hobbies-checkbox-3]"),
            uploadButton = $("#uploadPicture"),
            addressInput = $("#currentAddress"),
            stateSelector = $("#state"),
            stateCityDropdown = $("#stateCity-wrapper"),
            citysSlector = $("#city"),
            submitButton = $("#submit");

    public RegistrationPage openPage() {
        open("https://demoqa.com/automation-practice-form");
        formTitle.shouldHave(text(FORM_TITLE));
        return this;
    }

    public RegistrationPage typeFirstName(String value) {
        firstNameInput.setValue(value);
        return this;
    }

    public RegistrationPage typeLastName(String value) {
        lastNameInput.setValue(value);
        return this;
    }

    public RegistrationPage typeEmail(String value) {
        emailInput.setValue(value);
        return this;
    }

    public RegistrationPage choseGender() {
        genderRadioButton.click();
        return this;
    }

    public RegistrationPage typeNumber(String value) {
        userNumber.setValue(value);
        return this;
    }

    // Пробросил метод сюда чтобы не разрывать чейн в самом тесте
    public RegistrationPage pickDate(String day, String month, String year) {
        calendar.setDate(day, month, year);
        return this;
    }

    public RegistrationPage typeSubject(String value) {
        subjectsInput
                .setValue(value)
                .pressEnter();
        return this;
    }

    public RegistrationPage choseHobbies() {
        hobbiesCheckbox.click();
        return this;
    }

    public RegistrationPage uploadPicture(String path) {
        uploadButton
                .uploadFromClasspath(path);
        return this;
    }

    public RegistrationPage typeAddress(String value) {
        addressInput.setValue(value);
        return this;
    }

    public RegistrationPage choseStateAndCity(String state, String city) {
        stateSelector.click();
        stateCityDropdown.$(byText(state)).click();
        citysSlector.click();
        stateCityDropdown.$(byText(city)).click();
        return this;
    }

    public RegistrationPage submit() {
        submitButton.click();
        return this;
    }

    // Проверка того что поля заполнены верно
    public void checkResult(Map<String, String> valuesToCheck) {
        $(".modal-title").shouldHave(text("Thanks for submitting the form"));

        // Итерируемся по мапе в которой ключ это текст поля, а значение это то, что мы вводили в шагах.
        // Выглядит странно, но помогло сократить повторяющийся 10 раз код поиска элемента и ассерт на нём.
        Set entrySet = valuesToCheck.entrySet();
        for (Object o : entrySet) {
            Map.Entry<String, String> element = (Map.Entry) o;

            $(".table-responsive").$(byText(element.getKey())).sibling(0).shouldHave(text(element.getValue()));
        }
    }
}
