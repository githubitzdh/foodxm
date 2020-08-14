<template>
  <div>
    <!-- 面包屑start -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/hou' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>商品管理</el-breadcrumb-item>
      <el-breadcrumb-item>商品列表</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 面包屑end -->
    <div style="margin: 20px;"></div>
    <el-card class="box-card">
      <el-form ref="params" status-icon :model="params" size="mini" label-width="80px">
        <el-form-item label="商品查询">
          <el-input placeholder="请输入需要查询的商品" v-model="params.pname" style="width: 30%;"></el-input>
        </el-form-item>
        <el-form-item label="选择分类">
          <el-select v-model="params.cid" placeholder="选择分类">
            <el-option
              v-for="(item,index) in flist"
              :key="index"
              :label="item.cname"
              :value="item.cid"
            ></el-option>
          </el-select>
        </el-form-item>

        <el-form-item>
          <el-button type="success" @click="findall">查询</el-button>
          <el-button type="primary" @click="openadd">添加商品</el-button>
        </el-form-item>
      </el-form>
    </el-card>
    <!-- 卡片start -->
    <el-card class="box-card">
      <!-- 搜索框start -->
      <!-- 搜索框在vue里找布局 -->

      <!-- 搜索框end -->

      <!-- 表单start -->
      <el-table
        ref="singleTable"
        :data="productForm.list"
        highlight-current-row
        style="width: 100%"
      >
        <el-table-column type="selection" width="55"></el-table-column>
        <el-table-column property="pname" label="商品名" width="180"></el-table-column>
        <el-table-column property="marketPrice" label="市场价格" width="180"></el-table-column>

        <el-table-column label="展示" width="180">
          <template slot-scope="scope">
            <el-image :src="scope.row.pimage"></el-image>
          </template>
        </el-table-column>

        <el-table-column property="pdate" label="日期" width="180"></el-table-column>

        <el-table-column property="category.cname" label="分类名" width="180"></el-table-column>

        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button type="primary" icon="el-icon-edit" circle @click="opened(scope.row.pid)"></el-button>

            <el-button type="danger" icon="el-icon-delete" circle @click="delFn(scope.row.pid)"></el-button>
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
        :total="productForm.total"
      ></el-pagination>
      <!-- 分页条end -->

      <!-- 添加商品start -->
      <el-dialog title="添加商品" :visible.sync="dialogVisible" width="30%" :before-close="handleClose">
        <el-form ref="addForm" :model="addForm" label-width="80px" status-icon>
          <!--     <el-form-item label="状态" prop="status">
            <el-radio-group v-model="addForm.status">
              <el-radio label="上架"></el-radio>
              <el-radio label="下架"></el-radio>
            </el-radio-group>
          </el-form-item>-->

          <el-form-item label="商品名" prop="pname">
            <el-input v-model="addForm.pname"></el-input>
          </el-form-item>

          <el-form-item label="市场价格" prop="marketPrice">
            <el-input v-model="addForm.marketPrice"></el-input>
          </el-form-item>

          <el-form-item label="图片" prop="pimage">
            <el-input v-model="addForm.pimage"></el-input>
          </el-form-item>

          <el-form-item label="简介" prop="desc">
            <el-input type="textarea" v-model="addForm.pdesc"></el-input>
          </el-form-item>
          <el-form-item label="选择分类">
            <el-select v-model="addForm.cid" placeholder="选择分类">
              <el-option
                v-for="(item,index) in flist"
                :key="index"
                :label="item.cname"
                :value="item.cid"
              ></el-option>
            </el-select>
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
      <el-dialog title="修改管理员" :visible.sync="dialogedble" width="30%" :before-close="handleClose">
        <el-form ref="edForm" :model="edForm" label-width="80px" status-icon>
          <el-form-item label="商品名" prop="pname">
            <el-input v-model="edForm.pname"></el-input>
          </el-form-item>

          <el-form-item label="市场价格" prop="marketPrice">
            <el-input v-model="edForm.marketPrice"></el-input>
          </el-form-item>

          <el-form-item label="图片" prop="pimage">
            <el-input v-model="edForm.pimage"></el-input>
          </el-form-item>

          <el-form-item label="简介" prop="desc">
            <el-input type="textarea" v-model="edForm.pdesc"></el-input>
          </el-form-item>

          <el-form-item label="选择分类">
            <el-select v-model="edForm.cid" placeholder="选择分类">
              <el-option
                v-for="(item,index) in flist"
                :key="index"
                :label="item.cname"
                :value="item.cid"
              ></el-option>
            </el-select>
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
  productlist,
  categorylist,
  addproduct,
  delproduct,
  searchbyproductid,
  upproduct
} from "@/api";
export default {
  data() {
    return {
      query: "",
      cmessage: "",
      productForm: {},
      flist: [],
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
      let { data: baseresult } = await addproduct(this.addForm);
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
      let { data: a } = await productlist(this.params);
      this.productForm = a.data;
      let { data: s } = await categorylist();
      this.flist = s.data;
    },
    delFn(id) {
      this.$confirm("此操作将永久删除该文件, 是否继续?", "提示", {
        confirmBut0tonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(async () => {
          let { data: d } = await delproduct(id);
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
      let { data: e } = await searchbyproductid(id);
      this.edForm = e.data;
    },
    async edFn() {
      let { data: e } = await upproduct(this.edForm);
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

