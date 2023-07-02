Feature: Validate Payment method 
@demo1
Scenario: Validate Payment method on review and payment page 
Given User is on HomePage
When User navigate to the BuyNow button under any product and click on it "Water"
And User should be navigated to Checkout page
Then Payment method should be present on the page as "weVend Gateway"