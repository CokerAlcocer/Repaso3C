<%@ page import="java.util.List" %>
<%@ page import="utez.edu.mx.repaso.entity.Employee" %>
<%@ page import="utez.edu.mx.repaso.dao.EmployeeDao" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String context = request.getContextPath();
    List<Employee> list = new EmployeeDao().findAll();
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Repaso de examen</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<!-- As a heading -->
<nav class="navbar bg-body-tertiary">
    <div class="container-fluid">
        <span class="navbar-brand mb-0 h1">Repaso de examen</span>
    </div>
</nav>
<main class="mx-5 mt-4">
    <div class="d-flex align-items-center">
        <h3>Gestión de empleados</h3>
        <button type="button" class="ms-auto btn btn-outline-success" data-bs-toggle="modal" data-bs-target="#createModal">Registrar</button>
    </div>
    <hr>
    <section>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Nombre completo</th>
                <th scope="col">Departamento</th>
                <th scope="col">Fecha de registro</th>
                <th scope="col">Estado</th>
                <th scope="col" class="text-center">Acciones</th>
            </tr>
            </thead>
            <tbody>
            <%
                for(int i = 0; i < list.size(); i++) {
                Employee e = list.get(i);
            %>
            <tr>
                <th scope="row"><%=i + 1%></th>
                <td><%=e.getName()%> <%=e.getSurname()%><%=e.getLastname() != null ? " "+e.getLastname() : ""%></td>
                <td><%=e.getDepartment()%></td>
                <td><%=e.getCreatedAt()%></td>
                <td>
                    <%if(e.isStatus()) {%>
                    <span class="badge bg-success">Activo</span>
                    <%} else {%>
                    <span class="badge bg-danger">Inactivo</span>
                    <%}%>
                </td>
                <td class="text-center">
                    <button type="button" onclick="setIdOnForm(<%=e.getId()%>, false)" class="btn btn-outline-danger" data-bs-toggle="modal" data-bs-target="#deleteModal">Eliminar</button>
                    <button type="button" onclick="setIdOnForm(<%=e.getId()%>, true)" class="btn btn-outline-primary" data-bs-toggle="modal" data-bs-target="#changeModal">C. Estado</button>
                    <button type="button" onclick="setInfoEmployee(<%=e.getId()%>)" class="btn btn-outline-primary" data-bs-toggle="modal" data-bs-target="#updateModal">Editar</button>
                </td>
            </tr>
            <%}%>
            </tbody>
        </table>
    </section>
</main>

<!-- Modal -->
<div class="modal fade" id="createModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-body">
                <form action="<%=context%>/CreateEmployeeServlet" method="post">
                    <h4>Registrar empleado</h4>
                    <hr>
                    <div class="row g-3">
                        <div class="col-12">
                            <label for="name"><small>Nombre</small></label>
                            <input type="text" id="name" name="name" autocomplete="off" class="form-control">
                        </div>
                        <div class="col-6">
                            <label for="surname"><small>Apellido paterno</small></label>
                            <input type="text" id="surname" name="surname" autocomplete="off" class="form-control">
                        </div>
                        <div class="col-6">
                            <label for="lastname"><small>Apellido materno</small></label>
                            <input type="text" id="lastname" name="lastname" autocomplete="off" class="form-control">
                        </div>
                        <div class="col-12">
                            <label for="department"><small>Departamento</small></label>
                            <select name="department" id="department" class="form-select">
                                <option value="Administración">Administración</option>
                                <option value="Recursos Humanos">Recursos Humanos</option>
                                <option value="Finanzas">Finanzas</option>
                                <option value="Dirección de proyectos">Dirección de proyectos</option>
                            </select>
                        </div>
                        <div class="col-12 text-end">
                            <button type="reset" class="btn btn-outline-secondary me-1" data-bs-dismiss="modal">Cerrar</button>
                            <button type="submit" class="btn btn-outline-success">Registrar</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="updateModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-body">
                <form action="<%=context%>/UpdateEmployeeServlet" method="post">
                    <h4>Actualizar empleado</h4>
                    <input type="hidden" id="u_id" name="u_id">
                    <hr>
                    <div class="row g-3">
                        <div class="col-12">
                            <label for="u_name"><small>Nombre</small></label>
                            <input type="text" id="u_name" name="u_name" autocomplete="off" class="form-control">
                        </div>
                        <div class="col-6">
                            <label for="u_surname"><small>Apellido paterno</small></label>
                            <input type="text" id="u_surname" name="u_surname" autocomplete="off" class="form-control">
                        </div>
                        <div class="col-6">
                            <label for="u_lastname"><small>Apellido materno</small></label>
                            <input type="text" id="u_lastname" name="u_lastname" autocomplete="off" class="form-control">
                        </div>
                        <div class="col-12">
                            <label for="u_department"><small>Departamento</small></label>
                            <select name="u_department" id="u_department" class="form-select">
                                <option value="Administración">Administración</option>
                                <option value="Recursos Humanos">Recursos Humanos</option>
                                <option value="Finanzas">Finanzas</option>
                                <option value="Dirección de proyectos">Dirección de proyectos</option>
                            </select>
                        </div>
                        <div class="col-12 text-end">
                            <button type="reset" class="btn btn-outline-secondary me-1" data-bs-dismiss="modal">Cerrar</button>
                            <button type="submit" class="btn btn-outline-primary">Actualizar</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="changeModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-body">
                <form action="<%=context%>/ChangeEmployeeStatusServlet" method="post">
                    <h4>Actualizar estado del empleado</h4>
                    <input type="hidden" id="ch_id" name="ch_id">
                    <hr>
                    <div class="row g-3">
                        <div class="col-12">
                            ¿Seguro que desea actualizar el estado del empleado?
                        </div>
                        <div class="col-12 text-end">
                            <button type="reset" class="btn btn-outline-secondary me-1" data-bs-dismiss="modal">Cerrar</button>
                            <button type="submit" class="btn btn-outline-primary">Actualizar estado</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-body">
                <form action="<%=context%>/DeleteEmployeeServlet" method="post">
                    <h4>Eliminar empleado</h4>
                    <input type="hidden" id="d_id" name="d_id">
                    <hr>
                    <div class="row g-3">
                        <div class="col-12">
                            ¿Seguro que desea eliminar al empleado?
                        </div>
                        <div class="col-12 text-end">
                            <button type="submit" class="btn btn-outline-danger">Eliminar</button>
                            <button type="reset" class="btn btn-outline-secondary me-1" data-bs-dismiss="modal">Cerrar</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="<%=context%>/main.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>