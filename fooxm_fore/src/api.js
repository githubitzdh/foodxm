// 1 导入axios
import axios from 'axios'
// 1.1 导入element message组件
import { Message } from 'element-ui'
// 1.2 导入mockjs
import Mock from 'mockjs'


// 2 设置相关参数：基本路径、超时时间 等
axios.defaults.baseURL = 'http://localhost:10010/api'
axios.defaults.timeout = 3000

// 3 配置拦截器
axios.interceptors.response.use(response => {
    // 放行
    return response
}, error => {
    // 错误判断
   if(error.response.status==403){
       router.push('/login')
   }
    return Promise.reject(error)
})


// 3 配置拦截器
axios.interceptors.request.use(response=>{
    //每一次请求中,请求头需要携带token
    // 1) 获得token
let token=  sessionStorage.getItem('token')
    //2.如果有设置
 if(token){
     response.headers.authorization=token
 }
 //放行
 return response
},error=>{})





/** 登录 */
export function login( admin){
    return axios.post('/userservice/user/login', admin )
}

export function hlogin( admin){
    return axios.post('/userservice/user/hlogin', admin )
}

/** 注册会员 */
export function register(admin){
    return axios.post('/userservice/user/register', admin)
}

/**检测用户名 */
export function checkname(admin){
    return axios.post('/userservice/user/checkname', admin)
}

/**查询所有会员 */
export function userlist(params){
    return axios.post('/userservice/user/search',params)
}

/**查询所有管理员 */
export function adminlist(params){
    return axios.post('/userservice/user/searchadmin',params)
}

/** 添加会员 */
export function adduser(admin){
    return axios.post('/userservice/user/adds', admin)
}


/** 根据id删除会员 */
export function deluser(id){
    return axios.delete('/userservice/user/del/'+id)
}




/** 根据id查询会员详情 */
export function searchbyuserid(id){
    return axios.get('/userservice/user/searchbyid/'+id)
}


/** 修改会员 */
export function upuser(admin){
    return axios.put('/userservice/user', admin)
}







/**查询所有商品 */
export function productlist(params){
    return axios.post('/productservice/product/page',params)
}

/**查询所有分类 */
export function categorylist(){
    return axios.get('/productservice/category')
}

/** 添加商品 */
export function addproduct(product){
    return axios.post('/productservice/product/addp', product)
}


/** 根据id删除商品*/
export function delproduct(id){
    return axios.delete('/productservice/product/del/'+id)
}




/** 根据id查询商品详情 */
export function searchbyproductid(id){
    return axios.get('/productservice/product/searchbyid/'+id)
}


/** 修改商品 */
export function upproduct(product){
    return axios.put('/productservice/product', product)
}



// ---------------------------------

/**查询所有分类并分页 */
export function categorypage(params){
    return axios.post('/productservice/category/page',params)
}

/** 添加分类 */
export function addcategory(category){
    return axios.post('/productservice/category/addc', category)
}



/** 根据id删除分类*/
export function delcategory(id){
    return axios.delete('/productservice/category/del/'+id)
}




/** 根据id查询分类详情 */
export function searchbycategoryid(id){
    return axios.get('/productservice/category/searchbyid/'+id)
}


/** 修改分类 */
export function upcategory(category){
    return axios.put('/productservice/category', category)
}



// ----------------------------------------------------------

/**查询所有订单并分页 */
export function orderpage(params){
    return axios.post('/userservice/user/opage',params)
}

/** 添加订单 */
export function addorder(order){
    return axios.post('/userservice/user/addo', order)
}

/**查询所有用户 */
export function selectuserall(){
    return axios.get('/userservice/user/selectuserall')
}


/**查询所有商品 */
export function selectproductall(){
    return axios.post('/productservice/product/spall')
}


/** 根据id删除订单*/
export function delorder(id){
    return axios.delete('/userservice/user/'+id)
}




/** 根据id查询订单详情 */
export function searchbyorderid(id){
    return axios.get('/userservice/user/searchbyoid/'+id)
}


/** 修改订单 */
export function uporder(order){
    return axios.put('/userservice/user/up', order)
}



/** 我的订单*/
export function qorderpage(params,id){
    return axios.post('/userservice/user/oneopage/'+id,params)
}


/** 用户下单 */
export function qaddorder(order,id){
    return axios.post('/userservice/user/oneadd/'+id, order)
}


/* 查看订单包含商品信息 */
export function selectLookorder(id){
    return axios.get('/productservice/product/selectLookorder/'+id)
}


/* 处理订单状态 */
export function upstatue(id){
    return axios.put('/userservice/user/upstatue/'+id)
}
