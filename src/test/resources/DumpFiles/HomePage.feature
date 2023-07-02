@demo
Feature: Validate Payment method and AddToCart functionalities in HomePage
@demo1
Scenario: Validate Payment method on review and payment page 
Given Open the url "http://wevend.shop/"  
When User navigate to the BuyNow button under any product and click on it "Water"
And User should be redirected to "http://wevend.shop/checkout/"
Then Payment method should be present on the page as "weVend Gateway"

@add
Scenario: Validate AddToCart button 
Given Open the url "http://wevend.shop/"  
When User navigate to the AddToCart button under any product and click on it ["Mountain Dew", "Water", "Diet Coke"]
Then Product should be added to the cart