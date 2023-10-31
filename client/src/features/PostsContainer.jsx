import PostItem from "./PostItem";

function Posts() {
  return (
    <div className="flex flex-col gap-5 w-[743px] items-start">
      <div className="text-2xl font-['Montserrat'] font-bold leading-[27px] text-[#0f1a1c] ml-5">
        Articles in the Minecraft section
      </div>
      <div
        id="PostsRoot"
        className="overflow-hidden flex flex-col pb-16 gap-4 w-full"
      >
        <PostItem />
        <PostItem />
        <PostItem />
      </div>
    </div>
  );
}

export default Posts;
