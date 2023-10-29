import React from "react";
import ReactDOM from "react-dom/client";
import App from "./App.tsx";
import Dashboard from "./components/admin/dashboard";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import "./index.css";
import BookDetails from "./components/admin/bookdetails.tsx";
import AddBook from "./components/admin/forms/add-book.tsx";
import BatchDetails from "./components/admin/batchdetails.tsx";

const router = createBrowserRouter([
  {
    path: "/",
    element: <App />,
    children: [
      {
        path: "/",
        element: <Dashboard />,
      },
      {
        path: "/book-details",
        element: <BookDetails />,
      },
      {
        path: "/book-details/add/:bookId?",
        element: <AddBook />,
      },
      {
        path: "/batch-details",
        element: <BatchDetails />,
      },
    ],
  },
]);
ReactDOM.createRoot(document.getElementById("root")!).render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>
);
