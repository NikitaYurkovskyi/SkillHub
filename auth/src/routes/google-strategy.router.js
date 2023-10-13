const { Router } = require("express");
const passport = require("passport");

const router = new Router();

router.get(
  "/google",
  passport.authenticate("google", { scope: ["profile", "email"] })
);

router.get(
  "/google/callback",
  passport.authenticate("google", { failureRedirect: "/", session: false }),
  (req, res) => {
    res.cookie("refreshToken", req.user.refreshToken, { httpOnly: true });

    res.json({
      accessToken: req.user.accessToken,
      refreshToken: req.user.refreshToken,
      userData: req.user.userData,
    });
  }
);

module.exports = { googleStrategyRouter: router };
