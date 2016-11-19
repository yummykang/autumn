package me.yummykang;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.yummykang.bean.*;
import me.yummykang.helper.BeanHelper;
import me.yummykang.helper.ConfigHelper;
import me.yummykang.helper.ControllerHelper;
import me.yummykang.utils.ReflectionUtil;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * write some dec. here.
 * Created by Demon on 2016/11/19 0019.
 */
@WebServlet(urlPatterns = "/*", loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        ContextInit.init();
        ServletContext servletContext = config.getServletContext();
        ServletRegistration registration = servletContext.getServletRegistration("jsp");
        registration.addMapping(ConfigHelper.getJspPath() + "*");
        ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
        defaultServlet.addMapping(ConfigHelper.getAssetPath() + "*");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestMethod = req.getMethod();
        String requestPath = req.getPathInfo();
        Handler handler = ControllerHelper.getHandler(new Request(requestMethod, requestPath));
        if (handler != null) {
            Class<?> controllerClass = handler.getContrllerClass();
            Object controllerBean = BeanHelper.getBean(controllerClass);
            Map<String, Object> paramMap = new HashMap<String, Object>();
            Enumeration<String> paramNames = req.getParameterNames();
            while (paramNames.hasMoreElements()) {
                String paramName = paramNames.nextElement();
                String paramValue = req.getParameter(paramName);
                paramMap.put(paramName, paramValue);
            }
            StringBuilder stringBuilder = new StringBuilder();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(req.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            String body = URLDecoder.decode(line, "UTF-8");
            if (StringUtils.isNotEmpty(body)) {
                String[] params = StringUtils.split(body, "&");
                if (params != null && params.length != 0) {
                    for (String param : params) {
                        String[] keyValue = StringUtils.split(param, "=");
                        if (keyValue != null && keyValue.length == 2) {
                            paramMap.put(keyValue[0], keyValue[1]);
                        }
                    }
                }
            }
            Param param = new Param(paramMap);
            Method method = handler.getActionMethod();
            Object result = ReflectionUtil.invokeMethod(controllerBean, method, param);
            if (result instanceof View) {
                View view = (View) result;
                String path = view.getPath();
                if (StringUtils.isNotEmpty(path)) {
                    if (path.startsWith("/")) {
                        resp.sendRedirect(req.getContextPath() + path);
                    } else {
                        Map<String, Object> model = view.getModel();
                        for (Map.Entry<String, Object> entry : model.entrySet()) {
                            req.setAttribute(entry.getKey(), entry.getValue());
                        }
                        req.getRequestDispatcher(ConfigHelper.getJspPath() + path).forward(req, resp);
                    }
                }
            } else if (result instanceof Data) {
                Data data = (Data) result;
                Object model = data.getModel();
                if (model != null) {
                    resp.setContentType("application/json");
                    resp.setCharacterEncoding("UTF-8");
                    PrintWriter writer = resp.getWriter();
                    writer.write(new ObjectMapper().writeValueAsString(model));
                    writer.flush();
                    writer.close();
                }
            }
        }
    }
}
