// eslint-disable-next-line react/prop-types
function Input({ placeholder, type = "text", name, required = false }) {
  return (
    <input
      required={required}
      name={name}
      type={type}
      placeholder={placeholder}
      className="overflow-hidden font-['Montserrat'] font-bold leading-[27px] text-white/50 bg-[#001732] text-white flex flex-col justify-center pl-5 h-16 shrink-0 items-start rounded-[21px]"
    />
  );
}

export default Input;
