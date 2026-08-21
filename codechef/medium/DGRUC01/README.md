# DGRUC01

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

### Case Study: PawCare Vet Clinic

PawCare, a veterinary clinic in Zirakpur, wants a booking app. The founder sent this list, in her own words:

- Pet owners should be able to book an appointment with a vet
- The app should be fast
- Vets should be able to see their day's schedule
- The app should look modern and premium
- Owners should get a reminder notification 1 hour before their appointment
- The system should be reliable
- Appointments can only be booked up to 2 weeks in advance
- The app should support both Hindi and English

 **Your Task:** 

- [25 marks] Classify each of the 8 statements above as FR (Functional Requirement), NFR (Non-Functional Requirement), or Not a Requirement (an opinion that cannot be built or tested as it is). Give a one line reason for each.
- [25 marks] Statements 2 ("fast") and 6 ("reliable") are NFRs, but they are NOT measurable as written. Rewrite BOTH so they become measurable, using the [NUMBER][UNIT][SITUATION] format (example: "95% of appointment-search requests must finish in under 300ms under normal load").
- [25 marks] PawCare wants 99.9% uptime for the booking system. Calculate the MAXIMUM downtime allowed per month (use a 30-day month) and per year. In one line, explain what this means in practice for the clinic's team.
- [25 marks] Before designing this system, write 4 questions you would ask the PawCare founder — questions that would actually change your design (for example: about two vets getting booked at the same time, cancellation/no-show rules, one branch vs many branches, or the busiest hours). Simple questions like "what is your budget" do not count.

 **Note:**  Marking is done part by part — you get credit for each correct point. Correctly written full forms (Functional Requirement, Non-Functional Requirement) also get marks. Spelling/grammar are not checked. There is no single "correct" rewrite of the NFRs or set of questions — any answer that meets what was asked is accepted.

## Solution

**Language:** markdown  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-21T17:56:19.525Z  

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

Easy diagram flow to remember:

Mobile App → API Gateway → Services → Databases

And:

Booking Service → Message Queue → Notification Service → SMS Provider
```

---

[View on CodeChef](https://www.codechef.com/problems/DGRUC01)