const { Router } = require("express");
const { localStrategyRouter } = require("./local-strategy.router");
const { googleStrategyRouter } = require("./google-strategy.router");
const { githubStrategyRouter } = require("./github-strategy.router");
const { refreshRouter } = require("./refresh.router");
const { getUserRouter } = require("./get-user.router");

const router = new Router();

router
  .use("/", localStrategyRouter)
  .use("/", googleStrategyRouter)
  .use("/", githubStrategyRouter)
  .use("/", refreshRouter)
  .use("/", getUserRouter);

module.exports = router;
