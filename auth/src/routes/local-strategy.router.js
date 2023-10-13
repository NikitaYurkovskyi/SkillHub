const { Router } = require("express");
const passport = require("passport");
const User = require("../models/User");
const getTokens = require("../utils");
const signupValidation = require("../middlewares/validation/signup.validation");

const router = new Router();

router
  .post(
    "/signin",
    passport.authenticate("local", { session: false }),
    (req, res) => {
      // Вход выполнен успешно
      res.cookie("refreshToken", req.user.refreshToken, { httpOnly: true });

      res.json({
        accessToken: req.user.accessToken,
        refreshToken: req.user.refreshToken,
        userData: req.user.userData,
      });
    }
  )
  .post("/signup", signupValidation(), async (req, res, next) => {
    try {
      const { email, nickname, fullName, birthDate, password } = req.body;

      const user = new User({
        email,
        fullName,
        nickname,
        birthDate,
        providerName: "local",
      });
      await user.setPassword(password);
      await user.save();

      const { accessToken, refreshToken } = getTokens(user);

      res.cookie("refreshToken", refreshToken, { httpOnly: true });
      // Регистрация выполнена успешно
      res.status(201).json({ accessToken, refreshToken, userData: user });
    } catch (error) {
      next(error);
    }
  });

module.exports = { localStrategyRouter: router };
