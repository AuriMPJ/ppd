app = require('express')()
server = require('http').createServer(app).listen(process.env.SERVER_PORT)
io = require('socket.io').listen(server)

module.exports = function emit(req, res, next) {
    var notify = req.query.notification || process.env.DEFAULT_NOTIFICATION;
    if (notify != '') {
        io.emit('notification', notify);
        next();
    } else {
        next();
    }
};