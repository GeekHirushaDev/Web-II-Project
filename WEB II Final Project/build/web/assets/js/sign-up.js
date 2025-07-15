function signUp(){
    
    const firstName = document.getElementById("firstName").value;
    const lastName = document.getElementById("lastName").value;
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    
    console.log(firstName+" "+lastName+"'s email is " +email+" and password is "+password);
}