function upInfoUser(id) {

    document.getElementById("updateButton").style.display = "block";
    document.getElementById("deleteButton").style.display = "none";

    document.getElementById("exampleModalLabel").textContent = "Edit User ID: "+ id

    const url = '/admin/users/' + id

    $('#password_mod').css("display", "inline");
    $('#label_password_mod').css("display", "inline")

    $.getJSON(url, function (user) {

        let elemId = document.getElementById('userId_mod');
        elemId.value = user.id;
        elemId.classList.add("info_readonly");
        $('#userId_mod').prop('readonly', true);

        let elemFirstName = document.getElementById('first_name_mod');
        elemFirstName.value = user.firstName;
        $('#first_name_mod').prop('disabled', false);

        let elementLastName = document.getElementById('last_name_mod');
        elementLastName.value = user.lastName;
        $('#last_name_mod').prop('disabled', false);

        let elementAge = document.getElementById('age_mod');
        elementAge.value = user.age;
        $('#age_mod').prop('disabled', false);

        let elementSurname = document.getElementById('email_mod');
        elementSurname.value = user.email;
        $('#email_mod').prop('disabled', false);

        let elementActive = document.getElementById('act_mod');
        elementActive.checked = user.active;
        elementActive.value = elementActive.checked;

        $('#roles_mod').prop('disabled', false);
        $('#act_mod').prop('disabled', false);
    });
}

function saveUpUser() {

    const url = '/admin/users';
    const userId = document.getElementById('userId_mod').value;
    const firstName = document.getElementById('first_name_mod').value;
    const lastName = document.getElementById('last_name_mod').value;
    const age = document.getElementById('age_mod').value;
    const email = document.getElementById('email_mod').value;
    const password = document.getElementById('password_mod').value;
    const active = document.getElementById('act_mod').checked;
    const roles = Array.from(document.getElementById('roles_mod').selectedOptions).map(option => option.value);

    console.log(userId, firstName, lastName, age, email, password, active, roles)

    const user_data = {
        "id": userId,
        "firstName": firstName,
        "lastName": lastName,
        "age": age,
        "email": email,
        "password": password,
        "active": active,
        "roles": roles
    };

    console.log('USER -' + user_data)

    $.ajax({
        type: "PUT",
        url: url,
        contentType: "application/json",
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        data: JSON.stringify(user_data),
        success: function (response) {
            cleanTab()
            showAllUsers()
        },
        error: function (error) {
            console.log(error);
            alert("Failed to update user.");
        }
    });
}

let _user_id;

function deleteInfo(id) {
    const url = '/admin/users/' + id
    document.getElementById("deleteButton").style.display = "block";
    document.getElementById("updateButton").style.display = "none";

    document.getElementById("exampleModalLabel").textContent = "Deleted User ID: "+ id

    _user_id = id

    $.getJSON(url, function (user) {
        let elemId = document.getElementById('userId_mod');
        elemId.value = user.id;
        elemId.classList.add("info_readonly");
        $('#userId_mod').prop('readonly', true);

        let firstName = document.getElementById('first_name_mod');
        firstName.value = user.firstName;
        $('#first_name_mod').prop('disabled', true);

        let lastName = document.getElementById('last_name_mod');
        lastName.value = user.lastName;
        $('#last_name_mod').prop('disabled', true);

        let elementAge = document.getElementById('age_mod');
        elementAge.value = user.age;
        $('#age_mod').prop('disabled', true);

        let elementEmail = document.getElementById('email_mod');
        elementEmail.value = user.email;
        $('#email_mod').prop('disabled', true)

        $('#password_mod').css("display", "none");
        $('#label_password_mod').css("display", "none")

        let elementActive = document.getElementById('act_mod');
        elementActive.checked = user.active;
        elementActive.value = elementActive.checked;

        $('#roles_mod').prop('disabled', true);
        $('#act_mod').prop('disabled', true);

    });
}

function deleteUser() {

    const url = '/admin/users/' + _user_id

    $.ajax({
        url: url,
        type: 'DELETE',
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        success: function (result) {
            cleanTab()
            showAllUsers()
        }
    });
}