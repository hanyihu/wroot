#镗镗锣 


标签（空格分隔）： private

[toc]

---

# 一、通用说明
* `一般通用返回JSON格式说明`
```
{
    code:0,//返回码 0-默认  成功
    msg:"",//错误码说明信息
    data:null//接口需求的具体数据 或List 或String 等
}
```
* `一般返回的列表数据格式说明`
```
 {
    code:0,//返回码 0-默认  成功
    msg:"",//错误码说明信息
    data:{//接口需求的具体数据 或List 或String 等
        "total": 2,//总数量
        "size": 10,//每一页的数量
        "page": 1,//当前页码
        "pages": 1,//总页数
        "curSize": 2,//当前页的数量
        "datas"[//数据列表
        ]
    }
}
```

* `关于文件上传的一些说明`
    - 所有的文件均为异步上传，上传完毕，返回附件id，作为参数使用，
    - 参数为单文件的时候直接使用id，参数为多文件的时候，多文件id用英文逗号拼接(如1,2,3)；
    - 一般情况下后台返回的包含附件数据如下简单说明：
        > 如单附件 字段为xx ，则同时会转义xxUrl 作为真实访问地址，
            多附件字段为xxs,则转化为xxUrls数组作为多真实url
     

    
        -如   
        ```
        {
            "id":1,//某个数据的id
            "name"："someone",
            "icon"：2,//单附件id
            "iconUrl"："http://www.xxx.com/xxx/2",//附件url
            "images":"3,5,4",//多附件ids
            "imageUrls":[//多附件url数组
                "http://www.xxx.com/xxx/3","http://www.xxx.com/xxx/5","http://www.xxx.com/xxx/4"
            ]
        }
        ```
    
    - 文件上传请求如下

**请求地址**
`POST` `http://www.gzchike.cn/ajax/upfile`
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
upfile|file|Y|上传的文件
module|int|N|所属模块 如 1 ，2， 3

**module说明**
 `暂时包含`： 2-团购 3酒店 4-用户相关 5-商家相关

**响应结果:**
```javascript
{
   "code": 0,//成功为0  ，503=未知错误 204= 没有上传文件(空文件)
   "message":"",//失败时候的说明
   "id":5,//附件id
   "value": "/files/upload/2017/11/21/1.jpg-37904136-5868-4283-a3be-baf05576b30c/1.jpg",//附件本项目中的地址 暂不使用
   "url": "http://39.106.13.51:10081/attachment/ajax/visit/5",//附件的访问地址
   "fileName": "1.jpg",//文件名
   "size": 3349//文件大小
}
```
     


*  `base_url`
```
http://www.gzchike.cn/api
```
*  `请求参数key`
```
param
```
*  `安全`
```
  · 对所有请求参数转json后进行AES统一加密，加密key为....，请求参数为`param`
    · 对所用返回数据进行AES统一加密，加密key为....
    测试联调阶段不转json,不加密(就是普通的http请求，不对参数做额外处理)；
    
    另后续会考虑引入JWT（JSON WEB TOKEN）
 
```
* `通用错误码说明`
```
401 :  未登录
400 ： 请求参数错误
500 : 意料之外的错误，未处理的错误 
```
-----

#二、接口文档

## 1、系统全局
### 1.01 可使用银行列表      √ 

**接口地址:** 
`GET`   `/system/banks`

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
无|
**响应结果:**
```javascript
{
    "code": 0,  //0 - 接口调用成功，其他值表示失败
    "msg": null,//错误消息
    "data": [{
            "id":1,
            "name":"中国银行"，
            "iconUrl": "http://39.106.13.51:10081/attachment/ajax/visit/525"//银行图标
        }
        ,...
    ]
}
```

### 1.02 启动页列表 √
**接口地址:** 
`GET`   `/system/bootPages`

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
无|
**响应结果:**
```javascript
{
    "code": 0,  //0 - 接口调用成功，其他值表示失败
    "msg": null,
    "data": [{
            "id":1,
            "imageUrl":"http://39.106.13.51:10081/attachment/ajax/visit/1",//图片地址
            "title"："我是文字说明"
        }
        ,...
    ]
}
```
### 1.03 城市匹配 √
> 根据定位的城市 id或者名称 去匹配后台的城市

**接口地址:** 
`GET`   `/system/matchCity`

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
id|int|N|城市id
name|String|N| 城市名称 两个参数至少传一个


**错误码说明**
code| 错误原因  |返回消息
-----|---------|----
701|当前城市未开放| 当前城市未开放 或者不存在

**响应结果:**
```javascript
{
    "code": 0,  //0 - 接口调用成功，其他值表示失败
    "msg": null,
    "data": {
            "id":440100,
            "parentId":440000,
            "name"："广州市",
            "levelType":2,//层级:0国家级别，1省级，2地市级，3区县级
            "firstChar"："G",//首字母
            "opened"：true,
            "hot"：true
    }
    
}
```
### 1.04 热门城市   √

**接口地址:** 
`GET`   `/system/hotCities`

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
无|
**错误码说明**
code| 错误原因  |返回消息
-----|---------|----

**响应结果:**
```javascript
{
    "code": 0,  //0 - 接口调用成功，其他值表示失败
    "msg": null,
    "data": [{
            "id":440100,
            "parentId":440000,
            "name"："广州市",
            "levelType":2,//层级:0国家级别，1省级，2地市级，3区县级
            "firstChar"："G",//首字母
            "opened"：true,
            "hot"：true
        },...
    ]
    
}
```
### 1.05 已开放城市  √
**接口地址:** 
`GET`   `/system/openedCities`

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
无|
**错误码说明**
code| 错误原因  |返回消息
-----|---------|----

**响应结果:**
```javascript
{
    "code": 0,  //0 - 接口调用成功，其他值表示失败
    "msg": null,
    "data": [{
            "id":440100,
            "parentId":440000,
            "name"："广州市",
            "levelType":2,//层级:0国家级别，1省级，2地市级，3区县级
            "firstChar"："G",//首字母
            "opened"：true,
            "hot"：true
        },...
    ]
    
}
```
### 1.06 省市区下拉查询  √
**接口地址:** 
`GET`   `/system/selectCities`

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
levelType|int|N|1省级，2地市级，3区县级 ； 默认1，此时parentId不传 或者传入100000
parentId|int|N|所属省或者市的id

**错误码说明**
code| 错误原因  |返回消息
-----|---------|----

**响应结果:**
```javascript
{
    "code": 0,  //0 - 接口调用成功，其他值表示失败
    "msg": null,
    "data": [{
            "id":440100,
            "parentId":440000,
            "name"："广州市",
            "levelType":2,//层级:0国家级别，1省级，2地市级，3区县级
            "firstChar"："G",//首字母
            "opened"：true,
            "hot"：true
        },...
    ]
    
}
```

### 1.07 轮播图列表  √
**接口地址:** 
`GET`   `/system/banners`

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
cityId|int|Y|当前定位城市的id
type |int|N|类型 1-首页banner(默认) 2-百惠店 3-社区4-积分商城
**错误码说明**
code| 错误原因  |返回消息
-----|---------|----

**响应结果:**
```javascript
{
    "code": 0,  //0 - 接口调用成功，其他值表示失败
    "msg": null,
    "data": [{
            "id": 1,
            "image": 1,//图片所属附件id
            "imageUrl": "http://39.106.13.51:10081/attachment/ajax/visit/1",
            "target": "www.baidu.com",//跳转目标。url_type=1 则为页面url，2则为商家id
            "title": "去百度",
            "type": 1,
            "urlType": 2 // 跳转方式 1-页面，2-商家
        },...
    ]
    
}
```

### 1.08 意见反馈  √ 
**接口地址:** 
`POST`   `/system/feedback/add`

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
customerId|int|Y|用户id(在商家端的时候依然是用户id ，因为两端登陆的是相同的账号)
customerName |String|Y|用户姓名
content |String|Y|反馈内容
type |int|Y|1-用户端反馈 2-商家端反馈
images |String|Y|图片  附件ids
**错误码说明**
code| 错误原因  |返回消息
-----|---------|----

**响应结果:**
```javascript
{
    "code": 0,  //0 - 接口调用成功，其他值表示失败
    "msg": null,
    "data": null
    
}
```

### 1.09 关于我们  √ 
**接口地址:** 
`GET`   `/system/aboutus`

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
type |int|N|1-用户端的关于我们 2-商家端的关于我们 默认1

**错误码说明**
code| 错误原因  |返回消息
-----|---------|----

**响应结果:**
```javascript
{
    "code": 0,  //0 - 接口调用成功，其他值表示失败
    "msg": null,
    "data": ".....内容详情...."
    
}
```
### 1.10 商家分类  √ 
**接口地址:** 
`GET`   `/system/merchantCategories`

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
tree |boolean|N| false 或true ,是否组装为tree，默认false,为true则返回的对象数据会变化为type 对应的商家分类列表，为false则是全部分类的列表 

**错误码说明**
code| 错误原因  |返回消息
-----|---------|----

**响应结果:**
```javascript
{
    "code": 0,  //0 - 接口调用成功，其他值表示失败
    "msg": null,
    "data":[//参数tree 不传或者传为false的时候
    {
        "id": 1,
        "name": "美食",//分类名称
        "icon": 2,
        "type": 1,//类型 1-美食 2-酒店预订 3-其他
        "iconUrl": "http://39.106.13.51:10081/attachment/ajax/visit/2"
    },
    {
        "id": 2,
        "name": "酒店预订",
        "icon": 3,
        "type": 2,
        "iconUrl": "http://39.106.13.51:10081/attachment/ajax/visit/3"
    },
    {
        "id": 3,
        "name": "休闲娱乐",
        "icon": 4,
        "type": 3,
        "iconUrl": "http://39.106.13.51:10081/attachment/ajax/visit/4"
    },
    {
        "id": 4,
        "name": "运动健身",
        "icon": 5,
        "type": 3,
        "iconUrl": "http://39.106.13.51:10081/attachment/ajax/visit/5"
    },
    {
        "id": 5,
        "name": "丽人",
        "icon": 1,
        "type": 3,
        "iconUrl": "http://39.106.13.51:10081/attachment/ajax/visit/1"
    },
    {
        "id": 6,
        "name": "其他",
        "icon": 2,
        "type": 3,
        "iconUrl": "http://39.106.13.51:10081/attachment/ajax/visit/2"
    }
]
    
}
```
```javascript
{
    "code": 0,  //0 - 接口调用成功，其他值表示失败
    "msg": null,
    "data": {//tree 为 true 的时候
        "1": [
            {
                "id": 1,
                "name": "美食",
                "icon": 2,
                "type": 1,
                "iconUrl": "http://39.106.13.51:10081/attachment/ajax/visit/2"
            }
        ],
        "2": [
            {
                "id": 2,
                "name": "酒店预订",
                "icon": 3,
                "type": 2,
                "iconUrl": "http://39.106.13.51:10081/attachment/ajax/visit/3"
            }
        ],
        "3": [
            {
                "id": 3,
                "name": "休闲娱乐",
                "icon": 4,
                "type": 3,
                "iconUrl": "http://39.106.13.51:10081/attachment/ajax/visit/4"
            },
            {
                "id": 4,
                "name": "运动健身",
                "icon": 5,
                "type": 3,
                "iconUrl": "http://39.106.13.51:10081/attachment/ajax/visit/5"
            },
            {
                "id": 5,
                "name": "丽人",
                "icon": 1,
                "type": 3,
                "iconUrl": "http://39.106.13.51:10081/attachment/ajax/visit/1"
            },
            {
                "id": 6,
                "name": "其他",
                "icon": 2,
                "type": 3,
                "iconUrl": "http://39.106.13.51:10081/attachment/ajax/visit/2"
            }
        ]
    }
    
}
```

### 1.11  分享镗镗锣  √ 

> 分享可获得5积分   一日可分享N(当前为5)次

**接口地址:** 
`POST`   `/system/share`

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
userId|int|<i class="icon-ok"></i>|用户id
type |int|<i class="icon-remove"></i>|类型0-平台 1-团购 2-商家 默认0
target |String|<i class="icon-remove"></i>|分享的目标 如商家名称 id或团购名称 id等
**错误码说明**
code| 错误原因  |返回消息
-----|---------|----

**响应结果:**
```javascript
{
    "code": 0,  //0 - 接口调用成功，其他值表示失败
    "msg": null,
    "data": null
    
}
```
### 1.12  版本更新  √ 

> 获取当前APP的最新版本信息  前端比对 决定是否升级更新

**接口地址:** 
`POST`   `/system/version`

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
appType|int|<i class="icon-remove"></i>|1-用户端(默认) 2-商家端
machineType |int|<i class="icon-remove"></i>|1-安卓（默认） 2-IOS

**错误码说明**
code| 错误原因  |返回消息
-----|---------|----

**响应结果:**
```javascript
{
    "code": 0,  //0 - 接口调用成功，其他值表示失败
    "msg": null,
    "data": {
        "id":1,
        "name":"xx版本",  //版本名称
        "version":"1.1.1",//版本
        "number":2,//版本数字编号，高版本始终大于低版本
        "machineType":1,//1-安卓 2-ios
        "appType":1,//1-用户端 2-商家端
        "log":"本次更新的更新说明....",//更新说明
        "downloadUrl":"url"//下载地址 ios应指向苹果商店，安卓则为本项目中的下载地址
        
    }
    
}
```

### 1.13  联系客服  √ 

> 用户端和商户端公用

**接口地址:** 
`POST`   `/system/contact`

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
无|

**错误码说明**
code| 错误原因  |返回消息
-----|---------|----
无|

**响应结果:**
```javascript
{
    "code": 0,  //0 - 接口调用成功，其他值表示失败
    "msg": null,
    "data": "15088886666"//联系电话
    
}
```


## 2、个人中心

### 2.00 获取登录用户信息 √
**接口地址:** 
`GET`   `/auth/userInfo`
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
userId	|int	|<i class="icon-ok"></i>|当前登录用户id

**错误码说明**
code| 错误原因  |返回消息
-----|---------|----


**响应结果:**
```javascript
{
    "code": 0,  //0 - 接口调用成功，其他值表示失败
    "message": null,
    "data": {//同登录
        "id": 7,
        "mobile": "15011223344",//电话
        "nickname": "150****3344",//昵称
        "gender": 0,// 性别 0-保密1-男 2-女
        "birthday": null,//生日
        "homeProvince": 340000,//家乡-省
        "homeProvinceName": "安徽省",////家乡-省
        "homeCity": 340100,//家乡-市
        "homeCityName": "合肥市",//家乡-市
        "liveProvince": 340000,//居住地 -省
        "liveProvinceName": "安徽省",//居住地 -
        "liveCity": 340100,//居住地 -市
        "liveCityName": "合肥市",//居住地 -市
        "headpic": 1,//头像 附件id
        "thirdPartyType": 0,//第三方登陆方式 1-微信 2-qq 3-支付宝
        "thirdPartyUid": null,//第三方登陆id
        "recommendQcodePath": 4,//我的推荐二维码附件地址
        "recommendUrl": "http://www.gzchike.cn/static/recommend.html?recommend=lR39vmdbXXQRXdJFPXqMmg==",//我的推荐url地址
        "recommendId": null,//推荐我的人的id
        "createTime": "2017-11-21",//注册时间
        "type": 1,//1-用户 2-商家(商家必定是用户)
        "score": 0,//积分
        "headpicUrl": "http://39.106.13.51:10081/attachment/ajax/visit/1",//头像地址
        "recommendQcodePathUrl": "http://39.106.13.51:10081/attachment/ajax/visit/4"//我的二维码地址
    }
}
```
### 2.01 发送短信验证码 √
**接口地址:** 
`GET`   `/auth/smscode`
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
mobile	|string	|<i class="icon-ok"></i>|接收短信的手机号码

**错误码说明**
code| 错误原因  |返回消息
-----|---------|----
702 |发送短信验证码失败|发送短信验证码失败

**响应结果:**
```javascript
{
    "code": 0,  //0 - 接口调用成功，其他值表示失败
    "message": null,
    "data": 123456
}
```

### 2.02 用户注册 √
**接口地址:** 
`POST`   `/auth/register`
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
mobile	|string	|<i class="icon-ok"></i>|手机号
smscode|string|<i class="icon-ok"></i>|验证码
password|String|<i class="icon-ok"></i>|密码

**错误码说明**
code| 错误原因  |返回消息
-----|---------|----
703 |短信验证码错误|无效的短信验证码
801 |手机号已经注册|当前手机号已经注册

**响应结果:**
```javascript
{
    "code": 0,  //0 - 接口调用成功，其他值表示失败
    "message": null,
    "data": null
}
```
### 2.03 账号密码登录 √
**接口地址:** 
`POST`   `/auth/login`
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
mobile	|string	|<i class="icon-ok"></i>|手机号
password|string|<i class="icon-ok"></i>|密码
mobileCode|String|<i class="icon-remove"></i>|手机设备号 用于极光推送

