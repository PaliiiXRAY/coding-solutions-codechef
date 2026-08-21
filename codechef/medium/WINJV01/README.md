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
**Submitted:** 2026-08-21T17:40:00.120Z  

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

[View on CodeChef](https://www.codechef.com/problems/WINJV01)