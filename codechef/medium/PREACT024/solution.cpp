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