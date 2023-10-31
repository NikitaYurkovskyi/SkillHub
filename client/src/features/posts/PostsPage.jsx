import Filters from "../../ui/Filters";
import PostsContainer from "../PostsContainer";
import RandomPeopleContainer from "../RandomPeopleContainer";

function PostsPage() {
  return (
    <div id="FilterRoot" className="bg-[#f3f2ee] flex flex-col gap-5 w-full">
      <div className="flex flex-row gap-10 items-start mb-0 ml-20 mt-[15px] mr-8">
        <div className="flex flex-row gap-8 w-[971px] items-start">
          <Filters />
          <PostsContainer />
        </div>
        <RandomPeopleContainer />
      </div>
    </div>
  );
}

export default PostsPage;
