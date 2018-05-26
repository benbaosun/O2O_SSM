$(function () {
    // 获取店铺id
    var shopId = getQueryString('shopId');
    // 根据shopId是否存在判断是注册店铺还是修改店铺
    var isEdit = shopId ? true : false;
    // 初始化店铺信息网址
    var initUrl = '/o2o_ssm/shopadmin/getshopinitinfo';
    // 注册店铺网址
    var registerShopUrl = '/o2o_ssm/shopadmin/registershop';
    // 获取店铺信息网址
    var shopInfoUrl = '/o2o_ssm/shopadmin/getshopbyid?shopId=' + shopId;
    // 修改店铺信息网址
    var editShopUrl = '/o2o_ssm/shopadmin/modifyshop';

    if (!isEdit) { // 注册店铺
        getShopInitInfo();
    } else { // 修改店铺信息
        getShopInfo(shopId);
    }


    // 获取店铺信息（修改）
    function getShopInfo(shopId) {
        $.getJSON(shopInfoUrl, function (data) {
            if (data.success) {
                // 获取返回的店铺信息
                var shop = data.shop;
                // 将店铺信息赋值到页面上
                $('#shop-name').val(shop.shopName);
                $('#shop-addr').val(shop.shopAddr);
                $('#shop-phone').val(shop.phone);
                $('#shop-desc').val(shop.shopDesc);

                var shopCategory = '<option data-id="'
                    + shop.shopCategory.shopCategoryId + '" selected>'
                    + shop.shopCategory.shopCategoryName + '</option>';

                var tempAreaHtml = '';
                data.areaList.map(function (item, index) {
                    tempAreaHtml += '<option data-id="' + item.areaId + '">'
                        + item.areaName + '</option>';
                });
                // 添加店铺分类下拉选项（不可编辑）
                $('#shop-category').html(shopCategory)
                                       .attr('disabled', 'disabled');

                // 添加区域下拉选项
                $('#area').html(tempAreaHtml);
                // 为店铺选择正确的区域
                $("#area option[data-id='" + shop.area.areaId + "'")
                        .attr('selected', 'selected');
            }
        });
    }

    // 获取店铺初始化信息（添加）
    function getShopInitInfo() {
        $.getJSON(initUrl, function (data) {
            if (data.success) {
                var tempHtml = '';
                var tempAreaHtml = '';
                data.shopCategoryList.map(function (item, index) {
                    tempHtml += '<option data-id="' + item.shopCategoryId + '">'
                        + item.shopCategoryName + '</option>';
                });
                data.areaList.map(function (item, index) {
                    tempAreaHtml += '<option data-id="' + item.areaId + '">'
                        + item.areaName + '</option>';
                });
                // 添加店铺分类下拉选项
                $('#shop-category').html(tempHtml);
                // 添加区域下拉选项
                $('#area').html(tempAreaHtml);
            }
        });
    }

    // 设置提交按钮点击事件
    $('#submit').click(function () {
        var shop = {};

        // 如果是修改店铺信息，传入店铺id
        if (isEdit) {
            shop.shopId = shopId;
        }

        shop.shopName = $('#shop-name').val();
        shop.shopAddr = $('#shop-addr').val();
        shop.phone = $('#shop-phone').val();
        shop.shopDesc = $('#shop-desc').val();
        shop.shopCategory = {
            shopCategoryId: $('#shop-category').find('option').not(function () {
                return !this.selected;
            }).data('id')
        };
        shop.area = {
            areaId: $('#area').find('option').not(function () {
                return !this.selected;
            }).data('id')
        };
        var shopImg = $('#shop-img')[0].files[0];
        var formData = new FormData();
        formData.append('shopImg', shopImg);
        formData.append('shopStr', JSON.stringify(shop));
        var verifyCodeActual = $('#j_captcha').val();
        if (!verifyCodeActual) {
            $.toast('请输入验证码！');
            return;
        }
        formData.append('verifyCodeActual', verifyCodeActual);
        $.ajax({
            url: (isEdit ? editShopUrl : registerShopUrl),
            type: 'POST',
            data: formData,
            contentType: false,
            processData: false,
            cache: false,
            success: function (data) {
                if (data.success) {
                    $.toast('提交成功！');
                } else {
                    $.toast('提交失败！' + data.errMsg);
                }
            }
        });
    });
});