**错误码说明**
code| 错误原因  |返回消息
-----|---------|----
802 |不存在的用户|不存在的用户
803 |用户被禁用|用户被禁用
804 |密码错误|密码错误
**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": {
        "id": 7,
        "mobile": "15011223344",//电话
        "nickname": "150****3344",//昵称
        "gender": 0,// 性别 0-保密1-男 2-女
        "birthday": null,//生日
        "homeProvince": 340000,//家乡-省
        "homeProvinceName": "安徽省",////家乡-省
        "homeCity": 340100,//家乡-市
        "homeCityName": "合肥市",//家乡-市
        "liveProvince": 340000,//居住地 -省
        "liveProvinceName": "安徽省",//居住地 -
        "liveCity": 340100,//居住地 -市
        "liveCityName": "合肥市",//居住地 -市
        "headpic": 1,//头像 附件id
        "thirdPartyType": 0,//第三方登陆方式 1-微信 2-qq 3-支付宝
        "thirdPartyUid": null,//第三方登陆id
        "recommendQcodePath": 4,//我的推荐二维码附件地址
        "recommendUrl": "http://www.gzchike.cn/static/recommend.html?recommend=lR39vmdbXXQRXdJFPXqMmg==",//我的推荐url地址
        "recommendId": null,//推荐我的人的id
        "createTime": "2017-11-21",//注册时间
        "type": 1,//1-用户 2-商家(商家必定是用户)
        "score": 0,//积分
        "headpicUrl": "http://39.106.13.51:10081/attachment/ajax/visit/1",//头像地址
        "recommendQcodePathUrl": "http://39.106.13.51:10081/attachment/ajax/visit/4"//我的二维码地址
    }
}
```

### 2.04 手机验证码登录 √
**接口地址:** 
`POST`   `/auth/smscodeLogin`
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
mobile	|string	|<i class="icon-ok"></i>|手机号
smscode|string|<i class="icon-ok"></i>|验证码
mobileCode|String|<i class="icon-remove"></i>|手机设备号 用于极光推送

**错误码说明**
code| 错误原因  |返回消息
-----|---------|----
703 |短信验证码错误|无效的短信验证码
802 |不存在的用户|不存在的用户
803 |用户被禁用|用户被禁用

**响应结果:**
```javascript
{
    "code": 0,  //0 - 接口调用成功，其他值表示失败
    "message": null,
    "data": {// 见账号密码登陆
    }
}
```

### 2.05 第三方登录
`POST`   `/auth/thirdLogin`
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
**响应结果:**
```javascript
{
    "code": 0,  //0 - 接口调用成功，其他值表示失败
    "message": null,
    "data": null
}
```

### 2.06 忘记密码  √
`POST`   `/auth/forget`
 
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
mobile |String|<i class="icon-ok"></i>|手机号
smscode|String|<i class="icon-ok"></i>|验证码
password|String|<i class="icon-ok"></i>|新密码

**错误码说明**
code| 错误原因  |返回消息
-----|---------|----
703 |短信验证码错误|无效的短信验证码
802 |不存在的用户|不存在的用户


**响应结果:**
```javascript
{
    "code": 0,  //0 - 接口调用成功，其他值表示失败
    "message": null,
    "data": null
}
```

### 2.07 修改密码 √
`POST`   `/auth/updatePassword`
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
mobile |String|<i class="icon-ok"></i>|手机号
oldPassword|String|<i class="icon-ok"></i>|原密码
newPassword|String|<i class="icon-ok"></i>|新密码

**错误码说明**
code| 错误原因  |返回消息
-----|---------|----
804 |密码错误|密码错误
802 |不存在的用户|不存在的用户

**响应结果:**
```javascript
{
    "code": 0,  //0 - 接口调用成功，其他值表示失败
    "message": null,
    "data": null
}
```

### 2.08 编辑个人资料  √
`POST`   `/personal/edit`
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
id|int|<i class="icon-ok"></i>|用户id
headpic|int|<i class="icon-remove"></i>|头像 附件id
nickname|String|<i class="icon-remove"></i>|昵称
gender|int|<i class="icon-remove"></i>|性别 0-保密 1-男 2-女
homeCity|int|<i class="icon-remove"></i>|家乡 城市id
homeProvince|int|<i class="icon-remove"></i>|家乡 省id
liveCity|int|<i class="icon-remove"></i>|居住地-城市id
liveProvince|int|<i class="icon-remove"></i>|居住地-省id
**响应结果:**
```javascript
{
    "code": 0,  //0 - 接口调用成功，其他值表示失败
    "message": null,
    "data": {}//用户详情 参见登陆
}
```

### 2.09 收货地址列表 √
`GET`   `/personal/deliveries`
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
userId|int|<i class="icon-ok"></i>|用户id
page|int|<i class="icon-remove"></i>|分页参数-当前页码
size|int|<i class="icon-remove"></i>|分页参数-页数据量
**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": {
        "datas": [
            {
                "id": 2,
                "customerId": 4,
                "deliveryName": "啦啦啦",//收货人姓名
                "deliveryMobile": "15099998877",//电话
                "provinceId": 340000,//省
                "provinceName": "安徽省",
                "cityId": 340100,//市
                "cityName": "合肥市",
                "areaId": 340103,//区
                "areaName": "庐阳区",
                "address": "翡翠花园"//具体地址
            }
        ],
        "total": 1,
        "size": 10,
        "page": 1,
        "pages": 1,
        "curSize": 1,
        "lookup": {
            "page": 1,
            "size": 10,
            "userId": 1,
            "type": 0,
            "status": 0,
            "merchantId": 0
        }
    }
}
```

### 2.10 新增收货地址 √
`POST`   `/personal/delivery/add`
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
customerId|int|<i class="icon-ok"></i>|用户id
deliveryMobile|String|<i class="icon-ok"></i>|电话
deliveryName|String|<i class="icon-ok"></i>|姓名
provinceId|int|<i class="icon-ok"></i>|省id
cityId|int|<i class="icon-ok"></i>|市id
areaId|int|<i class="icon-ok"></i>|地区id
address|String|<i class="icon-ok"></i>|详细地址
**响应结果:**
```javascript
{
    "code": 0,  //0 - 接口调用成功，其他值表示失败
    "message": null,
    "data": null
}
```

### 2.11 删除收货地址 √
`POST`   `/personal/delivery/{id}/delete`
**路径参数：**
`id` `int` 当前地址ID

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
无|
**响应结果:**
```javascript
{
    "code": 0,  //0 - 接口调用成功，其他值表示失败
    "message": null,
    "data": null
}
```

### 2.12 我的余额 √
`GET`   `/personal/balance`
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
userId|String|<i class="icon-ok"></i>|用户ID
**响应结果:**
```javascript
{
    "code": 0,  //0 - 接口调用成功，其他值表示失败
    "message": null,
    "data": 500.20
}
```

### 2.13 余额明细  √
`GET`   `/personal/balances`
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
userId|int|<i class="icon-ok"></i>|用户ID

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": {
        "datas": [
            {
                "id": 1,
                "customerId": 4,//用户id
                "money": 10,//金额
                "createTime": "2017-11-10 08:36:16",//时间
                "describe": "注册送现金",//简单描述
                "type": 6//1-推荐消费收入 2-下单收入 3-红包收入 4-推荐商户收入 5-活动收入 6-注册送现金  7-消费支出 8-提现 9- 商户退款
                
            }
        ],
        "total": 1,
        "size": 10,
        "page": 1,
        "pages": 1,
        "curSize": 1
        
    }
}
```

### 2.14 我的银行卡列表 √

`GET`   `/personal/bankCards`
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
userId|int|<i class="icon-ok"></i>|用户ID

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": {
        "datas": [
            {
                "id": 1,
                "customerId": 1,
                "accountName": null,
                "bankId": 1,
                "bankName": "中国银行",
                "bankCardno": "123456",
                "bankIconUrl":"url",//银行图标
                "idNumber": "340123198812121213",
                "mobile": "15056920791"
            }
        ],
        "total": 1,
        "size": 10,
        "page": 1,
        "pages": 1,
        "curSize": 1
    }
}
```

### 2.15 绑定银行卡 √
`POST`   `/personal/bankCard/add`
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
customerId|int|<i class="icon-ok"></i>|用户ID
bankId|int|<i class="icon-ok"></i>|银行id
bankName|String|<i class="icon-ok"></i>|银行名称
bankCardno|String|<i class="icon-ok"></i>|银行卡号
idNumber|String|<i class="icon-ok"></i>|身份证号
mobile|String|<i class="icon-ok"></i>|手机号
**响应结果:**
```javascript
{
    "code": 0,  //0 - 接口调用成功，其他值表示失败
    "message": null,
    "data": null
}
```

### 2.16 删除银行卡 √
`POST`   `/personal/bankCard/{id}/delete`

**路径参数**
`id` ：`银行卡d`
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
无|
**响应结果:**
```javascript
{
    "code": 0,  //0 - 接口调用成功，其他值表示失败
    "message": null,
    "data": null
}
```

### 2.17 提现申请 √
`POST`   `/personal/fetch`

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
customerId|int|<i class="icon-ok"></i>|用户ID
money|double|<i class="icon-ok"></i>|提现金额
cardId|int|<i class="icon-ok"></i>|银行卡id

**错误码说明**
code| 错误原因  |返回消息
-----|---------|----
806 |不存在的银行卡或和用户不匹配|不存在的银行卡
815 |余额不足|余额不足

**响应结果:**
```javascript
{
    "code": 0,  //0 - 接口调用成功，其他值表示失败
    "message": null,
    "data": null
}
```


### 2.18 订单列表  √
`GET`   `/personal/orders`

> 订单状态  0-待支付 1-待消费 2-待确认 3- 待入住 4-待评价 (已完成) 5-已评价 6-退款中 7-已退款 8-已取消 
直接购买：0-待支付 4-待评价 (已完成) 5-已评价 
团购：	 0-待支付 1-待消费 4-待评价 (已完成) 5-已评价 6-退款中 7-已退款 8-已取消 
预定：	 0-待支付 2-待确认 3- 待入住 4-待评价 (已完成) 5-已评价 6-退款中 7-已退款 8-已取消

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
userId|String|<i class="icon-ok"></i>|用户ID
orderStatus|int|<i class="icon-remove"></i>|查询的订单 不传则为全部 ，1-待付款 2-可使用 3-待评价 4-退款
page|int|<i class="icon-remove"></i>|分页参数
size|int|<i class="icon-remove"></i>|分页参数
**响应结果:**
```javascript
{
    "code": 0,  //0 - 接口调用成功，其他值表示失败
    "message": null,
    "data": {
        "datas": [
           {
                "id": 1,
                "orderno": "",//订单号
                "customerId": 1,//用户id
                "merchantId": 2,//所属商家
                "merchantName": "",//商家名称
                "commodityId": 1,//商品id ：团购id 或酒店房间id
                "orderType": 2,//订单类型 1-直接购买 2-团购 3-酒店预订
                "totalFee": 15.20,//订单总金额
                "amount": 12.20,//订单实际支付金额
                "quantity": 1,//购买数量 团购时
                "status": 1,//订单状态
                "orderImage": 2,//订单图片
                "orderName": "",//团购名称或商家名称
                "merchantMobile": "15012345678",//商家手机号
                "payType": 1,//1-余额支付 2-支付宝支付 3-微信支付
                "createTime": "2017-12-12 12:12",//下单时间
                "merchantType": 1,//商家类型 1-美食 2-酒店预订 3-其他
                "roomNumber": 2,//酒店预订时-房间数
                "checkPerson": "张三,李四",//酒店预订时-入住人 多个逗号分隔
                "checkStartDate": "12-05",// 酒店预订-入住开始日期
                "checkEndDate": "12-06",//酒店预订-入住结束日期
                "orderImageUrl": "url1",//订单图片
                "durationDay": 2,//酒店预订 入住几晚
                "statusDesc": "待消费"//订单状态描述
            }
        ],
        "total": 1,
        "size": 10,
        "page": 1,
        "pages": 1,
        "curSize": 1
    }
}
```

### 2.19 订单详情 √
`GET`   `/personal/order/{orderno}`

**路径参数**
`orderno` ：`订单号`

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
无|

**响应结果:**
```javascript
{
    "code": 0,  //0 - 接口调用成功，其他值表示失败
    "message": null,
    "data": {
        "id": 1,
        "orderno": "",
        "customerId": 2,
        "merchantId": 2,
        "merchantName": "",
        "commodityId": 2,
        "orderType": 2,
        "totalFee": 20.12,
        "amount": 18.20,
        "quantity": 2,
        "status": 1,
        "orderImage": 2,
        "orderName": "",
        "merchantMobile": "",
        "payType": 1,
        "createTime": "",
        "merchantType": 1,
        "roomNumber": 2,
        "checkPerson": "",
        "checkStartDate": "",
        "checkEndDate": "",
        "mobile": "",
        "groupbutTicketNo": "",//团购时候的团购券
        "checkLastTime": "",//酒店预订-预计最晚到店时间
        "hotelDetail": {//酒店预订时候的房间详情 · 同房间详情
            "id": 0,
            "name": "房间标题",
            "icon": 1,//图标对应附件id
            "price": 150,//价格
            "roomWindow": 1,//窗户 1-无窗 2-有窗 3-部分有窗
            "roomBreakfast": 2,//早餐 1-无早餐 2-含早餐 3-双份早餐
            "roomBed": 2,//床型 1-单人床 2-豪华大床 3-双人床 4-三人床
            "roomPersonNum": 3,//可住几人
            "unsubscribeWay": 2,//退订政策 1-当天18：00前可免费取消订单 2 预定成功后 不可变更取消
            "verifyWay": 1,//订单确认时间 1-1小时内确认 2-立即确认
            "images": "2,3",//图片ids
            "stock": 2,//库存
            "roomArea": 65.5,//面积 
            "roomNetwork": 2,//宽带 1-无网络 2-有线宽带 3-无线WIFI
            "roomFloor": 2,//楼层
            "roomWindowmView": 2,//窗景：1-无 2-风景2-城景 3-花园景 4-地标景 5-无敌海景
            "imageUrls": ["url1", "url2"],
            "iconUrl": "http://39.106.13.51:10081/attachment/ajax/visit/0"
        },
        "refundReason": 2//退款理由 1.买错了， 2计划有变，没时间消费 3去过了，不满意 4预约不上 5商家停业维修转让 6商家营业，但不接待  7商家说可以直接以团购价到店里消费 8网友评价不好 19-其他原因
        "orderImageUrl": "",
        "durationDay": 1,
        "statusDesc": ""
    }
}
```
### 2.20 申请退款 √
`POST`   `/personal/order/refund` 

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
orderno|String|<i class="icon-ok"></i>|订单号
refundReason|int|<i class="icon-ok"></i>|退款理由 1.买错了， 2计划有变，没时间消费 3去过了，不满意 4预约不上 5商家停业维修转让 6商家营业，但不接待 7商家说可以直接以团购价到店里消费 8网友评价不好 19-其他原因

**错误码说明**
code| 错误原因  |返回消息
-----|---------|----
811 |不存在的订单|不存在的订单
1010 |直接购买不存在退款 、团购 待消费的时候才能退  、酒店预订 待确认和待评价才能退款|订单当前状态不可退款
1011 |酒店预定 16点之后不能退款|酒店预定16点之后不可退款
1012 |当前订单未找到第三方支付记录|当前订单未找到第三方支付记录
1013 |退款失败|退款失败


**响应结果:**
```javascript
{
    "code": 0,  //0 - 接口调用成功，其他值表示失败
    "message": null,
    "data": {//见订单详情}
}
```


### 2.21 修改订单支付方式 √
`POST`   `/personal/order/changePayType` 

>先判断可否修改支付方式（只有待支付可修改）， 余额支付 前端需判断余额大小，后端会判断余额 余额够会扣余额并改变订单状态，不够则只修改支付方式，而不抛出异常

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
orderno|String|<i class="icon-ok"></i>|订单号
payType|int|<i class="icon-ok"></i>|1-余额支付 2-支付宝支付 3-微信支付

**错误码说明**
code| 错误原因  |返回消息
-----|---------|----
811 |不存在的订单|不存在的订单
905 |订单当前状态不可变更支付方式|订单当前状态不可变更支付方式


**响应结果:**
```javascript
{
    "code": 0,  //0 - 接口调用成功，其他值表示失败
    "message": null,
    "data": {//见订单详情}
}
```


### 2.22 删除订单 √
`POST`   `/personal/order/{orderno}/delete`

> 未支付  已取消 已完成 已评价 已退款 ---->可删除 

**路径参数**
`orderno`:`订单号`
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
无|

**错误码说明**
code| 错误原因  |返回消息
-----|---------|----
811 |不存在的订单|不存在的订单
1014 |当前订单状态不可删除|当前订单状态不可删除

**响应结果:**
```javascript
{
    "code": 0,  //0 - 接口调用成功，其他值表示失败
    "message": null,
    "data": null
}
```
### 2.23 取消订单 √
`POST`   `/personal/order/{orderno}/cancel`

> 未支付的可取消

**路径参数**
`orderno`:`订单号`
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
无|

**错误码说明**
code| 错误原因  |返回消息
-----|---------|----
811 |不存在的订单|不存在的订单
1015 |当前订单状态不可删除|当前订单状态不可删除

