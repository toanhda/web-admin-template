const mongoose = require('mongoose');
const Schema =  mongoose.Schema;

const LiteQuestionSchema = new Schema({
  // 1: OOP
  // 2: Data structure & algorithm
  // 3: Database
  // 4: Network & Operating system
  category:{
    type:Number,
    required:true
  },
  // 1: Easy
  // 2: Medium
  // 3: Hard
  level:{
    type:Number,
    required:true
  },
  content:{
    type:String,
    required:true
  },
  hint:{
    type:String,
  },
  hash:{
    type: String,
    unique: true,
    required:true
  },
  campaign:{
    type:String
  }
});

const LiteQuestion = mongoose.model("LiteQuestion", LiteQuestionSchema);

module.exports = {
  LiteQuestion,
  LiteQuestionSchema
};
