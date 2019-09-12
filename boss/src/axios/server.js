import Vue from 'vue'
import axios from 'axios'
import router from '@/router'

// 创建axios实例
const service = axios.create({
    timeout: 1000 * 30,
    // 允许跨域带token
    withCredentials: true,
    headers: {
        'Content-Type': 'application/json; charset=utf-8'
    }
})

// request拦截器
service.interceptors.request.use(
    (config) => {
        let tokenLocal = localStorage.token;
        if (tokenLocal === undefined) {
            tokenLocal = "";
        }
        config.headers['token'] = tokenLocal;
        // if(config.url.indexOf("http://result.eolinker.com")) {
        //   config.headers['token'] = Vue.cookie.get('token') ? Vue.cookie.get('token') : '74c8ffe4a59da108f03aa7afc77cc24e';
        // }
        return config
    },
    (error) => {
        router.push({ name: 'login' })
        return Promise.reject(error)
    })

// response拦截器
service.interceptors.response.use(response => {
    console.log(response, "...ssssssssss............");
    if (response.data && response.data.code === '0' && response.data.token) {
        localStorage.setItem("token", response.data.token)
    }
    if (response.data && response.data.code === 401) { // 401, token失效
        Vue.cookie.delete('token')
        router.push({ name: 'login' })
    }
    return response
}, error => {
    //router.push({ name: 'login' })
    console.log(error, ".EEEEEEEE")
    return Promise.reject(error)
})
export default service
