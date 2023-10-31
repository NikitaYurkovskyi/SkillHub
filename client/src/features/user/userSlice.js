import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import getAxiosInstance from "../../api/interceptors";
import { AUTH_SERVICE_URL } from "../../credentials";

export const fetchUserData = createAsyncThunk(
  "user/fetchUserData",
  async function () {
    if (!localStorage.getItem("accessToken")) return null;

    const response = await getAxiosInstance(AUTH_SERVICE_URL).get("/user");
    return response.data || null;
  }
);

const initialState = {
  userData: null,
  status: "idle",
};

const userSlice = createSlice({
  name: "user",
  initialState,
  reducers: {
    clearUserData: (state) => {
      state.userData = null;
    },
    setUserData: (state, action) => {
      state.userData = action.payload;
    },
  },
  extraReducers: (builder) =>
    builder
      .addCase(fetchUserData.pending, (state) => {
        state.status = "loading";
      })
      .addCase(fetchUserData.fulfilled, (state, action) => {
        // console.log(action.payload);
        state.userData = action.payload;
        state.status = "idle";
      })
      .addCase(fetchUserData.rejected, (state) => {
        state.status = "error";
      }),
});

export const { clearUserData, setUserData } = userSlice.actions;

export const getIsLoggedIn = (state) => !!state.user.userData;

export default userSlice.reducer;
