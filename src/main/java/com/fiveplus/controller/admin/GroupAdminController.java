package com.fiveplus.controller.admin;

import com.fiveplus.controller.admin.bo.AdditionalParameters;
import com.fiveplus.controller.admin.bo.Item;
import com.fiveplus.controller.admin.bo.TreeRespBO;
import com.fiveplus.entity.Group;
import com.fiveplus.entity.GroupPermission;
import com.fiveplus.entity.Permission;
import com.fiveplus.service.GroupPermissionService;
import com.fiveplus.service.GroupService;
import com.fiveplus.service.PermissionService;
import com.fiveplus.utils.PageCode;
import com.fiveplus.utils.StringUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/admin/group")
public class GroupAdminController {
	@Autowired
	private GroupService groupService;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private GroupPermissionService groupPermissionService;

	@RequestMapping("/list/{page}")
	public String list(@PathVariable int page,HttpServletRequest request,Model model){
		PageInfo<Group> pu = groupService.queryPageListByWhere(null, page, 10);
		PageCode pc = new PageCode(page, pu.getPages());
		model.addAttribute("pu", pu);
		model.addAttribute("pc", pc);
		return "group/list";
	}

	@RequestMapping("/select/{id}")
	public String select(@PathVariable String id,HttpServletRequest request,Model model){
		Group group = groupService.queryById(id);
		model.addAttribute("group",group);
		return "group/select";
	}

	@RequestMapping("/upt/{id}")
	public String upt(@PathVariable String id,HttpServletRequest request,Model model){
		Group group = groupService.queryById(id);
		model.addAttribute("group",group);
		return "group/update";
	}

	@RequestMapping("/update")
	public @ResponseBody Map<String,Object> update(Group group,HttpServletRequest request,Model model){
		Map<String,Object> returnMap = new HashMap<String, Object>();
		Group g = groupService.queryByName(group.getName());
		if(g == null || (g != null && group.getName().equals(g.getName()))){
			int count = groupService.updateSelective(group);
			if(count > 0){
				returnMap.put("msg", "成功！很好地完成了提交。");
				returnMap.put("code", 0);
			}else{
				returnMap.put("msg", "错误！请进行一些更改。");
				returnMap.put("code", 4);
			}
		}else{
			returnMap.put("msg", "错误！组名称已存在。");
			returnMap.put("code", 4);
		}
		return returnMap;
	}

	@RequestMapping("/add")
	public String add(HttpServletRequest request,Model model){
		return "group/add";
	}

	@RequestMapping("/save")
	public @ResponseBody Map<String,Object> save(Group group,HttpServletRequest request,Model model){
		Map<String,Object> returnMap = new HashMap<String, Object>();
		Group g = groupService.queryByName(group.getName());
		if(g == null || (g != null && group.getName().equals(g.getName()))){
			group.setCreateTime(StringUtils.getDateToLong(new Date()));
			int count = groupService.saveSelect(group);
			if(count > 0){
				returnMap.put("msg", "成功！很好地完成了提交。");
				returnMap.put("code", 0);
			}else{
				returnMap.put("msg", "错误！请进行一些更改。");
				returnMap.put("code", 4);
			}
		}else{
			returnMap.put("msg", "错误！组名称已存在。");
			returnMap.put("code", 4);
		}
		return returnMap;
	}

	@RequestMapping("/perlist/{id}")
	public String perlist(@PathVariable String id,HttpServletRequest request,Model model){
		Group group =groupService.queryById(id);
		model.addAttribute("group",group);
		return "group/perlist";
	}

	@RequestMapping("/perlist.json")
	public @ResponseBody TreeRespBO perlist_json(String pid,int gid,HttpServletRequest request,Model model){
		List<Permission> perlist = permissionService.getChildPermissionByGroupId(gid);
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
	public @ResponseBody Map<String,Object> savepers(int gid,String pids,HttpServletRequest request,Model model){
		Map<String,Object> returnMap = new HashMap<String,Object>();
		//权限清除
		groupPermissionService.deletePermissionByGroupId(gid);
		if(pids != null && !pids.equals("")){
			//权限保存
			String[] ids = pids.split(",");
			for(String id:ids){
				GroupPermission gp = new GroupPermission();
				gp.setGroupId(gid);
				gp.setPermissionId(id);
				int count = groupPermissionService.saveSelect(gp);
			}
		}
		returnMap.put("code", 0);
		returnMap.put("msg", "成功！很好地完成了提交。");

		return returnMap;
	}



}
