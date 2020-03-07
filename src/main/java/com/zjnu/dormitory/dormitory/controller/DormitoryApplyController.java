package com.zjnu.dormitory.dormitory.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjnu.dormitory.dormitory.common.R;
import com.zjnu.dormitory.dormitory.dto.apply.ApplyRoomDto;
import com.zjnu.dormitory.dormitory.dto.basicInfo.materialDto;
import com.zjnu.dormitory.dormitory.entity.Roominfo;
import com.zjnu.dormitory.dormitory.entity.User;
import com.zjnu.dormitory.dormitory.service.RoominfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(description = "用户住宿舍申请")
@RestController
@RequestMapping("/dormitory/user/dormitoryApply")
@CrossOrigin
public class DormitoryApplyController {

    RoominfoService roominfoService;





}
