'use strict';

var util = require('util');
const {
  CANNOT_VERIFY_TOKEN,
  DATA_NOT_FOUND,
  DISABLED_USER,
  EXCEPTION_DATABASE,
  HAVE_NOT_PERMISSION_ACCESS_DOJO,
  INVALID_USER,
  NOT_PERMISSION_CODE,
  SUCCESS_CODE,
  INVALID_CODE,
  getResponseMessage
} = require("../helpers/exception");
const {LiteQuestion} = require('../models/lite-question');
const {generateToken} = require('../helpers/token');
let config = require('../../config/development.json');

const MODEL = "Question";

module.exports = {
  questionQuery,
  questionCreate,
  questionUpdate,
  questionRemove
};

function questionQuery(request) {
  return new Promise(((resolve, reject) => {
    LiteQuestion.find(request, (err, questions) => {
      if (err) {
        console.log(err);
        return resolve({status: INVALID_CODE, response: EXCEPTION_DATABASE});
      }
      if (questions === null)
        return resolve({status: INVALID_CODE, response: DATA_NOT_FOUND});
      return resolve({
        status: SUCCESS_CODE,
        response: {
          code: SUCCESS_CODE,
          data: questions
        }
      });
    });
  }));
}

function questionCreate(request) {
  return new Promise(((resolve, reject) => {
    LiteQuestion.insertMany(request, (err) => {
      if (err) {
        console.log(err);
        return resolve({status: INVALID_CODE, response: EXCEPTION_DATABASE});
      }
      return resolve({status: SUCCESS_CODE, response: getResponseMessage(MODEL, "Create")});
    });
  }));
}

function questionUpdate(request) {
  return new Promise(((resolve, reject) => {
    LiteQuestion.update({_id: request["_id"]}, request, (err) => {
      if (err) {
        console.log(err);
        return resolve({status: INVALID_CODE, response: EXCEPTION_DATABASE});
      }
      return resolve({status: SUCCESS_CODE, response: getResponseMessage(MODEL, "Update")});
    });
  }));
}

function questionRemove(_id) {
  return new Promise(((resolve, reject) => {
    LiteQuestion.findByIdAndDelete(_id, (err) => {
      if (err) {
        console.log(err);
        return resolve({status: INVALID_CODE, response: EXCEPTION_DATABASE});
      }
      return resolve({status: SUCCESS_CODE, response: getResponseMessage(MODEL, "Remove")});
    });
  }));
}



