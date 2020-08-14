<template>
  <div class="login">
    <div class="login-box">
      <h1>馋味时光-个人注册</h1>已有账号？
      <el-link type="primary" href="/login">请登录></el-link>

      <el-form ref="reForm" :model="reForm" label-width="80px" status-icon :rules="rules">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="reForm.username" @blur.prevent="checknameFn"></el-input>
        </el-form-item>

        <el-form-item label="密码" prop="password">
          <el-input v-model="reForm.password" show-password></el-input>
        </el-form-item>

        <el-form-item label="确认密码" prop="chpassword">
          <el-input v-model="reForm.chpassword" show-password></el-input>
        </el-form-item>

        <el-form-item label="姓名" prop="tname">
          <el-input v-model="reForm.tname"></el-input>
        </el-form-item>

        <el-form-item label="性别" prop="sex">
          <el-radio-group v-model="reForm.sex">
            <el-radio label="男"></el-radio>
            <el-radio label="女"></el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item label="年龄" prop="age">
          <el-input v-model.number="reForm.age"></el-input>
        </el-form-item>

        <el-form-item label="手机号码" prop="telephone">
          <el-input v-model="reForm.telephone"></el-input>
        </el-form-item>

        <el-form-item label="联系地址" prop="addrs">
          <el-input v-model="reForm.addrs"></el-input>
        </el-form-item>

        <el-form-item label="说明" prop="desc">
          <el-input type="textarea" v-model="reForm.desc" maxlength="50" show-word-limit></el-input>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="regFn('reForm')">注册</el-button>
          <el-button @click="resetForm('reForm')">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import { register, checkname } from "@/api";
export default {
  data() {
     let ckname = (rule, value, callback) => {
      if (!value) {
        callback(new Error("请输入用户名!"));
      } 
     setTimeout(() => {
        if(this.reForm.username!=this.cmessage){
         callback();
          }
           callback(new Error("用户名已存在,请重新输入!"));
      }, 800);
    };


    var rename = (rule, value, callback) => {
      const realnameReg = /^([a-zA-Z0-9\u4e00-\u9fa5\·]{1,10})$/;
      if (!value) {
        return callback(new Error("真实姓名不能为空!!"));
      }
      setTimeout(() => {
        if (!realnameReg.test(value)) {
          return callback(new Error("您的真实姓名格式错误,请输入英文或汉字!"));
        } else {
          callback();
        }
      }, 100);
    };
    var ps = (rule, value, callback) => {
      if (!value) {
        callback(new Error("请输入密码"));
      } else {
        if (this.reForm.chpassword !== "") {
          this.$refs.reForm.validateField("chpassword");
        }
        callback();
      }
    };
    var ps2 = (rule, value, callback) => {
      if (!value) {
        callback(new Error("请再次输入密码"));
      } else if (value !== this.reForm.password) {
        callback(new Error("两次输入密码不一致!"));
      } else {
        callback();
      }
    };
    var checkAge = (rule, value, callback) => {
      if (!value) {
        return callback(new Error("请输入年龄"));
      }
      setTimeout(() => {
        if (!Number.isInteger(value)) {
          callback(new Error("请输入数字值"));
        } else {
          if (value < 16) {
            callback(new Error("必须年满16岁"));
          } else {
            callback();
          }
        }
      }, 500);
    };

    return {
      cmessage: "",
      reForm: {},
      rules: {
        username: [
          { required: true, validator: ckname, trigger: "blur" },
          { min: 2, max: 7, message: "长度在 2 到 7 个字符", trigger: "blur" }
        ],
        tname: [
          { required: true, validator: rename, trigger: "blur" },
          { min: 2, max: 7, message: "长度在 2 到 7 个字符", trigger: "blur" }
        ],

        password: [
          { required: true, validator: ps, trigger: "blur" },
          {
            min: 5,
            max: 25,
            message: "长度在 5 到 25 个字符",
            trigger: "blur"
          },
          {
            pattern: /^(\w){5,25}$/,
            message: "只能输入5-25个字母、数字、下划线"
          }
        ],
        chpassword: [{ required: true, validator: ps2, trigger: "blur" }],
        utype: [{ required: true, message: "请选择类型", trigger: "change" }],
        sex: [{ required: true, message: "请选择性别", trigger: "change" }],
        telephone: [
          { required: true, message: "请输入手机号", trigger: "blur" },
          {
            validator: function(rule, value, callback) {
              if (/^1[34578]\d{9}$/.test(value) == false) {
                callback(new Error("手机号码格式错误!"));
              } else {
                callback();
              }
            },
            trigger: "blur"
          }
        ],
        addrs: [{ required: true, message: "请输入联系地址", trigger: "blur" }],
        age: [{ required: true, validator: checkAge, trigger: "blur" }]
      }
    };
  },
  methods: {
   async checknameFn(){
      let { data: c } = await checkname(this.reForm);
        this.cmessage=c.message
      
    },
    regFn(formName) {
      this.$refs[formName].validate(async valid => {
        //判断是否输入内容
        if (valid) {
          //发送axios
            let { data: baseresult } = await register(this.reForm);
            if (baseresult.code == 1) {
              //提示
              this.$message.success(baseresult.message);
              //跳转到首页
              this.$router.push("/login");
            }
       
          //处理

          //如果没输入就提交,会返回false
        } else {
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
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
  background-image: url("~@/assets/8.png");
}

.login .login-box {
  /* 还有一个问题，就是好像下面还有一张，有种重叠了的感觉。应该是一张图不足以填满整个表格。优化代码 */
  background: url("../assets/12.jpg") no-repeat;
  /* 调整图片大小 */
  background-size: cover;
  /*  border-top: 10px solid #051e27; 上边框 ：大小 样式 颜色*/
  height: 850px;
  width: 500px;
  padding: 30px; /* 内边距：上 右 下 左*/
  text-align: center; /* 文本居中 */
  position: absolute; /* 定位，可以让指定区域悬浮 */
  background-color: #fff; /* 背景色 */
  box-shadow: 0 0 10px #ccc; /* 阴影 */
}
.login .login-box h1 {
  /**h1标签字体 */
  margin: 55px 0 5px 0; /* 外边距 上 右 下 左*/
  font-size: 26px; /* 字体大小 */
  color: #0397fb; /* 字体颜色 */
}

.login .center-banner {
  height: 200px;
  background-color: #8a9196;
  width: 100%;
}
</style>