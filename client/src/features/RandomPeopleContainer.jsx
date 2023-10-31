import PeopleItem from "./PeopleItem";

function RandomPeopleContainer() {
  return (
    <div className="sticky top-[50px] flex flex-col justify-end mt-px w-[314px] shrink-0 items-start pt-[613px] px-2">
      <div
        id="Navigation"
        className="w-[314px] overflow-hidden bg-white absolute top-0 left-0 flex flex-col gap-6 items-start p-3 rounded-lg"
      >
        <div className="text-center text-xs font-['Montserrat'] font-bold leading-[20.3px] ml-3">
          Random People!
          <br />
          <span className="text-xs">Speak with random people we recomends</span>
        </div>
        <PeopleItem />
        <PeopleItem />
        <PeopleItem />
        <PeopleItem />
        <PeopleItem />
      </div>
    </div>
  );
}

export default RandomPeopleContainer;
