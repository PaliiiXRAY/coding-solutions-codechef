# PREACT024

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

### Understanding the && (Logical AND) Operator

The `&&` (logical AND) operator is used to evaluate two conditions (`true` or `false`). It returns `true` only if  **both**  conditions are `true`; otherwise, it returns `false`.

### How it Works:
- true && true → ✅ true
- true && false → ❌ false
- false && true → ❌ false
- false && false → ❌ false
### Example

Let's say we are checking whether a user gets a discount:

```
function DiscountMessage() {
  const isPremiumMember = true;
  const hasCoupon = true;

  return (
    <>
      {isPremiumMember && hasCoupon ? (
        <p className="success">You get an EXTRA discount! 🎉</p>
     ) : (
        <p className="warning">You do not qualify for an extra discount.</p>
     )}
    </>
 );
}

```

#### Explanation:
- If isPremiumMember is true AND hasCoupon is true, the user gets an extra discount.
- If either one is false, the discount is not applied.
### Your Task:

Update the `WelcomeMessage` component to:

- Display the title: <h1>My React App</h1>
- Check BOTH isLoggedIn AND isPremiumUser directly in the JSX using &&: If both are true, show: "<p>Welcome to Premium Content! 🎉</p>" If either is false, show: "<p>Please log in and upgrade to premium...</p>"

 **Note -** 
1. Display the title in an `<h1>` tag.
2. Display the message in a `<p>` tag.

You can Test by changing `isLoggedIn`/`isPremiumUser` values in the App component.
Try writing the function and submit your solution!

## Solution

**Language:** C++  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-07-30T10:30:42.153Z  

```cpp
    </>
  );
}

// App component: Manages state and renders the WelcomeMessage component
export default function App() {
  const isLoggedIn = true;  // Change these values to test different scenarios
  const isPremiumUser = true; // Change these values to test different scenarios
      {/* Shown if the user is not logged in or not a premium user */}
       
     
      
      {/* Shown if the user is logged in and is a premium user */}

      
      
      {/* Conditional rendering: Show different messages based on user status  */}
      }
      : (<p classname = "warning">Please log in and upgrade to premium...</p>)
      { isLoggedIn && isPremiumUser ? (<p classname = "success">Welcome to Premium Content! 🎉</p>)
      {/* Application title  */}
    <>
  return (
export function WelcomeMessage({ isLoggedIn, isPremiumUser }) {
// WelcomeMessage component: Displays a message based on login and premium status
```

---

[View on CodeChef](https://www.codechef.com/problems/PREACT024)