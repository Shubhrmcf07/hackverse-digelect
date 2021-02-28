const mongoose = require("mongoose");

const partySchema = mongoose.Schema({
  name: {
    type: String,
    required: true,
  },

  logo: {
    type: String,
    required: true,
  },

  electionsContesting: {
    type: [String],
  },
});
