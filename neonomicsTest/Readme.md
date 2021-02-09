# Neonomics Assignment

## Folder structure:

  1. Main driver script is in class getAccountsTest in src/test/java
  2. Implementation classes are placed in src/main/java
  3. Test execution report can be viewed in target/surefire-reports/emailable-report.html


## Execution Flow:
1. Declared and initialized class objects and variables in driver script
2. Then WebUI automation part has been called in executeWeb method                      
3. Then API automation part has been called in executeAPI method
4. And at last Negative scenario has been called in executeNegativeTests method


### Prerequisites to run the test
1. User must be signed up in Neonomics developer portal.
2. username and password must be updated against defined properties in application.properties file.


### Steps in executeWeb method:

1. Login to Neonomics developer portal using  username and password using pageObject model
2. Creating a new application in developer portal
3. Generating client id and secret for the user

### Steps in executeAPI method:

  1. Generate Access token for the client id and secrets which was generated in developer portal using rest assured concepts
  2. Get the Bank Id of justo bank out of the all banks received in API response
3. Generate session Id with user and justo Bank
4. Get consent of the User using consent API
5. Fetch account lists from the bank for the session


### Steps in executeNegativeTests method:

1. One negative test for incorrect client Id has been verified 

Similarly other negative validations can be appended in this class