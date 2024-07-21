package scv.DevOpsunity.shopInfo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("shopInfoController")
public class ShopInfoController {
    @GetMapping("/shopInfo.do")
    public ModelAndView ShopInfo(HttpServletRequest request, HttpServletResponse response) throws Exception{
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/shopInfo/shopInfo");
        return mav;
    }
}
