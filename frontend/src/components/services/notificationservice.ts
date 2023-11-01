import Notification from "@/model/Notification";
import axios from "axios";

const BASE_URL = "http://localhost:1205/api/v1/notification";

export function getAllNotification() {
    return axios.get(BASE_URL);
}

export function createNotification(notification: Notification) {
    return axios.post(BASE_URL, notification);
}

export function markAsReadNotification(receiverUserId: string) {
    return axios.post(`${BASE_URL}/${receiverUserId}`);
}

export function getAllUnreadNotifications(receiverUserId: string) {
    return axios.post(`${BASE_URL}/${receiverUserId}/unread`);
}

export function getAllReadNotifications(receiverUserId: string) {
    return axios.post(`${BASE_URL}/${receiverUserId}/read`);
}