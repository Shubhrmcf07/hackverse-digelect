const mongoose = require("mongoose");

const schema = mongoose.Schema({
  electionTitle: {
    type: String,
    required: true,
    trim: true,
  },

  numDistricts: {
    type: Number,
    required: true,
  },

  constituency: {
    type: String,
  },

  state: {
    type: String,
    required: true,
  },

  fingerPrintImpression: {
    type: String,
    required: true,
  },
});
