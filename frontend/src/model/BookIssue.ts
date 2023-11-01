export default interface BookIssue {
    bookIssueId?: string;
    adminId: string;
    bookId: string;
    bookReferenceNumber: string;
    studentId: string;
    status: string;
    createdDate?: string;
    createdBy?: string;
    updatedDate?: string;
    updatedBy?: string;
}