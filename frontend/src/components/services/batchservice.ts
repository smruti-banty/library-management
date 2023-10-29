import Batch from "@/model/Batch";
import axios from "axios";

const BASE_URL = "http://localhost:1205/api/v1/batch";

export function getAllBatch() {
  return axios.get(BASE_URL);
}

export function addBatch(batch: Batch) {
  return axios.post(BASE_URL, batch);
}

export function upadateBatch(batch: Batch, batchId: string) {
  return axios.put(`${BASE_URL}/${batchId}`, batch);
}

export function getBatch(batchId: string) {
  return axios.get(`${BASE_URL}/${batchId}`);
}

export function deleteBatch(batchId: string) {
  return axios.delete(`${BASE_URL}/${batchId}`);
}
