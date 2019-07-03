package com.sclnetworks.backend.config;

import com.sclnetworks.backend.service.UserService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    UserService userService;


    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(Arrays.asList("http://localhost:8081"));
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Override
    public void configure(WebSecurity web) throws Exception
    {
        //web.ignoring().antMatchers("/**");
        // 예를들어 이런식으로 인증할것들을 풀어주는겁니다. (주로 리소스)
        web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**" , "/images/**" ,"/script/**", "/");

    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //  X-Frame-Options이 http header에 추가되어 iframe 외부프레임 접근이 가능하도록 외부프레임 거부를 해제 한다.
        http.headers().frameOptions().disable();

        http.csrf().disable();

        http
                .authorizeRequests()
                // 어드민 권한으로만 접근할 수 있는 경로.
                .antMatchers("/#/admin/**").authenticated()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/login")
                .successHandler(successHandler())
                .failureHandler(failureHandler())
                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler())
                .authenticationEntryPoint(authenticationEntryPoint())
                .and()
//                .csrf().csrfTokenRepository(csrfTokenRepository()).and().addFilterAfter(csrfHeaderFilter(), CsrfFilter.class)
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                // 로그아웃이 성공했을 경우 이동할 페이지
                .logoutSuccessUrl("/login") // 로그아웃 성공 URL
                .permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    @Override public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    private AuthenticationSuccessHandler successHandler() {
        return (httpServletRequest, httpServletResponse, authentication) -> {

            httpServletResponse.setContentType( "application/json" );
            httpServletResponse.setCharacterEncoding("euc-kr");

            String loginId = authentication.getName();
//            UsersEntity user = userService.findByLoginId(loginId);

//            ObjectMapper mapper = new ObjectMapper();
//            String jsonInString = mapper.writeValueAsString(user);

            Map<String, Object> result = new HashMap<String,Object>();
            result.put("result", 0);
            result.put("dest","로그인 되었습니다.");
//            result.put("userToken",user.getUserToken());
            httpServletResponse.getWriter().append(new JSONObject(result).toJSONString());
            httpServletResponse.setStatus(200);
        };
    }

    private AuthenticationFailureHandler failureHandler() {
        return (httpServletRequest, httpServletResponse, e) -> {
            httpServletResponse.setContentType( "application/json" );
            httpServletResponse.setCharacterEncoding("euc-kr");

            Map<String, Object> result = new HashMap<String,Object>();
            result.put("result", -1);
            result.put("dest","존재하지 않는 계정입니다.");
            httpServletResponse.getWriter().append(new JSONObject(result).toJSONString());
            httpServletResponse.setStatus(401);
        };
    }

    private AccessDeniedHandler accessDeniedHandler() {
        return (httpServletRequest, httpServletResponse, e) -> {
            httpServletResponse.setContentType( "application/json" );
            httpServletResponse.setCharacterEncoding("euc-kr");

            Map<String, Object> result = new HashMap<String,Object>();
            result.put("result", -1);
            result.put("dest","접근이 거부되었습니다.");
            httpServletResponse.getWriter().append(new JSONObject(result).toJSONString());
            httpServletResponse.setStatus(403);
        };
    }

    private AuthenticationEntryPoint authenticationEntryPoint() {
        return (httpServletRequest, httpServletResponse, e) -> {
            httpServletResponse.setContentType( "application/json" );
            httpServletResponse.setCharacterEncoding("euc-kr");

            Map<String, Object> result = new HashMap<String,Object>();
            result.put("result", -1);
            result.put("dest","접근 권한이 없습니다.");
            httpServletResponse.getWriter().append(new JSONObject(result).toJSONString());
            httpServletResponse.setStatus(401);
        };
    }

    private Filter csrfHeaderFilter() {
        return new OncePerRequestFilter() {
            @Override
            protected void doFilterInternal(HttpServletRequest request,
                                            HttpServletResponse response, FilterChain filterChain)
                    throws ServletException, IOException {
                CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class
                        .getName());
                if (csrf != null) {
                    Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");
                    String token = csrf.getToken();
                    if (cookie == null || token != null
                            && !token.equals(cookie.getValue())) {
                        cookie = new Cookie("XSRF-TOKEN", token);
                        cookie.setPath("/");
                        response.addCookie(cookie);
                    }
                }
                filterChain.doFilter(request, response);
            }
        };
    }

    private CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName("X-XSRF-TOKEN");
        return repository;
    }
}