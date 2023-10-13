const { Router } = require("express");
const passport = require("passport");
const authenticateAccessTokenMiddleware = require("../middlewares/authenticate-access-token.middleware");

const router = new Router();

router.get("/user", authenticateAccessTokenMiddleware, (req, res) => {
  res.json(req.user.userData);
});

module.exports = { getUserRouter: router };
