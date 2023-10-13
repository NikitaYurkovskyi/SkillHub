const { Schema, model } = require("mongoose");
const passportLocalMongoose = require("passport-local-mongoose");
const validator = require("validator");

const userSchema = new Schema(
  {
    //название провайдера (есть 3 стратегии)
    providerName: {
      type: String,
      enum: ["local", "google", "github"],
      required: true,
      default: "local",
    },
    //айди гугл аккаунта или гитхаб
    providerId: {
      type: String,
      required: false,
      unique: function () {
        return this.providerName !== "local";
      },
    },
    //то же самое, шо и логин
    nickname: {
      type: String,
      required: function () {
        return this.providerName !== "google";
      },
      unique: true,
      minlength: 3,
    },
    //
    fullName: {
      type: String,
      required: true,
    },
    email: {
      type: String,
      required: true,
      unique: true,
      validate: {
        validator: (value) => validator.isEmail(value),
        message: "Invalid email format",
      },
    },
    birthDate: {
      type: Date,
      validate: {
        validator: function (value) {
          return validator.isBefore(value.toString(), new Date().toString());
        },
        message: "Birth date must be in the past",
      },
    },
    //дата регистрации
    regDate: {
      type: Date,
      default: Date.now,
    },
    userPhoto: {
      type: String,
    },
    isAdmin: {
      type: Boolean,
      default: false,
    },
    isBanned: {
      type: Boolean,
      default: false,
    },
    gender: {
      type: String,
      enum: ["male", "female", "unspecified"],
      default: "unspecified",
    },
    password: {
      type: String,
    },
  },
  {
    toJSON: {
      transform(doc, ret) {
        ret.id = ret._id;
        delete ret._id;
        delete ret.password;
        delete ret.salt;
      },
    },
  }
);

userSchema.set("versionKey", "version");

// Используйте passportLocalMongoose для поддержки локальной стратегии
userSchema.plugin(passportLocalMongoose, {
  usernameField: "email", // Здесь указывается, что поле email будет использоваться вместо username
  hashField: "password",
  errorMessages: {
    MissingPasswordError: "Password is required", // Специфичное сообщение об ошибке
  },
});

const User = model("User", userSchema);

module.exports = User;
