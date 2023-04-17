function cleanTab() {
    $("tr:has(td)").remove()
}

function showAllUsers() {
    cleanTab();

    const url = 'admin/users'

    $.get(url, function (response) {
        $.each(response, function (index, item) {
            const roleNames = item.roles.map(function (role) {
                return role.roleName.replace('ROLE_', '')
            }).join(', ')

            $("#user-table-id").find('tbody')
                .append($('<tr>')
                    .append($('<td class="center">').text(index + 1))
                    .append($('<td class="center">').text(item.email))
                    .append($('<td class="center">').text(item.firstName))
                    .append($('<td class="center">').text(item.lastName))
                    .append($('<td class="center">').text(item.age))
                    .append($('<td class="center">').text(roleNames))
                    .append($('<td class="center">').text(item.active))
                    .append($('<td class="center">').append(`
                                                            <button 
                                                                    type="button" 
                                                                    class="btn btn-info" 
                                                                    data-bs-toggle="modal"
                                                                    data-bs-target="#exampleModal"
                                                                    id=button_edit_${item.id}
                                                                    onclick="upInfoUser(${item.id})">EDIT
                                                            </button>
                                                            `))
                    .append($('<td class="center">').append(`
                                                            <button
                                                                    type="button"
                                                                    class="btn btn-danger"
                                                                    data-bs-toggle="modal"
                                                                    data-bs-target="#exampleModal"
                                                                    id=button_delet_${item.id}
                                                                    onclick="deleteInfo(${item.id})">DELETE
                                                            </button>
                                                            `))
                )
        })
    })
}

function showUser() {
    cleanTab()

    const user_auth_id = document
        .getElementById("user_auth_html")
        .getAttribute("data-user");

    fetch('user/' + user_auth_id).then(res => res.json())
        .then(user_auth => {
            const roles = user_auth.roles.map(role => role.roleName.replace('ROLE_', ''));
            console.log(user_auth)
            const tableRow = `
                    <tr>
                        <td class="center">${user_auth.id}</td>
                        <td class="center">${user_auth.email}</td>
                        <td class="center">${user_auth.firstName}</td>
                        <td class="center">${user_auth.lastName}</td>
                        <td class="center">${user_auth.age}</td>
                        <td class="center">${roles.join(', ')}</td>
                        <td class="center">${user_auth.active}</td>
                    </tr>
                `;

            $("#about-user-table-id").find('tbody').append(tableRow)
        })
}






