package com.ksimeo.nazaru.view.filters;


import com.ksimeo.nazaru.Utils.FacesUtil;
import com.ksimeo.nazaru.view.beans.LoginBean;

import javax.faces.context.FacesContext;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
* Created by @Ksimeo on 30.10.14.
*/
@WebFilter("/serventrance/secure/*")
public class LoginFilter implements Filter
{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException
    {
        HttpServletRequest req = (HttpServletRequest)request;
        FacesContext facesContext = FacesUtil.getFacesContext((HttpServletRequest)request, (HttpServletResponse)response);
        LoginBean loginBean = (LoginBean)facesContext.getApplication().evaluateExpressionGet(facesContext, "#{loginBean}", LoginBean.class);
        if (!loginBean.getLogin().isEmpty())
        {
            filterChain.doFilter(request, response);
        }
        else
        {
            loginBean.setError("You should enter your login!!");
            HttpServletResponse res = (HttpServletResponse) response;
            res.sendRedirect(req.getContextPath() + "/serventrance/login.xhtml");
        }
    }

    @Override
    public void destroy()
    {

    }
}
