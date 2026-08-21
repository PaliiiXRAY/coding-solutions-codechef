Q1) a) HLD-  Splitting the app into Booking and Notification services is a high-level arcjitectural decision

b) LLD - Choosing a  database  column to index is an implementation-level database decision

c) HLD - Choosing direct communication or a queue defines how servuces communicate. 

d) LLD- RESR endpoint naming is a detailed API implementation decision.

r) HLD- Placing  a load balancer affects the overall system/deployment architecture.

f) LLD- Retry logic inside the SMS function is an internnal code - level detail.


Q)2 

why load balancing fails:

-TrekGear is still one monolithic application.

-Booking, Payment, and SMS notification code are tightly coupled

-If SMS becomes slow, it blocks the same application resources

-Load balancer only distributes requests; it doesn't remove the bottleneck

- All users, even catalog users, can be affected by the slow SMS servuce.

-Scaling the whole application is also wasteful and expensive

Suggested Microservice:-

-Gear Catalog Service - manages gear details and searching

- Booking service - handles gear availability, reservations and returns

-Payment service: handles payment processing and status

- Notification service - handles booking confirmations and notifications
- User/Account service - handles login , profile and authentication

why this split?
Services are separated acc to bussiness function , so no one failing / slow function does not bring down unrelated fucntions

Q)3 
Currently:
Mobile App -> API GATEWAY -> Microservices
What changes?
- Mobile app should cell one API Gateway URL.
- Gateway routes requests to the one correct microservice
- Gateway can handle authentication , routing, rate limiting and security
- Mobile app does not need to know individual service addresses

Why not each microservice daily??
