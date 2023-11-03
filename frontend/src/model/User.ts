export default interface User {
    userId?: string;
    firstName: string;
    lastName: string;
    email: string;
    referenceNumber: string;
    password?: string;
    profilePic?: string;
    userStatus?: string;
    createdDate?: string;
    createdBy?: string;
    updatedDate?: string;
    updatedBy?: string;
}