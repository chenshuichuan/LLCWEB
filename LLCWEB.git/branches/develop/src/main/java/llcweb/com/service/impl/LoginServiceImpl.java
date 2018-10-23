package llcweb.com.service.impl;


import llcweb.com.dao.repository.UsersRepository;
import llcweb.com.domain.models.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by:Ricardo
 * Description:
 * Date: 2018/2/1
 * Time: 15:59
 */
@Service
public class LoginServiceImpl implements UserDetailsService {

    private  final  Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Users users = usersRepository.findByUsername(s);
        if(users == null)
        {
            throw new UsernameNotFoundException("未查询到用户："+s+"的信息！");
        }
        return users;
    }
}
