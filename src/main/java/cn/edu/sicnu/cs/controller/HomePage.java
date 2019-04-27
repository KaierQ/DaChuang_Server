package cn.edu.sicnu.cs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author kaier
 * @date 2019-04-26 14:21
 */
@Controller
@RequestMapping("/homepage")
public class HomePage {

    @RequestMapping("/getHome/{cid}")
    public String requestHomePage(@PathVariable(value = "cid")String cid, Model model){
        System.out.println("cid:"+cid);
        model.addAttribute("cid",cid);
        return "/homepage/homepage";
    }

}

