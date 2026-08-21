# TNXUT01

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

### Case Study: TrekGear Rentals

TrekGear Rentals is a trekking-gear rental startup in Manali. Everything — login, gear catalog, booking, payments, and SMS notifications — is built as ONE single application, using ONE database, running on ONE server.

- Every trekking season (May–June), traffic goes up 8 times as college groups book gear together.
- Last season, the SMS service (used to send booking confirmations) became slow. Because the notification code runs together with the booking and payment code, the WHOLE app stopped working — even users who just wanted to look at the gear catalog saw a blank page.
- The founder asked a junior developer to "just add more servers behind a load balancer" to fix this, without changing anything else in the code.
- The team has never drawn a proper architecture diagram before — decisions are made without any plan.

 **Your Task:** 

- [20 marks] Below are 6 decisions the TrekGear team needs to make. For EACH one, say if it is HLD or LLD, and give a one line reason: Deciding to split the app into separate services for Booking and Notifications Deciding which database column to index so gear search becomes faster Deciding if the Notification service should call the SMS service directly, or use a queue instead Deciding the naming style for REST API endpoints Deciding whether to place a load balancer in front of the Booking service Deciding the retry logic (loop) inside the SMS-sending function
- [30 marks] Using TrekGear's outage as the example, explain why "just adding more servers" behind a load balancer does NOT fix the real problem. Then suggest how to split TrekGear into microservices (name the services), and explain WHY you chose these groups — group them by what job they do for the business, not by technical layer (do not split into "frontend / backend / database").
- [25 marks] Right now, TrekGear's mobile app talks directly to the single application at one URL. Explain what needs to change in this client-server setup once the backend is split into microservices, and why the app should NOT call each microservice directly.
- [25 marks] List the parts TrekGear's architect must show in a draw.io HLD diagram of the new system (name each part and say in one line what it does). You do not need to draw the diagram — just describe the parts in text.

 **Note:**  Marking is done part by part — you get credit for each correct point, even if the whole answer is not complete. Correctly written full forms (like "High-Level Design") also get marks. Spelling and grammar are not checked. There is no single "correct" way to split into microservices — any split that is grouped by business function, with clear boundaries, is accepted.

## Solution

**Language:** markdown  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-21T17:54:37.495Z  

```markdown
1. HLD vs. LLD Classification
a) HLD - Splitting the app into Booking and Notification services dictates the overall system architecture and macro-level component structure.

b) LLD - Choosing a database column to index is an implementation-specific detail of the database schema.

c) HLD - Deciding between direct communication or a message queue defines the architectural communication pattern between major systems.

d) LLD - REST API endpoint naming is a detailed coding standard and implementation-level detail.

e) HLD - Placing a load balancer dictates the system's infrastructure, scaling strategy, and deployment architecture.

f) LLD - Retry logic is an internal, code-level algorithmic detail within a specific function.


2. Monolith Failure and Microservices Split
Why "just adding more servers" fails:

Because TrekGear is a tightly coupled monolith, a slow process (like the SMS service) consumes shared application resources (such as thread pools or memory) across the entire application. Adding more servers behind a load balancer simply replicates the same monolithic code onto new machines. The slow SMS requests will quickly exhaust the resources on the new servers as well, causing them to crash just like the original server. Load balancing distributes traffic but fails to isolate the underlying bottleneck.

Suggested Microservices (grouped by business function):

-User Service: Handles registration, login, authentication, and user profiles.

-Catalog Service: Manages trekking gear inventory, item details, and search functionality.

-Booking Service: Manages scheduling, gear availability, and reservations.

-Payment Service: Processes financial transactions and tracks payment statuses.

-Notification Service: Handles the generation and dispatch of SMS and email confirmations.

Why this split:
This architecture provides fault isolation. By separating services by business domain, a failure or slowdown in the Notification Service (due to SMS provider delays) will only affect notifications. The Catalog Service remains completely unblocked and independent, allowing users to continue browsing gear without encountering a blank page.

3. Client-Server Architecture Changes
What needs to change:
The architecture must introduce an API Gateway. The mobile app should point to this single API Gateway URL. The Gateway will intercept all incoming client requests, handle cross-cutting concerns (like authentication and rate limiting), and securely route the request to the appropriate backend microservice.

Why the app should NOT call microservices directly:

Tight Coupling: The mobile client would need to hardcode and manage the exact IP addresses/URLs of every individual service, which breaks easily if backend infrastructure changes.

Security Exposure: It exposes the entire internal network structure and individual service endpoints directly to the public internet.

Network Inefficiency (Chattiness): A single user action on the mobile app might require fetching data from three different services, forcing the client to make three separate high-latency round trips over the internet instead of one call to a Gateway that aggregates the data internally.

4. Parts of the HLD Diagram
Mobile App: The client-facing interface where users browse and book gear.

API Gateway: The single secure entry point that receives client requests, handles authentication, and routes traffic.

Load Balancer: Distributes incoming API Gateway traffic evenly across multiple instances of a specific microservice.

Microservices (User, Catalog, Booking, Payment, Notification): The independent backend applications responsible for executing isolated business logic.

Databases: Independent data storage systems dedicated to each specific microservice (ensuring strict data isolation).

Message Queue (e.g., Kafka/RabbitMQ): An asynchronous communication broker placed between the Booking and Notification services to prevent slow SMS operations from blocking the checkout process.

External SMS Provider: The third-party API (like Twilio) utilized by the Notification Service to dispatch actual text messages.
```

---

[View on CodeChef](https://www.codechef.com/problems/TNXUT01)