package com.sg.ssm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sg.ssm.pojo.User;
import com.sg.ssm.service.IUserService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("uc")
public class UserController {

    private IUserService userServiceImpl;

    public IUserService getUserServiceImpl() {
        return userServiceImpl;
    }

    public void setUserServiceImpl(IUserService userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }


    /**
     * 控制访问 页面
     *
     * @param req 前端超链接a携带的参数,指明要访问的页面
     * @return
     */
    @RequestMapping(value = "temp", method = RequestMethod.GET)
    public String register(String req) {
        System.out.println("req=" + req);
        if ("add".equals(req)) {
            return "register";
        }
        if ("login".equals(req)) {
            return "login";
        }
        return "index";
    }


    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String register(User user, @RequestParam MultipartFile picfile, HttpServletRequest request, Date birthday) {
        //日期格式转化测试
        System.out.println(birthday.toString());

        /*文件上传部分处理
		* (1)处理文件名
		* (2)写入到服务器硬盘
		* (3)将文件在服务器的路径保存在user里面，然后存入数据库
		* */

        /*处理文件名*/
        //获取文件的原始名称
        String filename = picfile.getOriginalFilename();
        System.out.println("文件名：" + filename);
        //获取文件的类型(所有的文件类型可以在tomcat的conf下的web.xml文件中查看)
        String fileType = picfile.getContentType();
        System.out.println("文件类型：" + fileType);

        //文件上传目录的绝对路径(这里暂时写死，手动创建upload文件夹)
        String realpath = request.getSession().getServletContext().getRealPath("/upload");
        //为防止文件重名上传，需要服务器自己给文件重新命名，常用两种方式,(1)UUID ,(2)使用时间戳
        String uuid = UUID.randomUUID().toString();
        //截取文件的后缀(包括点)
        String sufname = filename.substring(filename.lastIndexOf("."), filename.length());
        //最终给文件重新起的文件名
        String newFileName = uuid + sufname;
        //拼接文件上传到服务器的最终路径
        String picpath = realpath + "/" + newFileName;

		/*将文件写入到硬盘
		* (1)方式一:通过字节流边读边写将文件写入到硬盘
		* (2)方式二:通过commons-io包提供的工具来完成拷贝
		* */
        InputStream is = null;
        OutputStream os = null;
        //方式一
        /*try {
            is = picfile.getInputStream();
            os = new FileOutputStream(new File(picpath));
            byte[] b = new byte[1024];
            int len = is.read(b);
            while (len != -1) {
                os.write(b, 0, len);
                len = is.read(b);
            }
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (os != null){
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/

        //方式二
        try {
            is = picfile.getInputStream();
            os = new FileOutputStream(new File(picpath));
            //利用commons-io包中的工具完成拷贝
            FileCopyUtils.copy(is,os);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (is != null){
                    is.close();
                }
                if (os != null){
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /*将文件路径保存到user再保存到数据库,这里保存文件的相对路径即可（即文件名）*/
        user.setPicpath(newFileName);


        //保存到数据库
        boolean register = userServiceImpl.register(user);
        if (register) {
            System.out.println("register success");
            return "login";
        }
        System.out.println("register fail");
        return "register";
    }

    /**
     * 登录
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(User user, HttpSession session) {
        User loginUser = userServiceImpl.login(user);
        if (loginUser != null) {
            System.out.println("login success");
            session.setAttribute("user", loginUser);
            return "welcome";
        }
        return "login";
    }

    /**
     * 下载：
     * 1.需要把数据以流的形式来响应页面
     * 2.需要在页面上打开Content-disposition=attachment
     * @return
     */
    @RequestMapping("downloadfile")
    public ResponseEntity<byte[]> downloadFile(String picname,HttpServletRequest request){
        //第一步：读取在服务器文件的内容
        String realPath = request.getSession().getServletContext().getRealPath("/upload");
        File file = new File(realPath + "/" + picname);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment",picname);
        ResponseEntity<byte[]> re = null;
        try {
            re = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers,HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return re;
    }



    @RequestMapping(value="downloadmyfile")
    public ResponseEntity<byte[]> downloadFile1(String mypicname, HttpServletRequest request){
        System.out.println("downloadFile...");

        //需要先读取到文件的内容
        String realpath = request.getSession().getServletContext().getRealPath("/upload");
        File file = new File(realpath+"/"+mypicname);
        HttpHeaders header = new HttpHeaders();
        header.setContentDispositionFormData("attachment", mypicname);
        ResponseEntity<byte[]> re = null;
        try {
            re = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), header, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return re;
    }

}
