import "./login.css";
import { useState } from "react";
import Signup from "./signup";
import Signin from "./signin";
import { Toaster } from "../ui/toaster";

const Login = () => {
  const [isSignUp, setIsSignUp] = useState(true);

  const handleSignUpClick = () => {
    setIsSignUp(false);
  };

  const handleSignInClick = () => {
    setIsSignUp(true);
  };

  const wrapperClass = isSignUp ? "animated-signup" : "animated-signin";

  return (
    <main className="bg-teal-700 h-screen flex items-center justify-center overflow-hidden">
      <div className={`wrapper ${wrapperClass}`}>
        <Signup handleSignInClick={handleSignInClick} />
        <Signin handleSignUpClick={handleSignUpClick} />
      </div>
      <Toaster />
    </main>
  );
};
export default Login;
