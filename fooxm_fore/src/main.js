import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

Vue.config.productionTip = false


/**导入element相关自定义文件 */
import './element'
/** 导入全局css样式 */
import './assets/global.css'

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
