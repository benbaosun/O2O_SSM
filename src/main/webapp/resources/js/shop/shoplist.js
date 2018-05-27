$(function () {
    getList();

    /**
     * 获取店铺列表
     * @param e
     */
    function getList(e) {
        $.ajax({
           url: "/o2o_ssm/shopadmin/getshoplist",
           type: "get",
           dataType: "json",
           success: function (data) {
               if (data.success) {
                   handleList(data.shopList);
                   handleUser(data.user);
               }
           }
        });
    }

    /**
     * 将店铺列表信息更新到界面
     * @param data 店铺列表信息
     */
    function handleList(data) {
        var html = '';
        data.map(function (item, index) {
            // noinspection JSAnnotator
            html += '<div class="row row-shop">'
                 + '<div class="col-40"> '+ item.shopName + '</div>'
                 + '<div class="col-40">'+ shopStatus(item.enableStatus) + '</div>'
                 + '<div class="col-20">'+ goShop(item.enableStatus, item.shopId) + '</div>'
                 + '</div>'
        });
        // 设置店铺列表到页面
        $('.shop-wrap').html(html);
    }

    /**
     * 根据传入的状态值返回中文描述
     * @param status 状态值
     * @returns {string} 该状态的中文描述
     */
    function shopStatus(status) {
        if (status === 0) {
            return '审核中';
        } else if (status === 1) {
            return '审核通过';
        } else {
            return '店铺非法';
        }
    }

    /**
     * 根据店铺状态返回链接或空字符串
     * @param status 店铺状态
     * @param id 店铺id
     * @returns {string} 链接或空字符串
     */
    function goShop(status, id) {
        if (status === 1) {
            return '<a href="/o2o_ssm/shopadmin/shopmanagement?shopId=' + id + '">进入</a>';
        }
        return '';
    }

    /**
     * 将用户名设置到界面上
     * @param data
     */
    function handleUser(data) {
        $('#user-name').text(data.name);
    }
});