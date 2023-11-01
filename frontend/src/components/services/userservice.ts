import User from "@/model/User";
import axios from "axios";

const BASE_URL = "http://localhost:1205/api/v1/user";

export function createUser(user: User) {
    return axios.post(`${BASE_URL}/create`, user);
}

export function createAdmin(user: User) {
    return axios.post(`${BASE_URL}/createAdmin`, user);
}

export function updateUser(user: User, userId: string) {
    return axios.put(`${BASE_URL}/update/${userId}`, user);
}

export function deleteUser(userId: string) {
    return axios.delete(`${BASE_URL}/delete/${userId}`);
}

export function getUsers() {
    return axios.get(`${BASE_URL}/users`);
}

export function getAdmins() {
    return axios.get(`${BASE_URL}/admins`);
}

export function getUserById(userId: string) {
    return axios.get(`${BASE_URL}/${userId}`);
}

export function getUserByReferenceNumber(userReferenceNumber: string) {
    return axios.get(`${BASE_URL}/byreferencenumber/${userReferenceNumber}`);
}

export function approveUser(userId: string) {
    return axios.put(`${BASE_URL}/approve/${userId}`);
}

export function getAllPendingUser() {
    return axios.get(`${BASE_URL}/pending`);
}