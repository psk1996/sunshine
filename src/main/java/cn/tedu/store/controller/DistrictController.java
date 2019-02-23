package cn.tedu.store.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.store.entity.District;
import cn.tedu.store.service.IDistrictService;
import cn.tedu.store.util.ResponseResult;
/**
 * 控制器实现类
 * @author Administrator
 *
 */
@RestController
@RequestMapping("districts")
public class DistrictController extends BaseController{
	@Autowired 
	IDistrictService districtService;
	
	@RequestMapping("/")
	public ResponseResult<List<District>> getListByParent(@RequestParam("parent") String parent) {
		List<District> data=districtService.findListByParent(parent);
		return new ResponseResult<List<District>>(SUCCESS,data);
	}
	
	@RequestMapping("{code}")
	public ResponseResult<District> getByCode(@PathVariable("code") String code) {
		District data=districtService.findByCode(code);
		return new ResponseResult<District>(SUCCESS,data);
	}
}
