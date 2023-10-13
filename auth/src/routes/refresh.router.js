const { Router } = require("express");
const { JWT_REFRESH_SECRET } = require("../credentials");
const jwt = require("jsonwebtoken");
const getTokens = require("../utils");

const router = new Router();

router.post("/refresh", (req, res) => {
  const refreshToken = req.cookies.refreshToken;

  if (!refreshToken) {
    return res.sendStatus(401);
  }

  jwt.verify(refreshToken, JWT_REFRESH_SECRET, (err, user) => {
    if (err) {
      return res.sendStatus(403);
    }

    const tokens = getTokens(user.userData);

    res.json({ ...tokens, user: user.userData });
  });
});

module.exports = { refreshRouter: router };
