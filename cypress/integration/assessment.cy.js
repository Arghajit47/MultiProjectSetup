// Define a context for testing the Dynamic Table Website
context("Testing Dynamic Table Website", () => {
  // Describe the specific assessment scenario being tested
  describe("Testing the assessment scenario", () => {
    // Execute this before each test case to visit the website
    beforeEach("Visiting the website", () => {
      cy.visit("https://testpages.herokuapp.com/styled/tag/dynamic-table.html");
    });

    // Test the Dynamic Table functionality
    it("Testing the Dynamic Table functionality", () => {
      // Expand the details section on the webpage
      cy.get("details > summary").click();

      // Clear the textarea containing JSON data
      cy.get("div > p > textarea[id='jsondata']").clear();

      // Load test data from a fixture file and populate the textarea with JSON data
      cy.fixture("testData").then((data) => {
        cy.get("div > p > textarea[id='jsondata']").type(JSON.stringify(data), {
          parseSpecialCharSequences: false,
        });

        // Click the button to refresh the table with new data
        cy.get("#refreshtable").click();

        // Loop through each row in the table to assert data
        for (let i = 2; i <= data.length + 1; i++) {
          // Assert the 'name' column value matches the expected value from Json
          cy.xpath(`//table[@id="dynamictable"]/tr[${i}]/td[1]`).then(
            (text) => {
              expect(text.text()).to.eq(data[i - 2].name);
            }
          );

          // Assert the 'age' column value matches the expected value from Json
          cy.xpath(`//table[@id="dynamictable"]/tr[${i}]/td[2]`).then(
            (text) => {
              expect(text.text()).to.eq(data[i - 2].age.toString());
            }
          );

          // Assert the 'gender' column value matches the expected value from Json
          cy.xpath(`//table[@id="dynamictable"]/tr[${i}]/td[3]`).then(
            (text) => {
              expect(text.text()).to.eq(data[i - 2].gender);
            }
          );
        }
      });
    });
  });
});
