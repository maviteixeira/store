# Store application

Messing around with OOP and architecture to learn and apply some concepts.

## Tasks
* Create a **Store**
* Update a **Store** information
* Retrieve a **Store** by parameters
* Create an **Order** with items
* Create a **Payment** for an **Order**
* Retrieve an **Order** by parameters
* Refund **Order** or any **Order Item**

## Business Rules
* A **Store** is composed by name and address
* An **Order** is composed by address, confirmation date and status
* An **Order Item** is composed by description, unit price and quantity.
* A **Payment** is composed by status, credit card number and payment date
* An **Order** just should be refunded until ten days after confirmation and the payment is concluded.

## Features to be implemented
- ~~Resource design~~
- ~~Store class design without state~~
- ~~Simple database configuration~~
- Swagger
- Docker (WIP)
- Terraform
- Security
- Postman Collection

Before running the application you need to execute docker-compose up and run the schema.sql in the database.