package llcweb.com.controller;

import llcweb.com.dao.repository.DocumentRepository;
import llcweb.com.dao.repository.UsersRepository;
import llcweb.com.domain.models.Users;
import llcweb.com.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 *@Author: Ricardo
 *@Description: 此文件用于前端所有的页面的请求接口，接口名与html文件名对应
 *@Date: 15:14 2018/8/21
 **/
@Controller
@RequestMapping
public class WebPageController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UsersRepository usersRepository;
    private DocumentRepository documentRepository;
    @Autowired
    private UsersService usersService;

    //测试页面
    @RequestMapping("/test.html")
    public ModelAndView test(){

        List<String> learnList =new ArrayList<>();
        learnList.add("hello1");
        learnList.add("hello2");
        ModelAndView modelAndView = new ModelAndView("test");
        //添加当前登录用户
        modelAndView.addObject("learnList", learnList);
        return modelAndView;
    }
    @RequestMapping({"/","/index.html","/index","/main","/main.html"})
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("/home/index");
        return modelAndView;
    }
    @ResponseBody
    @RequestMapping("/hello")
    public Users hello(){
       return usersRepository.findOne(1);
    }

    //页面映射函数
    @RequestMapping({"/Academic_communication.html","/Academic_communication"})
    public ModelAndView Academic_communication(){
        ModelAndView modelAndView = new ModelAndView("/home/Academic_communication");
        return modelAndView;
    }
    @RequestMapping({"/Academic_conference.html","/Academic_conference"})
    public ModelAndView Academic_conference(){
        ModelAndView modelAndView = new ModelAndView("/home/Academic_conference");
        return modelAndView;
    }
    @RequestMapping({"/Academic_cooperation.html","/Academic_cooperation"})
    public ModelAndView Academic_cooperation(){
        ModelAndView modelAndView = new ModelAndView("/home/Academic_cooperation");
        return modelAndView;
    }
    @RequestMapping({"/Associate_professor.html","/Associate_professor"})
    public ModelAndView Associate_professor(){
        ModelAndView modelAndView = new ModelAndView("/home/Associate_professor");
        return modelAndView;
    }
    @RequestMapping({"/contact_us.html","/contact_us"})
    public ModelAndView contact_us(){
        ModelAndView modelAndView = new ModelAndView("/home/contact_us");
        return modelAndView;
    }
    @RequestMapping({"/Cultural_activity.html","/Cultural_activity"})
    public ModelAndView Cultural_activity(){
        ModelAndView modelAndView = new ModelAndView("/home/Cultural_activity");
        return modelAndView;
    }
    @RequestMapping({"/doctor.html","/doctor"})
    public ModelAndView doctor(){
        ModelAndView modelAndView = new ModelAndView("/home/doctor");
        return modelAndView;
    }


    @RequestMapping({"/graduate.html","/graduate"})
    public ModelAndView graduate(){
        ModelAndView modelAndView = new ModelAndView("/home/graduate");
        return modelAndView;
    }
    @RequestMapping({"/Institutional_profile.html","/Institutional_profile"})
    public ModelAndView Institutional_profile(){
        ModelAndView modelAndView = new ModelAndView("/home/Institutional_profile");
        return modelAndView;
    }
    @RequestMapping({"/Lecturer.html","/Lecturer"})
    public ModelAndView Lecturer(){
        ModelAndView modelAndView = new ModelAndView("/home/Lecturer");
        return modelAndView;
    }
    @RequestMapping({"/postdoctor.html","/postdoctor"})
    public ModelAndView postdoctor(){
        ModelAndView modelAndView = new ModelAndView("/home/postdoctor");
        return modelAndView;
    }
    @RequestMapping({"/Professor.html","/Professor"})
    public ModelAndView Professor(){
        ModelAndView modelAndView = new ModelAndView("/home/Professor");
        return modelAndView;
    }


    @RequestMapping({"/project_brief","/project_brief.html"})
    public ModelAndView project_brief(){
        ModelAndView modelAndView = new ModelAndView("/home/project_brief");
        return modelAndView;
    }
    @RequestMapping({"/Research_Project.html","/Research_Project"})
    public ModelAndView Research_Project(){
        ModelAndView modelAndView = new ModelAndView("/home/Research_Project");
        return modelAndView;
    }
    @RequestMapping({"/Research_Team.html","/Research_Team"})
    public ModelAndView Research_Team(){
        ModelAndView modelAndView = new ModelAndView("/home/Research_Team");
        return modelAndView;
    }
    @RequestMapping({"/scientific_achievements.html","/scientific_achievements"})
    public ModelAndView scientific_achievements(){
        ModelAndView modelAndView = new ModelAndView("/home/scientific_achievements");
        return modelAndView;
    }

    //
    @RequestMapping({"/Talent_recruitment.html","/Talent_recruitment"})
    public ModelAndView Talent_recruitment(){
        ModelAndView modelAndView = new ModelAndView("/home/Talent_recruitment");
        return modelAndView;
    }
    @RequestMapping({"/Talent_recruitment1.html","/Talent_recruitment1"})
    public ModelAndView Talent_recruitment1(){
        ModelAndView modelAndView = new ModelAndView("/home/Talent_recruitment1");
        return modelAndView;
    }
    @RequestMapping({"/Talent_recruitment2.html","/Talent_recruitment2"})
    public ModelAndView Talent_recruitment2(){
        ModelAndView modelAndView = new ModelAndView("/home/Talent_recruitment2");
        return modelAndView;
    }
    @RequestMapping({"/Talent_training.html","/Talent_training"})
    public ModelAndView Talent_training(){
        ModelAndView modelAndView = new ModelAndView("/home/Talent_training");
        return modelAndView;
    }
    @RequestMapping({"/Team_introduction.html","/Team_introduction"})
    public ModelAndView Team_introduction(){
        ModelAndView modelAndView = new ModelAndView("/home/Team_introduction");
        return modelAndView;
    }
    @RequestMapping({"/Untitled-1.html","/Untitled-1"})
    public ModelAndView Untitled_1(){
        ModelAndView modelAndView = new ModelAndView("/home/Untitled-1");
        return modelAndView;
    }



    //以下为子文件夹的页面映射函数
    /**
     * achievement
     * **/
    @RequestMapping({"/achievement/scientific_achievements1.html","/achievement/scientific_achievements1"})
    public ModelAndView scientific_achievements1(){
        ModelAndView modelAndView = new ModelAndView("/home/achievement/scientific_achievements1");
        return modelAndView;
    }
    @RequestMapping({"/achievement/scientific_achievements2.html","/achievement/scientific_achievements2"})
    public ModelAndView scientific_achievements2(){
        ModelAndView modelAndView = new ModelAndView("/home/achievement/scientific_achievements2");
        return modelAndView;
    }
    @RequestMapping({"/achievement/scientific_achievements3.html","/achievement/scientific_achievements3"})
    public ModelAndView scientific_achievements3(){
        ModelAndView modelAndView = new ModelAndView("/home/achievement/scientific_achievements3");
        return modelAndView;
    }
    /**
     * communication
     * **/
    @RequestMapping({"/communication/communicationIndexPage.html","/communication/communicationIndexPage"})
    public ModelAndView communicationIndexPage(){
        ModelAndView modelAndView = new ModelAndView("/home/communication/communicationIndexPage");
        return modelAndView;
    }
    @RequestMapping({"/communication/conferenceIndexPage.html","/communication/conferenceIndexPage"})
    public ModelAndView conferenceIndexPage(){
        ModelAndView modelAndView = new ModelAndView("/home/communication/conferenceIndexPage");
        return modelAndView;
    }
    @RequestMapping({"/communication/cooperationIndexPage.html","/communication/cooperationIndexPage"})
    public ModelAndView cooperationIndexPage(){
        ModelAndView modelAndView = new ModelAndView("/home/communication/cooperationIndexPage");
        return modelAndView;
    }
    /**
     * cultural activity
     * **/
