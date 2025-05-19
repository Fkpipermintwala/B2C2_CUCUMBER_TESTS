Feature: B2C2 Homepage Functionality

  Scenario: Verify navigation to About Us page
    Given I open the B2C2 homepage
    When I click on the "About Us" link in the navigation menu
    Then the page should contain the text "About Us"

  Scenario: Verify return to home page
    Given I open the About us page
    When  I click on the B2C2 image
    Then I should be redirected to the homepage

