// eslint-disable-next-line react/prop-types
const ContinueWithProviderButton = ({ src, text }) => (
  <button className="h-[50px] overflow-hidden bg-white flex flex-row gap-16 items-center px-5 rounded-[21px]">
    <img src={src} id="Image1" className="w-10 shrink-0 my-1" />
    <div className="font-['Montserrat'] font-semibold text-[#414141]">
      {text}
    </div>
  </button>
);

export default ContinueWithProviderButton;
