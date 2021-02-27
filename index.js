require("./utils/db");
const bodyParser = require("body-parser");
const express = require("express");
const electRoutes = require("./routes/elections");
const app = express();
app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());

app.use("/elections", electRoutes);

app.get("/", async (req, res) => {
  return res.json({
    message: "Reached API",
  });
});

app.listen(8130, () => console.log("Fine"));
