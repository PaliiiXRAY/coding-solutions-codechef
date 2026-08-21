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
**Submitted:** 2026-08-21T17:31:23.216Z  

```markdown


Q1. FR / NFR / Not a Requirement
FR – Owners can book vet appointments.
NFR – Describes performance (speed).
FR – Vets can view their daily schedule.
Not a Requirement – “Modern and premium” is subjective and not directly testable.
FR – System sends a reminder 1 hour before appointment.
NFR – Describes system reliability.
FR – Booking is allowed only up to 2 weeks in advance.
FR – App supports Hindi and English.
Q2. Make NFRs measurable
Fast: 95% of appointment requests must finish within 300 ms under normal load.
Reliable: System must maintain 99.9% availability per month.


Q3. 99.9% Uptime
Per month (30 days): 43.2 minutes maximum downtime.
Per year: 8.76 hours maximum downtime.

Meaning: The booking system can be unavailable for only about 43 minutes per month while still meeting the target.

Q4. 4 Important Questions
What happens if two vets/owners try to book the same slot simultaneously?
What are the cancellation and rescheduling rules?
Will there be one clinic branch or multiple branches?
What are the busiest hours/days for appointments?
```

---

[View on CodeChef](https://www.codechef.com/problems/DGRUC01)