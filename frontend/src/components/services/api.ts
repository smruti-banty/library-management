import axios from "axios";

const api = axios.create({
  baseURL: "http://localhost:1205/api/v1",
});

api.interceptors.request.use(
  (config) => {
    const noauth = config.headers.noauth;
    const authToken = localStorage.getItem("key");

    if (!noauth && authToken) {
      config.headers.Authorization = `Bearer ${authToken}`;
    }

    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

export default api;
