import Header from "./Header";
// import Loader from "./Loader";
// import CartOverview from '../features/cart/CartOverview';
import { Outlet, useNavigation } from "react-router-dom";
import Loader from "./Loader";

function AppLayout() {
  const navigation = useNavigation();
  const isLoading = navigation.state === "loading";

  return (
    <div className="grid grid-rows-[auto_1fr]">
      {isLoading && <Loader />}

      <Header />
      {/* 
        <main className="mx-auto max-w-3xl"> */}
      <div className="py-.5">
        <Outlet />
      </div>
      {/* </main>
       */}
    </div>
  );
}

export default AppLayout;
