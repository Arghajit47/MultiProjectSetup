import { test, expect } from "@playwright/test";
const testData = require("../Data/testData.json");

test.describe("Testing Dynamic Table Website", () => {
  test.beforeEach("Test Website visit", async ({ page }) => {
    await page.goto(
      "https://testpages.herokuapp.com/styled/tag/dynamic-table.html"
    );
    await page.waitForLoadState();
  });
  test("Testing the Dynamic Table functionality", async ({ page }) => {
    await page.click("details > summary");
    await page.locator("div > p > textarea[id='jsondata']").clear();
    await page.fill(
      "div > p > textarea[id='jsondata']",
      JSON.stringify(testData)
    );
    await page.click("#refreshtable");
    for (let i = 2; i <= testData.length + 1; i++) {
      await expect(
        page.locator(`//table[@id="dynamictable"]/tr[${i}]/td[1]`)
      ).toHaveText(testData[i - 2].name);
      await expect(
        page.locator(`//table[@id="dynamictable"]/tr[${i}]/td[2]`)
      ).toHaveText(testData[i - 2].age.toString());
      await expect(
        page.locator(`//table[@id="dynamictable"]/tr[${i}]/td[3]`)
      ).toHaveText(testData[i - 2].gender);
    }
  });
});
