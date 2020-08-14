<template>
  <div>
    <!-- 面包屑start -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/hou' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>商品管理</el-breadcrumb-item>
      <el-breadcrumb-item>添加订单</el-breadcrumb-item>
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

        <el-form-item label="下单时间">
          <el-date-picker
            v-model="addForm.ordertime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="选择日期时间"
            align="right"
          ></el-date-picker>
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

        <el-form-item label="下单用户">
          <el-select v-model="addForm.uid" placeholder="选择下单用户">
            <el-option
              v-for="(item,index) in ulist"
              :key="index"
              :label="item.username"
              :value="item.uid"
            ></el-option>
          </el-select>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="addFn">提交订单</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
import { addorder, selectuserall, selectproductall } from "@/api";
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
      let { data: d } = await addorder(this.addForm);
      if (d.code == 1) {
        this.$message.success(d.message);
        this.$router.push("/orderlist");
      }
    }
  }
};
</script>

<style>
</style>