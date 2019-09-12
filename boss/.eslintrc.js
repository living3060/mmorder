// https://eslint.org/docs/user-guide/configuring

module.exports = {
  root: true,
  parserOptions: {
    parser: 'babel-eslint'
  },
  env: {
    browser: true,
  },
  extends: [
    // https://github.com/vuejs/eslint-plugin-vue#priority-a-essential-error-prevention
    // consider switching to `plugin:vue/strongly-recommended` or `plugin:vue/recommended` for stricter rules.
    'plugin:vue/essential',
    // https://github.com/standard/standard/blob/master/docs/RULES-en.md
    'standard'
  ],
  // required to lint *.vue files
  plugins: [
    'vue'
  ],
  // add your custom rules here
  rules: {
    // allow async-await
    'indent': 'off',
    'generator-star-spacing': 'off',
    "singleQuote": false,//把双引号换成单引号
    "space-before-function-paren": 0,
    // allow debugger during development
    'quotes':0,
    'semi':0,
    "comma-dangle":0,
    "no-multiple-empty-lines": 0,
    "no-multi-spaces": 0,
    "space-infix-ops": 0,
    "spaced-comment": 0,
    "space-after-keywords": 0,
    "key-spacing": 0,
    "no-trailing-spaces": 0,
    "arrow-spacing": 0,
    'no-debugger': process.env.NODE_ENV === 'production' ? 'error' : 'off'
  }
}
