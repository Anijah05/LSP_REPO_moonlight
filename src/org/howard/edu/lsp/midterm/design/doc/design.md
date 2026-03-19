Class:
Order
Responsibilities:
- store order data
- provide order details
- hold customer, item, and price information
Collaborators:
- DiscountPolicy
- TaxCalculator

Class:
TaxCalculator
Responsibilities:
- calculate tax for an order
- return tax amount based on pricing rules
Collaborators:
- Order

Class:
DiscountPolicy
Responsibilities:
- determine discount eligibility
- apply discount rules to an order total
Collaborators:
- Order

Class:
ReceiptPrinter
Responsibilities:
- print receipt details
- display customer, item, tax, discount, and final total
Collaborators:
- Order
- TaxCalculator
- DiscountPolicy

Class:
OrderRepository
Responsibilities:
- save processed orders
- write order data to persistent storage
Collaborators:
- Order

Class:
EmailService
Responsibilities:
- send confirmation messages
- notify customers after order processing
Collaborators:
- Order

Class:
ActivityLogger
Responsibilities:
- record processing activity
- log processing timestamps and events
Collaborators:
- Order

Class:
OrderProcessor
Responsibilities:
- coordinate order processing steps
- delegate tax, discount, printing, saving, emailing, and logging
Collaborators:
- Order
- TaxCalculator
- DiscountPolicy
- ReceiptPrinter
- OrderRepository
- EmailService
- ActivityLogger