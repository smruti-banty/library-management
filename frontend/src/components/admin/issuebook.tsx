import { useEffect, useState } from "react";
import { issueBook, returnBook } from "../services/bookIssueservice";
import DataTable from "@/common/datatable";
import { BiSearch } from "react-icons/bi";
import OverFlowMenuItem from "@/model/OverFlowMenuItem";
import { useToast } from "../ui/use-toast";
import BookIssue from "@/model/BookIssue";
import AddBookIssue from "./forms/add-batch";

const IssueBook = () => {
  const { toast } = useToast();
  const [batchs, setBookIssues] = useState<BookIssue[]>([]);
  const [filteredBookIssue, setFilteredBookIssue] = useState<BookIssue[]>([]);
  const [search, setSearch] = useState("");
  const [displayBookIssueModal, setDisplayBookIssueModal] = useState(false);
  const [batchId, setBookIssueId] = useState("");

  const overflowMenuItems: OverFlowMenuItem[] = [
    {
      label: "Mark Return",
      callBack: updateIssueBook,
    },
  ];

  function updateIssueBook(id: string) {
    setBookIssueId(id);
    setDisplayBookIssueModal(true);
  }

  function getBookIssues() {
    try {
      getAllBookIssue().then((response) => {
        setBookIssues(response.data);
        setFilteredBookIssue(response.data);
      });
    } catch (err) {
      console.error(err);
    }
  }

  function onSearch(data: string) {
    setSearch(data);
    if (data.length > 3) {
      const result = batchs.filter((batch) =>
        batch.batchName.toLocaleLowerCase().includes(data.toLocaleLowerCase())
      );
      setFilteredBookIssue(result);
    } else {
      setFilteredBookIssue(batchs);
    }
  }

  useEffect(() => {
    getBookIssues();
  }, []);

  return (
    <div className="shadow-lg rounded-md p-5 bg-white">
      <h2 className="text-xl text-center font-bold p-2">BookIssue Details</h2>
      <hr />
      <div className="w-full mt-6 flex h-full justify-between items-center">
        <div
          className="flex items-center border border-gray-300 flex-1 px-2"
          style={{ borderBottom: "none" }}
        >
          <BiSearch />
          <input
            type="search"
            placeholder="search here"
            className="outline-none p-2 w-full"
            value={search}
            onChange={(e) => onSearch(e.target.value)}
          />
        </div>
        <div>
          <button
            className="bg-blacktext-white bg-black text-white p-2"
            onClick={() => {
              setBookIssueId("");
              setDisplayBookIssueModal(true);
            }}
          >
            Add BookIssue
          </button>
        </div>
      </div>
      <DataTable
        data={filteredBookIssue}
        idName="batchId"
        overflowMenu={overflowMenuItems}
      />
      {displayBookIssueModal && (
        <AddBookIssue
          setDisplayBookIssueModal={setDisplayBookIssueModal}
          updateBookIssueData={getBookIssues}
          batchId={batchId}
        />
      )}
    </div>
  );
};

export default IssueBook;
