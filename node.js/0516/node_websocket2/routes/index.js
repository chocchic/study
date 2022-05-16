const express = require('express');
const router = express.Router();

// /요청이 오면 websocket을 랜더링 - views/websocket.html을 출력
router.get('/', (req, res) => {
    res.render('websocket');
});

module.exports = router;
