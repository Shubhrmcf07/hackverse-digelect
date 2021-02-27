const express = require("express");

const app = express();

app.get("/", async (req, res) => {
  return res.json({
    message: "Bruh moment",
  });
});

app.listen(8130, () => console.log("Fine"));
