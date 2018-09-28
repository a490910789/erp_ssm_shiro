package cn.test.bus.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.test.bus.utils.RandomUtils;

@Controller
@RequestMapping("upload")
public class UploadController {
   
	@RequestMapping("goodsImgUpload")
	@ResponseBody
    public Map<String,Object> goodsImgUpload(HttpSession session, MultipartFile mf) {
		 Map<String,Object> map=new HashMap<>();
			//1,得到文件上传的保存路径
			String path = session.getServletContext().getRealPath("/upload/");
			//2,得到文件的老名字
			String oldName=mf.getOriginalFilename();
			//3,得到文件夹的名字
			String dirName=RandomUtils.getDirNameUseTime();
			//4,判断这个文件夹是否存在
			File dir=new File(path,dirName);
			if(!dir.exists()){
				dir.mkdirs();//创建文件夹
			}
			//5,根据老名字生成新名字
			String newName=RandomUtils.createFileNewNameUseTime(oldName);
			//6,组装文件   参数1 父文件夹的路径  参数2  文件保存的名字
			File file=new File(dir, newName);
			//7,保存文件到file
			try {
				mf.transferTo(file);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			String imgpath="../upload/"+dirName+"/"+newName;
			map.put("path", imgpath);
			return map;		
	}
}
