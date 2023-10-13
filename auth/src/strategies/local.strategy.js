const passport = require("passport");
const User = require("../models/User");
const getTokens = require("../utils");
const LocalStrategy = require("passport-local").Strategy;

const localStrategy = new LocalStrategy(
  {
    usernameField: "email", // Поменяйте на email, чтобы соответствовать вашей схеме пользователя
    passwordField: "password",
    session: false,
  },
  async (email, password, done) => {
    try {
      const user = await User.findOne({ email });
      if (!user || !user.authenticate(password)) {
        return done(null, false, { message: "Invalid email or password" });
      }

      const { accessToken, refreshToken } = getTokens(user);

      return done(null, {
        accessToken,
        refreshToken,
        userData: user,
      });
    } catch (error) {
      return done(error);
    }
  }
);

module.exports = localStrategy;
