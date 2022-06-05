package com.atsun.dormitory.controller;

import com.atsun.dormitory.dto.FacultyDTO;
import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.po.Faculty;
import com.atsun.dormitory.service.FacultyService;
import com.atsun.dormitory.vo.DataResponse;
import com.atsun.dormitory.vo.NoDataResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: TODO(学院管理)
 * @Author SH
 * @Date 2022/1/19 13:45
 */
@Api(tags = "学院管理")
@RequestMapping("/faculty")
@RestController
public class FacultyController extends BaseController {

    private FacultyService facultyService;

    @Autowired
    public void setFacultyService(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @ApiOperation(value = "查询全部")
    @RequiresPermissions("faculty:list")
    @GetMapping("/list")
    public DataResponse<?> list() throws TransException {
        return ok(facultyService.list());
    }

    @ApiOperation(value = "查询单个学院-班级信息")
    @RequiresPermissions("faculty:query")
    @GetMapping("/query")
    public DataResponse<?> query(@RequestParam("id") String id) throws TransException {
        return ok(facultyService.query(id));
    }

    @ApiOperation(value = "添加-修改学院班级")
    @RequiresPermissions(value = {"faculty:save", "faculty:update"},logical = Logical.OR)
    @PostMapping("/saveOrUpdate")
    public NoDataResponse saveOrUpdate(@RequestBody FacultyDTO facultyDTO) throws TransException {
        facultyService.saveOrUpdate(facultyDTO);
        return ok();
    }

    @ApiOperation(value = "删除学院")
    @RequiresPermissions("faculty:delete")
    @GetMapping("/delete")
    public NoDataResponse delete(@RequestParam("id") String id) throws TransException {
        facultyService.delete(id);
        return ok();
    }

    @ApiOperation(value = "列表全部学院")
    @RequiresPermissions("faculty:list")
    @GetMapping("/listAll")
    public DataResponse<?> listAll() throws TransException{
        return ok(facultyService.listAll());
    }
}
