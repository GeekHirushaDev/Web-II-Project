//window.addEventListener("load",async function(){
//    const response = await fetch("MyAccount");
//});

async function getUserData() {
    const response = await fetch("MyAccount");

    if (response.ok) {
        const json = await response.json();
        document.getElementById("username").innerHTML = `Hello ${json.firstName},`;
        document.getElementById("since").innerHTML = `Smart Trade Member Since  ${json.since}`;
        document.getElementById("firstName").value = json.firstName;
        document.getElementById("lastName").value = json.lastName;
        document.getElementById("currentPassword").value = json.password;
        
    } else {
        alert("Error");
    }
}