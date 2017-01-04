package com.chen.web;

import com.chen.socket.SocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.socket.TextMessage;

import javax.servlet.http.HttpSession;

/**
 * @desp Socket控制器
 * @author liulichao@ruc.edu.cn
 * @date 2016-5-6
 *
 */
@Controller
public class SocketController{

//    private static final Logger logger = LoggerFactory.getLogger(SocketController.class);

    @Autowired
    private SocketHandler socketHandler;

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping(value="/login")
    public String login(HttpSession session, String user, Model model){
        System.out.println("用户登录了建立连接啦");
        if(user!=null&&user!=""){
            session.setAttribute("user", user);
        }else{
            session.setAttribute("user", "liulichao");
        }
        model.addAttribute(user);
        model.addAttribute("count",SocketHandler.count);
        return "home";
    }

    @RequestMapping(value = "/message")
    public Boolean sendMessage(String user,String msg){

        socketHandler.sendMessageToUsers(new TextMessage(user+"："+msg));

        return true;
    }

}
