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
**Submitted:** 2026-08-21T17:13:27.268Z  

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

-
```

---

[View on CodeChef](https://www.codechef.com/problems/TNXUT01)