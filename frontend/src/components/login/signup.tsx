import { FaAt, FaLock, FaUser, FaUserPlus } from "react-icons/fa";
import { AiOutlineNumber } from "react-icons/ai";

interface SignupProps {
  handleSignInClick: () => void;
}

const Signup: React.FC<SignupProps> = ({ handleSignInClick }) => {
  return (
    <div className="login-form-container sign-up">
      <form action="#">
        <h2>sign up</h2>
        <div className="form-group">
          <input type="text" required />
          <label>first name</label>
          <i>
            <FaUser />
          </i>
        </div>
        <div className="form-group">
          <input type="text" required />
          <label>last name</label>
          <i>
            <FaUserPlus />
          </i>
        </div>
        <div className="form-group">
          <select name="" id="">
            <option value="">Select batch</option>
          </select>
        </div>
        <div className="form-group">
          <select name="" id="">
            <option value="">Select semester</option>
          </select>
        </div>
        <div className="form-group">
          <input type="text" required />
          <label>registration no</label>
          <i>
            <AiOutlineNumber />
          </i>
        </div>
        <div className="form-group">
          <input type="email" required />
          <label>email</label>
          <i>
            <FaAt />
          </i>
        </div>
        <div className="form-group">
          <input type="password" required />
          <label>password</label>
          <i>
            <FaLock />
          </i>
        </div>
        <button type="submit" className="btn">
          sign up
        </button>
        <div className="link">
          <p>
            You already have an account?
            <a href="#" className="signin-link" onClick={handleSignInClick}>
              sign in
            </a>
          </p>
        </div>
      </form>
    </div>
  );
};

export default Signup;
