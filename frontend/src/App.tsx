import "./App.css";
import SideBar from "./components/ui/sidebar";
import { Outlet, useLocation } from "react-router-dom";
import { getAdminMenus, getUserMenus } from "./common/menus";
import { Toaster } from "./components/ui/toaster";
function App() {
  const pathName = useLocation().pathname;
  const adminMenus = getAdminMenus();
  const userMenus = getUserMenus();
  const menus = pathName.includes("user") ? userMenus : adminMenus;

  return (
    <div className="flex">
      <SideBar menus={menus} active={pathName}></SideBar>
      <main className="flex-1">
        <header className="bg-gray-50 dark:bg-gray-800 p-5 shadow-md">
          <h1 className="text-right text-xl">👤</h1>
        </header>
        <section className="p-10">
          <Outlet />
        </section>
        <Toaster />
      </main>
    </div>
  );
}

export default App;
