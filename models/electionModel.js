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

  partiesContesting: [
    {
      partyId: {
        type: String,
        required: true,
      },

      numSeats: {
        type: Number,
        required: true,
      },
    },
  ],
});

module.exports = mongoose.model("upcoming elections", schema);
