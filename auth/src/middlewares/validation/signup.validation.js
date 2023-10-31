const { body } = require("express-validator");

const signupValidation = () => {
  return [
    body("username").isString().notEmpty().isLength({ min: 3 }),
    body("password").isString().notEmpty().isLength({ min: 4 }),
    // body("fullName").isString().notEmpty(),
    body("email").isEmail(),
    // body("birthDate")
    //   .isISO8601()
    //   .custom((value) => {
    //     return validator.isBefore(value, new Date().toISOString());
    //   })
    //   .withMessage("Birth date must be in the past"),
  ];
};

module.exports = signupValidation;
