package Locators;

import org.openqa.selenium.By;

public class Locator {
    public By tableDataButton = By.cssSelector("details > summary");
    public By refreshTable = By.cssSelector("#refreshtable");
    public By inputField = By.cssSelector("div > p > textarea[id='jsondata']");
}
