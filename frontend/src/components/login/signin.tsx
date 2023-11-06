import { FaLock, FaUser } from "react-icons/fa";
interface SigninProps {
  handleSignUpClick: () => void;
}
const Signin: React.FC<SigninProps> = ({ handleSignUpClick }) => {
  return (
    <div className="login-form-container sign-in">
      <form action="#">
        <h2>login</h2>
        <div className="form-group">
          <input type="text" required />
          <i>
            <FaUser />
          </i>
          <label>username</label>
        </div>
        <div className="form-group">
          <input type="password" required />
          <i>
            <FaLock />
          </i>
          <label>password</label>
        </div>
        {/* <div className="forgot-pass">
          <a href="#">forgot password?</a>
        </div> */}
        <div>
          <button type="submit" className="btn">
            login
          </button>
        </div>
        <div className="link">
          <p>
            Don't have an account?
            <a href="#" className="signup-link" onClick={handleSignUpClick}>
              sign up
            </a>
          </p>
        </div>
      </form>
    </div>
  );
};

export default Signin;