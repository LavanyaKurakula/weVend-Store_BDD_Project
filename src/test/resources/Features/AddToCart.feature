Feature: Validate AddToCart functionality in HomePage
Scenario: Validate AddToCart button 
Given User is on HomePage  
When User navigate to the AddToCart button under any product and click on it ["Mountain Dew", "Water", "Diet Coke"]
Then Product should be added to the cart
