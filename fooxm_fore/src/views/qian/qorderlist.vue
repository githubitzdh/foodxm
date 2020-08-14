<template>
  <div>
    <!-- 面包屑start -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>购物DIDI</el-breadcrumb-item>
      <el-breadcrumb-item>我的订单</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 面包屑end -->
    <div style="margin: 20px;"></div>
    <el-card class="box-card">
      <!-- 搜索框start -->
      <!-- 搜索框在vue里找布局 -->
      <el-form ref="params" status-icon :model="params" size="mini" label-width="80px">
        <el-form-item label="订单状态">
          <el-radio-group v-model="params.status">
            <el-radio>查询所有</el-radio>
            <el-radio label="1">未处理</el-radio>
            <el-radio label="2">已处理</el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item label="订单时间">
          <el-col :span="5">
            <el-date-picker
              type="date"
              placeholder="选择开始日期"
              v-model="params.stpostTime"
              value-format="yyyy-MM-dd"
              style="width: 100%;"
              :picker-options="pickerOptions"
            ></el-date-picker>
          </el-col>
          <el-col class="line" :span="1">---</el-col>
          <el-col :span="5">
            <el-date-picker
              type="date"
              placeholder="选择结束日期"
              value-format="yyyy-MM-dd"
              v-model="params.endpostTime"
              style="width: 100%;"
              :picker-options="pickerOptions"
            ></el-date-picker>
          </el-col>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="findall">查询</el-button>
        </el-form-item>
      </el-form>
    </el-card>

    <!-- 卡片start -->

    <el-card class="box-card">
      <!-- 表单start -->
      <el-table ref="singleTable" :data="orderForm.list" highlight-current-row style="width: 100%">
        <el-table-column type="selection" width="55"></el-table-column>
        <el-table-column property="oid" label="订单ID" width="100"></el-table-column>
        <el-table-column property="ordertime" label="订单日期" width="170"></el-table-column>
        <el-table-column property="total" label="订单总价" width="100"></el-table-column>
        <el-table-column property="address" label="订单地址" width="100"></el-table-column>
        <el-table-column property="name" label="收货人" width="100"></el-table-column>
        <el-table-column property="telephone" label="收货电话" width="100"></el-table-column>

        <el-table-column label="状态" width="100">
          <template slot-scope="scope">
            <el-tag v-if="scope.row.status==1" type="info">未处理</el-tag>
            <el-tag v-if="scope.row.status==2" type="success">已处理</el-tag>
          </template>
        </el-table-column>

        <el-table-column label="购买商品" width="210">
          <template slot-scope="scope">
 <el-tag v-for="(u,index) in scope.row.olist" :key="index"  >{{u.pname}}-</el-tag>      

          </template>
        
        </el-table-column>

        <el-table-column label="操作">
          <template slot-scope="scope">
           <el-button type="primary" icon="el-icon-document-copy" circle @click="openLookorder(scope.row.oid)"></el-button>

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
        :total="orderForm.total"
      ></el-pagination>
      <!-- 分页条end -->




<!-- 查看订单start -->
      <el-dialog title="查看订单" :visible.sync="dialogorble" width="50%" :before-close="handleClose">
        <el-table ref="singleTable" :data="Lookorder" highlight-current-row style="width: 100%">
          <el-table-column type="selection" width="55"></el-table-column>
          <el-table-column property="itemid" label="订单ID" width="80"></el-table-column>
          <el-table-column property="count" label="数量" width="80"></el-table-column>
          <el-table-column property="subtotal" label="商品价格" width="80"></el-table-column>
          <el-table-column property="pname" label="商品名称" width="100"></el-table-column>
        </el-table>

  
          <div>
           下单日期: {{ordertimeinfo}}
          </div>
             <div>   
            收货地址: {{addressinfo}}
          </div>       
         
          <div>   
            收货人: {{nameinfo}}
          </div>  
            <div>   
             联系电话: {{telephoneinfo}} 
          </div>  
         总价:￥{{totalinfo}}元  ----------   订单状态: {{statusinfo}}
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogorble = false">取 消</el-button>
        </span>
      </el-dialog>
      
<!-- 查看订单end -->







    </el-card>
    <!-- 卡片end -->
  </div>
</template>

<script>
import { qorderpage,selectLookorder ,searchbyorderid} from "@/api";
export default {
  data() {
    return {
      pickerOptions: {
        disabledDate(time) {
          return time.getTime() > Date.now();
        },
        shortcuts: [
          {
            text: "今天",
            onClick(picker) {
              picker.$emit("pick", new Date());
            }
          },
          {
            text: "昨天",
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() - 3600 * 1000 * 24);
              picker.$emit("pick", date);
            }
          },
          {
            text: "一周前",
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit("pick", date);
            }
          }
        ]
      },
      Lookorder: {},
      uid: 1,
      query: "",
      cmessage: "",
      orderForm: {},
      olist: {},
      dialogedble: false,
          dialogorble: false,
      params: {
        //分页对象
        pageNum: 1,
        pageSize: 3
      },
      totalinfo:"",
      statusinfo:"",
      ordertimeinfo:"",
      addressinfo:"",
     nameinfo:"",
telephoneinfo:""
    };
  },
  created() {
    let user = sessionStorage.getItem("user");
    let u = JSON.parse(JSON.stringify(user));
    this.uid = u;
    this.findall();
  },
  methods: {
        async openLookorder(id) {
      this.dialogorble = true;
      let { data: e } = await selectLookorder(id);
      this.Lookorder = e.data;

       let { data: b } = await searchbyorderid(id);
       this.totalinfo=b.data.total  
       this.ordertimeinfo=b.data.ordertime
       this.addressinfo=b.data.address
       this.nameinfo=b.data.name
       this.telephoneinfo=b.data.telephone
       if(b.data.status==1){
 this.statusinfo="未处理"
       }else{
          this.statusinfo="已处理"
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
      let { data: a } = await qorderpage(this.params, this.uid);
      this.orderForm = a.data;
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

