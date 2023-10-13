const { Strategy: GithubStrategy } = require("passport-github2");
const User = require("../models/User");
const getTokens = require("../utils");
const {
  GITHUB_CLIENT_ID,
  GITHUB_CLIENT_SECRET,
  GITHUB_CALLBACK_URL,
} = require("../credentials");

const githubStrategy = new GithubStrategy(
  {
    clientID: GITHUB_CLIENT_ID,
    clientSecret: GITHUB_CLIENT_SECRET,
    callbackURL: GITHUB_CALLBACK_URL,
    session: false,
    scope: ["profile", "user:email"],
  },
  async function (accessToken, refreshToken, profile, done) {
    if (!profile || !profile.id) {
      return done(new Error("Github profile ID not found"), null);
    }

    try {
      let user = await User.findOne({ providerId: profile.id });

      if (user) {
        user.fullName = profile.displayName;
        user.nickname = profile.username;
        user.email = profile.emails[0].value;

        await user.save();
      } else {
        user = new User({
          providerName: "github",
          providerId: profile.id,
          fullName: profile.displayName,
          nickname: profile.username,
          email: profile.emails[0].value,
        });

        await user.save();
      }

      const { accessToken, refreshToken } = getTokens(user);

      return done(null, {
        accessToken,
        refreshToken,
        userData: user,
      });
    } catch (error) {
      return done(error, null);
    }
  }
);

module.exports = githubStrategy;
