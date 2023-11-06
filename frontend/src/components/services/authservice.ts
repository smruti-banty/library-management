import axios from "axios";
const BASE_URL = "/login";

export function isAuthenticate() {
  if (!localStorage.getItem("key")) {
    return false;
  }

  return true;
}

export function storeKey(key: string) {
  localStorage.setItem("key", key);
}

export function loginUser(login: { username: string; password: string }) {
  return axios.post(BASE_URL, login);
}
