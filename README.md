# FRUIT SHOP API

A Fruit Shop which calculates the checkout amount depending on the number of items. 

## Design notes

In order to make the application more flexible in the future, the list of fruits and values are inside the application.yml file.
This way if the shop decides in future to increase or decrease prices or add more fruit to their stock, they just need to update the config file.
 
## Tech stack

This project has been develop using the following libraries:

- Spring boot and Web
- Lombok
- Java 8

## Run locally

You can start the application locally by using the following command:

mvn spring-boot:run

This will start a new tomcat web server on port 8080, so you should be able to access your app in http://localhost:8080/fruits/checkout.
