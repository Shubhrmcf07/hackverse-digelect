const mongoose = require("mongoose");

const voterSchema = mongoose.Schema({
  voterID: {
    type: String,
    required: true,
    trim: true,
  },

  name: {
    type: String,
    required: true,
    trim: true,
  },

  DOB: {
    type: String,
    required: true,
  },

  photoURL: {
    type: String,
    required: true,
  },

  gender: {
    type: Number,
    required: true,
  },

  address: {
    type: String,
    required: true,
  },
  constituency: {
    type: String,
    required: true,
  },

  district: {
    type: String,
    required: true,
  },

  state: {
    type: String,
    required: true,
  },

  phone: {
    type: Number,
    // validate: {
    //   validator: function (v) {
    //     return /d{10}/.test(v);
    //   },
    //   message: "Enter valid 10 digit number",
    // },
  },

  // fingerImpression: {
  //   type: String,
  //   required: true,
  //   trim: true,
  // },
});

module.exports = mongoose.model("voter", voterSchema);
