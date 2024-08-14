package utez.edu.mx.repaso.servlet;

import utez.edu.mx.repaso.dao.EmployeeDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ChangeEmployeeStatusServlet", value = "/ChangeEmployeeStatusServlet")
public class ChangeEmployeeStatusServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        EmployeeDao dao = new EmployeeDao();
        long id = Long.parseLong(request.getParameter("ch_id"));

        request.setAttribute("error", !dao.changeStatus(id));
        doGet(request, response);
    }
}
