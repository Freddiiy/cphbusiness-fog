package web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebFilter("/*")
public class RBACFilter implements Filter {

    private HashMap<String, String> restrictedPages;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        final String ADMIN = "ADMIN";
        final String CUSTOMER = "CUSTOMER";

        restrictedPages = new HashMap<>();
        restrictedPages.put("/admin/*", ADMIN);
        restrictedPages.put("/employee", ADMIN);
        restrictedPages.put("/give-money", ADMIN);

        restrictedPages.put("/cart/*", CUSTOMER);
        restrictedPages.put("/profile", CUSTOMER);
        restrictedPages.put("/confirm-order", CUSTOMER);
        restrictedPages.put("/shoppingcart", CUSTOMER);
        restrictedPages.put("/thank-you", CUSTOMER);
        restrictedPages.put("/view-orders", CUSTOMER);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        if (!roleHasAccess(req)) {
            // Create a new GET request to the home page
            res.sendRedirect(req.getContextPath());
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

    public boolean roleHasAccess(HttpServletRequest request) {
        String role = (String) request.getSession().getAttribute("role");
        String servletPath = request.getServletPath();
        return roleHasAccess(role, servletPath);
    }

    private boolean roleHasAccess(String role, String servletPath) {
        if (!pageIsRestricted(servletPath)) {
            return true;
        }
        return restrictedPages.get(servletPath).equals(role);
    }

    private boolean pageIsRestricted(String servletPath) {
        for (String path : restrictedPages.keySet()) {
            if (servletPath.equals(path)) {
                return true;
            }
        }
        return false;
    }
}