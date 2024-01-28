package demo.spring.aspect.web;

import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@WebServlet("/servlet")
public class DemoServlet extends HttpServlet {

    private WebApplicationContext context;

    public void init(ServletConfig config) throws ServletException {
        super.init();
        ServletContext servletContext = config.getServletContext();

        // 确保Spring的WebApplicationContext已初始化
        this.context = WebApplicationContextUtils.getWebApplicationContext(servletContext);

        if (context == null) {
            throw new ServletException("WebApplicationContext not found. Ensure Spring has been properly configured.");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 设置响应内容类型为文本/Plain
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        // 写入响应体
        response.getWriter().println("Hello, this is a simple Demo Servlet!");
    }
}