**响应结果:**
```javascript
{
    "code": 0,  //0 - 接口调用成功，其他值表示失败
    "message": null,
    "data": null
}
```
### 2.24 评价订单 √
`POST`   `/personal/order/comment`

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
orderno|String|<i class="icon-ok"></i>|订单号
customerId|int|<i class="icon-ok"></i>|评价的用户的ID
merchantId|String|<i class="icon-ok"></i>|评价的用户的ID
commodityId|String|<i class="icon-remove"></i>|团购或酒店房间id
images|String|<i class="icon-ok"></i>|图片附件ids
content|String|<i class="icon-ok"></i>|评论内容
star|double|<i class="icon-ok"></i>|总评分
environmentStar|double|<i class="icon-ok"></i>|环境评分
flavorStar|double|<i class="icon-remove"></i>|口味评分-美食才有
serviceStar|double|<i class="icon-ok"></i>|服务评分

**响应结果:**
```javascript
{
    "code": 0,  //0 - 接口调用成功，其他值表示失败
    "message": null,
    "data": null
}
```






### 2.25 我的团购券列表 √
`GET`   `/personal/ticket/groupbuys`

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
userId|int|<i class="icon-ok"></i>|用户ID
page|int|<i class="icon-remove"></i>|分页参数
size|int|<i class="icon-remove"></i>|分页参数

**响应结果:**
```javascript
{
    "code": 0,  //0 - 接口调用成功，其他值表示失败
    "message": null,
    "data": {
        "datas": [
           {
                "id": 1,
                "money": 20.20,//金额
                "name": "团购名称",//团购名称
                "ticketNo": "",//券码
                "orderno": "",//所属订单
                "merchantId": 2,//所属商家
                "createTime": "",//生成时间
                "startTime": "",//生效时间
                "endTime": "",//失效时间
                "usedTime": "",//使用时间
                "status": 0,//0-未使用 1-已使用 2-已过期 3-已删除
                "qrcode": 2,//二维码对应附件id
                "customerId": 2,//所属用户
                "qrcodeUrl": "",//二维码图片url
            }
        ],
        "total": 1,
        "size": 10,
        "page": 1,
        "pages": 1,
        "curSize": 1
    }
}
```
### 2.26 我的优惠券列表 √
`GET`   `/personal/ticket/discounts`

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
userId|int|<i class="icon-ok"></i>|用户ID
merchantId|int|<i class="icon-remove"></i>|所属商家id -- 在选择优惠券的时候可以传入此参数
page|int|<i class="icon-remove"></i>|分页参数
size|int|<i class="icon-remove"></i>|分页参数

**响应结果:**
```javascript
{
    "code": 0,  //0 - 接口调用成功，其他值表示失败
    "message": null,
    "data": {
        "datas": [
           {
                "id": 1,
                "customerId": 1,
                "merchantId": 2,
                "activityId": 1,//所属活动
                "merchantName": "",//商家名称
                "discount": 8.9,//折扣
                "minAmount": 52.50,//最低消费金额
                "createTime": "",//领用时间
                "startDate": "",//生效时间
                "endTime": "",//失效时间
                "usedTime": "",//使用时间
                "status": 0//0-未使用 1-已使用 2-已过期 3-已删除
            }
        ],
        "total": 1,
        "size": 10,
        "page": 1,
        "pages": 1,
        "curSize": 1
    }
}
```

### 2.27 我的积分 √
`GET`   `/personal/score`

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
userId|int|<i class="icon-ok"></i>|用户ID

**响应结果:**
```javascript
{
    "code": 0,  //0 - 接口调用成功，其他值表示失败
    "message": null,
    "data": 100//积分
}
```

### 2.28 积分明细 √
`GET`   `/personal/scores`

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
userId|int|<i class="icon-ok"></i>|用户ID
page|int|<i class="icon-remove"></i>|分页参数
size|int|<i class="icon-remove"></i>|分页参数
**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": {
        "datas": [
            {
                "id": 1,
                "customerId": 4,
                "score": 10,//积分
                "createTime": "2017-11-14 08:57:28",//创建时间
                "type": 2,//类型 1-评论积分 2-消费积分 3-签到积分 4-分享积分 5-邀请好友积分
                "describe": "签到送积分"//简单说明
            }
        ],
        "total": 1,
        "size": 10,
        "page": 1,
        "pages": 1,
        "curSize": 1
    }
}
```
### 2.29我的收藏-商家|团购  √
`GET`   `/personal/collections`

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
userId|int|<i class="icon-ok"></i>|用户ID
type|int|<i class="icon-ok"></i>|1-商家 2-团购 
page|int|<i class="icon-ok"></i>|分页参数
size|int|<i class="icon-ok"></i>|分页参数
**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": {
        "datas": [
            {
                "id": 1,
                "customerId": 1,
                "name":"虫二酒庄",//团购或者商家名称
                "type": 1,//1-商家 2-团购
                "targetId": 1,//收藏的目标的id type=1 时为商家id 2的时候为团购的id
                "image": 0,//图片id
                "star": 0,//评分
                "price": 3.5,//价格
                "sellNum": 0,//卖出的数量 type=2时
                "imageUrl": "http://39.106.13.51:10081/attachment/ajax/visit/0"//图标地址
            }
        ],
        "total": 1,
        "size": 10,
        "page": 1,
        "pages": 1,
        "curSize": 1
    }
}
```
### 2.30 取消收藏  √
`POST`   `/personal/collection/{id}/delete`

**路径参数**
`id` `收藏的id`
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
无|
**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": null
}
```


### 2.31 新增收藏 √
`POST`   `/personal/collection/add`
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
customerId|int|<i class="icon-ok"></i>|用户ID
type|int|<i class="icon-ok"></i>|1-商家 2-团购
targetId|int|<i class="icon-ok"></i>|收藏的目标id 商家或者团购 和type对应

**错误码说明**
code| 错误原因  |返回消息
-----|---------|----
902|重复收藏|已经收藏，请不要重复收藏

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": null
}
```
### 2.32 我的推荐概况 √
`GET`   `/personal/recomment/survey`
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
USERiD|int|<i class="icon-ok"></i>|用户ID

**错误码说明**
code| 错误原因  |返回消息
-----|---------|----


**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": {
         "customerNum": 11,////推荐的用户
         "merchantNum": 10,//推荐的商家
         "amount": 0.01//推荐的奖励总额
    }
}
```
### 2.33 推荐的会员列表 √
`GET`   `/personal/recomment/users`

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
userId |int|<i class="icon-ok"></i> |用户ID
type|int|<i class="icon-ok"></i>| 1-用户 2-商户
page|int|<i class="icon-remove"></i>|分页参数
size|int|<i class="icon-remove"></i>|分页参数

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": {
        "datas": [
            {
                "id": 0,
                "name": "张三",
                "mobile": "15056920791",
                "icon": 1,
                "createTime": "2017-12-12",
                "iconUrl": "http://39.106.13.51:10081/attachment/ajax/visit/1"
            }
        ],
        "total": 1,
        "size": 10,
        "page": 1,
        "pages": 1,
        "curSize": 1
    }
}
```
### 2.34 推荐的奖励列表 √
`GET`   `/personal/recomment/rewards`

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
userId |int|<i class="icon-ok"></i> |用户ID
type|int|<i class="icon-ok"></i>| 1-用户 2-商户
page|int|<i class="icon-remove"></i>|分页参数
size|int|<i class="icon-remove"></i>|分页参数

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": {
        "datas": [
            {//同2.13 余额明细
                "id": 1,
                "customerId": 4,//用户id
                "money": 10,//金额
                "createTime": "2017-11-10 08:36:16",//时间
                "describe": "注册送现金",//简单描述
                "type": 6//1-推荐消费收入 2-下单收入 3-红包收入 4-推荐商户收入 5-活动收入 6-注册送现金  7-消费支出 8-提现 9- 商户退款
            }
        ],
        "total": 1,
        "size": 10,
        "page": 1,
        "pages": 1,
        "curSize": 1
    }
}
```
### 2.35 申请合作 √
`POST`   `/personal/cooperation/add`
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
customerId|int|<i class="icon-ok"></i>|用户ID
companyName|int|<i class="icon-ok"></i>| 公司名称
mobile|String|<i class="icon-ok"></i>|电话
companyAddress|int|<i class="icon-remove"></i>|公司地址
companyPerson|int|<i class="icon-ok"></i>| 公司负责人
product|int|<i class="icon-remove"></i>|投放产品
type|int|<i class="icon-ok"></i>| 1-商城 2-城市代理
city|String|<i class="icon-remove"></i>|意向代理城市
amount|double|<i class="icon-remove"></i>| 意向投资金额



**错误码说明**
code| 错误原因  |返回消息
-----|---------|----


**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": null
}
```
### 2.36 我的消息 √
`GET`   `/personal/msgs`

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
userId |int|<i class="icon-ok"></i> |用户ID
status |int|<i class="icon-ok"></i> | 1-用户 2-商户
type |int|<i class="icon-ok"></i> | 1-个人消息 2-系统信息  
page|int|<i class="icon-remove"></i>|分页参数
size|int|<i class="icon-remove"></i>|分页参数

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": {
        "datas": [
            {
                "id": 1,
                "customerId": 1,
                "role": 1,//1-用户 2-商户
                "content": "消息内容",
                "createTime": "2017-12-12",
                "readed": 0,//0-未读 1-已读
                "type": 2,// 1-个人消息 2-系统信息  
                "isDelete": 0,
                "msgId": 1
            }
        ],
        "total": 1,
        "size": 10,
        "page": 1,
        "pages": 1,
        "curSize": 1
    }
}
```

### 2.37 消息详情(置为已读) √

`GET`   `/personal/msg/{id}`

**路径参数**
`id` `消息id`

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
无|

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data":   
        {
            "id": 1,
            "customerId": 1,
            "role": 1,//1-用户 2-商户
            "content": "消息内容",
            "createTime": "2017-12-12",
            "readed": 0,//0-未读 1-已读
            "type": 2,// 1-个人消息 2-系统信息  
            "isDelete": 0,
            "msgId": 1
        }
}
```
### 2.38 删除消息 √

`POST`   `/personal/msg/{id}/delete`

**路径参数**
`id` `消息id`

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----|
无|

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": null
        
}
```

### 积分商城与兑换相关

### 2.5-01 积分商品分类 √
`GET`   `/personal/swap/categorys`

**路径参数**
`无`

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
无|

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": [
        {
            "id":1,
            "name":"家具百货",
            "iconUrl":"url"//图标
        }
    ]    
        
}
```
### 2.5-02 积分兑换商品列表 √
`GET`   `/personal/swap/malls`

**路径参数**
`无`

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
categoryId|int|<i class="icon-renove"></i>|分类id
page|int|<i class="icon-renove"></i>|分页参数
size|int|<i class="icon-renove"></i>|分页参数

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data":{
        "datas": [
           {
                "id": 1,
                "name": "名称",
                "score": 2000,//所需积分
                "price": 599.99,//商品价格
                "norms": "",//规格描述，多个用逗号分隔
                "icon": 1,
                "iconUul":"url"//图标url
                "merchantName": "",//商户名称
                "merchantTel": "",//商户热线
                "merchantServiceTime": "",商户服务时间
                "swapedNum": 12,//已兑换数量
                "describe": "",//图文描述
                "categoryId": 2,//所属分类
                "createTime": "2012-12-12" //时间
           }
        ],
        "total": 1,
        "size": 10,
        "page": 1,
        "pages": 1,
        "curSize": 1
    }
        
}
```

### 2.5-03 积分商品详情  √
`GET`   `/personal/swap/mall/{id}`

**路径参数**
`id` `商品id`

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
无|

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": {
                "id": 1,
                "name": "名称",
                "score": 2000,//所需积分
                "price": 599.99,//商品价格
                "norms": "",//规格描述，多个用逗号分隔
                "icon": 1,
                "iconUul":"url"//图标url
                "merchantName": "",//商户名称
                "merchantTel": "",//商户热线
                "merchantServiceTime": "",商户服务时间
                "swapedNum": 12,//已兑换数量
                "describe": "",//图文描述
                "categoryId": 2,//所属分类
                "createTime": "2012-12-12" //时间
           }
        
}
```


### 2.5-04 兑换积分商品 √
> 前端注意先判断积分是否足够

`POST`   `/personal/swap/exchange`

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
userId|int|<i class="icon-ok"></i>|用户ID
mallId|int|<i class="icon-ok"></i>|商品id
quantity|int|<i class="icon-ok"></i>| 数量 大于0
addressId|int|<i class="icon-ok"></i>| 收获地址id
remark|String|<i class="icon-remove"></i>| 一些备注说明

**错误码说明**
code| 错误原因  |返回消息
-----|---------|----
812|不存在的积分商品|不存在的积分商品
1101|积分不足,不可兑换品|积分不足,不可兑换

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": {//兑换的详情
            "id": 1,
            "swapno": "",//兑换码
            "customerId": 2,//所属用户
            "swapMallId": 2,//兑换的商品id
            "quantity": 2,//数量
            "unitScore": 30,//兑换的单个商品积分
            "score": 60,//所使用的总积分
            "addressId": 2,//收货地址id
            "status": 1,//订单状态 0-未发货 1-配送中 2-已完成
            "remark": "",//备注
            "createTime": "2012-12-12 12:00",//兑换时间
            "address": {//收货信息
                "id": 2,
                "customerId": 4,
                "deliveryName": "啦啦啦",//收货人姓名
                "deliveryMobile": "15099998877",//电话
                "provinceId": 340000,//省
                "provinceName": "安徽省",
                "cityId": 340100,//市
                "cityName": "合肥市",
                "areaId": 340103,//区
                "areaName": "庐阳区",
                "address": "翡翠花园"//具体地址
            },
            "merchantName": "",//商家名称
            "icon": 1,
            "iconUrl": "url",//图标url
            "statusDesc": "未发货"//订单状态描述 0-未发货 1-配送中 2-已完成
    }
}
```
### 2.5-05 我的兑换列表 √

`GET`   `/personal/swap/exchanges`

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
userId|int|<i class="icon-ok"></i>|用户ID
page|int|<i class="icon-remove"></i>|分页参数
size|int|<i class="icon-remove"></i>|分页参数

**错误码说明**
code| 错误原因  |返回消息
-----|---------|----


**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data":{
        "datas": [
            {
              "id": 1,
            "swapno": "",//兑换码
            "customerId": 2,//所属用户
            "swapMallId": 2,//兑换的商品id
            "quantity": 2,//数量
            "unitScore": 30,//兑换的单个商品积分
            "score": 60,//所使用的总积分
            "addressId": 2,//收货地址id
            "status": 1,//订单状态 0-未发货 1-配送中 2-已完成
            "remark": "",//备注
            "createTime": "2012-12-12 12:00",//兑换时间
            "merchantName": "",//商家名称
            "icon": 1,
            "iconUrl": "url",//图标url
            "statusDesc": "未发货"//订单状态描述 0-未发货 1-配送中 2-已完成
            }
        ],
        "total": 1,
        "size": 10,
        "page": 1,
        "pages": 1,
        "curSize": 1,
        "lookup": {
            "page": 1,
            "size": 10,
            "userId": 1,
            "type": 0,
            "status": 0,
            "merchantId": 0
        }
    }
}
```


### 2.5-06 我的兑换详情 √

`GET`   `/personal/swap/exchange/{id}`

**路径参数**
`id` `兑换id`
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
无|

**错误码说明**
code| 错误原因  |返回消息
-----|---------|----


**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data":{
     "id": 1,
            "swapno": "",//兑换码
            "customerId": 2,//所属用户
            "swapMallId": 2,//兑换的商品id
            "quantity": 2,//数量
            "unitScore": 30,//兑换的单个商品积分
            "score": 60,//所使用的总积分
            "addressId": 2,//收货地址id
            "status": 1,//订单状态 0-未发货 1-配送中 2-已完成
            "remark": "",//备注
            "createTime": "2012-12-12 12:00",//兑换时间
            "address": {//收货信息
                "id": 2,
                "customerId": 4,
                "deliveryName": "啦啦啦",//收货人姓名
                "deliveryMobile": "15099998877",//电话
                "provinceId": 340000,//省
                "provinceName": "安徽省",
                "cityId": 340100,//市
                "cityName": "合肥市",
                "areaId": 340103,//区
                "areaName": "庐阳区",
                "address": "翡翠花园"//具体地址
            },
            "merchantName": "",//商家名称
            "icon": 1,
            "iconUrl": "url",//图标url
            "statusDesc": "未发货"//订单状态描述 0-未发货 1-配送中 2-已完成
       
    }
}
```








## 3、商家与商品
### 3.01 平台特色分类 √
> 城市相关 分首页和百惠店

`GET`   `/commodity/platformCategories`

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
cityId|int|<i class="icon-ok"></i>|当前城市id
type|int|<i class="icon-ok"></i>|展示位置 1-首页 2-百惠店
page|int|<i class="icon-remove"></i>|分页参数
size|int|<i class="icon-remove"></i>|分页参数

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": {
        "datas": [
            {
                "id": 1,
                "name":"分类名称",//分类名称
                "type": 1,//1-首页 2-百惠店
                "cityId": 10001,//所属城市id
                "icon":1,
                "iconUrl": "http://39.106.13.51:10081/attachment/ajax/visit/1"//图标地址
               
            }
        ],
        "total": 1,
        "size": 10,
        "page": 1,
        "pages": 1,
        "curSize": 1
    }
}
```
### 3.02 平台商家广告位 √
> 城市相关 分首页和百惠店

