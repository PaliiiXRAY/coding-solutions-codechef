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
**Submitted:** 2026-08-21T17:50:21.548Z  

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

[View on CodeChef](https://www.codechef.com/problems/DGRUC01)