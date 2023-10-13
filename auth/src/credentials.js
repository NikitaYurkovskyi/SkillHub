const GOOGLE_CLIENT_ID =
  "599431370407-v5796hq8t7u2hnpqok7p2s7esk74dg6v.apps.googleusercontent.com";
const GOOGLE_CLIENT_SECRET = "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";

const GITHUB_CLIENT_ID = "71697e8d1d7e4f52ab0d";
const GITHUB_CLIENT_SECRET = "3df2ae417eb3781043bad55d21e3ff71cb5cfebb";

const JWT_ACCESS_SECRET =
  process.env.JWT_ACCESS_SECRET || "some_jwt_access_secret";
const JWT_REFRESH_SECRET =
  process.env.JWT_REFRESH_SECRET || "some_jwt_refresh_secret";
const MONGO_URL =
  "mongodb+srv://emilevi4:QKNlcjPJe7LyHxq6@my-cluster.x0cjd1e.mongodb.net/?retryWrites=true&w=majority";

module.exports = {
  GOOGLE_CLIENT_ID,
  GOOGLE_CLIENT_SECRET,
  GITHUB_CLIENT_ID,
  GITHUB_CLIENT_SECRET,
  JWT_ACCESS_SECRET,
  JWT_REFRESH_SECRET,
  MONGO_URL,
};
