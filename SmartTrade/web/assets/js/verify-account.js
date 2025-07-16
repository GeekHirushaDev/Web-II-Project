async function verifyAccount() {
    const verificationCode = document.getElementById("verificationCode").value;

    const verificationObject = {
        verificationCode: verificationCode
    };

    const verificationJson = JSON.stringify(verificationObject);

    const response = await fetch(
            "VerifyAccount",
            {
                method: "POST",
                body: verificationJson,
                headers: {
                    "content-type": "application/json"
                }
            }
    );
    
    if (response.ok){
        const json = await response.json();
        
        if (json.status){
            window.location = "index.html";
        }else{
            
            if (json.message==="1") { //Email not found!
                window.location = "sign-in.html";
            } else {
                document.getElementById("message").innerHTML = json.message;
            }
            
            
        }
    }else{
        document.getElementById("message").innerHTML = "Verification failed. Please try again!";
    }
}


