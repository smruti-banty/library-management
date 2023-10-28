export default interface Book {
  bookId?: string;
  bookName: string;
  author: string;
  description: string;
  referenceNumber: string;
  availableStock?: number;
  status?: string;
  createdDate?: string;
  createdBy?: string;
  updatedDate?: string;
  updatedBy?: string;
}
