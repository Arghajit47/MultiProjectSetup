package StepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import Locators.Locator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileReader;
import java.util.concurrent.TimeUnit;

public class StepDefinitions extends Locator {
    WebDriver driver = new ChromeDriver();
    

    @Given("Visit The Website")
    public void visitWebsite() {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
        driver.get("https://testpages.herokuapp.com/styled/tag/dynamic-table.html");
    }

    @And("Says we visited")
    public void print() {
        System.out.println("Successfully visited the website!");
    }

    @When("Click on Table Data Button")
    public void clickTableDataButton(){
        driver.findElement(tableDataButton).click();
    }

    @And("We wait for {int} seconds")
    public void we_wait_seconds(int time) throws InterruptedException {
        int ms = time * 1000;
        Thread.sleep(ms);
    }

    @Then("Load the json text from {string}")
    public Object loadData(String fileName) throws Exception {
        // Initialize JSONParser and read JSON file
        JSONParser jsonParser = new JSONParser();
        FileReader reader = new FileReader("/Users/arghajitsingha/Assessment/AssessmentCucumber/src/main/resources/"+fileName);
        Object obj = jsonParser.parse(reader);
        return obj;
    }
    @And("We input the file data")
    public void fillInputField() throws Exception {
        Object data = loadData("testData.json");
        driver.findElement(inputField).clear();
        driver.findElement(inputField).sendKeys(data.toString());
    }

    @And ("We click on the refreshTable Button")
    public void clickRefreshButton() {
        driver.findElement(refreshTable).click();
    }


    @And("We verify the newly updated table data from {string}")
    public void weVerifyTheNewlyUpdatedTableData(String fileName) throws Exception {
        Object data = loadData(fileName);
        JSONArray userList = (JSONArray) data;
        for (int i = 0; i < userList.size(); i++) {
            JSONObject user = (JSONObject) userList.get(i);

            // Extract and print name, age, and gender from each object
            String name = user.get("name").toString();
            driver.findElement(By.xpath("//table[@id=\"dynamictable\"]/tr["+(i+2)+"]/td[1]")).getText().equals(name);
            System.out.println(name);

            String age = user.get("age").toString();
            driver.findElement(By.xpath("//table[@id=\"dynamictable\"]/tr["+(i+2)+"]/td[2]")).getText().equals(age);
            System.out.println(age);

            String gender = user.get("gender").toString();
            driver.findElement(By.xpath("//table[@id=\"dynamictable\"]/tr["+(i+2)+"]/td[3]")).getText().equals(gender);
            System.out.println(gender);
        }
    }

    @And("We close the browser")
    public void close() {
        driver.quit();
    }
}
