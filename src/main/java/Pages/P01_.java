package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P01_ {
    private final WebDriver driver;

    private final By A = By.cssSelector("");

    public P01_(WebDriver driver) {
        this.driver = driver;
    }

    public P01_ A01() {
        return this;
    }

    public P02_ B01() {

        return new P02_(driver);
    }


}
