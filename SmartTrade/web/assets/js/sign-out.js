async function SignOut(){
//    document.getElementById("spinner").style.display="block";
    const response = await fetch("SignOut");
//    try {
         if (response.ok) {
        const json = await response.json();
        if (json.status) {
            window.location="sign-in.html";
        } else {
            window.location.reload();
        }
    } else {
        console.log("Logout Failed");
    }
    
//    } catch (error) {
//        
//    }finally {
//    document.getElementById("spinner").style.display="none";
//    
//    }

    
}
