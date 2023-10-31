import { RouterProvider, createBrowserRouter } from "react-router-dom";
import AppLayout from "./ui/AppLayout";
import Error from "./ui/Error";
import PostsPage from "./features/posts/PostsPage";
import SignupPage, {
  action as signupPageAction,
} from "./features/signup/SignupPage";
import SigninPage, {
  action as signinPageAction,
} from "./features/signin/SigninPage";

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
        action: signupPageAction,
      },
      {
        path: "/signin",
        element: <SigninPage />,
        action: signinPageAction,
      },
    ],
  },
]);

function App() {
  return <RouterProvider router={router} />;
}

export default App;
