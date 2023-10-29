import Book from "@/model/Book";
import axios from "axios";

const BASE_URL = "http://localhost:1205/api/v1/book";

export function getAllBooks() {
  return axios.get(BASE_URL);
}

export function addBook(book: Book) {
  return axios.post(BASE_URL, book);
}

export function upadateBook(book: Book, bookId: string) {
  return axios.put(`${BASE_URL}/${bookId}`, book);
}

export function getBook(bookId: string) {
  return axios.get(`${BASE_URL}/${bookId}`);
}

export function deleteBook(bookId: string) {
  return axios.delete(`${BASE_URL}/${bookId}`);
}