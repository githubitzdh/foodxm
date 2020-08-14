<template>
  <div>
    <!-- 面包屑start -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/hou' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>商品管理</el-breadcrumb-item>
      <el-breadcrumb-item>分类列表</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 面包屑end -->
    <div style="margin: 20px;"></div>
    <!-- 卡片start -->
    <el-card class="box-card">
      <!-- 搜索框start -->
      <!-- 搜索框在vue里找布局 -->
      <el-row :gutter="20">
        <el-col :span="7" :model="params">
          <el-input placeholder="请输入需要查询的分类" class="inputSearch" v-model="params.cname">
            <el-button slot="append" icon="el-icon-search" @click="findall"></el-button>
          </el-input>
        </el-col>
        <!-- 搜索框end -->

        <el-col :span="4">
          <el-button type="primary" class="el-icon-circle-plus" @click="openadd">添加分类</el-button>
        </el-col>
      </el-row>

      <!-- 表单start -->
      <el-table
        ref="singleTable"
        :data="categorytForm.list"
        highlight-current-row
        style="width: 100%"
      >
        <el-table-column type="selection" width="55"></el-table-column>
        <el-table-column property="cid" label="分类ID" width="180"></el-table-column>
        <el-table-column property="cname" label="分类名" width="180"></el-table-column>

        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button type="primary" icon="el-icon-edit" circle @click="opened(scope.row.cid)"></el-button>

            <el-button type="danger" icon="el-icon-delete" circle @click="delFn(scope.row.cid)"></el-button>
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
        :total="categorytForm.total"
      ></el-pagination>
      <!-- 分页条end -->

      <!-- 添加商品start -->
      <el-dialog title="添加商品" :visible.sync="dialogVisible" width="30%" :before-close="handleClose">
        <el-form ref="addForm" :model="addForm" label-width="80px" status-icon>

          <el-form-item label="分类名" prop="cname">
            <el-input v-model="addForm.cname"></el-input>
          </el-form-item>

        </el-form>

        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="addFn()">确 定</el-button>
        </span>
        {{addForm}}
      </el-dialog>
      <!-- 添加会员end -->

      <!-- 修改会员start -->
      <el-dialog title="修改分类名" :visible.sync="dialogedble" width="30%" :before-close="handleClose">
        <el-form ref="addForm" :model="edForm" label-width="80px" status-icon>
           <el-form-item label="分类名" prop="cname">
            <el-input v-model="edForm.cname"></el-input>
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
 categorypage,
 addcategory,
 delcategory,
 searchbycategoryid,
 upcategory
} from "@/api";
export default {
  data() {
    return {
      query: "",
      cmessage: "",
      categorytForm: {},
      edForm: [],
      addForm: {},
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
    openadd() {
      this.dialogVisible = true;
      this.addForm = {};
    },
    async addFn() {
      //发送axios
      let { data: baseresult } = await addcategory(this.addForm);
      if (baseresult.code == 1) {
        //提示
        this.$message.success(baseresult.message);
        this.dialogVisible = false;
        this.findall();
      }
    },
    handleClose(done) {
      this.$confirm("确认关闭？")
        .then(_ => {
          1;
          done();
        })
        .catch(_ => {});
    },
    async findall() {
      let { data: a } = await categorypage(this.params);
      this.categorytForm = a.data;
    },
    delFn(id) {
      this.$confirm("此操作将永久删除该文件, 是否继续?", "提示", {
        confirmBut0tonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(async () => {
          let { data: d } = await delcategory(id);
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
      let { data: e } = await searchbycategoryid(id);
      this.edForm = e.data;
    },
    async edFn() {
      let { data: e } = await upcategory(this.edForm);
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

