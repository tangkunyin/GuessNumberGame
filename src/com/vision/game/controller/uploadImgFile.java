package com.vision.game.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.vision.game.utils.PinYinUitl;

/**
 * 处理文件上传
 * 如果上传的文件在服务器上有，就不再上传文件，直接返回地址
 * @author tangkunyin
 */
@Controller
public class uploadImgFile {
	@RequestMapping(value="/uploadImgFile.ktv",method=RequestMethod.POST)
    public void upload(@RequestParam("acPicAddress") MultipartFile image,HttpServletRequest request,HttpServletResponse response) throws IOException
    {
		response.setContentType("text/html;charset=UTF-8");
		//判断文件类型
		String fileType=image.getContentType().toString();
		//支持的类型
		String fileTypes[]=new String[]{"image/png","image/gif","image/jpg","image/jpeg"};
		PrintWriter out=response.getWriter();
		for(int i=0;i<fileTypes.length;i++){
			if(fileTypes[i].equalsIgnoreCase(fileType)){
				@SuppressWarnings("deprecation")
				String path = request.getRealPath("/resources/uploadfile");
				//对中午文件名进行转换，将其变成拼音
		        String imgpath="/resources/uploadfile/"+PinYinUitl.getStringPinYin(image.getOriginalFilename());
		        
		        //将相对路劲保存到数据库中，方便调用
		        HttpSession session = request.getSession();
		        session.setAttribute("imgsrc", imgpath);
		        
		        //判断服务器是否有
		        File file=new File(path+"/"+PinYinUitl.getStringPinYin(image.getOriginalFilename()));
		        if(file.exists() && file.isFile()){
		        	System.out.println("++++++++++++++++ 上传的图片已经存在，系统自动使用之前图片 ++++++++++++++++");
		        }else{
		        	FileCopyUtils.copy(image.getBytes(),file); 
		        }
		        out.write(request.getContextPath()+imgpath);
		        out.close();
		        return;
			}
		}
		//设置错误消息，并终止程序
		out.write("请上传合法的图片类型，支持：png|gif|jpg|jpeg");
		out.close();
		return;
    }
}
