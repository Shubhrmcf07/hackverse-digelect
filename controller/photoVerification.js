const exec = require("child_process").exec;
const voterModel = require("../models/userModel");

exports.verifyUser = async (req, res) => {
  try {
    console.log(req.body);
    const { url, photoURL } = req.body;

    const compile = await exec(`python3 match_face.py ${url} ${photoURL}`);

    compile.stdout.on("data", (data) => {
      console.log(data);
    });

    compile.stderr.on("data", (data) => {
      console.log(data);
    });
  } catch (err) {
    console.log(err);
  }
};
