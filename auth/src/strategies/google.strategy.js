const { Strategy: GoogleStrategy } = require("passport-google-oauth20");
const User = require("../models/User");
const getTokens = require("../utils");
const {
  GOOGLE_CLIENT_ID,
  GOOGLE_CLIENT_SECRET,
  GOOGLE_CALLBACK_URL,
} = require("../credentials");

const googleStrategy = new GoogleStrategy(
  {
    clientID: GOOGLE_CLIENT_ID,
    clientSecret: GOOGLE_CLIENT_SECRET,
    callbackURL: GOOGLE_CALLBACK_URL,
    session: false,
    // passReqToCallback: true,
    scope: ["profile", "user:email"],
  },
  async (googleAccessToken, googleRefreshToken, profile, done) => {
    if (!profile || !profile.id) {
      return done(new Error("Google profile ID not found"), null);
    }

    try {
      let user = await User.findOne({ providerId: profile.id });

      if (user) {
        user.fullName = profile.displayName;
        user.email = profile.emails[0].value;

        await user.save();
      } else {
        user = new User({
          providerName: "google",
          providerId: profile.id,
          fullName: profile.displayName,
          email: profile.emails[0].value,
        });

        await user.save();
      }

      //   console.log(user);

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

module.exports = googleStrategy;
