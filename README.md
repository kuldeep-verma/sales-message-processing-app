# sales-message-processing-app
Takes an input file of sales message to process each message.
One test file(sales.txt) is included in the src.

## Algorithm
> Convert the message and create a product details object with type, price etc. values.
> Store each product in hashmap, key as type and value as product details
> Set the total price in product details, will have other details as well like adjustment type, price etc.
> Keep this message in a List in SalesReport for reporting.
> In case of adjustment (Add|Subtract|Multiply), apply adjustment in total price and store it in adjusted price in PriceAdjustments.
> Also set the adjustment report
> Report will be shown after 10th iteration using report list and adjustment report will be shown after 50th using adjustment report list.

## Getting Started
MessageProcessor is main class to run this application.
Source included all required java files and sales message file as well.

## Requirements
Java 8
