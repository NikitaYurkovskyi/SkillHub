import { useLocation, useNavigate } from "react-router-dom";

function Header() {
  const navigate = useNavigate();
  const location = useLocation();

  console.log(location.pathname);

  if (location.pathname === "/signin" || location.pathname === "/signup")
    return null;

  return (
    <div className="bg-white flex flex-row justify-between items-center my-1 pl-[420px] pr-20 sticky top-0">
      <div className="bg-[#f3f2ee] flex flex-row justify-between gap-[383px] h-8 items-center my-1 pl-4 pr-3 py-2 rounded-full">
        <input
          placeholder="Search SkillHub"
          className="text-xs font-['Montserrat'] text-[#717171] self-start mt-px bg-transparent outline-none"
        />
        <img
          src="https://file.rendit.io/n/seJOKsxtoFdzYAQUbAzM.svg"
          className="w-3 shrink-0"
        />
      </div>
      <button className="overflow-hidden bg-[#003b93] flex flex-col w-20 shrink-0 h-8 items-start pl-5 py-2 rounded-[749.2500610351562px]">
        <button
          onClick={() => navigate("/signin")}
          className="text-xs font-['Montserrat'] font-semibold text-[#d2d2d4]"
        >
          Log In
        </button>
      </button>
    </div>
  );
}

export default Header;
