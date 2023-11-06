import BookIssue from "@/model/BookIssue";
import axios from "./api";

const BASE_URL = "http://localhost:1205/api/v1/book/issue";

export function issueBook(bookIssue: BookIssue) {
  return axios.post(BASE_URL, bookIssue);
}

export function returnBook(bookIssueId: string) {
  return axios.patch(`${BASE_URL}/${bookIssueId}/return`);
}

export function allBookIssue() {
  return axios.get(`${BASE_URL}/all`);
}
