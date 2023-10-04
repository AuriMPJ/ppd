var router = require('express').Router();

router.route('/notify')
    .get(function (req, res) {
        res.json({
            message: process.env.NOTIFICATION_TEST_MESSAGE
        });
    });

module.exports = router;