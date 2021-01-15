package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;
@Controller
//
public class BookingController {

    @Autowired
    UserAccountRepository userAccountRepository;

    /*
     * Mapping url exmaple:
     * http://localhost:18089/addUser
     */

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public ModelAndView users() {
        return new ModelAndView("addUser", "command", new UserAccount());

    }

    @ModelAttribute("users")
    public UserAccount createUserAccountModel() {
        return new UserAccount();
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("users") @Validated UserAccount addUser,
                          Model model) {

        model.addAttribute("username", addUser.getUsername());
        model.addAttribute("password", addUser.getPassword());
        model.addAttribute("email", addUser.getEmail());
        model.addAttribute("thing1", addUser.getThing1());
        model.addAttribute("thing2", addUser.getThing2());
        model.addAttribute("thing3", addUser.getThing3());
        model.addAttribute("firstname", addUser.getFirstname());
        model.addAttribute("lastname", addUser.getLastname());
        model.addAttribute("phone", addUser.getPhone());
        model.addAttribute("about", addUser.getAbout());
        UserAccount userAccount = new UserAccount();
        userAccount.setUsername(addUser.getUsername());
        userAccount.setPassword(addUser.getPassword());
        userAccount.setEmail(addUser.getEmail());
        userAccount.setThing1(addUser.getThing1());
        userAccount.setThing2(addUser.getThing2());
        userAccount.setThing3(addUser.getThing3());
        userAccount.setFirstname(addUser.getFirstname());
        userAccount.setLastname(addUser.getLastname());
        userAccount.setPhone(addUser.getPhone());
        userAccount.setAbout(addUser.getAbout());
        userAccountRepository.save(userAccount);
        return "users";
    }

    //-------------------------------------------------- login spring

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView user() {
        return new ModelAndView("login", "command", new UserAccount());

    }

    @ModelAttribute("user")
    public UserAccount createuseraccountmodel() {
        return new UserAccount();
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute("user") @Validated UserAccount login,
                        Model model) {
        model.addAttribute("username", login.getUsername());
        model.addAttribute("password", login.getPassword());
        if (login.getUsername().equals("admin") && login.getPassword().equals("1111")) {
            model.addAttribute("admin", userAccountRepository.findAll());
            return "admin";
        } else {
            List<UserAccount> userAccountList = (List<UserAccount>) userAccountRepository
                    .findByUsernameAndPassword(login.getUsername(), login.getPassword());

            if (userAccountList != null) {
                for (UserAccount userAccount : userAccountList) {
                    model.addAttribute("username", userAccount.getUsername());
                    model.addAttribute("firstname", userAccount.getFirstname());
                    model.addAttribute("lastname", userAccount.getLastname());
                    model.addAttribute("phone", userAccount.getPhone());
                    model.addAttribute("about", userAccount.getAbout());
                    model.addAttribute("email", userAccount.getEmail());
                    model.addAttribute("thing1", userAccount.getThing1());
                    model.addAttribute("thing2", userAccount.getThing2());
                    model.addAttribute("thing3", userAccount.getThing3());
                    model.addAttribute("id", userAccount.getId());
                    userAccountRepository.save(userAccount);
                }
                return "users";
            }
            model.addAttribute("invalidCredentials", true);
            return "login";
        }
    }

    /*------------------------------------------------
     * Mapping url exmaple: http://localhost:18089/all
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String findall(Model model) {

        model.addAttribute("find", userAccountRepository.findAll());
        return "all";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String alladmin(Model model) {


        return "admin";
    }

    /*
     * Mapping url exmaple:
     * http://localhost:18089/findbyname
     */
    @RequestMapping(value = "/findbyname", method = RequestMethod.GET)
    public ModelAndView finduser() {
        return new ModelAndView("findbyname", "command", new UserAccount());

    }

    @ModelAttribute("users")
    public UserAccount findByName() {
        return new UserAccount();
    }

