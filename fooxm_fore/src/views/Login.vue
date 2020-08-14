<template>
  <div class="login">
    <div class="login-box">
      <h1>馋味时光-欢迎登录</h1>
      <el-form ref="user" status-icon :model="user" :rules="rules" class="demo-ruleForm">
        <el-form-item  prop="username">
          <el-input
            type="text"
            v-model="user.username"
            prefix-icon="el-icon-user"
            placeholder="请输入用户名"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item  prop="password">
          <el-input
            v-model="user.password"
            type="password"
            prefix-icon="el-icon-lock"
            placeholder="请输入密码"
            autocomplete="off"
            show-password
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="loginFn('user')">登录</el-button>
        </el-form-item>
  <el-link type="primary" href="/register">免费注册</el-link>
          <div> <el-link type="info" href="/hlogin"><span style=" font-size: 10px;">管理员登录</span> </el-link> </div>
      </el-form>
    </div>
  </div>
</template>

<script>
import { login } from "@/api";
export default {
  data() {
    //检验判断非空
    var un = (rule, value, callback) => {
      if (!value) {
        return callback(new Error("用户名不能为空"));
      } else {
        callback();
      }
    };
    var ps = (rule, value, callback) => {
      if (!value) {
        return callback(new Error("密码不能为空"));
      } else {
        callback();
      }
    };
    return {
      user: {
        username: "",
        password: ""
      },
      rules: {
        username: [{ validator: un, trigger: "blur" }],
        password: [{ validator: ps, trigger: "blur" }]
      }
    };
  },
  methods: {
    //登录
    loginFn(formName) {
      this.$refs[formName].validate(async valid => {
        //判断是否输入内容
        if (valid) {
          //发送axios
          let { data: baseresult } = await login(this.user);
          //处理
          if (baseresult.code == 1) {
            //有数据 ,对象-->true
            //登录成功,跳转到home
            //将用户保存session
         sessionStorage.setItem("token",baseresult.other.token)
         sessionStorage.setItem("user",JSON.stringify(baseresult.other.user.uid))
            //提示
            this.$message.success(baseresult.message);
            //跳转到首页
            this.$router.push("/home");
          } else {
            //登录失败
            this.$message.error(baseresult.message);
          }

          //如果没输入就提交,会返回false
        } else {
          return false;
        }
      });
    }
  }
};
</script>

<style>
/* 内容居中 */
.login {
  height: 100%;
  display: flex; /* css3 新取值，允许居中 */
  align-items: center; /* 上下居中*/
  justify-content: center; /* 左右居中 */
  background-image: url("~@/assets/9.png");
}

.login .login-box {
  /* 还有一个问题，就是好像下面还有一张，有种重叠了的感觉。应该是一张图不足以填满整个表格。优化代码 */
  background: url("../assets/11.jpg") no-repeat;
  /* 调整图片大小 */
  background-size: cover;
  /*   border-top: 10px solid #051e27;  上边框 ：大小 样式 颜色 */
  height: 450px;
  width: 450px;
  padding: 30px; /* 内边距：上 右 下 左*/
  text-align: center; /* 文本居中 */
  position: absolute; /* 定位，可以让指定区域悬浮 */
  background-color: #fff; /* 背景色 */
  box-shadow: 0 0 10px #ccc; /* 阴影 */
}
.login .login-box h1 {
  /**h1标签字体 */
  margin: 60px 0 40px 0; /* 外边距 上 右 下 左*/
  font-size: 26px; /* 字体大小 */
  color: #0397fb; /* 字体颜色 */
}

.login .login-box .el-button {
  width: 100%;
}

.login .center-banner {
  height: 200px;
  background-color: #8a9196;
  width: 100%;
}
</style>