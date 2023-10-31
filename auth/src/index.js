const express = require("express");
const passport = require("passport");
const mongoose = require("mongoose");
const { MONGO_URL } = require("./credentials");
const googleStrategy = require("./strategies/google.strategy");
const localStrategy = require("./strategies/local.strategy");
var cookieParser = require("cookie-parser");
const githubStrategy = require("./strategies/github.strategy");
const swaggerJsdoc = require("swagger-jsdoc");
const swaggerUi = require("swagger-ui-express");
const errorMiddleware = require("./middlewares/error.middleware");
const router = require("./routes");
const options = require("./swagger.options");
const cors = require("cors"); // Add this line

const app = express();

const specs = swaggerJsdoc(options);
app.use(
  "/api/auth/docs",
  swaggerUi.serve,
  swaggerUi.setup(specs, { explorer: true })
);

app.use(cors()); // Add this line
app.use(express.json());
app.use(cookieParser());
app.use(passport.initialize());

passport.use(googleStrategy);
passport.use(localStrategy);
passport.use(githubStrategy);

app.use("/api/auth", router);
app.use(errorMiddleware);
const PORT = 3000;

const start = async () => {
  await mongoose.connect(MONGO_URL, {
    useNewUrlParser: true,
    useUnifiedTopology: true,
  });
  app.listen(PORT, () => {
    console.log(`Auth service is running at ${PORT} (v1)`);
  });
};

start();
