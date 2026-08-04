# CHEFSERVER

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Chef wants to send a message to Chefina through a cloud provider’s network of servers. The network is modeled as an  **undirected graph**, where servers act as nodes and connections act as edges.

Chef is located at  **server 1**, and Chefina is located at  **server N**.

Your task is to determine:

- Whether a route exists from server 1 to server N
- If it exists, return the minimum number of servers used along such a route (including both start and destination)
## Function Declaration
### Function Name

$getMinimumServers$ - This function finds the minimum number of servers required to send a message from  **server 1**  to  **server N**.

If server `N` cannot be reached, the function returns `-1`.

### Parameters
- $n$ : The total number of servers in the network.
- $serverConnections$ : Each pair represents a bidirectional connection between server a and server b.
## Return Value
- Returns an integer: The minimum number of servers on the shortest route from server 1 to server n Returns -1 if no route exists
## Constraints
- $1 \leq n \leq 200000$
- $1 \leq m \leq 200000$
- $1 \leq a, b \leq n$
- $a \neq b$
- No self-loops or duplicate connections
- The graph is undirected
### Input Format
- The first line contains two integers $n$ and $m$, representing the number of servers and connections.
- The next $m$ lines each contain two integers $a$ and $b$, representing a bidirectional connection between servers $a$ and $b$.
### Output Format
- Print a single integer: The minimum number of servers needed to connect server $1$ to server $n$ Print $-1$ if no such route exists
### Sample 1:
Input
Output

```
5 6
1 2
2 3
1 3
3 5
2 4
4 5
```

```
3
```

### Explanation:

One of the shortest paths discovered by BFS is:

```
1 -> 3 -> 5

```

- Number of connections = 2
- Number of servers = 2 + 1 = 3

Hence, the output is `3`.

## Solution

**Language:** c_cpp  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-04T10:05:06.404Z  

```c_cpp
class Solution {
public:
    int getMinimumServers(int n, vector<pair<int, int>> serverConnections) {
        // write your code here 
        vector <list<int>> adj(n+1);
        for (auto & x: serverConnections){
            int u = x.first; int v = x.second;
            adj[u].push_back(v);
            adj[v].push_back(u);
        }
        vector <bool> visited(n+1, 0);
        int ans = 0;
        
        queue<int> q;
        q.push(1);
        visited[1] = 1;
        while(!q.empty()){
            ans++;
            int st = q.size();
            for(int i = 0; i<st; i++){
                int now = q.front();
                if (now == n) return ans;
                q.pop();
                for(auto &x:adj[now]){
                    if(visited[x] == 1) continue;
                    visited[x] = 1;
                    q.push(x);
                }
            }
        }
        return -1;
    }
};
```

---

[View on CodeChef](https://www.codechef.com/problems/CHEFSERVER)