`GET`   `/commodity/adPositions`

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
cityId|int|<i class="icon-ok"></i>|当前城市id
type|int|<i class="icon-ok"></i>|展示位置 1-首页 2-百惠店

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data":[
            {
                "id": 1,
                "type": 1,//1-首页 2-百惠店
                "cityId": 10001,//所属城市id
                "merchantId":1,//商家id
                "merchantName":"商家名称",//商家名称
                "merchantType":1,//商家类型 类型 1-美食 2-酒店预订 3-其他 
                "platformDesc":"平台对商家的描述",//平台对商家的描述
                "icon":1,
                "iconUrl": "http://39.106.13.51:10081/attachment/ajax/visit/1"//图标地址
               
            }
        ]
}
```
### 3.03 平台分类商家广告位 √
> 进入商家分类的时候 列表上方的一个广告位

`GET`   `/commodity/categoryAdPosition`

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
cityId|int|<i class="icon-ok"></i>|当前城市id
merchantCategory|int|<i class="icon-ok"></i>|平台商家分类的id

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": {
                "id": 1,
                "type": 1,//1-首页 2-百惠店
                "cityId": 10001,//所属城市id
                "merchantId":1,//商家id
                "merchantName":"商家名称",//商家名称
                "merchantType":1,//商家类型 类型 1-美食 2-酒店预订 3-其他 
                "platformDesc":"平台对商家的描述",//平台对商家的描述
                "icon":1,
                "iconUrl": "http://39.106.13.51:10081/attachment/ajax/visit/1"//图标地址
            }
    }
}
```
### 3.04 平台分类商家列表 √
`GET`   `/commodity/merchants`

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
cityId|int|<i class="icon-ok"></i>|当前城市id
merchantCategory|int|<i class="icon-ok"></i>|平台分类id
longitude|double|<i class="icon-ok"></i>|当前经度
latitude|double|<i class="icon-ok"></i>|当前纬度
distance|int|<i class="icon-remove"></i>|距离范围内，单位m
sortType|int|<i class="icon-remove"></i>|排序字段 0-价格 1- 距离 2-签到  3-好评 4-销量 
orderType|int|<i class="icon-remove"></i>|排序 方式 1-升序 2-降序
page|int|<i class="icon-remove"></i>|分页参数
size|int|<i class="icon-remove"></i>|分页参数
**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": {
        "datas": [
            {
                "id": 1,
                "name":"商家姓名",//商家名称
                "star": 4.5,//评星
                "distance": 1200.2,//距离单位m
                "distanceDesc":"1.2千米",//距离的描述 如523米 1.2千米
                "price":12.5,//价格
                "sellNum":50,//销售量
                "activity":"1,2",//当前活动 1-满减 2-优惠券，3-立减都有则逗号分隔
                "activities":["满减","优惠券","立减"],//当前活动的描述列表
                "categoryId":2,//平台分类id
                "categoryName":"分类名称",//分类的名称
                "areaName":"沙家浜",// 商家做在区域
                "type":1,//商家类型 1-美食 2-酒店预订 3-其他
                 "niceCommentNum":12,//好评数
                "hotelLevel":62,//酒店星级 1-客栈 2-公寓 3-经济舒适型 4-高档豪华型 5-经济连锁 6-二星酒店 7-三星酒店 8-四星 9-五星
                "icon":1,
                "iconUrl": "http://39.106.13.51:10081/attachment/ajax/visit/1"//图标地址
               
            }
        ],
        "total": 1,
        "size": 10,
        "page": 1,
        "pages": 1,
        "curSize": 1
    }
}
```

### 3.05 特色分类商家列表 √
`GET`   `/commodity/uniqueMerchants`

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
cityId|int|<i class="icon-ok"></i>|当前城市id
merchantCategory|int|<i class="icon-ok"></i>|特色商家分类id
longitude|double|<i class="icon-ok"></i>|当前经度
latitude|double|<i class="icon-ok"></i>|当前纬度
distance|int|<i class="icon-remove"></i>|距离范围内，单位m
sortType|int|<i class="icon-remove"></i>|排序字段 0-价格 1- 距离 2-签到  3-好评 4-销量 
orderType|int|<i class="icon-remove"></i>|排序 方式 1-升序 2-降序
page|int|<i class="icon-remove"></i>|分页参数
size|int|<i class="icon-remove"></i>|分页参数
**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": {
        "datas": [
            {
                "id": 1,
                "name":"商家姓名",//商家名称
                "star": 4.5,//评星
                "distance": 1200.2,//距离单位m
                "distanceDesc":"1.2千米",//距离的描述 如523米 1.2千米
                "price":12.5,//价格
                "sellNum":50,//销售量
                "activity":"1,2,3",//当前活动 1-满减 2-优惠券，3-立减都有则逗号分隔
                "activities":["满减","优惠券","立减"],//当前活动的描述列表
                "categoryId":2,//平台分类id
                "categoryName":"分类名称",//分类的名称
                "areaName":"沙家浜",// 商家做在区域
                "type":1,//商家类型 1-美食 2-酒店预订 3-其他
                 "niceCommentNum":12,//好评数
                "hotelLevel":62,//酒店星级 1-客栈 2-公寓 3-经济舒适型 4-高档豪华型 5-经济连锁 6-二星酒店 7-三星酒店 8-四星 9-五星
                "icon":1,
                "iconUrl": "http://39.106.13.51:10081/attachment/ajax/visit/1"//图标地址
               
            }
        ],
        "total": 1,
        "size": 10,
        "page": 1,
        "pages": 1,
        "curSize": 1
    }
}
```
### 3.06 镗镗锣精选商家列表 √
`GET`   `/commodity/wellChosenes`

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
cityId|int|<i class="icon-ok"></i>|当前城市id
page|int|<i class="icon-remove"></i>|分页参数
size|int|<i class="icon-remove"></i>|分页参数
**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": {
        "datas": [
            {//去掉了距离
                "id": 1,
                "name":"商家姓名",//商家名称
                "star": 4.5,//评星
                "price":12.5,//价格
                "sellNum":50,//销售量
                "activity":"1,2",//当前活动 1-满减 2-优惠券，3-立减 都有则逗号分隔
                "activities":["满减","优惠券"],//当前活动的描述列表
                "categoryId":2,//平台分类id
                "categoryName":"分类名称",//分类的名称
                "areaName":"沙家浜",// 商家做在区域
                "type":1,//商家类型 1-美食 2-酒店预订 3-其他
                 "niceCommentNum":12,//好评数
                "hotelLevel":62,//酒店星级 1-客栈 2-公寓 3-经济舒适型 4-高档豪华型 5-经济连锁 6-二星酒店 7-三星酒店 8-四星 9-五星
                "icon":1,
                "iconUrl": "http://39.106.13.51:10081/attachment/ajax/visit/1"//图标地址
               
            }
        ],
        "total": 1,
        "size": 10,
        "page": 1,
        "pages": 1,
        "curSize": 1
    }
}
```
### 3.07 为你推荐 √

> 只显示当前定位方圆5000m的所有商家 排序：距离升序 

`GET`   `/commodity/recommends`

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
cityId|int|<i class="icon-ok"></i>|当前城市id
longitude|double|<i class="icon-ok"></i>|当前经度
latitude|double|<i class="icon-ok"></i>|当前纬度
page|int|<i class="icon-remove"></i>|分页参数
size|int|<i class="icon-remove"></i>|分页参数
**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": {
        "datas": [
            {
                "id": 1,
                "name":"商家姓名",//商家名称
                "star": 4.5,//评星
                "price":12.5,//价格
                "distance": 1200.2,//距离单位m
                "distanceDesc":"1.2千米",//距离的描述 如523米 1.2千米
                "sellNum":50,//销售量
                "activity":"1,2",//当前活动 1-满减 2-优惠券，3-立减 都有则逗号分隔
                "activities":["满减","优惠券"],//当前活动的描述列表
                "categoryId":2,//平台分类id
                "categoryName":"分类名称",//分类的名称
                "areaName":"沙家浜",// 商家做在区域
                "type":1,//商家类型 1-美食 2-酒店预订 3-其他
                 "niceCommentNum":12,//好评数
                "hotelLevel":62,//酒店星级 1-客栈 2-公寓 3-经济舒适型 4-高档豪华型 5-经济连锁 6-二星酒店 7-三星酒店 8-四星 9-五星
                "icon":1,
                "iconUrl": "http://39.106.13.51:10081/attachment/ajax/visit/1"//图标地址
               
            }
        ],
        "total": 1,
        "size": 10,
        "page": 1,
        "pages": 1,
        "curSize": 1
    }
}
```
### 3.08 人气榜单 √
> 根据 人气数或销量数 排序 也需要传经纬度

`GET`   `/commodity/bestBills`

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
cityId|int|<i class="icon-ok"></i>|当前城市id
longitude|double|<i class="icon-ok"></i>|当前经度
latitude|double|<i class="icon-ok"></i>|当前纬度
page|int|<i class="icon-remove"></i>|分页参数
size|int|<i class="icon-remove"></i>|分页参数
**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": {
        "datas": [
            {
                "id": 1,
                "name":"商家姓名",//商家名称
                "star": 4.5,//评星
                "price":12.5,//价格
                "distance": 1200.2,//距离单位m
                "distanceDesc":"1.2千米",//距离的描述 如523米 1.2千米
                "sellNum":50,//销售量
                "activity":"1,2",//当前活动 1-满减 2-优惠券，3-立减 都有则逗号分隔
                "activities":["满减","优惠券"],//当前活动的描述列表
                "categoryId":2,//平台分类id
                "categoryName":"分类名称",//分类的名称
                "areaName":"沙家浜",// 商家做在区域
                "type":1,//商家类型 1-美食 2-酒店预订 3-其他
                "niceCommentNum":12,//好评数
                "hotelLevel":62,//酒店星级 1-客栈 2-公寓 3-经济舒适型 4-高档豪华型 5-经济连锁 6-二星酒店 7-三星酒店 8-四星 9-五星
                "icon":1,
                "iconUrl": "http://39.106.13.51:10081/attachment/ajax/visit/1"//图标地址
               
            }
        ],
        "total": 1,
        "size": 10,
        "page": 1,
        "pages": 1,
        "curSize": 1
    }
}
```
### 3.09 猜你喜欢 √
> 去过最多的店 方圆10千米 由近到远

`GET`   `/commodity/guesses`

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
userId|int|<i class="icon-ok"></i>|当前登陆用户id
cityId|int|<i class="icon-ok"></i>|当前城市id
longitude|double|<i class="icon-ok"></i>|当前经度
latitude|double|<i class="icon-ok"></i>|当前纬度
page|int|<i class="icon-remove"></i>|分页参数
size|int|<i class="icon-remove"></i>|分页参数
**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": {
        "datas": [
            {
                "id": 1,
                "name":"商家姓名",//商家名称
                "star": 4.5,//评星
                "price":12.5,//价格
                "distance": 1200.2,//距离单位m
                "distanceDesc":"1.2千米",//距离的描述 如523米 1.2千米
                "sellNum":50,//销售量
                "activity":"1,2",//当前活动 1-满减 2-优惠券，3-立减 都有则逗号分隔
                "activities":["满减","优惠券"],//当前活动的描述列表
                "categoryId":2,//平台分类id
                "categoryName":"分类名称",//分类的名称
                "areaName":"沙家浜",// 商家做在区域
                "type":1,//商家类型 1-美食 2-酒店预订 3-其他
                "niceCommentNum":12,//好评数
                "hotelLevel":62,//酒店星级 1-客栈 2-公寓 3-经济舒适型 4-高档豪华型 5-经济连锁 6-二星酒店 7-三星酒店 8-四星 9-五星
                "icon":1,
                "iconUrl": "http://39.106.13.51:10081/attachment/ajax/visit/1"//图标地址
               
            }
        ],
        "total": 1,
        "size": 10,
        "page": 1,
        "pages": 1,
        "curSize": 1
    }
}
```
### 3.10 商家搜索 √

`GET`   `/commodity/search`

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
cityId|int|<i class="icon-ok"></i>|当前城市id
longitude|double|<i class="icon-ok"></i>|当前经度
latitude|double|<i class="icon-ok"></i>|当前纬度
keywords|String|<i class="icon-remove"></i>|搜索的关键字
page|int|<i class="icon-remove"></i>||分页参数
size|int|<i class="icon-remove"></i>|分页参数
**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": {
        "datas": [
            {
                "id": 1,
                "name":"商家姓名",//商家名称
                "star": 4.5,//评星
                "price":12.5,//价格
                "distance": 1200.2,//距离单位m
                "distanceDesc":"1.2千米",//距离的描述 如523米 1.2千米
                "sellNum":50,//销售量
                "activity":"1,2",//当前活动 1-满减 2-优惠券，3-立减都有则逗号分隔
                "activities":["满减","优惠券"],//当前活动的描述列表
                "categoryId":2,//平台分类id
                "categoryName":"分类名称",//分类的名称
                "areaName":"沙家浜",// 商家做在区域
                "type":1,//商家类型 1-美食 2-酒店预订 3-其他
                "niceCommentNum":12,//好评数
                "hotelLevel":62,//酒店星级 1-客栈 2-公寓 3-经济舒适型 4-高档豪华型 5-经济连锁 6-二星酒店 7-三星酒店 8-四星 9-五星
                "icon":1,
                "iconUrl": "http://39.106.13.51:10081/attachment/ajax/visit/1"//图标地址
               
            }
        ],
        "total": 1,
        "size": 10,
        "page": 1,
        "pages": 1,
        "curSize": 1
    }
}
```
### 3.11 商家详情 √
`GET`   `/commodity/merchant/{id}`
**路径参数**
`id` `商家id`
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
userId|int|<i class="icon-remove"></i>| 当前登陆用户
**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data":  {
                "id": 1,
                "name":"商家姓名",//商家名称
                "type":1,//商家类型 1-美食 2-酒店预订 3-其他
                "inPic":"2,3,4",//内景图对应附件ids 多张
                "inPicUrls":["url1","url2","url3"],//内景图url列表
                "star": 4.5,//评星
                "flavorStar": 8.5,//口味评分
                "environmentStar": 8.5,//环境评分
                "serviceStar": 8.5,//服务评分
                "price":12.5,//价格
                "areaName":"沙家浜",// 商家做在区域
                "address":"具体地址",// 具体地址
                "landmarks":"附件地标",// 附件地标
                "longitude": 114.23,//经度
                "latitude": 34.202,//纬度
                "mobile":"18812341234",// 电话
                "commentNum":50,//评论数
                "viewNum":51,//来过 签到人数
                "businessHours":"24小时营业",//营业时间
                "facility":"",//商家设施  多个逗号分隔
                "signed":false,//是否签到 - 今日
                "collected":false,//是否收藏
                "comments":[//2条评论
                   { 
                    "id":1,//
                    "orderno":"1332asd",//订单号
                    "customerId":1,//用户id
                    "customerHeadpic":1,//用户头像附件id
                    "customerHeadpicUrl":"url",//头像url
                    "customerName":"张三",//
                    "images":"2,3",//评论图片
                    "imageUrls":["url1","url2","url3"],//t图片url列表
                    "content":"不错不错",//评论内容
                    "star":4.5,//评星
                    "reply":"谢谢",//商家回复
                    "praiseNum":150,//评论点赞数
                    "praised":true,//当前用户是否对此点赞
                    "createTime":"2017-11-12"//评论时间
                    },{
                    //....
                    }
                ],
                "commodities":[//团购套餐2个
                    {
                    "id":1,//
                    "name":"团购测试商品",//订单号
                    "icon":4,//icon
                    "iconUrl":"url",//icon URL
                    "price":25.5,//价格
                    "sellNum":50//销售数量
                    },{
                    //...
                    }
                ],
                "activities":[//当前商家活动列表 每种最多一个 
                     {
                        "id": 1,
                        "type": 1,//1-满减 2-全场折扣券 3-立减
                        "startDate": "2017-11-21",//开始时间
                        "endDate": "2017-11-30",//结束时间
                        "rules": "100:10,200:20,300:30",//满减时候的规则每组用英文逗号分隔，组内用英文冒号分隔，见ruleDescs的
                        "limited": false,
                        "limitNum": 0,
                        "minAmount": 0,
                        "discount": 0,
                        "status": 1,//0-初始 1-进行中 2-已结束
                        "ruleDescs": [
                            {
                                "fill": 100,//满100元
                                "minus": 10//减10元
                            },
                            {
                                "fill": 200,
                                "minus": 20
                            },
                            {
                                "fill": 300,
                                "minus": 30
                            }
                        ]
                    },
                    {
                        "id": 2,
                        "type": 2,
                        "startDate": "2017-11-20",
                        "endDate": "2017-12-07",
                        "limited": false,//折扣券是否限制数量
                        "limitNum": 200,//折扣最大的发放数量
                        "minAmount": 30,//折扣券最低消费金额
                        "discount": 8.0,折扣券折扣
                        "status": 0//0-初始 1-进行中 2-已结束
                    }，
                     {
                        "id": 2,
                        "type":3,//立减
                        "startDate": "2017-11-20",
                        "endDate": "2017-12-07",
                        "minus":2.50,//立减活动的时候立减的金额
                        "status": 0//0-初始 1-进行中 2-已结束
                    }
                ]
               
            }
}
```
### 3.12 商家图文介绍 √
`GET`   `/commodity/merchant/{id}/describe`

