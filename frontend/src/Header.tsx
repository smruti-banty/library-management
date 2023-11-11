import { useState } from "react";
import UploadImage from "./common/uploadimage";

const Header = () => {
  const [showUploadModal, setUploadModal] = useState(false);
  function uploadImage(id: string, file: File) {
    // const formData = new FormData();
    console.log(id, file);

    // formData.append("file", file);
    // try {
    //   uploadBookImage(bookId, formData).then(() => {
    //     getBooks();
    //     toast({ title: "Image added" });
    //   });
    // } catch (err) {
    //   console.error(err);
    // }
  }
  return (
    <header className="bg-gray-50 dark:bg-gray-800 p-5 shadow-md flex justify-end">
      <button className="px-5" title="Upload image" onClick={() => setUploadModal(true)}>
        <span className="text-xl">👤</span>
      </button>

      {showUploadModal && (
        <UploadImage
          id="123"
          uploadFile={uploadImage}
          setDisplayModal={setUploadModal}
        />
      )}
    </header>
  );
};

export default Header;
