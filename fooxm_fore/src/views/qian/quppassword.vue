<template>
  <div>
    <!-- 面包屑start -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>会员设置</el-breadcrumb-item>
      <el-breadcrumb-item>修改密码</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 面包屑end -->
    <div style="margin: 20px;"></div>
    <el-card class="box-card">
      <el-form ref="reForm" :model="reForm" label-width="80px" status-icon :rules="rules">
        <el-form-item label="原密码" prop="oldpassword">
          <el-input v-model="reForm.oldpassword" placeholder="请输入原密码" show-password></el-input>
        </el-form-item>

        <el-form-item label="新密码" prop="password">
          <el-input v-model="reForm.password" placeholder="请输入新密码" show-password></el-input>
        </el-form-item>

        <el-form-item label="确认密码" prop="chpassword">
          <el-input v-model="reForm.chpassword" placeholder="请输入确认密码" show-password></el-input>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="regFn('reForm')">提交修改</el-button>
          <el-button @click="resetForm('reForm')">重置</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
import { upuser ,searchbyuserid} from "@/api";
export default {
  data() {
      let old = (rule, value, callback) => {
      if (!value) {
        callback(new Error("请输入原密码!"));
      } 
     setTimeout(() => {
        if(this.reForm.oldpassword!=this.pp){
        callback(new Error("请输入正确的原密码!"));
          }
        callback();
      }, 800);
    };
    let ps = (rule, value, callback) => {
      if (!value) {
        callback(new Error("请输入新密码!"));
      } else {
        if (value === this.reForm.oldpassword) {
          callback(new Error("新密码不能与原密码相同!"));
        }
        callback();
      }
    };
    let ps2 = (rule, value, callback) => {
      if (!value) {
        callback(new Error("请输入确认新密码!"));
      } else if (value !== this.reForm.password) {
        callback(new Error("与新密码不一致!"));
      } else {
        callback();
      }
    };

    return {
      pp:"",
        uid: 1,
      reForm: {},
      rules: {
        oldpassword: [
          { required: true, validator: old, trigger: "blur" }
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
        chpassword: [
          { required: true, validator: ps2, trigger: "blur" }
        ]
      }
    };
  },
   created() {
    let userstr = sessionStorage.getItem("user");
    let user = JSON.parse(JSON.stringify(userstr));
    this.uid = user;
    this.opened(user);
  },
  methods: {
       logout() {
      // 清空token
      sessionStorage.clear();
      //跳转到登录页
      this.$router.push("/login");
    },
    async opened(uid) {
      let { data: e } = await searchbyuserid(uid);
      this.reForm= e.data;
   this.pp = this.reForm.password
        this.reForm.password=""

    },
    regFn(formName) {
      this.$refs[formName].validate(async valid => {
        //判断是否输入内容
        if (valid) {
          //发送axios
          let { data: baseresult } = await upuser(this.reForm);
         if (baseresult.code == 1) {
            //提示
            this.$message.success("修改成功,请重新登录");
            //跳转
          this.logout()
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
</style>