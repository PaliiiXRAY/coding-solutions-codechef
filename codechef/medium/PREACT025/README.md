# PREACT025

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

### Ternary Operator in React

In React, conditional rendering allows us to render different UI elements based on certain conditions. One of the common ways to achieve this is by using the  **ternary operator `{condition ? trueValue : falseValue}`**.

#### Example:

Let's say we want to display a message based on a user's login status.

```
const UserGreeting = ({ isLoggedIn }) => {
  return (
    <div>
      {isLoggedIn ? <h1>Welcome, User!</h1> : <h1>Please log in</h1>}
    </div>
 );
};

```

### Explanation:
- If isLoggedIn is true, it displays "Welcome, User!".
- If isLoggedIn is false, it displays "Please log in".
- The ternary operator {isLoggedIn ? <h1>Welcome, User!</h1> : <h1>Please log in</h1>} helps us switch between the two components dynamically.
#### Task for You:

Update the React component `LightStatus` so that it  **displays**  the message  **"Lights ON"**  if the variable `isLightOn` is `true`; otherwise, it should display  **"Lights OFF"**.  **Make sure to use an `<h1>` tag for displaying the message.** 

Now, try modifying the code to handle a button click that toggles the light status!

## Solution

**Language:** C++  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-07-30T10:02:42.724Z  

```cpp
  {/* If `isLightOn` is true, it shows "Lights ON", otherwise "Lights OFF" */}
  return (
       <div>
  );
};

// Main App component
export default function App() {
  return (
    <LightStatus isLightOn={true} />
  );
        {isLightOn ? <h1>Lights ON</h1> : <h1>Lights OFF</h1>}
       </div>
}

export function LightStatus({ isLightOn }) {
// This component displays the light status based on the `isLightOn` prop.
```

---

[View on CodeChef](https://www.codechef.com/problems/PREACT025)