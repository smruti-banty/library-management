import BookIssue from "@/model/BookIssue";
import axios from "axios";

const BASE_URL = "http://localhost:1205/api/v1/book/issue";

export function issueBook(bookIssue: BookIssue) {
    return axios.post(BASE_URL, bookIssue);
}

export function returnBook(bookIssueId: string) {
    return axios.post(`${BASE_URL}/${bookIssueId}/return`);
}
