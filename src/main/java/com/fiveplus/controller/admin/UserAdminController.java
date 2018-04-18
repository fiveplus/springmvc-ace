package com.fiveplus.controller.admin;

import com.fiveplus.controller.admin.bo.AdditionalParameters;
import com.fiveplus.controller.admin.bo.Item;
import com.fiveplus.controller.admin.bo.TreeRespBO;
import com.fiveplus.controller.admin.bo.UserBO;
import com.fiveplus.entity.Group;
import com.fiveplus.entity.Permission;
import com.fiveplus.entity.User;
import com.fiveplus.entity.UserPermission;
import com.fiveplus.service.GroupService;
import com.fiveplus.service.PermissionService;
import com.fiveplus.service.UserPermissionService;
import com.fiveplus.service.UserService;
import com.fiveplus.utils.*;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.*;

@Controller
@RequestMapping("/admin/user")
public class UserAdminController {
    @Autowired
    private PasswordHelper passwordHelper;

    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private UserPermissionService userPermissionService;

    @RequestMapping("/list/{page}")
    public String list(@PathVariable int page, HttpServletRequest request, Model model){
        PageInfo<UserBO> pu = userService.getUserList(page);
        PageCode pc = new PageCode(page, pu.getPages());
        model.addAttribute("pu",pu);
        model.addAttribute("pc",pc);
        return "user/list";
    }

    @RequestMapping("/select/{id}")
    public String select(@PathVariable int id,HttpServletRequest request,Model model){
        UserBO us = userService.getUserById(id);
        model.addAttribute("us",us);
        return "user/select";
    }

    @RequestMapping("/perlist/{id}")
    public String perlist(@PathVariable int id,HttpServletRequest request,Model model){
        User us = userService.queryById(id);
        model.addAttribute("us",us);
        return "user/perlist";
    }

    @RequestMapping("/perlist.json")
    public @ResponseBody TreeRespBO perlist_json(String pid,int uid,HttpServletRequest request){
        User us = userService.queryById(uid);
        List<Permission> perlist = permissionService.getChildPermissionByUserId(uid);
        if(perlist == null || (perlist != null && perlist.size() == 0)){
            perlist = permissionService.getChildPermissionByGroupId(us.getGroupId());
        }
        List<Permission> list = permissionService.getPermissionByParentId(pid);
        TreeRespBO tree = new TreeRespBO();
        List<Item> boItemList = new ArrayList<Item>();
        if(null != list && list.size() > 0){
            for(Permission p:list){
                Item item = new Item();
                //查询子节点数量
                int child_count = permissionService.getCountByParentId(p.getId());
                item.setName(p.getName());
                if(child_count > 0){
                    item.setType("folder");
                    AdditionalParameters adp = new AdditionalParameters();
                    adp.setId(p.getId());
                    item.setAdditionalParameters(adp);
                }else{
                    AdditionalParameters adp = new AdditionalParameters();
                    adp.setId(p.getId());
                    item.setAdditionalParameters(adp);
                    for(Permission per:perlist){
                        if(per.getId().equals(p.getId())){
                            adp.setItemSeleted(true);
                            break;
                        }
                    }

                    item.setType("item");
                }
                boItemList.add(item);
            }
        }
        tree.setData(boItemList);
        tree.setStatus("OK");
        return tree;
    }

    @RequestMapping("/savepers")
    public @ResponseBody Map<String,Object> savepers(int uid,String pids,HttpServletRequest request,Model model){
        Map<String,Object> returnMap = new HashMap<String,Object>();
        //权限清除
        userPermissionService.deletePermissionByUserId(uid);
        if(pids != null && !pids.equals("")){
            //权限保存
            String[] ids = pids.split(",");
            for(String id:ids){
                UserPermission up = new UserPermission();
                up.setUserId(uid);
                up.setPermissionId(id);
                int count = userPermissionService.saveSelect(up);
                System.out.println("dp:"+count);
            }
        }
        returnMap.put("code", 0);
        returnMap.put("msg", "成功！很好地完成了提交。");

        return returnMap;
    }


    @RequestMapping("/upt/{id}")
    public String upt(@PathVariable String id,HttpServletRequest request,Model model){
        User us = userService.queryById(id);
        List<Group> groups = groupService.queryAll();
        model.addAttribute("groups",groups);
        model.addAttribute("us",us);
        return "user/update";
    }

    @RequestMapping("/update")
    public @ResponseBody Map<String,Object> update(User us,HttpServletRequest request,Model model){
        Map<String,Object> returnMap = new HashMap<String, Object>();
        int count = userService.updateSelective(us);
        if(count > 0){
            returnMap.put("msg", "成功！很好地完成了提交。");
            returnMap.put("code", 0);
        }else{
            returnMap.put("msg", "错误！请进行一些更改。");
            returnMap.put("code", 4);
        }
        return returnMap;
    }