    @PostMapping(path = "/findbyname")
    public String findByName(@ModelAttribute("users") UserAccount user,
                             ModelMap model) {

        StringBuffer retBuf = new StringBuffer();
        model.addAttribute("username", user.getUsername());
        List<UserAccount> userAccountList = userAccountRepository.findByUsername(user.getUsername());

        if (userAccountList != null) {
            for (UserAccount userAccount : userAccountList) {
                model.addAttribute("username", userAccount.getUsername());
                model.addAttribute("firstname", userAccount.getFirstname());
                model.addAttribute("about", userAccount.getAbout());
                model.addAttribute("email", userAccount.getEmail());
                model.addAttribute("thing1", userAccount.getThing1());
                model.addAttribute("thing2", userAccount.getThing2());
                model.addAttribute("thing3", userAccount.getThing3());
                userAccountRepository.save(userAccount);
            }
            return "users";
        }

        if (retBuf.length() == 0) {
            retBuf.append("No record find.");
        }

        return retBuf.toString();
    }

    @RequestMapping(value = "/findbycity", method = RequestMethod.GET)
    public ModelAndView findciti() {
        return new ModelAndView("findbycity", "command", new UserAccount());

    }

    @ModelAttribute("users")
    public UserAccount findByThing3() {
        return new UserAccount();
    }

    @PostMapping(path = "/findbycity")
    public String findByThing3(@ModelAttribute("users") UserAccount user,
                               ModelMap model) {

        StringBuffer retBuf = new StringBuffer();
        model.addAttribute("thing3", user.getThing3());
        List<UserAccount> userAccountList = userAccountRepository.findByThing3(user.getThing3());

        if (userAccountList != null) {
            for (UserAccount userAccount : userAccountList) {
                model.addAttribute("username", userAccount.getUsername());
                model.addAttribute("firstname", userAccount.getFirstname());
                model.addAttribute("about", userAccount.getAbout());
                model.addAttribute("email", userAccount.getEmail());
                model.addAttribute("thing1", userAccount.getThing1());
                model.addAttribute("thing2", userAccount.getThing2());
                model.addAttribute("thing3", userAccount.getThing3());
                userAccountRepository.save(userAccount);
            }
            return "users";
        }

        if (retBuf.length() == 0) {
            retBuf.append("No record find.");
        }

        return retBuf.toString();
    }

    /*
     * Mapping url exmaple:
     * http://localhost:18089/findbehavandfav
     */
    @RequestMapping(value = "/findbehavandfav", method = RequestMethod.GET)
    public ModelAndView finduserbehav() {
        return new ModelAndView("findbehavandfav", "command", new UserAccount());

    }

    @ModelAttribute("users")
    public UserAccount findByThing1AndThing2() {
        return new UserAccount();
    }

    @PostMapping(path = "/findbehavandfav")

//thing1 means behavior thing2 means favorite
    public String findByThing1AndThing2(@ModelAttribute("users") UserAccount user,
                                        ModelMap model) {

        StringBuffer retBuf = new StringBuffer();
        model.addAttribute("behavior", user.getThing1());
        model.addAttribute("favorite", user.getThing2());
        List<UserAccount> userAccountList = userAccountRepository.findByThing1AndThing2(user.getThing1(), user.getThing2());

        if (userAccountList != null) {
            for (UserAccount userAccount : userAccountList) {
                model.addAttribute("username", userAccount.getUsername());
                model.addAttribute("firstname", userAccount.getFirstname());
                model.addAttribute("about", userAccount.getAbout());
                model.addAttribute("email", userAccount.getEmail());
                model.addAttribute("thing1", userAccount.getThing1());
                model.addAttribute("thing2", userAccount.getThing2());
                model.addAttribute("thing3", userAccount.getThing3());
                userAccountRepository.save(userAccount);

            }

            return "users";
        }

        if (retBuf.length() == 0) {
            retBuf.append("No record find.");
        }

        return retBuf.toString();
    }

    /*
     * Mapping url exmaple:
     * http://localhost:18089/update
     */

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public ModelAndView updateuser() {
        return new ModelAndView("update", "command", new UserAccount());

    }

    @ModelAttribute("users")
    public UserAccount updateUser() {
        return new UserAccount();
    }

