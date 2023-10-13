const { Router } = require("express");
const passport = require("passport");

const router = new Router();

router.get(
  "/github",
  passport.authenticate("github", { scope: ["profile", "user:email"] })
);

router.get(
  "/github/callback",
  passport.authenticate("github", { failureRedirect: "/", session: false }),
  (req, res) => {
    res.cookie("refreshToken", req.user.refreshToken, { httpOnly: true });

    res.json({
      accessToken: req.user.accessToken,
      refreshToken: req.user.refreshToken,
      userData: req.user.userData,
    });
  }
);
module.exports = { githubStrategyRouter: router };
