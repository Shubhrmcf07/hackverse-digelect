const mongoose = require("mongoose");

const voteSchema = mongoose.Schema({
  electionId: {
    type: String,
    required: true,
  },

  partyId: {
    type: String,
    required: true,
  },

  numVotes: {
    type: Number,
    required: true,
  },
});

module.exports = mongoose.model("votes", voteSchema);
