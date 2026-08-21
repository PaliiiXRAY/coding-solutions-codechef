# SWXRP01

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

### Case Study: FlashMart Sale Day

FlashMart ran a 90-second flash sale. After it ended, the team found 4 separate problems. You are the on-call system designer, and you need to find the cause of each one and fix it.

- Incident 1: Some users added items to their cart, then got sent to a different server by the load balancer on their next click — and found their cart EMPTY. The investigation shows each server keeps cart data in its own memory, tied to that one server. A user's cart is only correct as long as every request goes to the same server (sticky sessions were on, but this broke when one server restarted in the middle of the sale).
- Incident 2: The load balancer used plain round-robin (sending requests to each server one after another, equally). One server was already struggling (high CPU, slow to respond), but it kept getting an equal share of new traffic anyway — until it crashed.
- Incident 3: There was no API Gateway — the mobile app, and any script, could call POST /flash-sale/buy directly on the backend. A scalper's bot sent about 500 requests every second to this one endpoint from a single account, and bought 40% of the limited stock in the first 10 seconds — before any real customer had a chance.
- Incident 4: Auto-scaling was set up correctly on paper (add a new server when CPU crosses 80%), but it took 6 minutes in total (trigger + start the new server) to bring a new server online. The real traffic spike lasted only about 90–120 seconds, and was already over by the time the new server was ready.

 **Your Task:** 

- [25 marks] Explain the cause of Incident 1 using the idea of stateless vs stateful servers. Suggest a fix — either a shared/central session store, or a token-based approach (cookies vs JWT). Name your choice and explain why it removes the need for sticky sessions.
- [25 marks] Explain the cause of Incident 2. Suggest a better load-balancing method than round-robin for this case, and design a proper health check for the load balancer (it must be more than a simple check that the port is open — explain what an application-level health check should actually check).
- [25 marks] Explain the cause of Incident 3. Explain the difference between a Load Balancer and an API Gateway in this situation, and design a rate-limiting rule for POST /flash-sale/buy — say what algorithm you use (for example, token bucket), what you use as the limiting key (why per-user is better than per-IP here), what the limit should be, and what response (code + message) a blocked request should get.
- [25 marks] Explain the cause of Incident 4. Explain why REACTIVE auto-scaling (adding servers only after CPU crosses a limit) can never react fast enough to a spike shorter than its own reaction time. Suggest a real fix for a sale that FlashMart already knows the date and time of, and briefly say whether horizontal or vertical scaling fits better here.

 **Note:**  Marking is done part by part — you get credit for each correct point, not for overall polish. Full forms (L4/L7, CPU, TTL, TCP) written correctly also get marks. Spelling/grammar are not checked. There is no single "correct" fix for each incident — any technically sound fix that addresses the real cause is accepted.

## Solution

**Language:** markdown  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-21T17:45:02.099Z  

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

[View on CodeChef](https://www.codechef.com/problems/SWXRP01)