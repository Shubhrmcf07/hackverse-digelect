const electionModel = require("../models/electionModel");
const voterModel = require("../models/userModel");
const voteModel = require("../models/voteData");
require("../utils/db");
exports.getElectionsList = async (req, res) => {
  try {
    const elections = await electionModel.find();
    return res.json({
      status: 200,
      message: "Elections List",
      data: elections,
    });
  } catch (err) {
    console.log(err);
    return res.json({
      status: 500,
      message: "Internal Server Error",
    });
  }
};

exports.enterElection = async (req, res) => {
  try {
    const election = new electionModel(req.body);
    await election.save();

    return res.json({
      status: 200,
      message: "Election Saved",
    });
  } catch (err) {
    console.log(err);
    return res.json({
      status: 500,
      message: "Internal Server Error",
    });
  }
};

exports.showParties = async (req, res) => {
  try {
    const _id = req.query.electionId;

    const parties = await electionModel
      .find({ _id })
      .select({ partiesContesting: 1 });

    return res.json({
      status: 200,
      message: "Parties List",
      data: parties,
    });
  } catch (err) {
    console.log(err);
    return res.json({
      status: 500,
      message: "Internal Server Error",
    });
  }
};

exports.verifyUser = async (req, res) => {
  try {
    const { voterID, dob, electionId } = req.query;
    const voter = await voterModel.findOne({ voterID });

    if (!voter) {
      return res.json({
        status: 401,
        message: "Invalid Credentials",
      });
    }

    if (voter.DOB != dob) {
      return res.json({
        status: 401,
        message: "Invalid Credentials",
      });
    }

    const election = await electionModel
      .findOne({ _id: electionId })
      .select({ constituency: 1 });

    console.log(election, voter);
    if (!election || election.constituency != voter.constituency) {
      return res.json({
        status: 402,
        message:
          "You are not authorized to cast your vote for the constituency",
      });
    }

    return res.json({
      status: 200,
      message: "Voter Details",
      data: voter,
    });
  } catch (err) {
    console.log(err);
    return res.json({
      status: 500,
      message: "Internal Server Error",
    });
  }
};

exports.addVoter = async (req, res) => {
  try {
    console.log(req.body);
    const voter = new voterModel(req.body);
    await voter.save();

    return res.json({
      status: 200,
      message: "Voter Added",
    });
  } catch (err) {
    console.log(err);
    return res.json({
      status: 500,
      message: "Internal Server Error",
    });
  }
};

exports.vote = async (req, res) => {
  try {
    const { electionId, partyId } = req.body;

    const voteEntry = voteModel.findOne({ electionId, partyId });

    if (!voteEntry) {
      const newVote = new voteModel({ electionId, partyId, numVotes: 1 });

      await newVote.save();

      return res.json({
        status: 200,
        message: "Vote Added Successfully!",
      });
    }

    await voteModel.findOneAndUpdate(
      { electionId, partyId },
      { $inc: { numVotes: 1 } }
    );

    return res.json({
      status: 200,
      message: "Vote Added Successfully!",
    });
  } catch (err) {
    console.log(err);
    return res.json({
      status: 500,
      message: "Internal Server Error",
    });
  }
};
