<template>
  <div>
    <!-- 面包屑start -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>购物DIDI</el-breadcrumb-item>
      <el-breadcrumb-item>购物车</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 面包屑end -->
    <div style="margin: 20px;"></div>
    <!-- 卡片start -->
    <el-card class="box-card">
      <el-form ref="addForm" :model="addForm" label-width="80px" status-icon>
        <el-form-item label="选择商品">
          <el-checkbox-group v-model="addForm.checklist">
            <el-checkbox v-for="(item,index) in plist" :key="index" :label="item.pid" name="type">
              <el-avatar shape="square" size="large" :src="item.pimage"></el-avatar>
              {{item.pname}}--￥{{item.marketPrice}}
            </el-checkbox>
          </el-checkbox-group>
        </el-form-item>



        <el-form-item label="收货地址">
          <el-input v-model="addForm.address"></el-input>
        </el-form-item>

        <el-form-item label="收货人">
          <el-input v-model="addForm.name"></el-input>
        </el-form-item>

        <el-form-item label="联系电话" prop="telephone">
          <el-input v-model="addForm.telephone"></el-input>
        </el-form-item>


        <el-form-item>
          <el-button type="primary" @click="addFn">提交订单</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
import { qaddorder, selectuserall, selectproductall } from "@/api";
export default {
  data() {
    return {
      ulist: [],
      plist: {},
      addForm: {
        checklist: []
      }
    };
  },
  created() {
    this.findall();
  },
  methods: {
    async findall() {
      let { data: f } = await selectuserall();
      this.ulist = f.data;

      let { data: p } = await selectproductall();
      this.plist = p.data;
    },

    async addFn() {
     let user = sessionStorage.getItem("user");
    let a = JSON.parse(JSON.stringify(user));
      let { data: d } = await qaddorder(this.addForm,a);
      if (d.code == 1) {
        this.$message.success(d.message);
        this.$router.push("/qorderlist");
      }
    }
  }
};
</script>

<style>
</style>