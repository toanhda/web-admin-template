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
const {LiteUser} = require('../models/lite-user');
const {generateToken} = require('../helpers/token');
let config = require('../../config/development.json');
const axios = require('axios');
const https = require('https');
const MODEL = "User";

module.exports = {
  userLogin,
  userQuery,
  userCreate,
  userUpdate,
  userRemove
};

function userLogin(params, username) {
  return new Promise(((resolve, reject) => {
    axios
      .post(config.urlVeriryOTP, params, {
        httpsAgent: new https.Agent({
          rejectUnauthorized: false
        })
      })
      .then(function (response) {
        if (response.data.status !== "true") {
          return resolve({status: INVALID_CODE, response: INVALID_USER});
        }
        let username = "toanhda";
        let role = 1;
        let token = generateToken(username, role);
        return resolve({
          status: SUCCESS_CODE,
          response: {
            code: SUCCESS_CODE,
            data: {
              username: username,
              role: role,
              token: token
            }
          }
        });

        // LiteUser.findOne({username}, (err, user) => {
        //   if (err) {
        //     console.log(err);
        //     return resolve({status: INVALID_CODE, response: EXCEPTION_DATABASE});
        //   }
        //
        //   if (!user)
        //     return resolve({status: NOT_PERMISSION_CODE, response: HAVE_NOT_PERMISSION_ACCESS_DOJO});
        //   if (!user.isActive)
        //     return resolve({status: NOT_PERMISSION_CODE, response: DISABLED_USER});
        //
        //   let token = generateToken(user.username, user.role);
        //   return resolve({
        //     status: SUCCESS_CODE,
        //     response: {
        //       code: SUCCESS_CODE,
        //       data: {
        //         username: username,
        //         role: user.role,
        //         token: token
        //       }
        //     }
        //   });
        // });
      })
      .catch(function (error) {
        return resolve({status: INVALID_CODE, response: CANNOT_VERIFY_TOKEN});
      });
  }));
}

function userQuery(request) {
  return new Promise(((resolve, reject) => {
    LiteUser.find(request, (err, users) => {
      if (err) {
        console.log(err);
        return resolve({status: INVALID_CODE, response: EXCEPTION_DATABASE});
      }
      if (users === null)
        return resolve({status: INVALID_CODE, response: DATA_NOT_FOUND});
      return resolve({
        status: SUCCESS_CODE,
        response: {
          code: SUCCESS_CODE,
          data: users
        }
      });
    });
  }));
}

function userCreate(request) {
  return new Promise(((resolve, reject) => {
    LiteUser.insertMany(request, (err) => {
      if (err) {
        console.log(err);
        return resolve({status: INVALID_CODE, response: EXCEPTION_DATABASE});
      }
      return resolve({status: SUCCESS_CODE, response: getResponseMessage(MODEL, "Create")});
    });
  }));
}

function userUpdate(request) {
  return new Promise(((resolve, reject) => {
    LiteUser.update({_id: request["_id"]}, request, (err) => {
      if (err) {
        console.log(err);
        return resolve({status: INVALID_CODE, response: EXCEPTION_DATABASE});
      }
      return resolve({status: SUCCESS_CODE, response: getResponseMessage(MODEL, "Update")});
    });
  }));
}

function userRemove(_id) {
  return new Promise(((resolve, reject) => {
    LiteUser.findByIdAndDelete(_id, (err) => {
      if (err) {
        console.log(err);
        return resolve({status: INVALID_CODE, response: EXCEPTION_DATABASE});
      }
      return resolve({status: SUCCESS_CODE, response: getResponseMessage(MODEL, "Remove")});
    });
  }));
}



