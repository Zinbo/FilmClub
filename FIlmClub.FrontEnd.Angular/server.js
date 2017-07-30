// modules =================================================
var express = require('express');
var app = express();

// configuration ===========================================

// set our port
var port = process.env.PORT || 8080;

// set the static files location /public/img will be /img for users
app.use(express.static(__dirname + '/build'));

// start app ===============================================
// startup our app at http://localhost:8080
app.listen(port);

// shoutout to the user                     
console.log('Magic happens on port ' + port);

app.get('*', function (req, res) {
    res.sendfile('./build/index.html'); // load our public/index.html file
});

// expose app           
exports = module.exports = app;