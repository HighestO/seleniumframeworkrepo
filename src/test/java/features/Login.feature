Feature: Application Login

Scenario: Login with valid credentials
Given Open my Browser
And Navigate to Login page
When User enters username as "kofirich35@gmail.com" and password as "12345" into the fields
And User clicks on Login button
Then Verify user is able to successfully login 