const Sequelize = require('sequelize');
const Item = require('./item');

const env = process.env.NODE_ENV || 'development';
const config = require('../config/config.json')[env];
const db = {};

let sequelize = new Sequelize(config.database, config.username, config.password, config);

db.sequelize = sequelize;
db.Sequelize = Sequelize;
db.Item = Item;
Item.init(sequelize);

module.exports = db;
