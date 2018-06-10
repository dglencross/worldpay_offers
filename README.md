# worldpay_offers
Java API to maintain a database of 'Offers'

This project was developed using Eclipse Oxygen as it has required plugins for web development. The project uses Gradle for dependency management.

Project uses Jersey/JAX-RS for easy Java rest services. I developed it using Tomcat.

Running the project:
(Assuming all relevant plugins installed)
  - Load project into Eclipse
  - Right-click on the project -> Gradle -> Refresh Gradle Project
  - Gradle Tasks -> eclipseWtp (generates config files)
  - Right-click on the project -> Run As -> Run On Server
  - Deploy to any server, Tomcat was used in the development
  - Use a REST client to connect to the project and run test queries

Assumptions made:
 - Pricing information etc is included in the description
 - No client is included, instructions for running are below
 - All 'Offers' are stored in memory, so will not be retained when closing the server
 - Offers can't be cancelled after they have expired
 - Users can only specify expiry time in hours, minutes and seconds
 - Offers can't be deleted, they can only be 'Cancelled'. Requirement did not specify deletion so I didn't implement it.

Base URL should be e.g.
http://localhost:8080

Example queries:
POST
http://localhost:8080/offers/rest/v1/offers?description="50% off all products for one hour"&expiryInHours=1&currency=GBP
IDs are added in chronological order so first one is 0

GET
http://localhost:8080/offers/rest/v1/offers/0
Displays info of order

POST
http://localhost:8080/offers/rest/v1/offers?description="Offer expires in one second!"&expiryInSeconds=1&currency=GBP

http://localhost:8080/offers/rest/offers/1
Offer will show as status EXPIRED

PUT
http://localhost:8080/offers/rest/v1/offers/cancel?id=0
Message should say id 0 is cancelled
GET
http://localhost:8080/offers/rest/v1/offers/0
Offer will show as status CANCELLED

GET
http://localhost:8080/offers/rest/v1/offers/allOffers
All offers will be listed
