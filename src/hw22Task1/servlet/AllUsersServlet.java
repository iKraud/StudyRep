package hw22Task1.servlet;

import hw15Task1.DBSQL;
import hw15Task1.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Collection;

public class AllUsersServlet {
    @WebServlet(urlPatterns = "/allusers")
    public class AllMobilesServlet extends HttpServlet {
        private DBSQL dbsql;

        @Override
        public void init() throws ServletException {
//            mobileDao = (MobileDao) getServletContext().getAttribute("dao");
            super.init();
        }

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            resp.setContentType("text/html");
            PrintWriter out = resp.getWriter();
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Hello World!</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Hello World!</h1>");
            out.println("</body>");
            out.println("</html>");
            
//            Connection cn = dbsql.getConnection();
//            Collection<User> users = dbsql.getAllUsers(cn);
//            req.setAttribute("users", users);
//            req.setAttribute("PageTitle", "Users");
//            req.setAttribute("PageBody", "allusers.jsp");
//            req.getRequestDispatcher("/layout.jsp").forward(req, resp);
        }
    }
}