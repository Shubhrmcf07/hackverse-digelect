const router = require("express").Router();
const electControl = require("../controller/elections");

router.get("/getElectionsList", electControl.getElectionsList);
router.post("/addElection", electControl.enterElection);
router.get("/partiesList", electControl.showParties);
router.post("/addVoter", electControl.addVoter);
router.get("/verifyUserCreds", electControl.verifyUser);
module.exports = router;
