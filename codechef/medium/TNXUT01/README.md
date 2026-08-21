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
**Submitted:** 2026-08-21T17:50:32.978Z  

```markdown
1. Requirement Classification
"Pet owners should be able to book an appointment with a vet"

Classification: FR (Functional Requirement)

Reason: It defines a specific action and behavior the system must perform.

"The app should be fast"

Classification: NFR (Non-Functional Requirement)

Reason: It describes a performance quality attribute of the system, not a specific feature.

"Vets should be able to see their day's schedule"

Classification: FR (Functional Requirement)

Reason: It describes a specific user capability and data retrieval function.

"The app should look modern and premium"

Classification: Not a Requirement

Reason: This is a subjective opinion that cannot be objectively quantified, built, or tested as written.

"Owners should get a reminder notification 1 hour before their appointment"

Classification: FR (Functional Requirement)

Reason: It defines a specific automated action and business logic the system must execute.

"The system should be reliable"

Classification: NFR (Non-Functional Requirement)

Reason: It specifies a system-wide quality attribute (availability/robustness) rather than a direct function.

"Appointments can only be booked up to 2 weeks in advance"

Classification: FR (Functional Requirement)

Reason: It acts as a specific business rule or constraint applied to the booking functionality.

"The app should support both Hindi and English"

Classification: NFR (Non-Functional Requirement)

Reason: It defines a system-wide architectural standard (localization) rather than a single user action.


2. Rewriting NFRs to be Measurable
Rewriting Statement 2 ("fast"):

"95% of screen load times must complete in under [500] [milliseconds] [during standard operational hours]."

Rewriting Statement 6 ("reliable"):

"The booking service must successfully process [99.9] [% of all requests] [without returning a server error under normal load]."
(Alternatively for uptime: "The system must maintain [99.9] [% uptime] [over any rolling 30-day period].")


3. Uptime and Downtime Calculation
Maximum downtime per month (30 days):

Total minutes in a 30-day month = 30 days × 24 hours × 60 minutes = 43,200 minutes.

Downtime allowance = 100% - 99.9% = 0.1%.

0.1% of 43,200 minutes = 43.2 minutes per month.

Maximum downtime per year (365 days):

Total minutes in a year = 365 days × 24 hours × 60 minutes = 525,600 minutes.

0.1% of 525,600 minutes = 525.6 minutes per year (approx. 8.76 hours).

What this means in practice:

The clinic's IT team has a strict limit of roughly 43 minutes total each month to handle all server crashes, bug fixes, and scheduled maintenance; if the system is down longer than that, they have failed their reliability agreement.



4. Design-Changing Questions for the Founder
Concurrency Handling: "How should the system behave if two pet owners try to select and book the exact same vet for the exact same time slot simultaneously?"

System Integration: "Will this app need to automatically sync appointments and patient data with your existing clinic management/billing software, or will it function as a completely standalone database?"

Future Scaling: "Do you plan to open additional clinic locations in the future, meaning the database architecture needs to be designed from the start to support multiple branches and cross-branch scheduling?"

No-Show Policy Logic: "What is the specific policy for cancellations and no-shows, and does the system need to automatically block or flag users who repeatedly miss appointments?"
```

---

[View on CodeChef](https://www.codechef.com/problems/TNXUT01)