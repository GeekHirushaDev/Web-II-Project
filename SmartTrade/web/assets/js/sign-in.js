async function signIn(){
    
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    
    const signInObject = {
        email:email,
        password:password
    };
    const signInJSON = JSON.stringify(signInObject);
    const response = await fetch("SignIn",{
        method:"POST",
        headers:{
            "Content-Type":"application/json"
        },
        body:signInJSON
    });
    
    if(response.ok){
        const json = await response.json();
        if(json.status){
            if(json.message==="1"){// ===
                window.location="verify-account.html";
            }else{
                window.location="index.html";
            }
        }else{
            document.getElementById("message").innerHTML = json.message;
        }
    }else{
        document.getElementById("message").innerHTML = "Sign In Failed! Please try again";
    }
    
}


//async function authenticateUser(){
//    const response = await fetch("SignIn");
//    
//    if(response.ok){
//        const json = await response.json();
//        if(json.message === "1"){
//            window.location = "index.html";
//        }
//    }else{
//        
//    }
//}