    @PostMapping(path = "/update")
    public String updateUser(@ModelAttribute("users") UserAccount user,
                             ModelMap model) {

        StringBuffer retBuf = new StringBuffer();
        model.addAttribute("username", user.getUsername());
        List<UserAccount> userAccountList = userAccountRepository.findByUsername(user.getUsername());

        if (userAccountList != null) {
            for (UserAccount userAccount : userAccountList) {
                model.addAttribute("email", userAccount.getEmail());
                if (userAccount.getEmail() != null) {
                    model.addAttribute("email", user.getEmail());
                    model.addAttribute("about", user.getAbout());
                    model.addAttribute("thing1", user.getThing1());
                    model.addAttribute("thing2", user.getThing2());
                    userAccount.setEmail(user.getEmail());
                    userAccount.setThing1(user.getThing1());
                    userAccount.setThing2(user.getThing2());
                    userAccount.setAbout(user.getAbout());
                    userAccountRepository.save(userAccount);
                }
            }
            return "users";
        }

        retBuf.append("User data update successfully.");

        return retBuf.toString();
    }

    /*
     * Mapping url exmaple:
     * http://localhost:18089/deleteByid/3
     */

    @GetMapping(path = "/deleteByid/{id}")
    public String deleteById(@PathVariable(name = "id", required = false) String id, Model model) {

        userAccountRepository.deleteById(id);
        model.addAttribute("admin", userAccountRepository.findAll());
        return "admin";
    }

    /*
     * Mapping url exmaple:
     *
     * http://localhost:18089/deleteacc
     */
    @RequestMapping(value = "/deleteacc", method = RequestMethod.GET)
    public ModelAndView deleteacc() {
        return new ModelAndView("deleteacc", "command", new UserAccount());

    }

    @ModelAttribute("users")
    public UserAccount deleteByUserNameAndPassword() {
        return new UserAccount();
    }

    @PostMapping(path = "/deleteacc")
    public String deleteByUserNameAndPassword(@ModelAttribute("users") UserAccount user,
                                              ModelMap model) {

        model.addAttribute("username", user.getUsername());
        model.addAttribute("password", user.getPassword());
        List<UserAccount> userAccountList = userAccountRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());

        if (userAccountList != null) {
            for (UserAccount userAccount : userAccountList) {
                model.addAttribute("email", userAccount.getEmail());
                if (userAccount.getEmail() != null) {

                    userAccountRepository.deleteByUsernameAndPassword(user.getUsername(), user.getPassword());

                    model.addAttribute("find", userAccountRepository.findAll());
                    return "all";
                }
            }
        }
        return "deleteacc";
    }
    @RequestMapping(value = "/deleteusername", method = RequestMethod.GET)
    public ModelAndView deleteuser() {
        return new ModelAndView("deleteusername", "command", new UserAccount());

    }

    @ModelAttribute("users")
    public UserAccount deleteByName() {
        return new UserAccount();
    }
    @PostMapping(path = "/deleteusername")
    public String deleteByUserName(@ModelAttribute("users") UserAccount user,
                                              ModelMap model) {

        model.addAttribute("username", user.getUsername());
        List<UserAccount> userAccountList = userAccountRepository.findByUsername(user.getUsername());

        if (userAccountList != null) {
            for (UserAccount userAccount : userAccountList) {
                model.addAttribute("email", userAccount.getEmail());
                if (userAccount.getEmail() != null) {

                    userAccountRepository.deleteByUsername(user.getUsername());

                    model.addAttribute("find", userAccountRepository.findAll());
                    return "all";
                }
            }
        }
        return "deleteacc";
    }

    @GetMapping(path = "/report/{id}")
    public String findByUserId(@PathVariable(name = "id", required = false) String id) {
        StringBuffer retBuf = new StringBuffer();

        List<UserAccount> userAccountList = userAccountRepository.findById(id);
        if (userAccountList != null) {
            for (UserAccount userAccount : userAccountList) {
                userAccount.setReport(userAccount.getReport() + 1);
                userAccountRepository.save(userAccount);
            }}
        String ret= "User data update successfully.";

        return "request/watchreq/";
    }
}