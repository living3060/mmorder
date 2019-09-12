<template>
  <div class="center" style>
    <div class="input-for">
      <b-form v-if="show" @submit="onSubmit" @reset="onReset">
        <b-form-group id="input-group-2" class="center" label="管理系统" label-for="input-2" />
        <b-form-group id="input-group-2" label label-for="input-2">
          <b-form-input id="input-2" v-model="form.name" required placeholder="请输入账号"></b-form-input>
        </b-form-group>

        <b-form-input
          id="input-1"
          v-model="form.password"
          type="password"
          required
          placeholder="请输入密码"
        ></b-form-input>
      </b-form>
      <div style="padding:20px;">
        <b-button type="submit" @click="onSubmit" variant="primary">Submit</b-button>
        <b-button type="reset" @click="onReset" variant="danger">Reset</b-button>
      </div>
    </div>
  </div>
</template>
<script>

import api from '../axios/api.js'

export default {
  name: "login",
  data() {
    return {
      form: {
        name: "",
        password: ""
      },
      show: true
    };
  },
  methods: {
    onSubmit() {
        const info= {
            usernmme: this.form.usernmme
        }
         console.log(api.loginData, ".............")
        let ret = api.loginData(info)
        console.log(ret, ".............")
    },
    onReset() {
      console.log(this.form);
      this.form.name = "";
      this.form.password = "";
    }
  }
};
</script>
<style scoped>
  body{
    font-size: 1.2rem;
  }
.center {
  color: black;
  padding-top: 10%;
  text-align: center;
  padding-left: 5%;
  padding-right: 5%;
  text-align: center;
}
.input-for {
  max-width: 400px;
  margin: 0 auto;
  height: auto;
}
</style>
