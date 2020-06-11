import React from "react";
import {Redirect, Route, Switch} from "react-router-dom";
import ability from "../../authorization/ability";

const Main = ({match}) => (
  <Switch>
    <Redirect exact from={`${match.url}/`} to={`${match.url}`}/>
  </Switch>
);

export default Main;
