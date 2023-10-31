const Image = ({ src, id, className }) => (
  <img src={src} id={id} className={className} />
);

const Text = ({ children, className }) => (
  <div className={`text-xs font-['Montserrat'] ${className}`}>{children}</div>
);

const Separator = ({ id }) => (
  <div
    id={id}
    className="border-solid border-[rgba(90,_90,_90,_0.2)] overflow-hidden h-px shrink-0 border-t-0 border-b border-x-0"
  />
);

function Filters() {
  return (
    <div className="sticky top-[50px] overflow-hidden bg-white flex flex-col justify-center mt-px gap-4 h-40 items-start rounded-lg">
      <div className="flex flex-row ml-4 gap-3 w-16 items-start">
        <div className="flex flex-col mb-0 gap-4 w-4 shrink-0 h-12 items-start">
          <Image
            src="https://file.rendit.io/n/PYWsmC6hbH0v8ebAWayy.svg"
            id="SVG"
            className="w-4"
          />
          <Image
            src="https://file.rendit.io/n/tVeJzX9sDsSYreiKXNwz.svg"
            id="SVG1"
            className="w-4"
          />
        </div>
        <div className="flex flex-col mt-0 gap-4 w-10 shrink-0 h-12 items-start">
          <Text className="leading-[15px] text-[#0c151c]">Home</Text>
          <Text className="leading-[15px] text-[#0c151c]">Popular</Text>
        </div>
      </div>
      <div className="flex flex-col gap-3 w-48 h-16 shrink-0">
        <Separator id="Separator" />
        <div className="flex flex-col mb-0 gap-2 h-6 shrink-0">
          <div className="flex flex-row justify-between items-start ml-3 mr-6">
            <Text className="tracking-[0.9] leading-[12px] text-[#0c151c] mt-px">
              TOPICS
            </Text>
            <Image
              src="https://file.rendit.io/n/b54E4dnHsK1F6sWlQ2fZ.svg"
              className="w-4 shrink-0"
            />
          </div>
          <Separator id="Separator1" />
        </div>
        <div className="flex flex-row justify-between items-start ml-3 mr-6">
          <Text className="tracking-[0.9] leading-[12px] text-[#0c151c] mt-px">
            RESOURCES
          </Text>
          <Image
            src="https://file.rendit.io/n/8ArNndhGJ5EckbeS6itC.svg"
            className="w-4 shrink-0"
          />
        </div>
      </div>
    </div>
  );
}

export default Filters;
