import { Outlet } from "react-router-dom";
import Hero from "./hero";
import NavBar from "./navbar";

const College = () => {
  return (
    <>
      <NavBar />
      <Hero />
      <Outlet />
    </>
  );
};

export default College;
