package com.atsun.dormitory.controller;

import com.atsun.dormitory.convert.Impl.CommonConvent;
import com.atsun.dormitory.enums.TransCode;
import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.po.Image;
import com.atsun.dormitory.po.User;
import com.atsun.dormitory.service.*;
import com.atsun.dormitory.utils.MD5Util;
import com.atsun.dormitory.utils.SnowFlake;
import com.atsun.dormitory.vo.CommonVO;
import com.atsun.dormitory.vo.DataResponse;
import com.atsun.dormitory.vo.PermissionVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @author: SH
 * @create: 2021-12-03 19:14
 **/
@Api(tags = "公共模块")
@RequestMapping("/common")
@RestController
public class CommonController extends BaseController {

    private UserService userService;
    private ImageService imageService;
    private NoticeService noticeService;
    private FacultyService facultyService;
    private UserRoleService userRoleService;
    private NoticeUserService noticeUserService;
    private PermissionService permissionService;
    private RolePermissionService rolePermissionService;
    private DepartApplicationUserService departApplicationUserService;

    @Autowired
    public void setFacultyService(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @Autowired
    public void setImageService(ImageService imageService) {
        this.imageService = imageService;
    }

    @Autowired
    public void setNoticeUserService(NoticeUserService noticeUserService) {
        this.noticeUserService = noticeUserService;
    }

    @Autowired
    public void setNoticeService(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @Autowired
    public void setDepartApplicationUserService(DepartApplicationUserService departApplicationUserService) {
        this.departApplicationUserService = departApplicationUserService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setPermissionService(PermissionService permissionService) {
        this.permissionService = permissionService;
    }


    @Autowired
    public void setUserRoleService(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @Autowired
    public void setRolePermissionService(RolePermissionService rolePermissionService) {
        this.rolePermissionService = rolePermissionService;
    }

    @ApiOperation(value = " 获取菜单，用户名，头像信息 菜单渲染")
    @GetMapping("/info")
    public DataResponse<?> getInfo(@RequestHeader("_ut") String token) throws TransException {

        User user = userService.findUser(token);

        List<String> roleIds = userRoleService.findRoleIds(user.getId());

        List<String> permissionIds = rolePermissionService.findPermissionIds(roleIds);
        List<PermissionVO> menu = null;
        // todo permiss 权限
        List<String> permiss = null;
        if (permissionIds.size() > 0) {
            menu = permissionService.findMenu(permissionIds);
            permiss = permissionService.findPermiss(permissionIds);
        }

        // TODO commonVO类返回菜单,用户名,头像信息
        CommonVO commonVO = CommonConvent.INSTANCE.userAndMenuPermissionToCommonVO(user, menu, permiss);
        return ok(commonVO);
    }

    // TODO 记录未读数

    @ApiOperation(value = " 记录未读数")
    @GetMapping("/countUnread")
    public DataResponse<?> countUnread(@RequestHeader("_ut") String token) throws TransException {
        User user = userService.findUser(token);
        int countNotice = noticeUserService.countByUserId(user.getId());
        int countDepartApplication = departApplicationUserService.countFlowAgreeNull(user.getId());
        return ok(countNotice + countDepartApplication);
    }

    // TODO 上传维修图片

    @ApiOperation(value = "上传维修图片")
    @PostMapping("/upload")
    public DataResponse<?> upload(@RequestParam("file") MultipartFile file, @RequestHeader("_ut") String token) throws TransException, IOException {
        String fileName = UUID.randomUUID().toString() + Objects.requireNonNull(file.getOriginalFilename()).
                substring(file.getOriginalFilename().lastIndexOf("."));
        if (file != null) {
            String md5 = MD5Util.getFileMD5(file.getInputStream());
            if (md5 == null) {
                throw new TransException(TransCode.CUSTOM_EXCEPTION_MSG, "上传失败");
            }
            List<Image> list = imageService.selectByMd5(md5);
            if (list.size() == 0) {
                file.transferTo(new File("E://uploadImg//", fileName));
                User user = userService.findUser(token);
                Image image = new Image();
                image.setId(String.valueOf(SnowFlake.nextId()));
                image.setUploadUser(user.getId());
                image.setOriginalName(file.getOriginalFilename());
                image.setSaveName(fileName);
                image.setMd5(md5);
                imageService.insert(image);
            } else {
                fileName = list.get(0).getSaveName();
            }
        }
        return ok(fileName);
    }

    /**
     * 图表查询每个学院人数
     * @return
     * @throws TransException
     */
    @GetMapping("/number")
    public DataResponse<?> number() throws TransException {
        return ok(facultyService.FaculyByNumber());
    }
}
