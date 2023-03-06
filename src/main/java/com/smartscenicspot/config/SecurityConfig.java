package com.smartscenicspot.config;

import com.smartscenicspot.auth.WeChatAuthenticationFilter;
import com.smartscenicspot.auth.WeChatAuthenticationProvider;
import com.smartscenicspot.service.Impl.AdminDetailServiceImpl;
import com.smartscenicspot.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * SpringSecurity 配置
 *
 * @author <a href="mailto: sjiahui@gmail.com">songjiahui</a>
 * @since 2023/3/6 10:53
 **/
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private AdminDetailServiceImpl adminDetailService;

    @Resource
    private UserService userService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(adminDetailService).passwordEncoder(passwordEncoder());
        auth.authenticationProvider(weChatAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers("admin/auth/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
//                FIXME 暂时关闭 cors
                .cors().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(weChatAuthenticationProvider())
                .addFilterBefore(weChatAuthenticationFilter(),
                        UsernamePasswordAuthenticationFilter.class)
        ;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public WeChatAuthenticationFilter weChatAuthenticationFilter() {
        return new WeChatAuthenticationFilter();
    }
    @Bean
    public WeChatAuthenticationProvider weChatAuthenticationProvider() {
        return new WeChatAuthenticationProvider(userService);
    }
}
