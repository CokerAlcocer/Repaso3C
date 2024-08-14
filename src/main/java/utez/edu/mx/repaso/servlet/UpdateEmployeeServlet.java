package utez.edu.mx.repaso.servlet;

import utez.edu.mx.repaso.dao.EmployeeDao;
import utez.edu.mx.repaso.entity.Employee;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UpdateEmployeeServlet", value = "/UpdateEmployeeServlet")
public class UpdateEmployeeServlet extends HttpServlet {
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
                Long.parseLong(request.getParameter("u_id")),
                request.getParameter("u_name"),
                request.getParameter("u_surname"),
                request.getParameter("u_lastname"),
                request.getParameter("u_department")
        );

        request.setAttribute("error", !dao.update(e));
        doGet(request, response);
    }
}
