package com.example.devscheduler.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Slf4j
public class LoginFilter implements Filter {

    // 로그인 인증 할 필요 없는 URL Path 배열
    private static final String[] WHITE_LIST = {"/", "/users", "/login", "/logout"};

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain
    ) throws IOException, ServletException {

        // Filter 에서 수행할 Logic
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String uri = httpRequest.getRequestURI();

        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        log.info("로그인 필터 로직 실행: {}", uri);

        // 로그인 해야 하는 url 인 경우
        if (!isWhiteList(uri)){

            HttpSession session = httpRequest.getSession(false);

            // 로그인 하지 않은 사용자인 경우. 즉, 세션이 없는 경우
            if(session == null || session.getAttribute("sessionKey") == null){
                httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Need Login");
                return;
            }

            // 로그인 성공 로직
            log.info("로그인에 성공하였습니다.");
        }
        // 다음 필터 있으면 Filter 호출, chain 이 없으면 Servlet 바로 호출
        filterChain.doFilter(servletRequest, servletResponse);
    }

    // 로그인 여부를 확인하는 URL 인지 체크하는 메서드
    private boolean isWhiteList(String uri) {
        return PatternMatchUtils.simpleMatch(WHITE_LIST, uri);
    }
}