Feature: Open any Flight booking portal
  1. Search for a given flight source/destination and get the fares
  2. Select the best itinerary based on the fastest and cheapest travel.
  3. If there are multiple itinerary options, select the evening flight but that should be the
  fastest.

  Scenario: verify Flights section is available or not in home page
    Given Open the chromeBrowser and launch the application
    When check Flights option is displaying or not
    Then Flights option should be displaying 
    
     Scenario: verify flishts search functionality 
    Given enter source and destination
    |del|Banga|
    
    When click on search button 
    Then user should be navigate to flights list page 
    And select fastest flight
    
    