package com.lin.web.superadmin;


import com.lin.entity.Area;
import com.lin.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lkmc2
 * @date 2018/5/19.
 * 区域控制器
 */

@Controller
@RequestMapping("/superadmin") //路径映射
public class AreaController {

    @Autowired
    private AreaService areaService;

    /**
     * 列出所有区域对象
     * @return 区域对象列表
     */
    @RequestMapping(value = "/listarea", method = RequestMethod.GET)
    @ResponseBody // 将结果转换成json
    private Map<String, Object> listArea() {
        // 存放结果信息的Map
        Map<String, Object> modelMap = new HashMap<>();
        // 区域实体列表
        List<Area> list;

        try {
            // 获取区域列表
            list = areaService.getAreaList();
            // 存放正确的返回信息
            modelMap.put("rows", list);
            modelMap.put("total", list.size());
        } catch (Exception e) {
            e.printStackTrace();
            // 存放错误信息
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
        }

        return modelMap;
    }

}
