let employee = {};

const getInfoEmployee = async id => {
    await fetch(`http://localhost:8080/repaso_war_exploded/GetEmployeeServlet?id=${id}`, {
        method: 'GET',
        headers: {
            Accept: 'application/json',
            'Content-Type': 'application/json'
        }
    }).then(response => response.json()).then(response => {
        console.log(response);
        employee = response;
    }).catch(console.log)
}

const setInfoEmployee = async id => {
    await getInfoEmployee(id);
    document.getElementById('u_id').value = id;
    document.getElementById('u_name').value = employee.name;
    document.getElementById('u_surname').value = employee.surname;
    document.getElementById('u_lastname').value = employee.lastname;
    document.getElementById('u_department').value = employee.department;
}

const setIdOnForm = (id, flag) => document.getElementById(flag ? 'ch_id' : 'd_id').value = id;