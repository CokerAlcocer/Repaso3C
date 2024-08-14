package utez.edu.mx.repaso.servlet;

import utez.edu.mx.repaso.dao.EmployeeDao;
import utez.edu.mx.repaso.entity.Employee;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CreateEmployeeServlet", value = "/CreateEmployeeServlet")
public class CreateEmployeeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        EmployeeDao dao = new EmployeeDao();
        Employee e = new Employee(
                request.getParameter("name"),
                request.getParameter("surname"),
                request.getParameter("lastname"),
                request.getParameter("department")
        );

        request.setAttribute("error", !dao.create(e));
        doGet(request, response);
    }
}
