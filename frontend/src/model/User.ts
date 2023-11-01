export default interface User {
    userId?: string;
    firstName: string;
    lastName: string;
    email: string;
    password: string;
    referenceNumber: string;
    profilePic?: string;
    userRole?: string;
    userStatus?: string;
    createdDate?: string;
    createdBy?: string;
    updatedDate?: string;
    updatedBy?: string;
}