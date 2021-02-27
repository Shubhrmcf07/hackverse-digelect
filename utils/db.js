const mongoose = require("mongoose");

module.exports = mongoose
  .connect("mongodb://localhost:27017/hackverse", {
    useFindAndModify: false,
    useNewUrlParser: true,
    useUnifiedTopology: true,
  })
  .then(() => console.log("Connected"));
