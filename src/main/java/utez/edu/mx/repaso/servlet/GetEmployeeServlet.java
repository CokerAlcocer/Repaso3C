package utez.edu.mx.repaso.servlet;

import com.google.gson.Gson;
import utez.edu.mx.repaso.dao.EmployeeDao;
import utez.edu.mx.repaso.entity.Employee;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "GetEmployeeServlet", value = "/GetEmployeeServlet")
public class GetEmployeeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        EmployeeDao dao = new EmployeeDao();
        long id = Long.parseLong(request.getParameter("id"));
        Employee e = dao.findById(id);
        String json = new Gson().toJson(e);
        response.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
