<template>
  <div>
    <!-- 面包屑start -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/hou' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>管理员管理</el-breadcrumb-item>
      <el-breadcrumb-item>管理员列表</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 面包屑end -->
    <div style="margin: 20px;"></div>
    <!-- 卡片start -->
    <el-card class="box-card">
      <!-- 搜索框start -->
      <!-- 搜索框在vue里找布局 -->
      <el-row :gutter="20">
        <el-col :span="7" :model="params">
          <el-input placeholder="请输入需要查询的用户名" class="inputSearch" v-model="params.username">
            <el-button slot="append" icon="el-icon-search" @click="findall"></el-button>
          </el-input>
        </el-col>
        <!-- 搜索框end -->

        <el-col :span="4">
          <el-button type="primary" class="el-icon-circle-plus" @click="openadd">添加管理员</el-button>
        </el-col>
      </el-row>

      <!-- 表单start -->
      <el-table ref="singleTable" :data="userForm.list" highlight-current-row style="width: 100%">
        <el-table-column type="selection" width="55"></el-table-column>
        <el-table-column property="username" label="用户名" width="150"></el-table-column>
        <el-table-column property="utype" label="用户类型" width="150"></el-table-column>
        <el-table-column property="tname" label="姓名" width="150"></el-table-column>
        <el-table-column property="sex" label="性别" width="150"></el-table-column>
        <el-table-column property="age" label="年龄" width="150"></el-table-column>
        <el-table-column property="telephone" label="手机号码" width="150"></el-table-column>
        <el-table-column property="addrs" label="联系地址" width="150"></el-table-column>

        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button type="primary" icon="el-icon-edit" circle @click="opened(scope.row.uid)"></el-button>

            <el-button type="danger" icon="el-icon-delete" circle @click="delFn(scope.row.uid)"></el-button>
          </template>
        </el-table-column>
      </el-table>
      <!-- 表单end -->
      <!-- 分页条start -->
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="params.pageNum"
        :page-sizes="[1, 2, 3, 4,5,6]"
        :page-size="params.pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="userForm.total"
      ></el-pagination>
      <!-- 分页条end -->

      <!-- 添加管理员start -->
      <el-dialog
        title="添加管理员"
        :visible.sync="dialogVisible"
        width="30%"
        :before-close="handleClose"
      >
        <el-form ref="reForm" :model="reForm" label-width="80px" status-icon :rules="rules">
          <el-form-item label="类别" prop="utype">
            <el-radio-group v-model="reForm.utype">
              <el-radio label="管理员"></el-radio>
            </el-radio-group>
          </el-form-item>

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

          <el-form-item label="电话" prop="telephone">
            <el-input v-model="reForm.telephone"></el-input>
          </el-form-item>

          <el-form-item label="联系地址" prop="addrs">
            <el-input v-model="reForm.addrs"></el-input>
          </el-form-item>

          <el-form-item label="说明" prop="desc">
            <el-input type="textarea" v-model="reForm.desc"></el-input>
          </el-form-item>
        </el-form>

        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="addFn('reForm')">确 定</el-button>
        </span>
        {{reForm}}
      </el-dialog>
      <!-- 添加管理员end -->

      <!-- 修改管理员start -->
      <el-dialog title="修改管理员" :visible.sync="dialogedble" width="30%" :before-close="handleClose">
        <el-form ref="edForm" :model="edForm" label-width="80px" status-icon :rules="rules">
          <el-form-item label="类别" prop="utype">
            <el-radio-group v-model="edForm.utype">
              <el-radio label="管理员"></el-radio>
              <el-radio label="普通用户"></el-radio>
            </el-radio-group>
          </el-form-item>

          <el-form-item label="用户名" prop="username">
            <el-input v-model="edForm.username" @blur.prevent="checknameFn"></el-input>
          </el-form-item>

          <el-form-item label="密码" prop="password">
            <el-input v-model="edForm.password" show-password></el-input>
          </el-form-item>

          <el-form-item label="姓名" prop="tname">
            <el-input v-model="edForm.tname"></el-input>
          </el-form-item>

          <el-form-item label="性别" prop="sex">
            <el-radio-group v-model="edForm.sex">
              <el-radio label="男"></el-radio>
              <el-radio label="女"></el-radio>
            </el-radio-group>
          </el-form-item>

          <el-form-item label="年龄" prop="age">
            <el-input v-model.number="edForm.age"></el-input>
          </el-form-item>

          <el-form-item label="电话" prop="telephone">
            <el-input v-model="edForm.telephone"></el-input>
          </el-form-item>

          <el-form-item label="联系地址" prop="addrs">
            <el-input v-model="edForm.addrs"></el-input>
          </el-form-item>

          <el-form-item label="说明" prop="desc">
            <el-input type="textarea" v-model="edForm.desc"></el-input>
          </el-form-item>
        </el-form>

        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogedble = false">取 消</el-button>
          <el-button type="primary" @click="edFn('edForm')">确 定</el-button>
        </span>
      </el-dialog>
      <!-- 修改会员end -->
    </el-card>
    <!-- 卡片end -->
  </div>
