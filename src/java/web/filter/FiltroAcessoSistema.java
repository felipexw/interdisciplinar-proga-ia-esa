package web.filter;

import java.io.IOException;
import javax.faces.context.FacesContext;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import web.bean.LoginBean;

/**
 *
 * @author Felipe
 */
//public class FiltroAcessoSistema implements javax.servlet.Filter {
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpSession sessao = ((HttpServletRequest) request).getSession();
//        LoginBean loginBean = (LoginBean) sessao.getAttribute("loginBean");
//        chain.doFilter(request, response);
////        if (loginBean.getUserSession() == null) {
////            FacesContext.getCurrentInstance().getExternalContext().redirect("index.jsf");
////        } else {
////            chain.doFilter(request, response);
////        }
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//
//}
