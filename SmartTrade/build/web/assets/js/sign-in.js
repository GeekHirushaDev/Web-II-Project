async function signIn(){
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
                method:"POST",
                body:signInJson,
                headers:{
                    "content-type":"application/json"
                }
            }
    );
    
    
    if (response.ok){ //success
        const json = await response.json();
             
        if (json.status){ //status true
            //redirect to another page
            if (json.message==="1") {
                window.location = "verify-account.html";
            } else {
                window.location = "index.html";
            }
            
        } else { //status false
            //custom message
            document.getElementById("message").innerHTML=json.message;
        }
    } else {
        document.getElementById("message").innerHTML="Sign In failed. Please try again!";
    }
}

//async function authenticateUser(){
//    const response = await fetch("SignIn");
//    
//    if (response.ok) {
//        const json = await response.json();
//        if (json.message==="1") {
//            window.location = "index.html";
//        } 
//    } else {
//        
//    }
//}

