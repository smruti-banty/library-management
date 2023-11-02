import React from "react";
import ReactDOM from "react-dom/client";
import App from "./App.tsx";
import Dashboard from "./components/admin/dashboard";
import {
  createBrowserRouter,
  RouterProvider,
  Navigate,
} from "react-router-dom";
import "./index.css";
import BookDetails from "./components/admin/bookdetails.tsx";
import AddBook from "./components/admin/forms/add-book.tsx";
import BatchDetails from "./components/admin/batchdetails.tsx";
import Home from "./components/college/home.tsx";
import College from "./components/college/college.tsx";
import About from "./components/college/about.tsx";
import Contact from "./components/college/contact.tsx";
import IssueBook from "./components/admin/issuebook.tsx";

const router = createBrowserRouter([
  {
    path: "/",
    element: <Navigate to="college/home" />,
  },
  {
    path: "college",
    element: <College />,
    children: [
      {
        path: "home",
        element: <Home />,
      },
      {
        path: "about",
        element: <About />,
      },
      {
        path: "contact",
        element: <Contact />,
      },
    ],
  },
  {
    path: "library",
    element: <App />,
    children: [
      {
        path: "dashboard",
        element: <Dashboard />,
      },
      {
        path: "book-details",
        element: <BookDetails />,
      },
      {
        path: "book-details/add/:bookId?",
        element: <AddBook />,
      },
      {
        path: "batch-details",
        element: <BatchDetails />,
      },
      {
        path: "issue-book",
        element: <IssueBook />,
      },
    ],
  },
]);

ReactDOM.createRoot(document.getElementById("root")!).render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>
);
