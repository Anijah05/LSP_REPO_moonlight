# Design Evaluation of PriceCalculator

The current `PriceCalculator` design is hard to maintain because all discount rules are placed inside one method using multiple `if` statements. As the system grows and more customer types or discount rules are added, the method will become longer, harder to read, and more difficult to test.

The design also violates the open/closed principle because adding a new pricing rule requires modifying the existing class. A better design is to use the Strategy Pattern so that each discount behavior is placed in its own class. This makes the system easier to extend, test, and maintain.