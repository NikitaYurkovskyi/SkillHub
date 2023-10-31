function PeopleItem() {
  return (
    <div
      //   id="PeopleRoot"
      className="overflow-hidden flex flex-row gap-20 w-full items-start"
    >
      <div className="self-end flex flex-col mb-0 gap-10 w-2/5 h-20 items-start">
        <div className="text-xl font-['Montserrat'] font-semibold leading-[15px] ml-2 w-full">
          Benjamin
        </div>
        <div className="text-center text-xs font-['Montserrat'] font-bold leading-[20.3px]">
          posts 70
        </div>
      </div>
      <div
        id="Image2"
        className="border-solid overflow-hidden flex flex-col w-20 shrink-0 items-start mt-1 mb-px border-black/10 border rounded"
      >
        <img
          src="https://c.animaapp.com/L0CjW9OF/img/image-32@2x.png"
          id="Image1"
          className="w-20 mb-0 ml-0"
        />
      </div>
    </div>
  );
}

export default PeopleItem;
