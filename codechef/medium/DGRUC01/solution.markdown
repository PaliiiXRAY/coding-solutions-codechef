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