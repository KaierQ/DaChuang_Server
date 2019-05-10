package cn.edu.sicnu.cs.controller.terminal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 控制终端系统的控制器
 *
 * @author kaier
 * @date 2019-05-10 23:53
 */
@Controller
@RequestMapping("/terminal")
public class TerminalController {

    @RequestMapping("/terminal")
    public String checkManager(){
        return "terminal/terminal";
    }


}
