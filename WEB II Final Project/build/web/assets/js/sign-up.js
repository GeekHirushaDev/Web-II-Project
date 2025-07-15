async function signUp() {

    const firstName = $("#firstName").val(); // using jQuery
    const lastName = document.getElementById("lastName").value;
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    // console.log(firstName+" "+lastName+"'s email is " +email+" and password is "+password);

    const user = {
        firstName: firstName,
        lastName: lastName,
        email: email,
        password: password
    };

    const userJson = JSON.stringify(user);

    const response = await fetch(
            "SignUp",
            {
                method: "POST",
                body: userJson,
                header: {
                    "content-type": "application/json"
                }
            }
    );

    if (response.ok) {
        const responseJSON = await response.json();

        if (responseJSON.status) {
            window.location = "verify-account.html";
        } else {
            document.getElementById("message").innerHTML = responseJSON.message;
        }

    } else {
        document.getElementById("message").innerHTML = "Registration failed. Please try again.";
    }

}