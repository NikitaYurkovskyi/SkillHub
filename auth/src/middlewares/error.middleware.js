module.exports = (error, req, res, next) => {
  //   console.log(error);

  if (error.code === 11000) {
    // Обработка ошибки дубликата nickname
    return res.status(409).json({ message: error.message });
  }

  return res.status(500).json({ message: "Internal Server Error" });
};
