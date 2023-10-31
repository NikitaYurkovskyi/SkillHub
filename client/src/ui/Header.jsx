import { useLocation, useNavigate } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { clearUserData, fetchUserData } from "../features/user/userSlice";
import { useEffect } from "react";

function Header() {
  const navigate = useNavigate();
  const location = useLocation();

  const userData = useSelector((state) => state.user.userData);

  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(fetchUserData());
  }, [dispatch]);

  const logout = () => {
    const sure = window.confirm("Are you sure you want to logout?");
    if (sure) {
      localStorage.removeItem("accessToken");
      localStorage.removeItem("refreshToken");
      dispatch(clearUserData());
      navigate("/");
    }
  };

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
      {userData && userData.username ? (
        <span
          onClick={() => logout()}
          className="bg-[#003b93] px-1 py-1 text-white rounded-md"
        >
          {userData.username}
        </span>
      ) : (
        <button
          onClick={() => navigate("/signin")}
          className="overflow-hidden bg-[#003b93] flex flex-col w-20 shrink-0 h-8 items-start pl-5 py-2 rounded-[749.2500610351562px]"
        >
          <span className="text-xs font-['Montserrat'] font-semibold text-[#d2d2d4]">
            Log In
          </span>
        </button>
      )}
    </div>
  );
}

export default Header;
