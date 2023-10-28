import {
  addBook,
  getBook,
  upadateBook,
} from "@/components/services/bookservice";
import { useToast } from "@/components/ui/use-toast";
import Book from "@/model/Book";
import { useEffect, useRef, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";

const AddBook = () => {
  const { bookId } = useParams();
  const navigate = useNavigate();
  const { toast } = useToast();
  const headingMessage = bookId ? "Update" : "Add";
  const toastMessage = bookId ? "Book updated" : "Book added";

  const [book, setBook] = useState<Book>({
    bookName: "",
    author: "",
    description: "",
    referenceNumber: "",
  });

  useEffect(() => {
    try {
      if (bookId) getBook(bookId).then((response) => setBook(response.data));
    } catch (err) {
      console.error(err);
    }
  }, [bookId]);

  useEffect(() => {
    bookNameRef.current!.value = book.bookName;
    referenceNumberRef.current!.value = book.referenceNumber;
    authorRef.current!.value = book.author;
    descriptionRef.current!.value = book.description
  }, [book.author, book.bookName, book.description, book.referenceNumber]);

  const bookNameRef = useRef<HTMLInputElement>(null);
  const referenceNumberRef = useRef<HTMLInputElement>(null);
  const authorRef = useRef<HTMLInputElement>(null);
  const descriptionRef = useRef<HTMLTextAreaElement>(null);

  function onSubmit(event: React.FormEvent<HTMLFormElement>) {
    event.preventDefault();
    const bookName = bookNameRef.current?.value || "";
    const referenceNumber = referenceNumberRef.current?.value || "";
    const author = authorRef.current?.value || "";
    const description = descriptionRef.current?.value || "";

    const book: Book = { bookName, referenceNumber, author, description };

    function afterApiCall() {
      toast({
        title: toastMessage,
      });

      navigate("/book-details");
    }

    try {
      bookId
        ? upadateBook(book, bookId).then(() => afterApiCall())
        : addBook(book).then(() => afterApiCall());
    } catch (err) {
      toast({
        variant: "destructive",
        title: "Something wen wrong!",
        description: `${err}`,
      });
    }
  }

  const styleInput =
    "shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline";
  const styleLabel = "block text-gray-700 text-sm font-bold mb-2";
  return (
    <div className="bg-white shadow-lg rounded-md container">
      <h2 className="text-center text-xl font-bold p-3">
        {headingMessage} book
      </h2>
      <hr />
      <div className="py-5">
        <form onSubmit={onSubmit}>
          <div className="mb-5">
            <label className={styleLabel}>Book name</label>
            <input
              className={styleInput}
              id="bookname"
              type="text"
              placeholder="book name"
              ref={bookNameRef}
            />
          </div>
          <div className="mb-5">
            <label className={styleLabel}>Book author</label>
            <input
              className={styleInput}
              id="authorName"
              type="text"
              placeholder="Author name"
              ref={authorRef}
            />
          </div>
          <div className="mb-5">
            <label className={styleLabel}>Reference number</label>
            <input
              className={styleInput}
              id="referenceNumber"
              type="text"
              placeholder="Reference number"
              ref={referenceNumberRef}
            />
          </div>
          <div className="mb-5">
            <label className={styleLabel}>Book description</label>
            <textarea
              className={styleInput}
              id="description"
              placeholder="Author name"
              ref={descriptionRef}
            />
          </div>
          <div className="flex items-center justify-between">
            <button
              className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
              type="submit"
            >
              Submit
            </button>
            <Link
              className="inline-block align-baseline font-bold text-sm text-blue-500 hover:text-blue-800"
              to="/book-details"
            >
              back to books!
            </Link>
          </div>
        </form>
      </div>
    </div>
  );
};

export default AddBook;
