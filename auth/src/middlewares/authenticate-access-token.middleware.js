const { JWT_ACCESS_SECRET } = require("../credentials");
const jwt = require("jsonwebtoken");

const authenticateAccessTokenMiddleware = (req, res, next) => {
  const authHeader = req.headers["authorization"];
  const token = authHeader && authHeader.split(" ")[1];

  if (!token) {
    return res.sendStatus(401);
  }

  jwt.verify(token, JWT_ACCESS_SECRET, (err, user) => {
    if (err) {
      return res.sendStatus(403);
    }

    if (user.password) {
      delete user.password;
    }

    req.user = user;
    next();
  });
};

module.exports = authenticateAccessTokenMiddleware;
