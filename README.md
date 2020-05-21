# Template web admin

## Overview

This is template can help you build project admin so fast

## Architecture

### Front-end

ReactJs

### Back-end

 We have multiple option:
 - Java
    - String boot
 - JS
    - Swagger + expressJS
    - TypeJS
 ## Installation
 
 ### Front-end

#### Requirements

- Nodejs
- NPM

#### Start

```shell
cd client
npm install
npm start
```

### Back-end

#### Java

##### Requirements

- Java 8


##### Build

```shell
cd server/java
mvn clean install
```

#### JS

##### Swagger ExpressJS

```shell
cd server/js/swagger-expressjs
npm install
npm start
```

##### TypeJS

```shell
cd server/js/ts-graphql
npm install
NODE_ENV=production npm run build 
npm run start:env
```


