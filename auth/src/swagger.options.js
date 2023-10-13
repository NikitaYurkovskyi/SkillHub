const options = {
  definition: {
    openapi: "3.0.0",
    info: {
      title: "Auth service documentation",
      version: "1.0.0",
      description:
        "Authorization in the system is implemented through two tokens (an access that is required to access protected resources and refresh) and three strategies (local (with login and password), Google and GitHub)",
      license: {
        name: "GitHub repository",
        url: "https://github.com/emileyski/atark",
      },
      contact: {
        name: "Telegram @emileyski",
        url: "https://t.me/emilevi4",
        // email: "info@email.com",
      },
    },
    servers: [
      {
        url: "http://localhost:3000",
      },
    ],
  },
  apis: ["./src/routes/swagger-doc/*.js"],
};

module.exports = options;
