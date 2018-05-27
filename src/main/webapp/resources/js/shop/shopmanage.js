$(function () {
    // 获取店铺id
    var shopId = getQueryString('shopId');
    var shopInfoUrl = '/o2o_ssm/shopadmin/getshopmanagementinfo?shopId=' + shopId;
    $.getJSON(shopInfoUrl, function (data) {
        // 需要重定向
        if (data.redirect) {
            window.location.href = data.url;
        } else {
            // 店铺id非空
            if (data.shopId !== undefined && data.shopId != null) {
                shopId = data.shopId;
            }
            $('#shopInfo')
                .attr('href', '/o2o_ssm/shopadmin/shopoperation?shopId=' + shopId);
        }
    })
});