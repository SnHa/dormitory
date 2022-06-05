package com.atsun.dormitory.controller;

import com.atsun.dormitory.dto.DepartApplicationDTO;
import com.atsun.dormitory.dto.StudentDTO;
import com.atsun.dormitory.dto.StudentPageDTO;
import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.po.DepartApplication;
import com.atsun.dormitory.service.StudentService;
import com.atsun.dormitory.vo.DataResponse;
import com.atsun.dormitory.vo.NoDataResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @Description: TODO(学生)
 * @Author SH
 * @Date 2022/1/12 13:35
 */
@Api(tags = "学生")
@RequestMapping("/student")
@RestController
public class StudentController extends BaseController {

    private StudentService studentService;

    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    @ApiOperation(value = "查询全部学生信息")
    @RequiresPermissions("student:list")
    @PostMapping("/list")
    public DataResponse<?> list(@RequestBody StudentPageDTO studentPageDTO, @RequestHeader("_ut") String token) throws TransException {
        return ok(studentService.list(studentPageDTO, token));
    }

    @ApiOperation(value = "单个查询学生信息")
    @RequiresPermissions("student:query")
    @GetMapping("/query")
    public DataResponse<?> query(@RequestParam("id") String id) throws TransException {
        return ok(studentService.query(id));
    }

    @ApiOperation("添加学生信息")
    @RequiresPermissions(value = {"student:save", "student:update"},logical = Logical.OR)
    @PostMapping("/saveOrUpdate")
    public NoDataResponse saveOrUpdate(@RequestBody StudentDTO studentDTO) throws TransException {
        studentService.saveOrUpdate(studentDTO);
        return ok();
    }

    @ApiOperation(value = "退舍")
    @RequiresPermissions("student:delete")
    @PostMapping("/delete")
    public NoDataResponse delete(@RequestBody DepartApplicationDTO departApplicationDTO, @RequestHeader("_ut") String token) throws TransException {
        studentService.delete(departApplicationDTO,token);
        return ok();
    }

    @ApiOperation(value = "根据名字查询学生名字")
    @RequiresPermissions("student:list")
    @GetMapping("/listName")
    public DataResponse<?> listByName(@RequestParam("name") String name,@RequestHeader("_ut") String token) throws TransException {
        return ok(studentService.list(name, token));
    }

    @ApiOperation(value = "查询性别比例")
    @GetMapping("/sexNumber")
    public DataResponse<?> sexNumber() throws TransException {
        HashMap<String, Integer> sex = studentService.sexNumber();
        return ok(sex);
    }

}
