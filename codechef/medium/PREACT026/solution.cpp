
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