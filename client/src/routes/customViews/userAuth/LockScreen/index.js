import React from "react";
import {Avatar, Button, Form, Input} from "antd";
import IntlMessages from "util/IntlMessages";

const FormItem = Form.Item;

const LockScreen = (props) => {

  const handleSubmit = (e) => {
    e.preventDefault();
    props.form.validateFields((err, values) => {
      if (!err) {
        console.log('Received values of form: ', values);
      }
    });
  };

  const {getFieldDecorator} = props.form;

  return (
    <div className="gx-login-container">
      <div className="gx-login-content gx-text-center">

        <div className="gx-login-header">
          <Avatar shape="circle" className="gx-size-120" src={"https://via.placeholder.com/150x150"}/>
        </div>
        <div className="gx-mb-4">
          <h3>John Smith</h3>
          <p><IntlMessages id="appModule.enterPasswordUnlock"/></p>
        </div>

        <Form onSubmit={handleSubmit} className="gx-login-form gx-form-row0">
          <FormItem>
            {getFieldDecorator('password', {
              rules: [{required: true, message: 'Please input your Password!'}],
            })(
              <Input type="password" placeholder="Password"/>
            )}
          </FormItem>

          <FormItem>
            <Button type="primary" htmlType="submit">
              <IntlMessages id="app.userAuth.unLock"></IntlMessages>
            </Button>
          </FormItem>
        </Form>

      </div>
    </div>
  );
};

const WrappedLockScreenForm = Form.create()(LockScreen);

export default (WrappedLockScreenForm);
