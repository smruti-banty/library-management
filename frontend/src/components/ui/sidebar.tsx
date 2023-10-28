import Menu from "@/model/Menu";
import { Link } from "react-router-dom";

interface SideBarProps {
  menus: Menu[];
  active: string;
}

const SideBar: React.FC<SideBarProps> = ({ menus, active }) => {
  return (
    <aside
      id="sidebar-multi-level-sidebar"
      className="shadow-lg top-0 left-0 z-40 w-64 h-screen transition-transform -translate-x-full sm:translate-x-0"
      aria-label="Sidebar"
    >
      <div className="h-full px-3 py-4 overflow-y-auto bg-gray-50 dark:bg-gray-800">
        <ul className="space-y-2 font-medium">
          {menus.map(({ label, icon, path }) => (
            <li key={label}>
              <Link
                to={path}
                className={`flex items-center p-2 text-gray-900 rounded-lg dark:text-white hover:bg-gray-100 dark:hover:bg-gray-700 group ${
                  path == active || (path !== "/" && active.startsWith(path))
                    ? "bg-gray-100 dark:bg-gray-700"
                    : ""
                }`}
              >
                <span className="w-5 h-5 text-gray-500 transition duration-75 dark:text-gray-400 group-hover:text-gray-900 dark:group-hover:text-white">
                  {icon}
                </span>
                <span className="ml-3">{label}</span>
              </Link>
            </li>
          ))}
        </ul>
      </div>
    </aside>
  );
};

export default SideBar;
