const Sequelize = require('sequelize')

module.exports = class Item extends Sequelize.Mode{
    static init(sequelize){
        return super.init({
            itemid:{
                type:Sequelize.INTEGER.UNSIGNED,
                allowNull:false,
                unique : true
            },
            itemname:{
                type:Sequelize.STRING(100),
                allowNull:true
            },
            price:{
                type:Sequelize.INTEGER.UNSIGNED,
                allowNull:true
            },
            description:{
                type:Sequelize.STRING(200),
                allowNull:true
            },
            pictureurl:{
                type:Sequelize.STRING(100),
                allowNull:true
            },
            updatedate:{
                type:Sequelize.STRING(20),
                allowNull:true
            }
        },{
            sequelize,
            timestamp:false,
            underscored:false,
            modelName:Item,
            tableName:'goods',
            paranoid:false,
            charset:'utf8',
            collate:'utf8_general_ci'
        })
    }
}