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