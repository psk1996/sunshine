package cn.tedu.store.controller;

import cn.tedu.store.entity.Logo;
import cn.tedu.store.service.ILogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 * 控制器实现类   restFull风格的接口
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/Logo")
public class LogoController extends BaseController {
	@Autowired
	private ILogoService iLogoService;

	@RequestMapping(value = "" , method = RequestMethod.POST)
	public  Integer addNew(@RequestBody Logo logo){

		return iLogoService.addNew(logo) ;
	}

	@RequestMapping(value = "", method = RequestMethod.DELETE)
	public Integer deleteOne(@RequestParam("id") Integer id){

		return iLogoService.deleteOne(id);
	}

	@RequestMapping(value = "", method = RequestMethod.PUT)
	public Integer updateOne(@RequestParam("id") Integer id){
		return iLogoService.updateOne(id);
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public Logo selectOne(@RequestParam("id") Integer id){

		return iLogoService.selectOne(id);
	}


}
