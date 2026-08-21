# WINJV01

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

### Case Study: SnapLink

SnapLink is a URL shortener made for a marketing agency's campaigns. A junior developer built version 1. It works in the demo, but it has real problems:

- Bug A: POST /createLink ALWAYS sends back HTTP 200, even when the request is missing the target URL — so the frontend has no way to know it failed.
- Bug B: GET /allLinks sends back all 60,000 existing short links in one single response, with no way to get them in smaller pages.
- Bug C: When a marketing user's app has a bad connection and sends POST /createLink again with the SAME target URL after a timeout, SnapLink quietly creates a SECOND short link for the same URL, wasting key space.
- Bug D: To delete a link, the app sends a GET request to /deleteLink?id=123.
- Once it grows, SnapLink expects 2,000,000 new links every day, and 200,000,000 redirect (click) events every day.

 **Your Task:** 

- [25 marks] Design how short codes are generated. Given 2,000,000 new links per day, choose a good Base62 code length and justify it by comparing the total possible codes with the number of links needed (show the maths). Briefly explain ONE way to generate the code (counter + Base62, OR random + check for duplicates) and say why you picked it.
- [25 marks] Fix Bugs A, B, C and D above. For each bug, say (i) what is wrong, in REST terms, and (ii) the exact fix — including the correct HTTP method, correct status code(s), and for Bug C, how you would stop link creation from duplicating on retry (idempotency).
- [25 marks] SnapLink must support 60,000+ links, and needs: exact-match lookup by short code, an expiry time (TTL) on some links, and a click-count that updates every time someone uses the link. Choose SQL or NoSQL for the main link storage and explain your choice based on these needs. Then list the database fields for a link record.
- [25 marks] Design a cache-aside setup for the redirect lookup (GET /r/{code}) that can handle 200,000,000 reads a day. Explain: what gets cached, what happens on a cache hit vs a cache miss, and how the cache stays correct when a link is deleted or edited.

 **Note:**  Marking is done part by part — you get credit for each correct point even if the full design isn't complete. Full forms (REST, TTL, CRUD) written correctly also get marks. Spelling/grammar are not checked. There is no single "correct" SQL/NoSQL choice — any choice explained clearly against the stated needs is accepted.

## Solution

**Language:** markdown  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-21T17:36:45.180Z  

```markdown
1. Capacity Planning: QPS and Ratios
Daily Active Users (DAU):

Formula: Total Registered Users × DAU Percentage

Calculation: 5,000,000 × 0.15 = 750,000 DAU

Average Write QPS (Queries Per Second):

Formula: (DAU × Notes Uploaded per User) / Seconds in a Day

Calculation: (750,000 × 2) / 86,400 = 1,500,000 / 86,400 = 17.36 QPS

Peak Write QPS:

Formula: Average Write QPS × Peak Multiplier

Calculation: 17.36 × 4 = 69.44 QPS

Average Read QPS:

Formula: (DAU × Notes Viewed per User) / Seconds in a Day

Calculation: (750,000 × 40) / 86,400 = 30,000,000 / 86,400 = 347.22 QPS

Peak Read QPS:

Formula: Average Read QPS × Peak Multiplier

Calculation: 347.22 × 4 = 1388.88 QPS

Read:Write Ratio:

Formula: Total Daily Reads / Total Daily Writes (or Notes Viewed per User / Notes Uploaded per User)

Calculation: 40 / 2 = 20:1



2. Storage and Bandwidth
Storage needed per day (before copies):

Formula: Total Daily Writes × Average Note Size

Calculation: 1,500,000 writes × 300 KB = 450,000,000 KB.

Converted to GB: 450,000,000 / 1,000,000 = 450 GB/day

Storage needed per day (after 3 copies):

Formula: Daily Storage (before copies) × Replication Factor

Calculation: 450 GB × 3 = 1,350 GB/day

Peak Read Bandwidth:

Formula: Peak Read QPS × Average Note Size

Calculation: 1388.88 QPS × 300 KB = 416,664 KB/s

Converted to MB/s: 416,664 / 1,000 = 416.66 MB/s


3. Long-Term Storage Estimation (3 Years)
Assuming 365 days in a year, the storage compounds annually based on the 30% user base growth.

Year 1 Total Storage: 1,350 GB/day × 365 days = 492,750 GB

Year 2 Total Storage (30% growth): 492,750 GB × 1.30 = 640,575 GB

Year 3 Total Storage (30% growth): 640,575 GB × 1.30 = 832,747.5 GB

Total Estimated Storage Over 3 Years: 492,750 + 640,575 + 832,747.5 = 1,966,072.5 GB (approx. 1.96 PB)


4. Network Steps for Opening the URL
When a student opens [https://studystack.com/notes/123](https://studystack.com/notes/123), the following major network steps occur in order:

DNS Lookup: The browser checks its cache (and the OS cache) to see if it knows the IP address for studystack.com. If not, it queries a DNS resolver to translate the domain name into an IP address.

TCP Handshake: The browser initiates a connection to the server's IP address on port 443 (for HTTPS). This is done using a 3-way handshake (SYN, SYN-ACK, ACK) to establish a reliable connection.

TLS Handshake: Because the connection uses HTTPS, the browser and server perform a TLS handshake. They negotiate encryption algorithms, the server sends its SSL/TLS certificate to prove its identity, and they generate session keys to encrypt the data.

HTTP Request: The browser sends an encrypted HTTP GET request for the specific resource path (/notes/123) to the server over the established secure connection.

HTTP Response: The server processes the request, retrieves the requested note from its backend/database, and sends back an HTTP response containing the status code (e.g., 200 OK) and the requested data (HTML, JSON, etc.).

Browser Rendering: The browser receives the response data, parses the HTML, CSS, and JavaScript to construct the DOM (Document Object Model) and CSSOM trees, and finally renders the visual page on the screen for the user.
```

---

[View on CodeChef](https://www.codechef.com/problems/WINJV01)