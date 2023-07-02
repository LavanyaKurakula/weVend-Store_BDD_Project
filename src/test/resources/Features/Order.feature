@demo
Feature: Place an Order

Scenario:  Place an order using BuyNow button
Given User is on HomePage  
When User navigates to the BuyNow button under any product and click on it "Water"
And User should be redirected to Checkout page
And User clicks on proceed button 
And User selects card payment method and navigates to Payment Gateway page
And User enters card details
And User clicks on Pay button
Then Order should be placed and user will be navigated to Success page