**路径参数**
`id` `商家id`
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
无|

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": "图文说明"
}
```
### 3.13 商家团购列表 √
`GET`   `/commodity/groupbuys`

**路径参数**
`无`
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
merchantId|int|<i class="icon-ok"></i>|商家id
page|int|<i class="icon-remove"></i>|分页参数
size|int|<i class="icon-remove"></i>|分页参数

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": {
        "datas": [
            {
                "id": 1,
                "name": "团购商品",
                "price": 0.01,//价格
                "sellNum": 10,//销售数量
                "icon": 3,//图标
                "iconUrl": "http://39.106.13.51:10081/attachment/ajax/visit/3"//图标url
            }
        ],
        "total": 1,
        "size": 10,
        "page": 1,
        "pages": 1,
        "curSize": 1
    }
}
```
### 3.14 团购详情 √
`GET`   `/commodity/groupbuy/{id}`

**路径参数**
`id` `团购商品id`
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
无|

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data":   
        {
        "id": 1,
        "name": "团购商品",
        "price": 0.01,//价格
        "describe":"团购图文详情",
        "startTime": "2017-11-22",//开始时间
        "endTime": "2017-12-09",//结束时间
        "consumedAllHours": false,//是否24小时可消费
        "consumeStartTime": "23:00",//消费开始时间
        "consumeEndTime": "12:30",//消费结束时间
        "labels": "政策1,政策2",//团购标签(政策) 多个逗号分隔
        "tips": "须知1,须知2,须知3",//购买须知 多个逗号分隔
        "praiseRate":"90%",//好评率
        "star": 4.5,//评星
        "images": "2,3",//图片ids
        "imageUrls": [//图片列表
            "http://39.106.13.51:10081/attachment/ajax/visit/2",
            "http://39.106.13.51:10081/attachment/ajax/visit/3"
        ]
    }
}
```
### 3.15 商家/团购评论列表 √
`GET`   `/commodity/comments`

**路径参数**
`无`
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
merchantId|int|<i class="icon-remove"></i>|商家id 传入此参数表示查询商家评论列表
groupbuyId|int|<i class="icon-remove"></i>|团购id 传入此参数表示查询团购商品评论列表
userId|int|<i class="icon-remove"></i>| 当前登陆用户
page|int|<i class="icon-remove"></i>|分页参数
size|int|<i class="icon-remove"></i>|分页参数

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": {
        "datas": [
            {
                 "id":1,//
                "orderno":"1332asd",//订单号
                "customerId":1,//用户id
                "customerHeadpic":1,//用户头像附件id
                "customerHeadpicUrl":"url",//头像url
                "customerName":"张三",//
                "images":"2,3",//评论图片
                "imageUrls":["url1","url2","url3"],//图片url列表
                "content":"不错不错",//评论内容
                "star":4.5,//评星
                "reply":"谢谢",//商家回复
                "praiseNum":150,//评论点赞数
                "praised":true,//当前用户是否对此点赞
                "createTime":"2017-11-12"//评论时间
            }
        ],
        "total": 1,
        "size": 10,
        "page": 1,
        "pages": 1,
        "curSize": 1
    }
}
```
### 3.16 酒店商家列表 √
`GET`   `/commodity/hotelMerchants`

**路径参数**
`无`
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
cityId|int|<i class="icon-ok"></i>|当前城市id
longitude|double|<i class="icon-ok"></i>|当前经度
latitude|double|<i class="icon-ok"></i>|当前纬度
upPrice|double|<i class="icon-remove"></i>|价格上限
downPrice|double|<i class="icon-remove"></i>|价格下限
hotelLevel|int|<i class="icon-remove"></i>|酒店星级 1-客栈 2-公寓 3-经济舒适型 4-高档豪华型 5-经济连锁 6-二星酒店 7-三星酒店 8-四星 9-五星
keywords|string|<i class="icon-remove"></i>|查询的关键字
distance|int|<i class="icon-remove"></i>|距离范围内，单位m
sortType|int|<i class="icon-remove"></i>|排序字段  0-价格 1- 距离  2-好评
orderType|int|<i class="icon-remove"></i>|排序 方式 1-升序 2-降序
page|int|<i class="icon-remove"></i>|分页参数
size|int|<i class="icon-remove"></i>|分页参数

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": {
        "datas": [
            {//同普通商家列表 多个好评数niceCommentNum和 酒店星级 hotelLevel
                "id": 1,
                "name":"商家姓名",//商家名称
                "star": 4.5,//评星
                "distance": 1200.2,//距离单位m
                "distanceDesc":"1.2千米",//距离的描述 如523米 1.2千米
                "price":12.5,//价格
                "sellNum":50,//销售量
                "activity":"1,2",//当前活动 1-满减 2-优惠券，都有则逗号分隔
                "activities":["满减","优惠券"],//当前活动的描述列表
                "categoryId":2,//平台分类id
                "categoryName":"分类名称",//分类的名称
                "areaName":"沙家浜",// 商家做在区域
                "type":1,//商家类型 1-美食 2-酒店预订 3-其他
                "niceCommentNum":12,//好评数
                "hotelLevel":62,//酒店星级 1-客栈 2-公寓 3-经济舒适型 4-高档豪华型 5-经济连锁 6-二星酒店 7-三星酒店 8-四星 9-五星
                "icon":1,
                "iconUrl": "http://39.106.13.51:10081/attachment/ajax/visit/1"//图标地址
            }
        ],
        "total": 1,
        "size": 10,
        "page": 1,
        "pages": 1,
        "curSize": 1
    }
}
```
### 3.17酒店商家详情  √
> 返回数据同商家列表 但是去掉商品(团购)列表  另多一个入店时间和离店时间

`GET`   `/commodity/hotel/{id}`
**路径参数**
`id` `商家id`
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
userId|int|<i class="icon-remove"></i>| 当前登陆用户
**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": {
                "id": 1,
                "name":"商家姓名",//商家名称
                "type":1,//商家类型 1-美食 2-酒店预订 3-其他
                "inPic":"2,3,4",//内景图对应附件ids 多张
                "inPicUrls":["url1","url2","url3"],//内景图url列表
                "star": 4.5,//评星
                "flavorStar": 8.5,//口味评分
                "environmentStar": 8.5,//环境评分
                "serviceStar": 8.5,//服务评分
                "price":12.5,//价格
                "areaName":"沙家浜",// 商家做在区域
                "address":"具体地址",// 具体地址
                "landmarks":"附件地标",// 附件地标
                "longitude": 114.23,//经度
                "latitude": 34.202,//纬度
                "mobile":"18812341234",// 电话
                "commentNum":50,//评论数
                "viewNum":51,//来过 签到人数
                "businessHours":"24小时营业",//营业时间
                "facility":"",//商家设施  多个逗号分隔
                "signed":false,//是否签到 - 今日
                "collected":false,//是否收藏
                "hotelInTime":"16:00",//入店时间
                "hotelOutTime":"12:00",//离店时间
                "comments":[//2条评论
                   { 
                    "id":1,//
                    "orderno":"1332asd",//订单号
                    "customerId":1,//用户id
                    "customerHeadpic":1,//用户头像附件id
                    "customerHeadpicUrl":"url",//头像url
                    "customerName":"张三",//
                    "images":"2,3",//评论图片
                    "imageUrls":["url1","url2","url3"],//t图片url列表
                    "content":"不错不错",//评论内容
                    "star":4.5,//评星
                    "reply":"谢谢",//商家回复
                    "praiseNum":150,//评论点赞数
                    "praised":true,//当前用户是否对此点赞
                    "createTime":"2017-11-12"//评论时间
                    },{
                    //....
                    }
                ],
                "commodities":[],
                "activities":[//当前商家活动列表 每种最多一个
                     {
                        "id": 1,
                        "type": 1,//1-满减 2-全场折扣券
                        "startDate": "2017-11-21",//开始时间
                        "endDate": "2017-11-30",//结束时间
                        "rules": "100:10,200:20,300:30",//满减时候的规则每组用英文逗号分隔，组内用英文冒号分隔，见ruleDescs的
                        "limited": false,
                        "limitNum": 0,
                        "minAmount": 0,
                        "discount": 0,
                        "status": 1,//0-初始 1-进行中 2-已结束
                        "ruleDescs": [
                            {
                                "fill": 100,//满100元
                                "minus": 10//减10元
                            },
                            {
                                "fill": 200,
                                "minus": 20
                            },
                            {
                                "fill": 300,
                                "minus": 30
                            }
                        ]
                    },
                    {
                        "id": 2,
                        "type": 2,
                        "startDate": "2017-11-20",
                        "endDate": "2017-12-07",
                        "limited": false,//折扣券是否限制数量
                        "limitNum": 200,//折扣最大的发放数量
                        "minAmount": 30,//折扣券最低消费金额
                        "discount": 8.0,折扣券折扣
                        "status": 0//0-初始 1-进行中 2-已结束
                    }
                ]
               
            }
}
```
### 3.18 酒店商家介绍 √
`GET`   `/commodity/hotel/{id}/describe`

**路径参数**
`id` `商家id`
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
无|

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": {
        "id":1,//merchantId
        "hotelInTime":"18:00",//入店时间
        "hotelOutTime":"12:20",//离店时间
        "hotelCanAddBed":true,//可否加床
        "hotelAddBedPrice":50.5,//加床价格
        "hotelForeigned":true,//可否接待外宾
        "hotelPeted":true,//是否携带宠物
        "hotelPledge":fase,//是否需要押金
        "describe":"....."//图文描述
    }
}
```
### 3.19 房间列表  √
`GET`   `/commodity/hotel/rooms`

**路径参数**
`无`
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
merchantId|int|<i class="icon-ok"></i>|当前酒店商家id

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": {
        "datas": [
            {
                "id": 1,
                "name":"豪华单人间",//房间标题
                "price":12.5,//价格
                "icon":1,
                "iconUrl": "http://39.106.13.51:10081/attachment/ajax/visit/1",//图标地址
                "roomWindow": 1,//窗户 1-无窗 2-有窗 3-部分有窗
                "roomBreakfast": 1,//早餐 1-无早餐 2-含早餐 3-双份早餐
                "roomBed": 1,//床型 1-单人床 2-豪华大床 3-双人床 4-三人床
                "roomPersonNum": 2,//可住几人
                "unsubscribeWay": 1,//退订政策 1-当天18：00前可免费取消订单 2 预定成功后 不可变更取消
                "verifyWay": 1//订单确认时间 1-1小时内确认 2-立即确认
            }
        ],
        "total": 1,
        "size": 10,
        "page": 1,
        "pages": 1,
        "curSize": 1
    }
}
```
### 3.20酒店房间详情  √
`GET`   `/commodity/hotel/room/{id}`

**路径参数**
`id` `房间id`
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
无|

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": {
        "id": 0,
        "name": "房间标题",
        "icon": 1,//图标对应附件id
        "price": 150,//价格
        "roomWindow": 1,//窗户 1-无窗 2-有窗 3-部分有窗
        "roomBreakfast": 2,//早餐 1-无早餐 2-含早餐 3-双份早餐
        "roomBed": 2,//床型 1-单人床 2-豪华大床 3-双人床 4-三人床
        "roomPersonNum": 3,//可住几人
        "unsubscribeWay": 2,//退订政策 1-当天18：00前可免费取消订单 2 预定成功后 不可变更取消
        "verifyWay": 1,//订单确认时间 1-1小时内确认 2-立即确认
        "images": "2,3",//图片ids
        "stock": 2,//库存
        "roomArea": 65.5,//面积 
        "roomNetwork": 2,//宽带 1-无网络 2-有线宽带 3-无线WIFI
        "roomFloor": 2,//楼层
        "roomWindowmView": 2,//窗景：1-无 2-风景2-城景 3-花园景 4-地标景 5-无敌海景
        "imageUrls": ["url1", "url2"],
        "iconUrl": "http://39.106.13.51:10081/attachment/ajax/visit/0"
    }
}
```

### 3.21 商家的签到列表  √
`GET`   `/commodity/signs`

**路径参数**
`id` `房间id`
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
merchantId|int|<i class="icon-ok"></i>|商家id
page|||分页参数
size|||分页参数

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data":{
        "datas": [
           {
                "id": 0,
                "customerId": 2,//用户id
                "customerName": "张三",//name
                "customerHeadpic": 1,//头像id
                "createTime": "2017-12-12",//签到时间
                "customerHeadPicUrl": "http://39.106.13.51:10081/attachment/ajax/visit/1"//头像地址
            }
        ],
        "total": 1,
        "size": 10,
        "page": 1,
        "pages": 1,
        "curSize": 1
    }
}
```

### 3.22 签到商家  √
`POST`   `/commodity/sign/add`

**路径参数**
`无` 

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
userId|int|<i class="icon-ok"></i>|用户id
merchantId|int|<i class="icon-ok"></i>|商家id

 **错误码说明**
code| 错误原因  |返回消息
-----|---------|----
901 |重复签到|已经签到，请不要重复签到
 
**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": null
}
```

### 3.23 对评论点赞  √
`POST`   `/commodity/praise/add`

**路径参数**
`id` `房间id`
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
userId|int|<i class="icon-ok"></i>|用户id
commentId|int|<i class="icon-ok"></i>|评论的id

 **错误码说明**
code| 错误原因  |返回消息
-----|---------|----
903 |重复点赞|已经点赞，请不要重复点赞

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": null
}
```

###购买的一些操作
### 3.5-01 领取打折券    √
`POST`   `/business/receiveTicket`

**路径参数**
`无``
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
userId|int|<i class="icon-ok"></i>|用户id
activityId|int|<i class="icon-ok"></i>|活动id

 **错误码说明**
code| 错误原因  |返回消息
-----|---------|----
807 |不存在的折扣活动，活动非为折扣活动，或者非激活状态|不存在的折扣活动
808 |打折券被领完了（有数了限制的活动）|打折券被领完了

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": {
        "id": 1,
        "customerId": 1,
        "merchantId": 2,
        "activityId": 1,//所属活动
        "merchantName": "",//商家名称
        "discount": 8.9,//折扣
        "minAmount": 52.50,//最低消费金额
        "createTime": "",//领用时间
        "startDate": "",//生效时间
        "endTime": "",//失效时间
        "usedTime": "",//使用时间
        "status": 0//0-未使用 1-已使用 2-已过期 3-已删除
    }
}
```
### 3.5-02 直接购买    √
> 直接购买不可使用优惠券，但是后台会自动判断满减

`POST`   `/business/buy`

**路径参数**
`无``
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
userId|int|<i class="icon-ok"></i>|用户id
merchantId|int|<i class="icon-ok"></i>|商户id
totalFee|double|<i class="icon-ok"></i>|总金额
payType|int|<i class="icon-ok"></i>|支付方式 1-余额支付 2-支付宝支付 3-微信支付

 **错误码说明**
code| 错误原因  |返回消息
-----|---------|----
802 |不存在的用户|不存在的用户
805 |不存在的商户|不存在的商户

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": {//存入订单表的基本信息 ，前端主要使用到的参数应为 订单号orderno，实际应支付金额 amount
        "id": 1,
        "orderno": "",
        "customerId": 1,//所属用户
        "merchantId": 2,//所属商家
        "commodityId": 0,//商品id
        "orderType": 1,//订单类型 1-直接购买 2-团购 3-酒店预订
        "totalFee": 50.20,//订单总金额
        "amount": 45.2,//订单实际支付金额
        "minusMoney": 5,//达到满减 减少的金额
        "reachMoney": 50,//达到的满减金额
        "ticketId": 0,//优惠券id
        "ticketDiscount": 9.8,//优惠券打的折扣
        "ticketDiscountMoney": 0,//优惠券打折优惠的金额
        "discountsDesc": "",//优惠简单说明
        "quantity": 0,//购买数量  团购时
        "status": 0,//订单状态
        "isComment": 0,//是否评价
        "orderImage": 1,//订单图标
        "orderName": "",//订单名称
        "mobile": "15012341234",
        "payType": 1,//1-余额支付 2-支付宝支付 3-微信支付
        "payTime": "2017-12-12",//支付成功时间
        "payOutNo": "",//第三方支付单号
        "createTime": "2017-12-12 12:12",//创建时间-购买时间
        "refundReason": 1,//退款理由 
        "isDelete": 0,//是否删除
        "merchantType": 1,//商家类型 1-美食 2-酒店预订 3-其他
        "remark": "",//购买的备注
        "roomNumber": 3,// 酒店预订-房间数
        "checkPerson": "",//酒店预订-入住人 多个逗号分隔
        "checkStartDate": "2017-12-12",//酒店预订-入住开始日期
        "checkEndDate": "2017-12-13",//酒店预订-入住结束日期
        "checkLastTime": "21:00"//酒店预订-预计最晚到店时间
    }
}
```