</template>

<script>
import {
  adminlist,
  adduser,
  deluser,
  searchbyuserid,
  upuser,
  checkname
} from "@/api";
export default {
  data() {
    let ckname = (rule, value, callback) => {
      if (!value) {
        callback(new Error("请输入用户名!"));
      }
      setTimeout(() => {
        if (this.reForm.username != this.cmessage) {
          if (this.edForm.username != this.cmessage) {
            callback();
          }
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
      query: "",
      cmessage: "",
      reForm: {},
      edForm: [],
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
        chpassword: [
          { required: true, validator: ps2, trigger: "blur" },
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
      },
      userForm: [],
      dialogVisible: false,
      dialogedble: false,
      params: {
        //分页对象
        pageNum: 1,
        pageSize: 3
      }
    };
  },
  created() {
    this.findall();
  },
  methods: {
    async checknameFn() {
      let { data: c } = await checkname(this.reForm);
      this.cmessage = c.message;
      let { data: d } = await checkname(this.edForm);
      this.cmessage = d.message;
    },

    openadd() {
      this.dialogVisible = true;
      this.reForm = {};
    },
    addFn(formName) {
      this.$refs[formName].validate(async valid => {
        //判断是否输入内容
        if (valid) {
          //发送axios
          let { data: baseresult } = await adduser(this.reForm);
          if (baseresult.code == 1) {
            //提示
            this.$message.success(baseresult.message);
            this.dialogVisible = false;
            this.findall();
          }

          //处理
          //如果没输入就提交,会返回false
        } else {
          return false;
        }
      });
    },
    handleClose(done) {
      this.$confirm("确认关闭？")
        .then(_ => {
          done();
        })
        .catch(_ => {});
    },
    async findall() {
      let { data: a } = await adminlist(this.params);
      this.userForm = a.data;
    },
    delFn(id) {
      this.$confirm("此操作将永久删除该管理员, 是否继续?", "提示", {
        confirmBut0tonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(async () => {
          let { data: d } = await deluser(id);
          if (d.code == 1) {
            this.$message.success(d.message);
            this.findall();
          }
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除"
          });
        });
    },
    async opened(id) {
      this.dialogedble = true;
      let { data: e } = await searchbyuserid(id);
      this.edForm = e.data;
    },
    async edFn() {
      let { data: e } = await upuser(this.edForm);
      if (e.code == 1) {
        this.$message.success(e.message);
        this.dialogedble = false;
        this.findall();
      }
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.params.pageNum = 1;
      this.params.pageSize = val;
      this.findall();
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.params.pageNum = val;
      this.findall();
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
    }
  }
};
</script>

<style>
.box-card {
  height: 100%;
}
.el-main {
  text-align: left;
}
</style>

