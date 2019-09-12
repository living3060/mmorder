'use strict'
const merge = require('webpack-merge')
const prodEnv = require('./prod.env')
const BASE_URL="/api/"
module.exports = merge(prodEnv, {
  NODE_ENV: '"development"'
})