### 3.5-03 购买团购    √
 
`POST`   `/business/groupbuy`

**路径参数**
`无``
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
userId|int|<i class="icon-ok"></i>|用户id
merchantId|int|<i class="icon-ok"></i>|商户id
groupbuyId|int|<i class="icon-ok"></i>|团购id
totalFee|double|<i class="icon-ok"></i>|总金额
payType|int|<i class="icon-ok"></i>|支付方式 1-余额支付 2-支付宝支付 3-微信支付
quantity|int|<i class="icon-ok"></i>|数量 >0
mobile|String|<i class="icon-ok"></i>|电话
remark|String|<i class="icon-remove"></i>|备注
ticketId|int|<i class="icon-remove"></i>|打折券id
 **错误码说明**
code| 错误原因  |返回消息
-----|---------|----
802 |不存在的用户|不存在的用户
805 |不存在的商户|不存在的商户 不存在的团购
809 |不存在的团购|不存在的团购
1001 |总金额不等于 数量*单价|金额错误
810 |不存在的折扣券或者非 未使用的|不存在的折扣券
1002 |折扣券已过期|折扣券已过期
1003 |折扣券和商家不匹配|折扣券和商家不匹配
1004|折扣券没有达到最低使用标准|折扣券没有达到最低使用标准

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": {//存入订单表的基本信息 ，前端主要使用到的参数应为 订单号orderno，实际应支付金额 amount
        "id": 1,
        "orderno": "",
        "customerId": 1,//所属用户
        "merchantId": 2,//所属商家
        "commodityId": 0,//商品id
        "orderType": 1,//订单类型 1-直接购买 2-团购 3-酒店预订
        "totalFee": 50.20,//订单总金额
        "amount": 45.2,//订单实际支付金额
        "minusMoney": 5,//达到满减 减少的金额
        "reachMoney": 50,//达到的满减金额
        "ticketId": 0,//优惠券id
        "ticketDiscount": 9.8,//优惠券打的折扣
        "ticketDiscountMoney": 0,//优惠券打折优惠的金额
        "discountsDesc": "",//优惠简单说明
        "quantity": 0,//购买数量  团购时
        "status": 0,//订单状态
        "isComment": 0,//是否评价
        "orderImage": 1,//订单图标
        "orderName": "",//订单名称
        "mobile": "15012341234",
        "payType": 1,//1-余额支付 2-支付宝支付 3-微信支付
        "payTime": "2017-12-12",//支付成功时间
        "payOutNo": "",//第三方支付单号
        "createTime": "2017-12-12 12:12",//创建时间-购买时间
        "refundReason": 1,//退款理由 
        "isDelete": 0,//是否删除
        "merchantType": 1,//商家类型 1-美食 2-酒店预订 3-其他
        "remark": "",//购买的备注
        "roomNumber": 3,// 酒店预订-房间数
        "checkPerson": "",//酒店预订-入住人 多个逗号分隔
        "checkStartDate": "2017-12-12",//酒店预订-入住开始日期
        "checkEndDate": "2017-12-13",//酒店预订-入住结束日期
        "checkLastTime": "21:00"//酒店预订-预计最晚到店时间
    }
}
```
### 3.5-04 预定酒店    √
 
`POST`   `/business/reserve`

**路径参数**
`无``
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
userId|int|<i class="icon-ok"></i>|用户id
merchantId|int|<i class="icon-ok"></i>|商户id
totalFee|double|<i class="icon-ok"></i>|总金额
payType|int|<i class="icon-ok"></i>|支付方式 1-余额支付 2-支付宝支付 3-微信支付
hotelId|int|<i class="icon-ok"></i>|房间的id
roomNumber|int|<i class="icon-ok"></i>|入住几人
checkPerson|String|<i class="icon-ok"></i>|入住人姓名 多个逗号分隔
checkStartDate|Date|<i class="icon-ok"></i>|入住时间 yyyy-MM-dd
checkEndDate|Date|<i class="icon-ok"></i>|离开时间 yyyy-MM-dd
checkLastTime|Date|<i class="icon-ok"></i>|最迟入住时间  HH:mm
mobile|int|<i class="icon-ok"></i>|电话
remark|int|<i class="icon-remove"></i>|备注
ticketId|int|<i class="icon-remove"></i>|打折券id
 **错误码说明**
code| 错误原因  |返回消息
-----|---------|----
802 |不存在的用户|不存在的用户
805 |不存在的商户|不存在的商户 不存在的团购
812 |不存在的酒店房间|不存在的酒店房间
1001 |总金额不等于 数量*单价|金额错误
810 |不存在的折扣券或者非 未使用的|不存在的折扣券
1002 |折扣券已过期|折扣券已过期
1003 |折扣券和商家不匹配|折扣券和商家不匹配
1004|折扣券没有达到最低使用标准|折扣券没有达到最低使用标准


**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": {//存入订单表的基本信息 ，前端主要使用到的参数应为 订单号orderno，实际应支付金额 amount
        "id": 1,
        "orderno": "",
        "customerId": 1,//所属用户
        "merchantId": 2,//所属商家
        "commodityId": 0,//商品id
        "orderType": 1,//订单类型 1-直接购买 2-团购 3-酒店预订
        "totalFee": 50.20,//订单总金额
        "amount": 45.2,//订单实际支付金额
        "minusMoney": 5,//达到满减 减少的金额
        "reachMoney": 50,//达到的满减金额
        "ticketId": 0,//优惠券id
        "ticketDiscount": 9.8,//优惠券打的折扣
        "ticketDiscountMoney": 0,//优惠券打折优惠的金额
        "discountsDesc": "",//优惠简单说明
        "quantity": 0,//购买数量  团购时
        "status": 0,//订单状态
        "isComment": 0,//是否评价
        "orderImage": 1,//订单图标
        "orderName": "",//订单名称
        "mobile": "15012341234",
        "payType": 1,//1-余额支付 2-支付宝支付 3-微信支付
        "payTime": "2017-12-12",//支付成功时间
        "payOutNo": "",//第三方支付单号
        "createTime": "2017-12-12 12:12",//创建时间-购买时间
        "refundReason": 1,//退款理由 
        "isDelete": 0,//是否删除
        "merchantType": 1,//商家类型 1-美食 2-酒店预订 3-其他
        "remark": "",//购买的备注
        "roomNumber": 3,// 酒店预订-房间数
        "checkPerson": "",//酒店预订-入住人 多个逗号分隔
        "checkStartDate": "2017-12-12",//酒店预订-入住开始日期
        "checkEndDate": "2017-12-13",//酒店预订-入住结束日期
        "checkLastTime": "21:00"//酒店预订-预计最晚到店时间
    }
}
```

### 3.5-05 支付金额计算    √

> 直接个购买不可使用优惠券
 
`POST`   `/business/calcAmount`

**路径参数**
`无``
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
orderType|int|<i class="icon-remove"></i>| 订单类型 1-直接购买(默认) 2-团购 3-酒店预订
merchantId|int|<i class="icon-ok"></i>|商户id
totalFee|double|<i class="icon-ok"></i>|总金额
ticketId|int|<i class="icon-remove"></i>|打折券id
 **错误码说明**
code| 错误原因  |返回消息
-----|---------|----
802 |不存在的用户|不存在的用户
810 |不存在的折扣券或者非 未使用的|不存在的折扣券
1002 |折扣券已过期|折扣券已过期
1003 |折扣券和商家不匹配|折扣券和商家不匹配
1004|折扣券没有达到最低使用标准|折扣券没有达到最低使用标准

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": {
        "totalFee": 55.55,//总金额
        "amount": 53.33,//应支付金额
        "discountFee": 2.22,//优惠的金额
        "discountsDesc": "对优惠信息的简要描述"//优惠的描述
    }
}
```



## 4、社区
### 吃喝玩乐文章相关接口

### 4.1-01 文章列表
`GET`   `/community/articles`

**路径参数**
`无`
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
cityId|int|<i class="icon-ok"></i>|当前城市
page|int|<i class="icon-remove"></i>|分页参数
size|int|<i class="icon-remove"></i>|分页参数

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": {
        "datas": [
            {
            "id": 1,
            "title": "这是标题",
            "summary": "文章简介",
            "icon": 1,
            "iconUrl":"url", //图标url
            "cityId": 1,
            "categoryId": 2,
            "categoryName": "所属分类",
            "createTime": "2017-12-12 20:15",
            "praiseNum": 20,//点赞数
            "commentNum": 15,//评论数
            }
        ],
        "total": 1,
        "size": 10,
        "page": 1,
        "pages": 1,
        "curSize": 1
    }
}
```

### 4.1-02 文章详情 + 2条评论
`GET`   `/community/article/{id}`

**路径参数**
`id` `文章id`
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
userId|int|<i class="icon-remove"></i>|当前登陆用户id

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": {
            "id": 1,
            "title": "这是标题",
            "summary": "文章简介",
            "icon": 1,
            "iconUrl":"url", //图标url
            "cityId": 1,
            "categoryId": 2,
            "categoryName": "所属分类",
            "content": "....图文详情",
            "createTime": "2017-12-12 20:15",
            "praiseNum": 20,//点赞数
            "commentNum": 15//评论数
            "isPraised": true,//当前用户是否对此文章点赞
            "comments": [{//两条评论
                        "id": 1,
                        "articleId": 2,
                        "customerId": 2,
                        "images": "1,2",
                        "content": "不错哦",
                        "reply": "谢谢夸奖",
                        "imageUrls": ["url1","url2"]
                }
            ]
    }
}
```


### 4.1-03 文章评论列表
`GET`   `/community/article/comments`

**路径参数**
`无`
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
articleId|int|<i class="icon-ok"></i>|当前文章id
page|int|<i class="icon-remove"></i>|分页参数
size|int|<i class="icon-remove"></i>|分页参数

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": {
        "datas": [
            {
               "id": 1,
                "articleId": 2,
                "customerId": 2,
                "images": "1,2",
                "content": "不错哦",
                "reply": "谢谢夸奖",
                "imageUrls": ["url1","url2"]
        ],
        "total": 1,
        "size": 10,
        "page": 1,
        "pages": 1,
        "curSize": 1
    }
}
```


### 4.1-04 评论文章
`POST`   `/community/article/comment/add`

**路径参数**
`无`
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
articleId|int|<i class="icon-ok"></i>|当前文章id
customerId|int|<i class="icon-ok"></i>|用户id
images|String|<i class="icon-ok"></i>|图片附件ids
content|String|<i class="icon-ok"></i>|评论内容

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": null
}
```
### 4.1-05 点赞/取消点赞文章
> 已点赞则删除点赞 未点赞则新增点赞

`POST`   `/community/article/praise`

**路径参数**
`无`
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
articleId|int|<i class="icon-ok"></i>|当前文章id
userId|int|<i class="icon-ok"></i>|用户id

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": null
}
```
### 社区 活动相关

###4.2-01 当前活动列表 每种类型最多取一条 √
`GET`   `/activity/activities`

**路径参数**
`无`
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
无

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data":[
        {
            "id": 1,
            "type": 2,//1-抢红包 2-抢话费 3-推荐商家有奖
            "title": "",//标题
            "icon": 2,
            "iconUrl":"url1",//图标地址
            "startDate": "2017-12-12",//活动开始时间
            "endDate": "2018-12-12",//活动结束时间
            "createTime": "2017-12-12",//活动创建时间
            "phoneChargeLevels": [//type 为2的时候有效
                {
                    "level":1,// 1到5 对应五个星级别
                    "number":3,//需要邀请的人数
                    "amount":50.00,//完成时候分红包的时候的红包总额
                    "canOpen":false,//是否可以打开 详情中传入用户id的时候 会判断
                    "opened":false//是否已经打开过 详情中传入用户id的时候会判断
                }
            ]
    
        }
    ]
}
```

###4.2-02 活动详情 √
`GET`   `/activity/{id}`

**路径参数**
`id` `活动的id`
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
userId|int|<i class="icon-remove"></i>|用户id

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data":
        {
            "id": 1,
            "type": 2,//1-抢红包 2-抢话费 3-推荐商家有奖
            "title": "",//标题
            "icon": 2,
            "iconUrl":"url1",//图标地址
            "content":"活动内容",//活动内容  图文
            "startDate": "2017-12-12",//活动开始时间
            "endDate": "2018-12-12",//活动结束时间
            "createTime": "2017-12-12",//活动创建时间
            "phoneChargeLevels": [//type 为2的时候有效 理应为长度为5的数组
               { 
                    "level":1,// 1到5 对应五个星级别
                    "number":3,//需要邀请的人数
                    "amount":50.00,//完成时候分红包的时候的红包总额
                    "canOpen":false,//是否可以打开 详情中传入用户id的时候 会判断
                    "opened":false//是否已经打开过 详情中传入用户id的时候会判断
               }
            ]
    
     }
}
```
###4.2-03 红包活动的领奖记录   √

> 最多15条

`GET`   `/activity/redpacketRecords`

**路径参数**
`无`
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
无|

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data":[
        {
            "id": 1,
            "name":"xx领取了xx元消费奖励红包"
        }
    ]
}
```

###4.2-04 查询可领取红包 √

> ：昨日是否消费 当前活动是否存在，若存在  返回红包的值

`GET`   `/activity/checkRedpacket`

**路径参数**
`无`
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
userId|int|<i class="icon-remove"></i>|用户id
activityId|int|<i class="icon-remove"></i>|活动id


 **错误码说明**
code| 错误原因  |返回消息
-----|---------|----
807 |不存在的活动|不存在的活动
907 |已领取红包，不可重复领取|已领取红包，不可重复领取
1102 |昨日没有消费|昨日没有消费，不可领取红包
1103 |没有可领取红包|没有可领取红包

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data":0.52
}
```

###4.2-05 领取红包活动的红包 √

> 应在查询可领取红包 后调用此接口  红包不领取24小时候后会失效

`POST`   `/activity/receiveRedpacket`

**路径参数**
`无`
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
userId|int|<i class="icon-remove"></i>|用户id


 **错误码说明**
code| 错误原因  |返回消息
-----|---------|----
907 |已领取红包，不可重复领取|已领取红包，不可重复领取
1103 |没有可领取红包|没有可领取红包

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data":0.52//领取的金额
}
```

###4.2-06 话费领取记录  √
> 最多15条

`GET`   `/activity/phoneChargeRecords`

**路径参数**
`无`
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
无|

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data":[
        {
            "id": 1,
            "name":"xx 抢到了xx话费"
        }
    ]
}
```

###4.2-07 抢话费的一些数据统计  √

`GET`   `/activity/phoneChargeStatictics`

**路径参数**
`无`
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
无|

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data":
    {
        "totalAmount": 88.88,//历史抢到的总额
        "chargeAmount": 80,//历史充值话费
        "currentRecommentNum": 10,//本活动内邀请到的人数
        "currentAmount": 1.52//本次抢到的话费
    }
    
}
```
###4.2-08 抢话费-打开对应的红包  √

`POST`   `/activity/rob`

**路径参数**
`无`
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
userId|int|<i class="icon-remove"></i>|用户id
activityId|int|<i class="icon-remove"></i>|活动id
level|int|<i class="icon-remove"></i>|哪个级别的红包 1——5

 **错误码说明**
