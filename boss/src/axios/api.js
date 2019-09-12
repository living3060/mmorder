import service from "./server.js"

const base="http://127.0.0.1:8080"
export default{
    loginData:params=>{
        return service({
            url: base+'/user/login',
            method: 'post',
            data:params
        })
    }
}

