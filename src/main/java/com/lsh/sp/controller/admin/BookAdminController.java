package com.lsh.sp.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lsh.sp.domain.ResponseResult;
import com.lsh.sp.pojo.Book;
import com.lsh.sp.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @Author luminous
 * @Description //TODO
 * @Date 10:40 2022/4/3
 **/
@RestController
@RequestMapping("admin")
@Slf4j
public class BookAdminController {
    @Autowired
    private BookService bookService;

    @PostMapping("create")
    public ResponseResult createBook(Book book){
        return bookService.createBook(book);
    }

    @PostMapping("update")
    public ResponseResult updateBook(Book book){
        Book book1 = bookService.updateBook(book);
        return new ResponseResult(200,"success",book1);
    }
//    假删除
    @PostMapping("delete")
    public ResponseResult deleteBook(Book book){
        bookService.deleteBook(book);
        return new ResponseResult(200,"success");
    }
//    查询删除的图书
    @PostMapping("selectDeleted")
    public ResponseResult selectDeleted(){
        List<Book> books = bookService.selectDeletedBook();
        return new ResponseResult(200,"success",books);
    }
//    恢复删除的图书
    @PostMapping("updateDeleted")
    public ResponseResult updateDeleted(Book book){
        Book book1 = bookService.updateBookDeleted(book);
        return new ResponseResult(200,"success",book1);
    }

    //查询可用的图书
    @GetMapping("list")
    public Map bookList(){
        Map result=new HashMap();
        List<Book> bookList = bookService.findAllBook();
        result.put("book",bookList);
        return result;
    }
//    页数   每页的数据量
    @PostMapping("pagebooks")
    public Map pageSelectBook(Integer page,Integer rows){
        if (page==null)page=1;
        if (rows==null)rows=10;

        IPage<Book> pageObject=bookService.bookPage(null,null,page,rows);
        Map result=new HashMap();
        result.put("data",pageObject.getRecords());
        result.put("count",pageObject.getTotal());
        result.put("pages",pageObject.getPages());
        return result;
    }

    @PostMapping(value = "upload")
    public Map upload(@RequestPart(value = "file",required = false) MultipartFile file,
                      @RequestPart(value = "files",required = false)MultipartFile[] files,
                      HttpSession session,
                      HttpServletRequest request) throws IOException {
        //通过servletContext获取服务器中photo目录路径  可能不存在
        ServletContext servletContext = session.getServletContext();
        //可能不存在
        String uploadPath = servletContext.getRealPath("upload");
        File file1=new File(uploadPath);

        //判断所对应路径是否存在
        if (!file1.exists()){
            file1.mkdir();

        }

        //----------------
        // 获取当前项目运行的完整url
        String requestURL = request.getRequestURL().toString();
        // 获取当前项目的请求路径url
        String requestURI = request.getRequestURI();
        // 得到去掉了uri的路径
        String url = requestURL.substring(0, requestURL.length()-requestURI.length() + 1);

        Map result = new HashMap();
        //单文件上传
        if (file!=null){
//        if (!file.isEmpty()){
            //获取文件名
            String filename = file.getOriginalFilename();
            System.out.println(filename);
            //获取上传文件的后缀名
            String suffixName = filename.substring(filename.lastIndexOf("."));
            System.out.println(suffixName);
            //将uuid作为文件名
            String uuid= UUID.randomUUID().toString();
            //拼接文件名
            filename=uuid+suffixName;
            //最终文件路径和文件名   绝对路径
        String finalName=file1+File.separator+ filename;
            //保存文件到upload目录
            file.transferTo(new File(finalName));
            //
            result.put("imgUrl", new String[]{ url+"upload/"+filename});

        }
        System.out.println("单文件上传结束");

        //多文件上传
        if (files!=null&&files.length>0){
//            存放多文件url
            ArrayList imgsUrl=new ArrayList();
            for (MultipartFile f:files) {
                if (!f.isEmpty()) {

                    //获取文件名
                    String filename = f.getOriginalFilename();
                    //获取上传文件的后缀名
                    String suffixName = filename.substring(filename.lastIndexOf("."));
                    //将uuid作为文件名
                    String uuid= UUID.randomUUID().toString();
                    //拼接文件名
                    filename=uuid+suffixName;
                    //最终文件路径和文件名   绝对路径
                    String finalName=uploadPath+File.separator+ filename;
                    System.out.println(finalName);

                    f.transferTo(new File(finalName));
                    imgsUrl.add(new String[]{ url+"upload/"+filename});
                }
            }
            result.put("imgsUrl", imgsUrl);
        }
        result.put("success", 0);
//        result.put("data", new String[]{ url+"upload/"+filename});
        return result;
    }


}
