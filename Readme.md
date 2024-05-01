# Cypress Automated Testing Framework

## Getting Started

To get started with Cypress, follow these steps:

1. **Installation**: To setup the Cypress project via npm:

   `npm i`

1. **Configuration**: Here's the cypress project configuration:

   ```json
   {
     "reporter": "cypress-mochawesome-reporter",
     "reporterOptions": {
       "embeddedScreenshots": true,
       "charts": true,
       "reportPageTitle": "Cypress Inline Report",
       "inlineAssets": true
     },
     "chromeWebSecurity": false,
     "video": false,
     "viewportWidth": 1280,
     "viewportHeight": 720,
     "defaultCommandTimeout": 20000,
     "pageLoadTimeout": 200000,
     "testFiles": ["assessment.cy.js"]
   }
   ```

1. **Running Tests**: Execute your tests using the Cypress Test Runner:

   ```bash
   npm run test
   ```

   ### This command will install the npm package for Cypress and run the tests in background mode on Electron Browser.

   ```bash
   npx run test:headed
   ```

   ### This command will install the npm package for Cypress and run the tests in headed mode on Chrome Browser.

1. **Generating Reports**: Cypress can generate detailed reports of test runs using the specified reporter (`cypress-mochawesome-reporter` in this case). Reports will be generated automatically after each test run.

## Configuration Options

- **Reporter**: Specify the reporter for generating test reports.
- **Chrome Web Security**: Enable or disable Chrome web security.
- **Video**: Enable or disable video recording of test runs.
- **Viewport Size**: Set the default viewport width and height.
- **Timeouts**: Configure default command timeout and page load timeout.
- **Test Files**: Specify the location of test files and sequenece of the test files.
