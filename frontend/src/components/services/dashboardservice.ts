import axios from "axios";

const BASE_URL = "http://localhost:1205/api/v1/admin/dashboard";

export function getDashboardDetails() {
  return axios.get(BASE_URL);
}