code| 错误原因  |返回消息
-----|---------|----
807 |不存在的活动|不存在的活动
814 |不存在的话费红包|不存在的话费红包
907 |已领取红包，不可重复领取|已领取红包，不可重复领取
1102 |未达到开启红包的要求|未达到开启红包的要求

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": 1.52//本次抢到的话费
}
```
###4.2-09 充话费申请  √

`POST`   `/activity/fetch`

**路径参数**
`无`
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
userId|int|<i class="icon-remove"></i>|用户id
money|double|<i class="icon-remove"></i>|充值的金额
mobile|String|<i class="icon-remove"></i>|要充话费的号码

 **错误码说明**
code| 错误原因  |返回消息
-----|---------|----
815 |余额不足|余额不足

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": null
}
```
###4.2-10 充话费申请记录 √
`GET`   `/activity/fetchRecords`
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
userId|int|<i class="icon-ok"></i>|用户ID
page|int|<i class="icon-remove"></i>|分页参数
size|int|<i class="icon-remove"></i>|分页参数
**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": {
        "datas": [
            {
                "id": 1,
                "customerId": 1,
                "money": 50.00,//金额
                "createTime": "2017-12-12",
                "status": 1,//0-初始 1-已充值 2-审核不通过
                "fetchTime": "2017-12-13",//充值日期
                "denyReason": "" //不通过的原因
            }
        ],
        "total": 1,
        "size": 10,
        "page": 1,
        "pages": 1,
        "curSize": 1
        
    }
}
```


## 5 商家端

### 5.01 商家登陆 √ 
`POST`   `/merchant/auth/login`

> 商户的审核情况需要另外根据，返回的数据中是 `status`字段进行判断  1-审核中 2-通过 3-拒绝 4-尚未提交审核资料

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
mobile|String|<i class="icon-ok"></i>|手机号
password|String|<i class="icon-ok"></i>|密码
mobileCode|String|<i class="icon-remove"></i>|设备号

 **错误码说明**
code| 错误原因  |返回消息
-----|---------|----
802 |不存在的用户|不存在的用户
803 |用户被禁用|用户被禁用
804 |密码错误|密码错误


**响应结果:**
```javascript
{
    "code": 0,  //0 - 接口调用成功，其他值表示失败
    "message": null,
    "data": {
        "id":1,//商户id
        "customerId":2,//登陆的用户id
        "recommendId":0,//推荐人id
        "name":"商家名称",
        "provinceId":100010,//省id
        "cityId":"100011",//市id
        "areaId":"1000112",//区id
        "address":"陆家嘴12号",
        "landmarks":"地铁口",//附近坐标
        "longitude":117.01,//经度
        "latitude":32.2,//纬度
        "type":1,//类型 1-美食 2-酒店预订 3-其他
        "categoryId":3,//商家分类的id
        "mobile":"15045691122"//联系电话
        "outPic":2,//外景照附件id  提交数据时使用此字段
        "outPicUrl":"http://www.chike.xx/attac/2",//外景照url
        "inPic":"3,4,5",//内景照 附件ids 提交数据时使用此字段
        "inPicUrls":[]//内景照图片url数据
        "licensePic":6,//营业执照 附件id 提交数据时使用此字段
        "licensePicUrl":"....",//营业执照 url
        "licensePersistent":false,//营业执照是否长期有效
        "licenseDate":"2020-12-12",//营业执照有效期
        "licenseRegcode":"",//营业执照注册号
        "permitPic":10,//营业许可证图片 id
        "permitPicUrl":"...",//营业许可证图片 url
        "permitPersistent":false,//许可证是否长期有效
        "permitDate":"2019-12-12",  //营业许可证有效期
        "otherProvePic":"5,8,9",//其他证明图片 附件ids
        "otherProvePicUrls":[],//其他证明图片url数组
        "accountName":"",//帐户名  不返回 提交的时候的数据
        "bankAccount":"",//银行账号 不返回 提交的时候的数据
        "bankName":"中国银行",//开户银行   不反回   提交的时候的数据
        "status":1,//审核状态 1-审核中 2-通过 3-拒绝 4 -尚未提交审核资料
        "facility":"可刷卡，停车位",//商家设施，多个逗号分隔
        "businessDay":"周一至周五",//营业日
        "businessHours":"24小时营业",//营业时间
        "price":20.50,//最低消费金额
        "hotelInTime":"18:00",//酒店-入店时间  编辑新增时保持格式HH:mm
        "hotelOutTime":"12:00",//酒店-离店时间 编辑新增时保持格式HH:mm
        "hotelCanAddBed":false,//酒店-可否加床
        "hotelAddBedPrice":100.20,//酒店-加床价格
        "hotelForeigned":false,//酒店-是否接待外宾
        "hotelPeted":true,//酒店-可否携带宠物
        "hotelPledge":"true",//酒店-是否需要押金
        "hotelLevel":3,//酒店星级 1-客栈 2-公寓 3-经济舒适型 4-高档豪华型 5-经济连锁 6-二星酒店 7-三星酒店 8-四星 9-五星
        "describe":"商家描述", // 图文详情
        "qrcode":1,//商家二维码
        "qrcodeUrl":"url",//二维码地址 二维码信息如：{"merchantId":1,"merchantName":"张三"}
        "customer":{//所属用户的信息 略
        }
    }
}
```
### 5.02 提交审核资料 √  **提交的参数改为json格式**
`POST`   `/merchant/auth/submitAudit`

> 注意涉及到图片的需提交附件id 或者ids(多附件id逗号连接)

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
customerId|String|<i class="icon-ok"></i>|所属用户id  从登陆返回的data.customer.id 取得
|其他数据参见 [登陆返回数据] (#501-商家登陆) 与原型


 **错误码说明**
code| 错误原因  |返回消息
-----|---------|----
无|

**响应结果:**
```javascript
{
    "code": 0,  //0 - 接口调用成功，其他值表示失败
    "message": null,
    "data": null
}
```
### 5.03 修改基本信息-需重新审核 √  **提交的参数改为json格式**
`POST`   `/merchant/auth/updateBaseInfo`

> 注意涉及到图片的需提交附件id 或者ids(多附件id逗号连接)

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
id|String|<i class="icon-ok"></i>|当前登录用户的商户id 参见登陆后返回的id
|其他数据参见 [登陆返回数据] (#501-商家登陆) 与原型

 **错误码说明**
code| 错误原因  |返回消息
-----|---------|----
无|

**响应结果:**
```javascript
{
    "code": 0,  //0 - 接口调用成功，其他值表示失败
    "message": null,
    "data": null
}
```
### 5.04 修改商家信息 √  **提交的参数改为json格式**

`POST`   `/merchant/auth/updateBusiness`

> 注意涉及到图片的需提交附件id 或者ids(多附件id逗号连接)

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
id|String|<i class="icon-ok"></i>|当前登录用户的商户id 参见登陆后返回的id
|其他数据参见 [登陆返回数据] (#501-商家登陆) 与原型

 **错误码说明**
code| 错误原因  |返回消息
-----|---------|----
无|

**响应结果:**
```javascript
{
    "code": 0,  //0 - 接口调用成功，其他值表示失败
    "message": null,
    "data": null
}
```
### 5.05 提交实名认证信息  √ **提交的参数改为json格式**
`POST`   `/merchant/auth/authentication`

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
merchantId|int|<i class="icon-ok"></i>|当前登录用户的商户id 参见登陆后返回的id
idNumber|String|<i class="icon-ok"></i>|身份证
readName|String|<i class="icon-ok"></i>|姓名
gender|int|<i class="icon-ok"></i>|1-男 2-女 0-保密
nation|String|<i class="icon-ok"></i>|民族
birthday|date|<i class="icon-ok"></i>|出生年月 yyyy-MM-dd
houseAddress|String|<i class="icon-ok"></i>|注册地地址
expiryDate|date|<i class="icon-ok"></i>|失效日期 yyyy-MM-dd
 **错误码说明**
code| 错误原因  |返回消息
-----|---------|----
无|

**响应结果:**
```javascript
{
    "code": 0,  //0 - 接口调用成功，其他值表示失败
    "message": null,
    "data": null
}
```
### 5.06 获得商家信息 √
`GET`   `/merchant/auth/info`

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
merchantId|int|<i class="icon-ok"></i>|当前登录用户的商户id 参见登陆后返回的id

 **错误码说明**
code| 错误原因  |返回消息
-----|---------|----
无|

**响应结果:**
```javascript
{
    "code": 0,  //0 - 接口调用成功，其他值表示失败
    "message": null,
    "data": {//参见登陆的返回数据 ，去掉customer
    }
}
```
### 5.07 交通信息列表 √
`GET`   `/merchant/auth/traffics`

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
merchantId|int|<i class="icon-ok"></i>|当前登录用户的商户id 
page|int|<i class="icon-remove"></i>|分页参数 -当前页码 默认1
size|int|<i class="icon-remove"></i>|分页参数 -页数据量 默认10
 **错误码说明**
code| 错误原因  |返回消息
-----|---------|----
无|

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": {
        "datas": [
            {
                "id": 2,
                "startPoint": "南山南",//出发地
                "merchantId": 2,
                "tool": "自行车",//交通方式
                "distance": 12.2,//距离
                "times": 65//时间
            },
            {
                "id": 1,
                "startPoint": "宝安机场",
                "merchantId": 2,
                "tool": "出租车",
                "distance": 50.2,
                "times": 20
            }
        ],
        "total": 2,
        "size": 10,
        "page": 1,
        "pages": 1,
        "curSize": 2
    }
}
```

### 5.08 新增交通信息 √
`POST`   `/merchant/auth/traffic`

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
merchantId|int|<i class="icon-ok"></i>|当前登录用户的商户id 
startPoint|String|<i class="icon-ok"></i>|出发地点
tool|String|<i class="icon-ok"></i>|交通工具
distance|double|<i class="icon-ok"></i>|距离
times|double|<i class="icon-ok"></i>|时间
 **错误码说明**
code| 错误原因  |返回消息
-----|---------|----
无|

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": {
    }
}
```

### 5.09 删除交通信息 √
`POST`   `/merchant/auth/traffic/{id}/delete`
**路径参数**
`id`：`当前交通信息的id`

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
无|
 **错误码说明**
code| 错误原因  |返回消息
-----|---------|----
无|

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": {
    }
}
```

### 5.10 商家相册列表 √
`GET`   `/merchant/photo/photos`

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
merchantId|int|<i class="icon-ok"></i>|当前登录用户的商户id 
type|int|<i class="icon-ok"></i>|类型  0-全部 1-外景 2-内景 3-其他 4-酒店
page|int|<i class="icon-remove"></i>|分页参数 -当前页码 默认1
size|int|<i class="icon-remove"></i>|分页参数 -页数据量 默认10
 **错误码说明**
code| 错误原因  |返回消息
-----|---------|----
无|

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": {
        "datas": [
            {
                "id": 2,
                "merchantId": 2,
                "image": 2,//图片在附件表的地址
                "type": 1,
                "imageUrl": "http://39.106.13.51:10081/attachment/ajax/visit/2"//图片
            },
            {
                "id": 1,
                "merchantId": 2,
                "image": 1,
                "type": 1,
                "imageUrl": "http://39.106.13.51:10081/attachment/ajax/visit/1"
            }
        ],
        "total": 2,
        "size": 10,
        "page": 1,
        "pages": 1,
        "curSize": 2
    }
}
```

### 5.11 新增相册图片 √
`POST`   `/merchant/photo/add`

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
merchantId|int|<i class="icon-ok"></i>|当前登录用户的商户id 
image|int|<i class="icon-ok"></i>|附件id
type|int|<i class="icon-ok"></i>|1-外景 2-内景 3-其他 4-酒店

 **错误码说明**
code| 错误原因  |返回消息
-----|---------|----
无|

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": {
    }
}
```

### 5.12 删除相册图片 √
`POST`   `/merchant/photo/{id}/delete`
**路径参数**
`id`：`当前交通信息的id`

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
无|
 **错误码说明**
code| 错误原因  |返回消息
-----|---------|----
无|

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": {
    }
}
```

### 5.13 营销活动列表 √
`GET`   `/merchant/activity/activities`

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
merchantId|int|<i class="icon-ok"></i>|当前登录用户的商户id 
page|int|<i class="icon-remove"></i>|分页参数 -当前页码 默认1
size|int|<i class="icon-remove"></i>|分页参数 -页数据量 默认10
 **错误码说明**
code| 错误原因  |返回消息
-----|---------|----
无|

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": {
        "datas": [
            {
                "id": 1,
                "type": 1,//1-满减 2-全场折扣券 3-立减活动
                "startDate": "2017-11-21",//开始时间
                "endDate": "2017-11-30",//结束时间
                "rules": "100:10,200:20,300:30",//满减时候的规则每组用英文逗号分隔，组内用英文冒号分隔，见ruleDescs的
                "minus":0.0,//立减活动时候立减的金额
                "limited": false,
                "limitNum": 0,
                "minAmount": 0,
                "discount": 0,
                "status": 1,//0-初始 1-进行中 2-已结束
                "ruleDescs": [
                    {
                        "fill": 100,//满100元
                        "minus": 10//减10元
                    },
                    {
                        "fill": 200,
                        "minus": 20
                    },
                    {
                        "fill": 300,
                        "minus": 30
                    }
                ]
            },
            {
                "id": 2,
                "type": 2,
                "startDate": "2017-11-20",
                "endDate": "2017-12-07",
                "limited": false,//折扣券是否限制数量
                "limitNum": 200,//折扣最大的发放数量
                "minAmount": 30,//折扣券最低消费金额
                "discount": 8.0,折扣券折扣
                "status": 0//0-初始 1-进行中 2-已结束
            }
        ],
        "total": 2,
        "size": 10,
        "page": 1,
        "pages": 1,
        "curSize": 2
    }
}
```

### 5.14 创建满减活动/折扣活动/立减活动 √ 提交的参数改为json格式
`POST`   `/merchant/activity/add`

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
merchantId|int|<i class="icon-ok"></i>|当前登录用户的商户id 
type|int|<i class="icon-ok"></i>|1-满减 2-全场折扣券 3-立减
startDate|date|<i class="icon-ok"></i>|开始时间 yyyy-MM-dd
endDate|date|<i class="icon-ok"></i>|开始时间 yyyy-MM-dd
rules|String|<i class="icon-remove"></i>|满减时候的规则：满多少减多少:格式为100:5,200:15
minus|double|<i class="icon-remove"></i>|立减活动时立减的金额
limited|Booelan|<i class="icon-remove"></i>|折扣券时的参数-折扣券是否限制数量
limitNum|int|<i class="icon-remove"></i>|折扣券时的参数-折扣券数量
minAmount|double|<i class="icon-remove"></i>|折扣券时的参数-最低消费金额
discount|double|<i class="icon-remove"></i>|折扣券时的参数-折扣 一位小数
 **错误码说明**
code| 错误原因  |返回消息
-----|---------|----
无|

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": {
    }
}
```    

### 5.15 修改活动 √ 提交的参数改为json格式
`POST`   `/merchant/activity/update`
> 修改活动不能修改类型 故前段根据类型修改相应参数
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
id|int|<i class="icon-ok"></i>|当前活动id 
startDate|date|<i class="icon-ok"></i>|开始时间 yyyy-MM-dd
endDate|date|<i class="icon-ok"></i>|开始时间 yyyy-MM-dd
rules|String|<i class="icon-remove"></i>|满减时候的规则：满多少减多少:格式为100:5,200:15,
limited|Booelan|<i class="icon-remove"></i>|折扣券时的参数-折扣券是否限制数量
limitNum|int|<i class="icon-remove"></i>|折扣券时的参数-折扣券数量
minAmount|double|<i class="icon-remove"></i>|折扣券时的参数-最低消费金额
discount|double|<i class="icon-remove"></i>|折扣券时的参数-折扣 一位小数
 **错误码说明**
code| 错误原因  |返回消息
-----|---------|----
无|

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": {
    }
}
```    

### 5.16 删除活动 √
`POST`   `/merchant/activity/{id}/delete`
 
 **路径参数**
 `id` `当前活动的id`
 
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
无|
 **错误码说明**
code| 错误原因  |返回消息
-----|---------|----
无|

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": null
}
```    
### 5.17 发布活动 √
`POST`   `/merchant/activity/{id}/publish`
 
 > 初始的活动可发布
 **路径参数**
 `id` `当前活动的id`
 
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
无|
 **错误码说明**
code| 错误原因  |返回消息
-----|---------|----
无|

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": null
}
```    
### 5.18 关闭活动 √
`POST`   `/merchant/activity/{id}/close`

 > 进行中的的活动可关闭
 
 **路径参数**
 `id` `当前活动的id`
 
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
无|
 **错误码说明**
code| 错误原因  |返回消息
-----|---------|----
无|

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data":null
}
```    

### 5.19 团购列表 √
`GET`   `/merchant/commodity/groubuys`

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
merchantId|int|<i class="icon-ok"></i>|当前登录用户的商户id 
status|int|<i class="icon-remove"></i>|团购状态 0-初始 1-上架 2-下架- 3-全部
page|int|<i class="icon-remove"></i>|分页参数 -当前页码 默认1
size|int|<i class="icon-remove"></i>|分页参数 -页数据量 默认10
 **错误码说明**
code| 错误原因  |返回消息
-----|---------|----
无|

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": {
        "datas": [
            {
                "id": 1,
                "name": "团购商品",
                "price": 0.01,//价格
                "consumedAllHours": false,//是否24小时可消费
                "status": 0,//团购状态 0-初始 1-上架 2-下架
                "icon": 3,//图标
                "iconUrl": "http://39.106.13.51:10081/attachment/ajax/visit/3"//图标url
            }
        ],
        "total": 1,
        "size": 10,
        "page": 1,
        "pages": 1,
        "curSize": 1
    }
}
```
### 5.20 团购详情 √
`GET`   `/merchant/commodity/groubuy/{id}`

**路径参数：**
`id` `团购id`
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
无|
 **错误码说明**
