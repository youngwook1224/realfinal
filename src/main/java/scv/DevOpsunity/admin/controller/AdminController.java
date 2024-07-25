package scv.DevOpsunity.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import scv.DevOpsunity.admin.service.AdminService;


@Controller("adminController")
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("")
    public String adminMain(Model model) {
        return "redirect:/admin/dashboard.do";
    }

    @GetMapping("/dashboard.do")
    public String dashBoard(Model model) {
        long boardCount = adminService.getAllBoards().size();
        long memberCount = adminService.getTotalMembers();
        long commentCount = adminService.getAllComments().size();

        model.addAttribute("boards", boardCount);
        model.addAttribute("members", memberCount);
        model.addAttribute("comments", commentCount);

        System.out.println("Dashboard data - Boards: " + boardCount + ", Members: " + memberCount + ", Comments: " + commentCount);

        return "/admin/dashboard";
    }

    @GetMapping("/members.do")
    public String members(Model model) {
        model.addAttribute("membersList", adminService.getAllMembers());
        System.out.println(adminService.getAllMembers());
        return "member/listMembers";
    }

    @GetMapping("/boards")
    public String boards(Model model) {
        model.addAttribute("boards", adminService.getAllBoards());
        return "admin/boards";
    }

    @GetMapping("/comments")
    public String comments(Model model) {
        model.addAttribute("comments", adminService.getAllComments());
        return "admin/comments";
    }

    @PostMapping("/deleteBoard")
    @ResponseBody
    public String deleteBoard(@RequestParam int freeArticleNo) {
        adminService.deleteBoard(freeArticleNo);
        return "success";
    }

    @PostMapping("/deleteMember")
    @ResponseBody
    public String deleteMember(@RequestParam String id) {
        adminService.deleteMember(id);
        return "success";
    }

    @PostMapping("/deleteComment")
    @ResponseBody
    public String deleteComment(@RequestParam int commentNo) {
        adminService.deleteComment(commentNo);
        return "success";
    }
}
