package com.lin.web.superadmin;


import com.lin.entity.Area;
import com.lin.service.AreaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@Api(value = "区域Controller", tags = {"区域接口操作"})
@Controller
@RequestMapping("/superadmin") //路径映射
public class AreaController {

    /*** 日志记录器 ***/
    private Logger logger = LoggerFactory.getLogger(AreaController.class);

    @Autowired
    private AreaService areaService;

    /**
     * 列出所有区域对象
     * @return 区域对象列表
     */
    @ApiOperation(value = "获取所有区域信息", tags = {"获取区域信息"}, notes = "仅限广西范围内")
    @RequestMapping(value = "/listarea", method = RequestMethod.GET)
    @ResponseBody // 将结果转换成json
    private Map<String, Object> listArea() {
        logger.info("===start===");

        // 方法执行开始时间
        long startTime = System.currentTimeMillis();
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

        logger.error("test error!");
        // 方法执行结束时间
        long endTime = System.currentTimeMillis();
        logger.debug("costTime[{}]ms", endTime - startTime);
        logger.info("===end===");
        return modelMap;
    }

}
