const csrfToken = $("meta[name='_csrf']").attr("content");
const csrfHeader = $("meta[name='_csrf_header']").attr("content");

function addNewUser() {

    const url = '/admin/users';
    const firstName = document.getElementById('first_name').value;
    const lastName = document.getElementById('last_name').value;
    const age = document.getElementById('age').value;
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    const roles = Array.from(document.getElementById('roles_').selectedOptions).map(option => option.value);

    const user = {
        "firstName": firstName,
        "lastName": lastName,
        "age": age,
        "email": email,
        "password": password,
        "active": true,
        "roles": roles
    };


    fetch(url, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            [csrfHeader]: csrfToken,
        },
        body: JSON.stringify(user),
    })
        .then((response) => {
            if (response.ok) {
                location.reload();
            } else {
                throw new Error("Failed to update user.");
            }
        })
        .catch((error) => {
            alert(error.message);
        });
}


function validation_email(inputId) {
    const url = '/admin/user/'

        $(inputId).blur(function () {
            const data = $.trim($(inputId).val());
            if (data !== '') {
                fetch(url + data, {
                    method: 'GET',
                })
                    .then((res) => res.text())
                    .then((res) => {
                        console.log(res);
                        if (res === 'NO') {
                            $(inputId).prev().prev().removeClass().text(data + ' - ok')
                                .addClass('ok');
                        } else if (res === 'YES') {
                            $(inputId).prev().prev().removeClass().text(data + ' - email exists!')
                                .addClass('error');
                        }
                    })
                    .catch(() => {
                        $(inputId).prev().prev().text('ERROR!');
                    });
            } else {
                $(inputId).prev().prev().removeClass().text('required field')
                    .addClass('error');
            }
        });
}

