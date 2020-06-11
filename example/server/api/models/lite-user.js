const mongoose = require('mongoose');
const Schema =  mongoose.Schema;

const LiteUserSchema = new Schema({
  username:{
    type:String,
    unique: true,
    required:true
  },
  // 1: Admin
  // 2: User
  role:{
    type: Number,
    required:true
  },
  createdTime:{
    type:Number,
    required:true
  },
  isActive:{
    type:Boolean,
    required:true
  }
});

const LiteUser = mongoose.model("LiteUser", LiteUserSchema);

module.exports = {
  LiteUser,
  LiteUserSchema
};
