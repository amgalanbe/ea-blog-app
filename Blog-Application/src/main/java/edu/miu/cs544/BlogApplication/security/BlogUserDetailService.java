package edu.miu.cs544.BlogApplication.security;

import edu.miu.cs544.BlogApplication.dao.IUserDAO;
import edu.miu.cs544.BlogApplication.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("userDetailsService")
@Transactional
public class BlogUserDetailService implements UserDetailsService {

    private final IUserDAO userDAO;

    public BlogUserDetailService(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.findByUsername(username).orElse(null);
        BlogUserDetails userDetails = new BlogUserDetails(user);
        return userDetails;
    }
}
