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
**Submitted:** 2026-08-21T17:29:38.911Z  

```markdown
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

Why not each microservice daily?
- Create tight coupling between mobile app and backend services
- Servuce URLs/structure may change
- More security exposrue
- client must manage multiple endpoints
- makes versoing and maintenance harder 
- 

4) Parts to show in draw.io HLD diagram

we can show these boxes

- Mobile APP
- API GATEWAY
- Load Balancer
- User/Account service
- Gear Catalog Service
- Booking/Rental Service 
- Payment Service
- Notification Service
- Message Queue
- Database
- SMS Provider
- Monitoring/Logging

Easy diagram flow to remember:
 Mobile APP -> API GATEWAY -> Servies ->Databases
 
 &
 
 Booking service -> Message Queue ->Notification Service ->SMS Provider

```

---

[View on CodeChef](https://www.codechef.com/problems/DGRUC01)