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
**Submitted:** 2026-08-21T17:40:07.906Z  

```markdown
1. Short Code Generation
Base62 Code Length & Math:

Base62 uses 62 characters (A-Z, a-z, 0-9).

The system expects 2,000,000 new links per day.

In 10 years, the system will need to support: 2,000,000 × 365 days × 10 years = 7.3 billion links.

A 6-character Base62 string allows for 62^6 combinations, which equals 56.8 billion possible unique codes.

Justification: A length of 6 is more than sufficient to cover 7.3 billion links over a 10-year lifespan without running out of space, while keeping the URL as short as possible.

Generation Method:

Counter + Base62: A distributed counter (e.g., using an auto-incrementing database ID or a system like ZooKeeper) generates a unique base-10 number for every new link. This number is then mathematically converted into Base62.

Why pick it: It guarantees 100% uniqueness without ever needing to check the database for collisions (unlike random generation), making it highly performant and scalable under heavy load.


2. Fixing the REST Bugs
Bug A (Missing URL payload):

(i) What is wrong: Returning HTTP 200 OK for a failed or invalid request violates REST. 200 means success.

(ii) Exact fix: The method remains POST. The server should validate the payload and return an HTTP 400 Bad Request status code to indicate the client sent invalid/missing data.

Bug B (60,000 links in one response):

(i) What is wrong: Returning massive datasets in a single response overwhelms both the server and the client. It lacks proper resource state representation via pagination.

(ii) Exact fix: The method remains GET. Implement pagination. The endpoint should accept query parameters (e.g., GET /allLinks?limit=50&offset=0). Return HTTP 200 OK with a small, manageable chunk of links.

Bug C (Duplicate links on retry):

(i) What is wrong: The POST request is not acting idempotently. Retrying the exact same creation action is resulting in duplicate resources and wasted space.

(ii) Exact fix: Implement an Idempotency-Key. The client sends a unique Idempotency-Key header with the POST request. The server maps this key to the target URL. If a timeout happens and the client retries with the same key, the server identifies the key, blocks the duplicate creation, and returns the existing short link with an HTTP 200 OK (instead of HTTP 201 Created).

Bug D (Deleting via GET):

(i) What is wrong: GET requests must be "safe" and idempotent, meaning they should only retrieve data and never alter or delete state on the server.

(ii) Exact fix: Change the HTTP method to DELETE. The endpoint should use a path variable, e.g., DELETE /links/{id}. Return HTTP 204 No Content (or HTTP 200 OK) upon successful deletion.



3. Database Selection
Choice: NoSQL (e.g., MongoDB, DynamoDB, or a Key-Value store).

(Note: SQL is also acceptable if justified properly, but NoSQL is a perfect fit here).

Justification: A URL shortener's primary read operation is a simple, exact-match key-value lookup (short code -> long URL). NoSQL document or key-value stores are highly optimized for this specific read pattern and scale horizontally very easily. Furthermore, many NoSQL databases (like MongoDB or Redis) have native TTL (Time-To-Live) index features built directly into the engine, perfectly satisfying the expiry requirement without writing custom cleanup scripts.

Database Fields:

short_code (Primary Key / String)

long_url (String)

click_count (Integer, default 0)

expires_at (Timestamp / TTL Index)

created_at (Timestamp)



4. Cache-Aside Setup
What gets cached: The direct mapping of the short_code (as the cache key) to the long_url (as the cache value).

Cache Hit: When a user requests GET /r/{code}, the application first checks the cache. If the {code} is found (a hit), the application immediately returns an HTTP 301 or 302 redirect to the long_url without touching the main database.

Cache Miss: If the {code} is not in the cache, the application queries the main database. Once retrieved, it writes that {code} and long_url pair into the cache for future requests, and then returns the redirect to the user.

Keeping cache correct (Invalidation): When a link is deleted or edited in the main database, the application must immediately issue a "delete/evict" command to the cache for that specific short_code key. The next time a user requests that link, it will result in a cache miss, forcing the application to fetch the fresh, updated state from the main database.
```

---

[View on CodeChef](https://www.codechef.com/problems/SWXRP01)