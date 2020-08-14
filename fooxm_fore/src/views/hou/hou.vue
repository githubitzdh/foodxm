<template>
  <el-container style="height:100%">
    <!-- 顶部区域start -->
    <el-header>
      <el-row>
        <el-col :span="6">
          <div class="grid-content bg-purple">
            <img src="~@/assets/2.png" alt="无法显示图片" />
          </div> 
          
          <!-- <div class="a">
 <el-link type="info" href="/hlogin"><span style=" font-size: 15px;">后台管理</span> </el-link> 
   </div> -->
        </el-col>
        <el-col :span="16" class="middle">
          <h2>零食销售系统管理员中心</h2>
        </el-col>
        <el-col :span="2">
          <div class="grid-content bg-purple">
            <el-button type="info" @click="logout">退出</el-button>
          </div>
           <div class="grid-content bg-purple">
           你好! {{meinfo}}
          </div>
        </el-col>
      </el-row>
    </el-header>
    <!-- 顶部区域end -->
    <el-container>
      <!-- 侧边栏start -->
      <!-- <el-aside width="200px"> -->
      <el-aside :width="isCollapse?'64px':'200px'">
        <div class="toggle-button" @click="toggleCollapse">|||</div>
        <!-- 在导航菜单寻找
      unique-opened 是否只保持一个子菜单的展开
      collapse 是否水平折叠收起菜单
      collapse-transition 是否开启折叠动画(快速折叠效果更好)
        -->
        <el-menu
          default-active="2"
          class="el-menu-vertical-demo"
          router
          background-color="rgb(40, 84, 109)"
          text-color="#fff"
          active-text-color="#558FF2"
          unique-opened
          :collapse="isCollapse"
          :collapse-transition="false"
        >
          <el-submenu index="0">
            <template slot="title">
              <i class="el-icon-s-tools"></i>
              <span>管理员管理</span>
            </template>
            <el-menu-item index="/oneinfo">
              <template slot="title">
                <i class="el-icon-menu"></i>
                <span>个人信息</span>
              </template>
            </el-menu-item>

            <el-menu-item index="/uppassword">
              <template slot="title">
                <i class="el-icon-menu"></i>
                <span>修改密码</span>
              </template>
            </el-menu-item>

            <el-menu-item index="/adminlist">
              <template slot="title">
                <i class="el-icon-menu"></i>
                <span>管理员列表</span>
              </template>
            </el-menu-item>
          </el-submenu>

          <el-submenu index="1">
            <template slot="title">
              <i class="el-icon-s-check"></i>
              <span>用户管理</span>
            </template>

            <el-menu-item index="/huiyuanlist">
              <template slot="title">
                <i class="el-icon-menu"></i>
                <span>会员列表</span>
              </template>
            </el-menu-item>
          </el-submenu>

          <el-submenu index="2">
            <template slot="title">
              <i class="el-icon-s-shop"></i>
              <span>商品管理</span>
            </template>
            <el-menu-item index="/productlist">
              <template slot="title">
                <i class="el-icon-menu"></i>
                <span>商品列表</span>
              </template>
            </el-menu-item>
            <el-menu-item index="/categorylist">
              <template slot="title">
                <i class="el-icon-menu"></i>
                <span>商品分类</span>
              </template>
            </el-menu-item>
          </el-submenu>

          <el-submenu index="3">
            <template slot="title">
              <i class="el-icon-s-data"></i>
              <span>订单管理</span>
            </template>
            <el-menu-item index="/orderlist">
              <template slot="title">
                <i class="el-icon-menu"></i>
                <span>订单列表</span>
              </template>
            </el-menu-item>
             <el-menu-item index="/addorder">
              <template slot="title">
                <i class="el-icon-menu"></i>
                <span>添加订单</span>
              </template>
            </el-menu-item>
          </el-submenu>
        </el-menu>
      </el-aside>
      <!-- 侧边栏end -->
      <el-container>
        <!-- 主体区域start -->
        <el-main>
          <router-view></router-view>
        </el-main>
        <!-- 主体区域end -->
      </el-container>
    </el-container>
  </el-container>
</template>

<script>
import { searchbyuserid } from "@/api";
export default {
  data() {
    return {
      // 是否折叠
      isCollapse: false,
      meinfo:""
    };
  },
  created() {
        let userstr = sessionStorage.getItem("user");
    let user = JSON.parse(JSON.stringify(userstr));
    this.uid = user;
    this.welcomeinfo(user);
  },
  methods: {
    async welcomeinfo(uid) {
      let { data: e } = await searchbyuserid(uid);
      this.meinfo = e.data.username;
    },
    logout() {
      // 清空token
      sessionStorage.clear();
      //跳转到登录页
      this.$router.push("/hlogin");
    },
    // 点击按钮,切换菜单按钮折叠与展开
    toggleCollapse() {
      this.isCollapse = !this.isCollapse;
    }
  }
};
</script>

<style>
.el-header {
  background-color: lightskyblue;
  color: #333;
  /* text-align: center; */
}

.el-aside {
  background-color: rgba(37, 94, 148, 0.795);
  color: rgba(210, 202, 226, 0.795);
}
/* 去除左栏的突出边框线(点击下拉时不对齐) */
.el-menu {
  border-right: none;
}
.el-main {
  background-color: #8caccc;
  color: rgb(51, 51, 51);
    text-align: left;
}

/* 头部样式 */
.middle {
  line-height: 60px;
  text-align: center;
}

.loginout {
  line-height: 60px;
  text-decoration: none;
}
/* 
折叠位置
letter-spacing: 0.2em;  |||三个竖线之间的间距
 cursor: pointer;   当鼠标放到|||位置变小手图标
 */
.toggle-button {
  background-color: #4a5064;
  font-size: 10px;
  line-height: 24px;
  text-align: center;
  letter-spacing: 0.2em;
  cursor: pointer;
}
</style>

