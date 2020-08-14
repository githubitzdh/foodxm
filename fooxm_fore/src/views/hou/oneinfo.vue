<template>
  <div>
    <!-- 面包屑start -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/hou' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>用户管理</el-breadcrumb-item>
      <el-breadcrumb-item>个人信息</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 面包屑end -->
    <div style="margin: 20px;"></div>

    <el-card class="box-card">
      <el-form ref="oneForm" :model="oneForm" label-width="80px" status-icon :rules="rules">
        <el-form-item label="创建时间">
          <el-input v-model="oneForm.savetime" :disabled="true"></el-input>
        </el-form-item>

        <el-form-item label="用户名" prop="username">
          <el-input v-model="oneForm.username" :disabled="true"></el-input>
        </el-form-item>

        <el-form-item label="姓名" prop="tname">
          <el-input v-model="oneForm.tname"></el-input>
        </el-form-item>

        <el-form-item label="性别" prop="sex">
          <el-radio-group v-model="oneForm.sex">
            <el-radio label="男"></el-radio>
            <el-radio label="女"></el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item label="年龄" prop="age">
          <el-input v-model.number="oneForm.age"></el-input>
        </el-form-item>

        <el-form-item label="手机号码" prop="telephone">
          <el-input v-model="oneForm.telephone"></el-input>
        </el-form-item>

        <el-form-item label="联系地址" prop="addrs">
          <el-input v-model="oneForm.addrs"></el-input>
        </el-form-item>

        <el-form-item label="说明" prop="desc">
          <el-input type="textarea" v-model="oneForm.desc" maxlength="50" show-word-limit></el-input>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="oneFn('oneForm')">提交信息</el-button>
          <el-button @click="resetForm('oneForm')">重置</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
import { searchbyuserid, upuser } from "@/api";
export default {
  data() {
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
      oneForm: {},
      rules: {
        
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" },
          { min: 2, max: 7, message: "长度在 2 到 7 个字符", trigger: "blur" }
        ],
        tname: [
          { required: true, validator: rename, trigger: "blur" },
          { min: 2, max: 7, message: "长度在 2 到 7 个字符", trigger: "blur" }
        ],
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
  created() {
    let userstr = sessionStorage.getItem("user");
    let user = JSON.parse(JSON.stringify(userstr));
    // this.uid = user;
    this.opened(user);
  },
  methods: {
    async opened(uid) {
      let { data: e } = await searchbyuserid(uid);
      this.oneForm = e.data;
    },
    oneFn(formName) {
      this.$refs[formName].validate(async valid => {
        //判断是否输入内容
        if (valid) {
          //发送axios
          let { data: baseresult } = await upuser(this.oneForm);
          if (baseresult.code == 1) {
            //提示
            this.$message.success(baseresult.message);
            //跳转到首页
            this.$router.push("/oneinfo");
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