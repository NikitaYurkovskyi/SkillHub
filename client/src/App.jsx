import { RouterProvider, createBrowserRouter } from "react-router-dom";
import AppLayout from "./ui/AppLayout";
import Error from "./ui/Error";
import PostsPage from "./features/posts/PostsPage";
import SignupPage from "./features/signup/SignupPage";
import SigninPage from "./features/signin/SigninPage";
function Header() {
  return (
    <div
      id="UpperBarRoot"
      className="bg-white flex flex-row justify-between w-full items-center pl-[420px] pr-20"
    >
      <div
        id="Divwfull"
        className="bg-[#f3f2ee] flex flex-row justify-between gap-[383px] h-8 items-center my-1 pl-4 pr-3 py-2 rounded-lg"
      >
        <div className="text-xs font-['Montserrat'] text-[#717171] self-start mt-px">
          Search SkillHub
        </div>
        <img
          src="https://file.rendit.io/n/sIZTjqji7IEGW9y078Xd.svg"
          className="w-3 shrink-0"
        />
      </div>
      <button className="overflow-hidden bg-[#003b93] flex flex-col w-20 shrink-0 h-8 items-start pl-5 py-2 rounded-[749.2500610351562px]">
        <div className="text-xs font-['Montserrat'] font-semibold text-[#d2d2d4] ml-1">
          Log In
        </div>
      </button>
    </div>
  );
}

const router = createBrowserRouter([
  {
    element: <AppLayout />,
    errorElement: <Error />,

    children: [
      {
        path: "/",
        element: <PostsPage />,
      },
      {
        path: "/signup",
        element: <SignupPage />,
      },
      {
        path: "/signin",
        element: <SigninPage />,
      },
    ],
  },
]);

function App() {
  return <RouterProvider router={router} />;
}

export default App;
