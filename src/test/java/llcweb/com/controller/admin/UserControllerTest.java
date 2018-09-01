//package llcweb.com.controller.admin;
//
//import llcweb.com.dao.repository.ConferenceRepository;
//import llcweb.com.domain.User;
//import llcweb.com.domain.models.Users;
//import llcweb.com.service.UsersService;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockHttpSession;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
////import org.springframework.security.test.context.support.WithMockUser;
//import javax.servlet.Filter;
//
//import static org.hamcrest.Matchers.greaterThan;
//import static org.hamcrest.Matchers.is;
//import static org.junit.Assert.*;
//import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
///**
// * Created by:Ricardo
// * Description:
// * Date: 2018/8/29
// * Time: 22:25
// */
////@RunWith(SpringRunner.class)
////@SpringBootTest
////@RunWith(SpringJUnit4ClassRunner.class)
////@ContextConfiguration
////@WebAppConfiguration
//public class UserControllerTest {
//    private Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    @Autowired
//    private WebApplicationContext context;
//
//    @Autowired
//    private Filter springSecurityFilterChain;
//
//    private MockMvc mvc;
//
//    private MockHttpSession session;
//
//    @Autowired
//    private UsersService usersService;
//
//    @Before
//    public void setupMockMvc(){
//        mvc = MockMvcBuilders
//                .webAppContextSetup(context)
//                .addFilters(springSecurityFilterChain)
//                .build();
//
////        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
////                .defaultRequest(MockMvcRequestBuilders.get("/")
////                        .with(user("admin").password("admin")
////                        .authorities(new SimpleGrantedAuthority("USER_QUERY"),
////                                new SimpleGrantedAuthority("USER_SAVE"),
////                                new SimpleGrantedAuthority("USER_DELETE"))))
////                .addFilters(springSecurityFilterChain)
////                .build();
//
//    }
//   @Test
//   public void tsetLogin(){
//
//   }
//    @Test
//    public void page() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.get("/user/page")
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .accept(MediaType.APPLICATION_JSON_UTF8)
//                .session(session)
//        )
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.total").value(greaterThan(1)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(1))
//                .andDo(MockMvcResultHandlers.print());
//        //Assert.assertThat(conferenceRepository.save(conference).getId(),is(0));
//    }
//
//
////    @Test
////    public void authenticationFailed() throws Exception {
////        mvc
////                .perform(formLogin().user("user").password("invalid"))
////                .andExpect(status().isMovedTemporarily())
////                .andExpect(redirectedUrl("/login?error"))
////                .andExpect(unauthenticated());
////    }
//
//
//    @Configuration
//    @EnableWebMvcSecurity
//    @EnableWebMvc
//    static class Config extends WebSecurityConfigurerAdapter {
//        @Autowired
//        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//            auth
//                    .inMemoryAuthentication()
//                    .withUser("admin").password("admin").roles("ADMIN");
//        }
//    }
//}