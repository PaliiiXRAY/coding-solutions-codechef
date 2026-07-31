# PREACT014

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

### Now, Lets See How to Create a Component

We now know that React is all about  **components**, and we have already discussed why they are important. Now, let's see  **how to create a component**  in the simplest way possible.

#### Creating a Basic React Component

A component in React is just a  **JavaScript function**  that returns  **JSX (which looks like HTML)**. Let’s understand this by creating a  **Welcome Message**  component inside the same file (`App.js`).

#### Here's the Code:

```
function Welcome() {
  return <h2>Welcome to My React App! 🎉</h2>;
}

function App() {
  return (
    <div>
      <Welcome /> {/ *Using the Welcome component* /}
      <p>React components make the UI more structured and reusable.</p>
    </div>
 );
}

export default App;

```

#### Understanding the Code

 **Step 1:**  First, we define a function called  **`Welcome`**, which is our  **component**.
 **Step 2:**  Inside this function, we return some JSX, which in this case, is an `<h2>` tag with a message.
 **Step 3:**  In the `App` function, we use the `<Welcome />` component just like a regular  **HTML tag**.
 **Step 4:**  React replaces `<Welcome />` with the actual JSX returned from the `Welcome` function.

#### What Will This Render on the Screen?

```
Welcome to My React App! 🎉  
React components make the UI more structured and reusable.

```

The  **`Welcome` component**  gets inserted inside the `App` component, making the structure of the UI  **clean**  and  **organized** !

### Your Task:

Now that you’ve learned how to create a basic React component,  **here’s a small challenge for you!** 

- Create a new function called Goodbye inside App.js.
- This function should return a (h3 - heading) message saying goodbye (e.g., "Thanks for visiting! See you soon!").
- Use the <Goodbye /> component inside the App function, below the <Welcome /> component.

🔥  **In the next, we will learn about "props"**, which allow us to  **pass dynamic data**  inside components! Stay tuned. 🚀

## Solution

**Language:** C++  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-07-31T17:50:12.177Z  

```cpp
}

// Creating the new Goodbye component
function Goodbye(){



function App() {
  return (
    <div>
      <Welcome /> {/* Using the Welcome component */}
      <p>React components make the UI more structured and reusable.</p>
      {/* Using the Goodbye component */}
     <Goodbye/>
    
    </div>
  );
}
  return <h3>Thanks for visiting! See you soon!</h3>;
}

export default App;
  return <h2>Welcome to My React App! 🎉</h2>;
function Welcome() {
```

---

[View on CodeChef](https://www.codechef.com/problems/PREACT014)