package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {
    SelenideElement dateOfBirthInput = $("#dateOfBirthInput"),
            monthSelect = $(".react-datepicker__month-select"),
            yearSelect = $(".react-datepicker__year-select");

    public void setDate(String day, String month, String year) {
        dateOfBirthInput.click();
        monthSelect.selectOption(month); // April
        yearSelect.selectOption(year);
        // Не вынес селектор в переменную потому что пришлось бы делать 3 на каждую его часть и саму переменную day
        $(".react-datepicker__day--0" + day +
                ":not(.react-datepicker__day--outside-month)").click();
    }
}