    /**
     * 初始化密码
     * @param id
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/initpass")
    public @ResponseBody Map<String,Object> initpass(String id,HttpServletRequest request,Model model){
        Map<String,Object> returnMap = new HashMap<String, Object>();
        User us = userService.queryById(id);
        PropertiesUtils util = new PropertiesUtils();
        us.setPassword(util.getProperty("init.pass"));
        us = passwordHelper.encryptPassword(us);
        int count = userService.updateSelective(us);
        if(count > 0){
            returnMap.put("msg", "成功！很好地完成了提交。");
            returnMap.put("code", 0);
        }else{
            returnMap.put("msg", "错误！请进行一些更改。");
            returnMap.put("code", 4);
        }
        return returnMap;
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request,Model model){
        List<Group> groups = groupService.queryAll();
        model.addAttribute("groups",groups);
        return "user/add";
    }

    @RequestMapping("/save")
    public @ResponseBody Map<String,Object> save(User us,HttpServletRequest request,Model model){
        Map<String,Object> returnMap = new HashMap<String, Object>();
        User u = userService.getUserByLoginName(us.getLoginName());
        int count = 0;
        String message = "成功！用户创建成功，初始化密码为Wuhan2016";
        if(u == null){
            count = userService.saveUser(us);
        }else{
            message = "错误！用户名已存在，请重新输入!";
        }

        if(count > 0){
            returnMap.put("msg",message);
            returnMap.put("code", 0);
        }else{
            returnMap.put("msg",message);
            returnMap.put("code", 4);
        }
        return returnMap;
    }

    @RequestMapping("/uptuser")
    public String uptuser(HttpServletRequest request,Model model){
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        UserBO us = userService.getUserById(user.getId());
        model.addAttribute("us",us);
        return "user/uptuser";
    }

    @RequestMapping("/updateuser")
    public @ResponseBody Map<String,Object> updateuser(User us,HttpServletRequest request,Model model){
        Map<String,Object> returnMap = new HashMap<String,Object>();

        if(us.getPassword().equals("")){
            us.setPassword(null);
        }else{
            us = passwordHelper.encryptPassword(us);
        }

        int count = userService.updateSelective(us);
        if(count > 0){
            returnMap.put("msg", "成功！很好地完成了提交。");
            returnMap.put("code", 0);
        }else{
            returnMap.put("msg", "错误！请进行一些更改。");
            returnMap.put("code", 4);
        }

        return returnMap;
    }

    @RequestMapping("/head")
    public String head(HttpServletRequest request,Model model){
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        UserBO us = userService.getUserById(user.getId());
        model.addAttribute("us",us);
        return "user/head";
    }

    @RequestMapping("/upload")
    public @ResponseBody Map<String,Object> upload(HttpServletRequest request,@RequestParam MultipartFile file) throws Exception{
        String filePath = request.getSession().getServletContext().getRealPath("/") + "upload_images/";
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        Map<String,Object> returnMap = new HashMap<String,Object>();

        String _x = request.getParameter("x");
        String _y = request.getParameter("y");
        String _width = request.getParameter("width");
        String _height = request.getParameter("height");
        int x = _x == null ? 0 : Integer.parseInt(_x);
        int y = _y == null ? 0 : Integer.parseInt(_y);
        int width = _width == null ? 0 : Integer.parseInt(_width);
        int height = _height == null ? 0 : Integer.parseInt(_height);

        String fileName = file.getOriginalFilename();
        if (fileName == null || "".equals(fileName))
        {
            returnMap.put("code", 4);
            returnMap.put("msg", "错误！文件名不存在。");
        }else{
            String fileEx = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
            if(fileEx.equals(".png")|| fileEx.equals(".jpg")||fileEx.equals(".gif")||fileEx.equals(".jpeg")){
                String fn = user.getLoginName() + fileEx;
                try {
                    //文件保存路径
                    String fp = filePath + fn;
                    // 转存文件
                    file.transferTo(new File(fp));
                    //裁剪图片
                    ImageUtils.cutImage(fp, x, y, width, height);
                    //修改数据库记录
                    user.setPortrait("upload_images/"+fn);
                    int count = userService.updateSelective(user);
                    if(count > 0){
                        returnMap.put("msg", "成功！很好地完成了提交。");
                        returnMap.put("code", 0);
                    }else{
                        returnMap.put("msg", "错误！请进行一些更改。");
                        returnMap.put("code", 4);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                returnMap.put("code", 4);
                returnMap.put("msg", "错误！上传图片类型错误。");
            }
        }
        return returnMap;
    }


}
