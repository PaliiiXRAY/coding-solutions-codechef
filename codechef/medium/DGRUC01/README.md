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
**Submitted:** 2026-08-21T17:50:02.559Z  

```markdown
Q1. Classify each statement

1. FR (Functional Requirement) – The system must allow pet owners to book appointments with a veterinarian.

2. NFR (Non-Functional Requirement) – “Fast” describes the performance of the system rather than a specific function.

3. FR (Functional Requirement) – The system must allow vets to view their daily appointment schedule.

4. Not a Requirement – “Modern and premium” is subjective and cannot be clearly measured or tested as written.

5. FR (Functional Requirement) – The system must send owners a reminder notification one hour before their appointment.

6. NFR (Non-Functional Requirement) – “Reliable” describes the availability/reliability quality of the system.

7. FR (Functional Requirement) – The system must restrict appointment booking to a maximum of two weeks in advance.

8. FR (Functional Requirement) – The system must support both Hindi and English languages.


Q2. Make the NFRs measurable

Fast:
95% of appointment-search requests must be completed within 300 ms under normal system load.

Reliable:
The booking system must maintain 99.9% availability per month, excluding planned maintenance.


Q3. 99.9% Uptime

Per month:
30 × 24 × 60 = 43,200 minutes
0.1% downtime = 43.2 minutes/month

Per year:
365 × 24 × 60 = 525,600 minutes
0.1% downtime = 525.6 minutes = 8.76 hours/year

In practice: The booking system can be unavailable for a maximum of about 43.2 minutes in a 30-day month while still meeting the 99.9% uptime requirement.


Q4. Four questions for the founder
Can two users try to book the same vet and time slot simultaneously?
→ This affects concurrency and booking-locking design.
What are the cancellation and rescheduling rules?
→ This affects the booking workflow and database design.
Will PawCare have one clinic branch or multiple branches?
→ This affects how appointments, vets and locations are managed.
What are the busiest days and hours for appointments?
→ This affects performance, scaling and resource planning.
```

---

[View on CodeChef](https://www.codechef.com/problems/DGRUC01)