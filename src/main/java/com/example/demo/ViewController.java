package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/* follow unfollow and reject side
    Z.B
    localhost:18089/request/delete?userid=2
     */

@Controller
@RequestMapping(path = "/request")
public class ViewController {
@Autowired
 requestRepository requestRepository;



    /*
     * Mapping url exmaple:
     * http://localhost:18089/request/delete?idr=
     */

    @GetMapping(path = "/delete")
    @ResponseBody
    public String deleteByIdr(@RequestParam String idr) {

        StringBuffer retBuf = new StringBuffer();

        requestRepository.deleteByIdr(idr);

        retBuf.append("User data has been deleted successfully.");

        return retBuf.toString();
    }

    /*
     * Mapping url exmaple:
     * http://localhost:8080/request/accept?userid=2&geuestid=4&tik=1
     */
    @GetMapping(path = "/accept")
    @ResponseBody
    public String updateUser(@RequestParam(value = "userId", required = false)
                                     String userId, @RequestParam (value = "guestId", required = false)
                                     String guestId, @RequestParam (value = "tik", required = false)String tik) {

        StringBuffer retBuf = new StringBuffer();

        List<request> userAccountList = requestRepository.findByUserId(userId);

        if (userAccountList != null) {
            for (request request : userAccountList) {
                request.setUserid(userId);
                request.setGuestid(guestId);
                request.setTik(tik);
                requestRepository.save(request);
            }
        }

        retBuf.append("User data update successfully.");


        return retBuf.toString();
    }

    /*
     * Mapping url exmaple:
     * http://localhost:18089/request/req?userId=2&guestId=4&tik=0
     */
    @GetMapping(path = "/req/{userId}/{guestId}/{tik}")
    @ResponseBody
    public String requestUser(@PathVariable(name="userId", required = false)
                                     String userId,@PathVariable(name="guestId", required = false)
                                     String guestId,@PathVariable(name="tik", required = false) String tik) {

        request request = new request();
        request.setUserid(userId);
        request.setGuestid(guestId);
        request.setTik(tik);

        requestRepository.save(request);

        String ret = "User account has been added, user  = " + userId + ", guest = " + guestId + ", check = "
                + tik;
        return ret;
    }

    @GetMapping(path = "/allreq")
       public String findAllUser(Model model) {


        model.addAttribute("request", requestRepository.findAll());

        return "allreq";

        }
    @GetMapping(path = "/watchreq/{userId}")
    public String findByUserId(@PathVariable(name = "userId", required = false) String userId,Model model) {


        model.addAttribute("request", requestRepository.findByUserId(userId));

        return "watchreq";

    }
    @GetMapping(path = "/deleteByid/{userid}/{guestid}")
    public String deleteById(@PathVariable(name = "userid", required = false) String userId, @PathVariable(name = "guestid", required = false) String guestId
            , Model model) {

        requestRepository.deleteByUserIdAndGuestId(userId,guestId);
        model.addAttribute("watchreq", requestRepository.findAll());
        model.addAttribute("request", requestRepository.findByUserId(userId));
        return "watchreq";
}

}