//    @RequestMapping({"/Untitled-1.html","/Untitled-1"})
//    public ModelAndView Untitled_1(){
//        ModelAndView modelAndView = new ModelAndView("/home/Untitled-1");
//        return modelAndView;
//    }
//    @RequestMapping({"/Untitled-1.html","/Untitled-1"})
//    public ModelAndView Untitled_1(){
//        ModelAndView modelAndView = new ModelAndView("/home/Untitled-1");
//        return modelAndView;
//    }
//    @RequestMapping({"/Untitled-1.html","/Untitled-1"})
//    public ModelAndView Untitled_1(){
//        ModelAndView modelAndView = new ModelAndView("/home/Untitled-1");
//        return modelAndView;
//    }
    /**
     * research
     * **/
    @RequestMapping({"/Research/Research_Project1.html","/Research/Research_Project1"})
    public ModelAndView Research_Project1(){
        ModelAndView modelAndView = new ModelAndView("/home/Research/Research_Project1");
        return modelAndView;
    }
    @RequestMapping({"/Research/Research_Project2.html","/Research/Research_Project2"})
    public ModelAndView Research_Project2(){
        ModelAndView modelAndView = new ModelAndView("/home/Research/Research_Project2");
        return modelAndView;
    }
    @RequestMapping({"/Research/Research_Project3.html","/Research/Research_Project3"})
    public ModelAndView Research_Project3(){
        ModelAndView modelAndView = new ModelAndView("/home/Research/Research_Project3");
        return modelAndView;
    }
    /**
     * research project
     * **/
    @RequestMapping({"/ResearchProject/associate_demo.html","/ResearchProject/associate_demo"})
    public ModelAndView associate_demo(){
        ModelAndView modelAndView = new ModelAndView("/ResearchProject/associate_demo");
        return modelAndView;
    }
    @RequestMapping({"/ResearchProject/lecturer_demo.html","/ResearchProject/lecturer_demo"})
    public ModelAndView lecturer_demo(){
        ModelAndView modelAndView = new ModelAndView("/ResearchProject/lecturer_demo");
        return modelAndView;
    }
    @RequestMapping({"/ResearchProject/professor_demo.html","/ResearchProject/professor_demo"})
    public ModelAndView professor_demo(){
        ModelAndView modelAndView = new ModelAndView("/ResearchProject/professor_demo");
        return modelAndView;
    }

    /**
     * talent
     * **/
    @RequestMapping({"/Talent/doctor_demo.html","/Talent/doctor_demo"})
    public ModelAndView doctor_demo(){
        ModelAndView modelAndView = new ModelAndView("/Talent/doctor_demo");
        return modelAndView;
    }
    @RequestMapping({"/Talent/graduate_demo.html","/Talent/graduate_demo"})
    public ModelAndView graduate_demo(){
        ModelAndView modelAndView = new ModelAndView("/Talent/graduate_demo");
        return modelAndView;
    }
    @RequestMapping({"/Talent/post_demo.html","/Talent/post_demo"})
    public ModelAndView post_demo(){
        ModelAndView modelAndView = new ModelAndView("/Talent/post_demo");
        return modelAndView;
    }
}
