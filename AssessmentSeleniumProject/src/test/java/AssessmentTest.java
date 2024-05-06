import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class AssessmentTest {
    public static void main(String[] args) throws IOException, ParseException {
        // Set up Chrome WebDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);

        // Initialize JSONParser and read JSON file
        JSONParser jsonParser = new JSONParser();
        FileReader reader = new FileReader("AssessmentSlelenium/src/main/resources/testData.json");
        Object obj = jsonParser.parse(reader);
        JSONArray userList = (JSONArray) obj;

        // Print the size of the JSON array
        System.out.println(userList.size());

        // Navigate to the test page
        driver.get("https://testpages.herokuapp.com/styled/tag/dynamic-table.html");

        // Expand the JSON input section
        driver.findElement(By.cssSelector("details > summary")).click();

        // Clear and input JSON data into the textarea
        driver.findElement(By.cssSelector("div > p > textarea[id='jsondata']")).clear();
        driver.findElement(By.cssSelector("div > p > textarea[id='jsondata']")).sendKeys(obj.toString());

        // Click the button to refresh the table
        driver.findElement(By.id("refreshtable")).click();

        // Iterate over each JSON object in the array
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

        // Quit the WebDriver session
        driver.quit();
    }
}
