@products
Feature: E-commerce Project Web Site

  Background: Navigation to the URL
    Given User navigated to the home application url

  Scenario: User is able to Open the browser, navigate to the URL
    When User Search for url
    Then Search Result page is displayed

  Scenario: User is click on Logo and check the Logo displayed
    When User click on Logo
    Then Fetch The Application Logo

  Scenario Outline: User is able to search multiple products
    When User Search for product
      | product_name |
      | Women        |
      | Dresses      |
      | T-shirts     |
    Then product categories show 3 quantity

  Scenario: 
    When User Search for text
    Then Search result page is displayed

  Scenario: 
    When User click on the link
    Then Search result page
