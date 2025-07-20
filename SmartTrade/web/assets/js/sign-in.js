async function SignIn() {


    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    const signIn = {
        email: email,
        password: password
    };
    const signInJson = JSON.stringify(signIn);

    const response = await fetch(
            "SignIn",
            {
                method: "POST",
                body: signInJson,

                header: {
                    "Content-Type": "application/json"
                }
            });
        

    if (response.ok) {// success
        const  json = await  response.json();
//        console.log(json);
        if (json.status) {  //if true

            if (json.message === "1") {
                window.location = "verify-account.html";

            } else {
                window.location = "index.html";

            }
        } else {//when status false
            // custome message
//            console.log(json.message);
            document.getElementById("message").innerHTML = json.message;

        }
    } else {
        document.getElementById("message").innerHTML = "Sign in failed. Please try again";

    }
}

//
//async function authenticateUser(){
//    const  response = await fetch("SignIn");
//    if (response.ok) {
//        const  json = await response.json();
//        if (json.message === "1") {
//            window.location="index.html";
//        } else {
//        }
//        
//    } else {
// }
//}