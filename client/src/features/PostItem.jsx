function PostItem({ post }) {
  // const {}

  return (
    <div
      id="Post5"
      className="bg-white flex flex-col justify-end mr-1 gap-4 items-start pt-5 pb-1 px-8 rounded-[20px]"
    >
      <div className="self-stretch flex flex-col mr-6 gap-4">
        <div className="flex flex-col items-start ml-px mr-16">
          <div className="flex flex-row gap-1 w-20 items-start">
            <img
              src="https://file.rendit.io/n/3o3ji9fl1rUG8jThUW9Y.png"
              id="Icon8"
              className="mt-1 w-5 shrink-0"
            />
            <div className="font-['Montserrat'] leading-[27px]">Hakuru</div>
          </div>
          <div
            id="PostName2"
            className="text-2xl font-['Montserrat'] font-bold leading-[27px] text-[#0f1a1c]"
          >
            The importance of being there for your children
          </div>
        </div>
        <div className="flex flex-col gap-2 items-start">
          <img
            src="https://upload.wikimedia.org/wikipedia/commons/thumb/4/4b/Focus_ubt.jpeg/350px-Focus_ubt.jpeg"
            id="Video"
            className="mx-auto"
          />
          <div
            id="Text13"
            className="font-['Montserrat'] leading-[27px] w-full"
          >
            Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
            eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim
            ad... continue in full
          </div>
        </div>
      </div>
      <div className="flex flex-row items-start">
        <div
          id="Like2"
          className="bg-[#eaedef] bg-[linear-gradient(#ffffff,_#ffffff)] bg-cover bg-50%_50% bg-blend-normal bg-no-repeat flex flex-row gap-1 w-12 shrink-0 h-6 items-center pt-1 px-1 rounded-[749.2500610351562px]"
        >
          <img
            src="https://file.rendit.io/n/ZIk9FWqsSf1KJXSDvaLG.svg"
            className="w-3 shrink-0"
          />
          <div
            id="Text11"
            className="text-center text-xs font-['Montserrat'] font-semibold leading-[12px] self-start"
          >
            22K
          </div>
        </div>
        <div
          id="Comment2"
          className="overflow-hidden bg-white flex flex-row justify-center pt-1 gap-1 w-12 shrink-0 h-6 items-center rounded-[749.2500610351562px]"
        >
          <img
            src="https://file.rendit.io/n/qyG51oTx7CP1DfnQ0a8n.svg"
            id="Icon6"
            className="w-4 shrink-0"
          />
          <div
            id="Text9"
            className="text-center text-xs font-['Montserrat'] font-semibold leading-[12px] self-start mt-px"
          >
            319
          </div>
        </div>
      </div>
    </div>
  );
}

export default PostItem;
