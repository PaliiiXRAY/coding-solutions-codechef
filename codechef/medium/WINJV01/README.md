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
**Submitted:** 2026-08-21T17:46:33.418Z  

```markdown
1. Incident 1: Cart Data Loss
The Cause: The issue occurred because the application was using stateful servers, meaning that user data (the shopping cart) was stored in the local memory of a specific individual server rather than a shared database. When a user's request was routed to a different server, that new server had no knowledge of the local memory state on the previous server, resulting in an empty cart.

The Fix: Implement a Shared/Central Session Store (such as Redis or Memcached).

Why it removes sticky sessions: By moving the session data out of the individual servers and into a fast, centralized database, the application servers become stateless. Any server in the cluster can handle any request because they all retrieve the user's cart state from the exact same central store, entirely eliminating the need to force a user to "stick" to one specific server.


2. Incident 2: Server Crash under Load
The Cause: The load balancer utilized a plain round-robin algorithm, which blindly distributes traffic evenly in a cycle to all servers regardless of their current health, processing capacity, or active load. Consequently, it continued sending requests to a server that was already struggling, ultimately causing it to crash.

Better Load-Balancing Method: Least Connections or Least Response Time. These algorithms dynamically evaluate server load by routing new requests to the server currently handling the fewest active connections or responding the fastest, naturally bypassing overwhelmed servers.

Proper Application-Level Health Check: A simple port check only confirms the server is powered on. A true application-level health check (e.g., an HTTP GET request to a specific /health endpoint) should require the server to verify its critical dependencies before returning an HTTP 200 OK. This includes verifying it can successfully query the database, has sufficient memory/CPU headroom, and can execute a basic test transaction without timing out.



3. Incident 3: Bot Abuse
The Cause: The architecture lacked an API Gateway, meaning backend endpoints were directly exposed without a protective layer to enforce security policies, allowing a scalper's bot to overwhelm the system.

Load Balancer vs. API Gateway: A Load Balancer primarily distributes network traffic across multiple backend servers to ensure availability. An API Gateway acts as a reverse proxy that sits in front of APIs and handles cross-cutting concerns that a basic load balancer does not, such as authentication, request validation, and crucially, rate limiting.

Rate-Limiting Design:

Algorithm: Token Bucket. This allows for small, legitimate bursts of traffic (like a user quickly navigating) while maintaining a strict overall rate limit over time.

Limiting Key: Per-User ID (extracted from the authentication token). This is vastly superior to a Per-IP limit because multiple legitimate customers might share a single IP address (e.g., corporate networks, dorms), and malicious bots frequently use proxy networks to rotate IPs while attempting to use a single compromised or scalper account.

The Limit: Very restrictive during a flash sale, such as 1 request per second per user ID.

Blocked Response: The API Gateway should return an HTTP status code 429 Too Many Requests, with a clear JSON payload message like {"error": "Rate limit exceeded. Please try again later."}.



4. Incident 4: Delayed Auto-Scaling
The Cause: The failure was caused by relying on reactive auto-scaling for an extreme, instantaneous traffic spike.

Why Reactive Fails Here: Reactive auto-scaling relies on lagging indicators (like CPU usage crossing an 80% threshold). The total operational time required to provision a new server, boot the OS, start the application, and pass health checks (6 minutes) is significantly longer than the entire duration of a flash sale spike (90-120 seconds). By the time the server is ready, the event is over.

The Real Fix: Predictive/Scheduled Scaling (Pre-warming). Because FlashMart knows the exact date and time of the scheduled flash sale, they must manually configure the infrastructure to scale out to maximum expected capacity before the sale begins (e.g., 30 to 60 minutes prior).

Scaling Type: Horizontal scaling (adding more servers) fits better here. It provides better fault tolerance, allows for massive parallel connection handling, and avoids the downtime typically required to shut down and resize instances in vertical scaling (adding more CPU/RAM to a single server).
```

---

[View on CodeChef](https://www.codechef.com/problems/WINJV01)