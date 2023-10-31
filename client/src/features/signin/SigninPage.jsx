import { Form, redirect, useNavigate } from "react-router-dom";
import Input from "../../ui/Input";
import ContinueWithProviderButton from "../../ui/ContinueWithProviderButton";
import store from "../../store";
import { setUserData } from "../user/userSlice";
import { AUTH_SERVICE_URL } from "../../credentials";

function SigninPage() {
  const navigate = useNavigate();

  return (
    <div className="overflow-hidden bg-black/39 flex flex-col justify-center w-full items-center px-[417px] h-[100vh] bg-[#121212]">
      <div
        id="Registration2"
        className="bg-[#003b93] bg-[linear-gradient(rgba(0,_0,_0,_0.2),_rgba(0,_0,_0,_0.2)),_linear-gradient(rgba(38,_35,_35,_0.08),_rgba(38,_35,_35,_0.08))] bg-cover,_cover bg-50%_50%,_50%_50% bg-blend-normal,_normal bg-repeat-no-repeat,_no-repeat relative flex flex-col gap-10 w-full items-center pt-5 pb-12 px-8 rounded-[50px] overflow-auto"
      >
        <div className="self-stretch relative flex flex-col ml-16 gap-8">
          <div className="flex flex-row justify-between items-start">
            <div className="text-4xl font-['Montserrat'] font-bold leading-[27px] text-white self-end w-1/2">
              Log In
            </div>
            <img
              onClick={() => navigate("/")}
              src="https://file.rendit.io/n/qInF88BUW4OVRZUxJo5t.svg"
              id="CloseButton"
              className="mb-6 w-8 shrink-0"
            />
          </div>
          <Form method="POST" className="flex flex-col mr-16 gap-6">
            <div className="text-sm font-['Montserrat'] font-bold leading-[20px] text-white self-start w-full">
              By continuing, you agree to our User Agreement and knowledge that
              you understand the Privacy Policy.
            </div>
            <div className="flex flex-col mb-px gap-3">
              <ContinueWithProviderButton
                src="https://c.animaapp.com/hFiUumBg/img/image-15@2x.png"
                text="Continue with Google"
              />

              <ContinueWithProviderButton
                src="https://c.animaapp.com/hFiUumBg/img/image-16@2x.png"
                text="Continue with Github"
              />
            </div>
            <div className="flex items-center">
              <div className="flex-1 mx-2 border-t" />
              <span className="text-2xl font-['Montserrat'] font-bold leading-[20px] text-white">
                OR
              </span>
              <div className="flex-1 mx-2 border-t" />
            </div>

            <div className="flex flex-col mb-px gap-3">
              <div className="flex flex-col gap-2">
                <Input name="email" placeholder="Email" type="email" />
                <Input name="password" placeholder="Password" type="password" />
              </div>
            </div>
            <button className="overflow-hidden bg-[#001732] flex flex-col justify-center h-12 shrink-0 items-center mx-16 rounded-[21px]">
              <span className="font-['Montserrat'] font-semibold text-white">
                Login
              </span>
            </button>
          </Form>
        </div>
        <div className="relative flex flex-row gap-2 w-1/2 items-start">
          <div className="text-xl font-['Montserrat'] font-bold leading-[27px] text-white">
            New to SkillHub?
          </div>
          <button
            onClick={() => navigate("/signup")}
            className="text-xl font-['Montserrat'] font-bold leading-[27px] text-[#d9d9d9]"
          >
            Sign Up
          </button>
        </div>
      </div>
    </div>
  );
}

// eslint-disable-next-line react-refresh/only-export-components
export async function action({ request }) {
  const formData = await request.formData();
  const data = Object.fromEntries(formData);

  const response = await fetch(`${AUTH_SERVICE_URL}signin`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
  });

  if (response.ok) {
    const data = await response.json();

    localStorage.setItem("accessToken", data.accessToken);
    localStorage.setItem("refreshToken", data.refreshToken);
    console.log(data);
    store.dispatch(setUserData(data.userData));
    return redirect("/");
  }
  return redirect("/signin");
}
export default SigninPage;
