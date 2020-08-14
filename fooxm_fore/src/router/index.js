import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect:'/home'
  },
  {
    path: '/login',
      component: () => import( '../views/Login.vue')
  },
  {
    path: '/hlogin',
      component: () => import( '../views/hou/hlogin.vue')
  },
  {
    path: '/hou',
      component: () => import( '../views/hou/hou.vue'),
      redirect:'/welcome',
      children:[
        {
          path: '/huiyuanlist',
            component: () => import( '../views/hou/huiyuanlist.vue')
        },
        {
          path: '/adminlist',
            component: () => import( '../views/hou/adminlist.vue')
        },
        {
          path: '/welcome',
            component: () => import( '../views/hou/welcome.vue')
        },
        {
          path: '/oneinfo',
            component: () => import( '../views/hou/oneinfo.vue')
        },
        {
          path: '/uppassword',
            component: () => import( '../views/hou/uppassword.vue')
        },
        {
          path: '/productlist',
            component: () => import( '../views/hou/productlist.vue')
        },
        {
          path: '/categorylist',
            component: () => import( '../views/hou/categorylist.vue')
        },
        {
          path: '/orderlist',
            component: () => import( '../views/hou/orderlist.vue')
        }, 
         {
          path: '/addorder',
            component: () => import( '../views/hou/addorder.vue')
        }
      ]
  },
  {
    path: '/home',
      component: () => import('../views/Home.vue'),
      redirect:'/qwelcome',
      children:[
        {
          path: '/qorderlist',
            component: () => import( '../views/qian/qorderlist.vue')
        },
        {
          path: '/qoneinfo',
            component: () => import( '../views/qian/qoneinfo.vue')
        },
        {
          path: '/qwelcome',
            component: () => import( '../views/qian/qwelcome.vue')
        },
        {
          path: '/qaddorder',
            component: () => import( '../views/qian/qaddorder.vue')
        },
        {
          path: '/quppassword',
            component: () => import( '../views/qian/quppassword.vue')
        }

      ]
  },
  {
    path: '/register',
      component: () => import('../views/register.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})




// 设置 全局前置导航守卫，简单权限控制，如果没有登录不允许访问main页面
router.beforeEach((to,from,next)=>{
  // 1 如果是登录页面，放行
  if(to.path == '/login' ){
    next()
    return
  } 
  if(to.path == '/register' ){
    next()
    return
  }
  if(to.path == '/hlogin' ){
    next()
    return
  } 



 
  // 2 如果有token，放行其他页面，否则跳转登录页面
  let token = sessionStorage.getItem('token')
  if(token){
    next()
  } else {
    next('/login')
  }
})
export default router