code| 错误原因  |返回消息
-----|---------|----
无|

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": {
        "id": 1,
        "merchantId": 2,
        "name": "团购商品",
        "price": 0.01,//价格
        "startTime": "2017-11-22",//开始时间
        "endTime": "2017-12-09",//结束时间
        "consumedAllHours": false,//是否24小时可消费
        "consumeStartTime": "23:00",//消费开始时间
        "consumeEndTime": "12:30",//消费结束时间
        "labels": "政策1,政策2",//团购标签(政策) 多个逗号分隔
        "tips": "须知1,须知2,须知3",//购买须知 多个逗号分隔
        "describe": "我是商家描述",//团购文字介绍
        "images": "2,3",//图片ids
        "status": 1,//状态 0-待上线 1-已上线 2-已下线 
        "sellNum": 2,//销售数量
        "commentNum": 2,//评论数量
        "icon": 3,//图标 附件id
        "imageUrls": [//图片列表
            "http://39.106.13.51:10081/attachment/ajax/visit/2",
            "http://39.106.13.51:10081/attachment/ajax/visit/3"
        ],
        "iconUrl": "http://39.106.13.51:10081/attachment/ajax/visit/3"//图标
    }
}
```
### 5.21 新增团购 √ **请求参数改为json格式**

`POST`   `/merchant/commodity/groubuy/add`

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
merchantId|int|<i class="icon-ok"></i>|当前登录用户的商户id 
name|String|<i class="icon-ok"></i>|团购名称
price|double|<i class="icon-ok"></i>|价格  可含两位小数
startTime|date|<i class="icon-ok"></i>|开始时间 yyyy-MM-dd
endTime|date|<i class="icon-ok"></i>|结束时间 yyyy-MM-dd
consumedAllHours|boolean|<i class="icon-ok"></i>|是否24小时可消费
consumeStartTime|date|<i class="icon-remove"></i>|消费开始时间 HH:mm
consumeEndTime|date|<i class="icon-remove"></i>|消费结束时间 HH:mm
labels|int|<i class="icon-ok"></i>|团购标签(政策) 多个逗号分隔
tips|int|<i class="icon-ok"></i>|购买须知 多个逗号分隔
describe|String|<i class="icon-ok"></i>|团购文字介绍
images|String|<i class="icon-ok"></i>| 团购图片 多张 附件ids
icon|int|<i class="icon-ok"></i>|图标 附件id

 **错误码说明**
code| 错误原因  |返回消息
-----|---------|----
无|

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": null
}
```
### 5.22 修改团购 √ **请求参数转为json格式**
`POST`   `/merchant/commodity/groubuy/update`

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
id|int|<i class="icon-ok"></i>|团购的id
name|String|<i class="icon-ok"></i>|团购名称
price|double|<i class="icon-ok"></i>|价格  可含两位小数
startTime|date|<i class="icon-ok"></i>|开始时间 yyyy-MM-dd
endTime|date|<i class="icon-ok"></i>|结束时间 yyyy-MM-dd
consumedAllHours|boolean|<i class="icon-ok"></i>|是否24小时可消费
consumeStartTime|date|<i class="icon-remove"></i>|消费开始时间 HH:mm
consumeEndTime|date|<i class="icon-remove"></i>|消费结束时间 HH:mm
labels|String|<i class="icon-ok"></i>|团购标签(政策) 多个逗号分隔
tips|String|<i class="icon-ok"></i>|购买须知 多个逗号分隔
describe|String|<i class="icon-ok"></i>|团购文字介绍
images|String|<i class="icon-ok"></i>| 团购图片 多张 附件ids
icon|int|<i class="icon-ok"></i>|图标 附件id

 **错误码说明**
code| 错误原因  |返回消息
-----|---------|----
无|

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": null
}
```
### 5.23 删除团购 √

`POST`   `/merchant/commodity/groubuy/{id}/delete`

**路径参数**
`id` `团购id`

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
无|
 **错误码说明**
code| 错误原因  |返回消息
-----|---------|----
无|

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": null
}
```
### 5.24 上架团购 √

`POST`   `/merchant/commodity/groubuy/{id}/publish`

> 对初始的团购进行上架

**路径参数**
`id` `团购id`

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
无|
 **错误码说明**
code| 错误原因  |返回消息
-----|---------|----
无|

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": null
}
```
### 5.25 下架团购 √

`POST`   `/merchant/commodity/groubuy/{id}/close`

> 对上架的团购进行下架

**路径参数**
`id` `团购id`

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
无|
 **错误码说明**
code| 错误原因  |返回消息
-----|---------|----
无|

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": null
}
```

### 5.26 酒店列表 √
`GET`   `/merchant/commodity/hotels`

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
merchantId|int|<i class="icon-ok"></i>|当前登录用户的商户id 
status|int|<i class="icon-remove"></i>|团购状态 0-初始 1-上架 2-下架- 3-全部
page|int|<i class="icon-remove"></i>|分页参数 -当前页码 默认1
size|int|<i class="icon-remove"></i>|分页参数 -页数据量 默认10
 **错误码说明**
code| 错误原因  |返回消息
-----|---------|----
无|

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": {
        "datas": [
            {//字段说明见酒店详情
                "id": 1,
                "icon": 3,
                "name": "大床房",
                "unsubscribeWay": 1,
                "verifyWay": 1,
                "roomBed": 1,
                "roomPersonNum": 1,
                "roomNetwork": 1,
                "roomWindow": 1,
                "roomArea": 65,
                "roomFloor": 1,
                "roomTel": 1,
                "roomWindowmView": 1,
                "roomBreakfast": 1,
                "roomAddBeded": true,
                "roomSmoke": 1,
                "roomBedWide": 1,
                "status": 0,
                "iconUrl": "http://39.106.13.51:10081/attachment/ajax/visit/3"
            }
        ],
        "total": 1,
        "size": 10,
        "page": 1,
        "pages": 1,
        "curSize": 1
    }
}
```
### 5.27 酒店详情 √

`GET`   `/merchant/commodity/hotel/{id}`

**路径参数**
`id` `酒店id`
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
无|

 **错误码说明**
code| 错误原因  |返回消息
-----|---------|----
无|

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": {
        "id": 1,
        "icon": 3,//图标
        "images": "2,3",//图片
        "name": "大床房",//房间标题
        "price": 180.0,//价格
        "stock": 3,//库存数
        "unsubscribeWay": 1,//退订政策 1-当天18：00前可免费取消订单 2 预定成功后 不可变更取消
        "verifyWay": 1,//订单确认时间 1-1小时内确认 2-立即确认
        "roomBed": 1,//床型 1-单人床 2-豪华大床 3-双人床 4-三人床
        "roomPersonNum": 1,//可住人数
        "roomNetwork": 1,//宽带 1-无网络 2-有线宽带 3-无线WIFI
        "roomWindow": 1,//窗户 1-无窗 2-有窗 3-部分有窗
        "roomArea": 65.0,// 面积
        "roomFloor": 1,//roomFloor
        "roomTel": 1,//电话 1-无电话 2-免费电话 3-收费电话
        "roomWindowmView": 1,//窗景：1-无 2-风景2-城景 3-花园景 4-地标景 5-无敌海景
        "roomBreakfast": 1,//早餐 1-无早餐 2-含早餐 3-双份早餐
        "roomAddBeded": true,//可否加床
        "roomSmoke": 1,//无烟 1- 可吸烟2-该房无烟处理
        "roomBedWide": 1,//床宽1-圆形床 2-1.5米大床 3-1.8米大床 3-2.0米大床
        "status": 0,//0-待上线 1-已上线 2-已下线 
        "iconUrl": "http://39.106.13.51:10081/attachment/ajax/visit/3",//图标
        "imageUtls": [//图片列表
            "http://39.106.13.51:10081/attachment/ajax/visit/2",
            "http://39.106.13.51:10081/attachment/ajax/visit/3"
        ]
    }
}
```
### 5.28 新增酒店 √ **请求参数改为json格式**

`POST`   `/merchant/commodity/hotel/add`

**路径参数**
`无`
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
merchantId|int|<i class="icon-ok"></i>|当前登录用户的商户id 
icon|int|<i class="icon-ok"></i>|图标
images|String|<i class="icon-ok"></i>|图片
name|String|<i class="icon-ok"></i>|房间标题
price|double|<i class="icon-ok"></i>|价格
stock|int|<i class="icon-ok"></i>|库存数
unsubscribeWay|int|<i class="icon-ok"></i>|退订政策 1-当天18：00前可免费取消订单 2 预定成功后 不可变更取消
verifyWay|int|<i class="icon-ok"></i>|订单确认时间 1-1小时内确认 2-立即确认
roomBed|int|<i class="icon-ok"></i>|床型 1-单人床 2-豪华大床 3-双人床 4-三人床
roomPersonNum|int|<i class="icon-ok"></i>|可住人数
roomNetwork|int|<i class="icon-ok"></i>|宽带 1-无网络 2-有线宽带 3-无线WIFI
roomWindow|int|<i class="icon-ok"></i>|窗户 1-无窗 2-有窗 3-部分有窗
roomArea|double|<i class="icon-ok"></i>|面积
roomFloor|int|<i class="icon-ok"></i>|楼层
roomTel|int|<i class="icon-ok"></i>|电话 1-无电话 2-免费电话 3-收费电话
roomWindowmView|int|<i class="icon-ok"></i>|窗景：1-无 2-风景2-城景 3-花园景 4-地标景 5-无敌海景
roomBreakfast|int|<i class="icon-ok"></i>|早餐 1-无早餐 2-含早餐 3-双份早餐
roomAddBeded|boolean|<i class="icon-ok"></i>|可否加床
roomSmoke|int|<i class="icon-ok"></i>|无烟 1- 可吸烟2-该房无烟处理
roomBedWide|int|<i class="icon-ok"></i>|床宽1-圆形床 2-1.5米大床 3-1.8米大床 3-2.0米大床

 **错误码说明**
code| 错误原因  |返回消息
-----|---------|----
无|

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": {
    }
```
### 5.29 修改酒店 √ **请求参数改为json格式**

`POST`   `/merchant/commodity/hotel/update`

**路径参数**
`无`
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
id|int|<i class="icon-ok"></i>|酒店的id
icon|int|<i class="icon-remove"></i>|图标
images|String|<i class="icon-remove"></i>|图片
name|String|<i class="icon-remove"></i>|房间标题
price|double|<i class="icon-remove"></i>|价格
stock|int|<i class="icon-remove"></i>|库存数
unsubscribeWay|int|<i class="icon-remove"></i>|退订政策 1-当天18：00前可免费取消订单 2 预定成功后 不可变更取消
verifyWay|int|<i class="icon-remove"></i>|订单确认时间 1-1小时内确认 2-立即确认
roomBed|int|<i class="icon-remove"></i>|床型 1-单人床 2-豪华大床 3-双人床 4-三人床
roomPersonNum|int|<i class="icon-remove"></i>|可住人数
roomNetwork|int|<i class="icon-remove"></i>|宽带 1-无网络 2-有线宽带 3-无线WIFI
roomWindow|int|<i class="icon-remove"></i>|窗户 1-无窗 2-有窗 3-部分有窗
roomArea|double|<i class="icon-remove"></i>|面积
roomFloor|int|<i class="icon-remove"></i>|楼层
roomTel|int|<i class="icon-remove"></i>|电话 1-无电话 2-免费电话 3-收费电话
roomWindowmView|int|<i class="icon-remove"></i>|窗景：1-无 2-风景2-城景 3-花园景 4-地标景 5-无敌海景
roomBreakfast|int|<i class="icon-remove"></i>|早餐 1-无早餐 2-含早餐 3-双份早餐
roomAddBeded|boolean|<i class="icon-remove"></i>|可否加床
roomSmoke|int|<i class="icon-remove"></i>|无烟 1- 可吸烟2-该房无烟处理
roomBedWide|int|<i class="icon-remove"></i>|床宽1-圆形床 2-1.5米大床 3-1.8米大床 3-2.0米大床

 **错误码说明**
code| 错误原因  |返回消息
-----|---------|----
无|

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": {
    }
```
### 5.30 删除酒店 √
`POST`   `/merchant/commodity/hotel/{id}/delete`

**路径参数**
`id` `酒店预订id`

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
无|
 **错误码说明**
code| 错误原因  |返回消息
-----|---------|----
无|

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": null
}
```
### 5.31 上架酒店 √ 
`POST`   `/merchant/commodity/hotel/{id}/publish`

**路径参数**
`id` `酒店预订id`

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
无|
 **错误码说明**
code| 错误原因  |返回消息
-----|---------|----
无|

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": null
}
```
### 5.32 下架酒店 √
`POST`   `/merchant/commodity/hotel/{id}/close`

**路径参数**
`id` `酒店预订id`

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
无|
 **错误码说明**
code| 错误原因  |返回消息
-----|---------|----
无|

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": null
}
```
----
### 和money相关

### 5.2-01 对账 √
`GET`   `/merchant/money/comparebill`

**路径参数**
`无` 

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
merchantId|int|<i class="icon-ok"></i>|商户id
startDate|Date|<i class="icon-ok"></i>|开始时间  yyyy-MM-dd 包含
endDate|Date|<i class="icon-ok"></i>|结束时间 yyyy-MM-dd 包含
 **错误码说明**
code| 错误原因  |返回消息
-----|---------|----
无|

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": {
        "nums":5,//数量
        "amount":980.50//金额
    }
}
```

### 5.2-02 商户余额 √

`GET`   `/merchant/money/balance`

**路径参数**
`无` 

**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
merchantId|int|<i class="icon-ok"></i>|商户id

 **错误码说明**
code| 错误原因  |返回消息
-----|---------|----
无|

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": 999.20
}
```

### 5.2-03 余额明细 √
`GET`   `/merchant/money/balances`
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
merchantId|int|<i class="icon-ok"></i>|用户ID
page|int|<i class="icon-remove"></i>|分页参数
size|int|<i class="icon-remove"></i>|分页参数
**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": {
        "datas": [
            {
                "id": 1,
                "merchantId": 4,//商户id
                "money": 10.01,//金额
                "createTime": "2017-11-10 08:36:16",//时间
                "describe": "用户付款金",//简单描述
                "type": 6//1-用户付款 2-团购销售收入 3-给用户退款 4-提款 5 酒店预订收入
                
            }
        ],
        "total": 1,
        "size": 10,
        "page": 1,
        "pages": 1,
        "curSize": 1
        
    }
}
```

### 5.2-04 验证团购券 √

> 使用团购券 修改订单状态

`POST`   `/merchant/money/checkTicket`
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
merchantId|int|<i class="icon-ok"></i>|用户ID
ticketNo|String|<i class="icon-ok"></i>|团购券 券码

 **错误码说明**
code| 错误原因  |返回消息
-----|---------|----
813|不存在的团购券|不存在的团购券
906|团购券已使用|团购券已使用
1005|团购券不属于本商户|团购券不属于本商户

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": null
}
```

### 5.2-05 已经验证的团购券列表 √
`GET`   `/merchant/money/groupTickets`
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
merchantId|int|<i class="icon-ok"></i>|商户ID
page|int|<i class="icon-remove"></i>|分页参数
size|int|<i class="icon-remove"></i>|分页参数
**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": {
        "datas": [
            {
               "id": 1,
                "money": 20.20,//金额
                "name": "团购名称",//团购名称
                "ticketNo": "",//券码
                "orderno": "",//所属订单
                "merchantId": 2,//所属商家
                "createTime": "",//生成时间
                "startTime": "",//生效时间
                "endTime": "",//失效时间
                "usedTime": "",//使用时间
                "status": 0,//0-未使用 1-已使用 2-已过期 3-已删除
                "qrcode": 2,//二维码对应附件id
                "customerId": 2,//所属用户
                "qrcodeUrl": "",//二维码图片url
                
            }
        ],
        "total": 1,
        "size": 10,
        "page": 1,
        "pages": 1,
        "curSize": 1
        
    }
}
```

### 5.2-05 已经验证的团购券列表 √
`GET`   `/merchant/money/groupTicket/{id}/delete`

**路径参数**
`id` `团购券id`
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
无|
**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data":null
}
```
----
### 商家评论相关
### 5.3-01 商家评论列表  √
`GET`   `/merchant/comment/comments`

**路径参数**
`无`
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
merchantId|int|<i class="icon-ok"></i>|商家id 
page|int|<i class="icon-remove"></i>|分页参数
size|int|<i class="icon-remove"></i>|分页参数

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data": {
        "datas": [
            {
                 "id":1,//
                "orderno":"1332asd",//订单号
                "customerId":1,//用户id
                "customerHeadpic":1,//用户头像附件id
                "customerHeadpicUrl":"url",//头像url
                "customerName":"张三",//
                "images":"2,3",//评论图片
                "imageUrls":["url1","url2","url3"],//图片url列表
                "content":"不错不错",//评论内容
                "star":4.5,//评星
                "reply":"谢谢",//商家回复
                "praiseNum":150,//评论点赞数
                "createTime":"2017-11-12"//评论时间
            }
        ],
        "total": 1,
        "size": 10,
        "page": 1,
        "pages": 1,
        "curSize": 1
    }
}
```

### 5.3-02 商家删除评论 √
`POST`   `/merchant/comment/comment/{id}/delete`

**路径参数**
`id` `评论id`
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
无|

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data":null
}
```

### 5.3-03 商家回复评论 √
`POST`   `/merchant/comment/comment/{id}/reply`

**路径参数**
`id` `评论id`
**请求参数:**   
参数|数据类型|必填|参数说明
---|-------|----|-----
reply|String|<i class="icon-ok"></i>|回复的内容

**响应结果:**
```javascript
{
    "code": 0,
    "msg": "",
    "data":null
}
```

##6 其他
### 6.1 支付宝回调地址
`GET || POST` `/callback/alipay`
### 6.2 微信回调地址
`POST` `/callback/wxpay`












