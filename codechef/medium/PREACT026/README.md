# PREACT026

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

### Range Utility

A  **Range Utility**  is a function that lets us create an array with a sequence of numbers, like `[0, 1, 2, 3, 4]`. Instead of writing loops manually, we use this function to generate a range of numbers easily.

### Why is it Useful?

In `JavaScript`, we often need to repeat an action multiple times. Normally, we use a `for` loop, but loops are statements and cannot be used inside `JSX` (`React's` syntax).

By using a  **range utility function**, we can create an array of numbers that we can loop over easily using `.map()`. This makes the code shorter, cleaner, and easier to read when working with React.

### How Does It Work?

A range function generates an array based on three values:

- Start: The first number in the sequence. (default is 0)
- End: The last number (not included in the array).
- Step: How much we increase the number each time. (default is 1)

Let's look at the function:

```
const range = (start, end, step = 1) => {
  let output = [];  // Create an empty array
  
  if (typeof end === undefined) {
    end = start;   // If only one number is given, start from 0.
    start = 0;
  }

  for (let i = start; i < end; i += step) {
    output.push(i);  // Add numbers to the array
  }

  return output;  // Return the generated array
};

```

### Examples

```
console.log(range(5)); 
// Output: [0, 1, 2, 3, 4]

console.log(range(2, 6));
// Output: [2, 3, 4, 5]

console.log(range(1, 10, 2));
// Output: [1, 3, 5, 7, 9]

```

This function helps us create arrays dynamically without manually writing loops.

### Using Range in React

If we want to generate numbered boxes dynamically, we can use `range()` instead of a for loop:

```
function NumberBoxes({ count }) {  
  return ( 
    <div className="number-container">  
      {range(count).map((num) => ( 
        <div key={num} className="number-box">  
          {num + 1}  
        </div>  
     ))}  
    </div>  
 );  
}  

```

Instead of manually writing multiple number boxes, we generate them dynamically using `range()` and `.map()`.

### Task for You!

Write a function using the  **range utility**  that will create a list of numbers inside `<li>` tags, and render them in a `<ul>`.

For example, if `range(1, 6)` is used, it should generate:

```
<ul>
  <li>1</li>
  <li>2</li>
  <li>3</li>
  <li>4</li>
  <li>5</li>
</ul>

```

Try writing the component in React

#### Video Explanation:

## Solution

**Language:** C++  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-07-30T10:43:02.177Z  

```cpp

  return result;
};

// update this function 
function NumberBoxes({ count }) {  
  return (  
    <ul>
       <div className="number-container">  
    </ul>
  );  
}
      {range(count).map((num) => (  
        <li key={num}>
      ))}  
    </div>  

          {num + 1}  </li> 
export default function App() {
  return (
    <NumberBoxes count={3} />
  );
}
```

---

[View on CodeChef](https://www.codechef.com/problems/PREACT026)