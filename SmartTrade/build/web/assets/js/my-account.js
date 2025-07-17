//window.addEventListener("load",async function(){
//    const response = await fetch("MyAccount");
//});
function loadData(){
    getUserData();
    getCityData();
}

async function getUserData(){
    const response = await fetch("MyAccount");
    
    if (response.ok) {
        const json = await response.json();
        document.getElementById("username").innerHTML = `Hello ${json.firstName} ${json.lastName}`;
        document.getElementById("since").innerHTML = `Smart Trade Member Since ${json.since}`;
        document.getElementById("firstName").value = `${json.firstName}`;
        document.getElementById("lastName").value = `${json.lastName}`;
        document.getElementById("currentPassword").value = `${json.password}`;
    } 
}

async function getCityData(){
    const response = await fetch("CityData");
    
    if (response.ok) {
        const json = await response.json();
        const citySelect = document.getElementById("citySelect");
        json.forEach(city =>{
            let option = document.createElement("option");
            option.innerHTML = city.name;
            option.value = city.id;
            citySelect.appendChild(option);
        });
        
        
    }
}

async function saveChanges(){
    
    const firstName = document.getElementById("firstName").value;
    const lastName = document.getElementById("lastName").value;
    const lineOne = document.getElementById("lineOne").value;
    const lineTwo = document.getElementById("lineTwo").value;
    const postalCode = document.getElementById("postalCode").value;
    const citySelect = document.getElementById("citySelect").value;
    const currentPassword = document.getElementById("currentPassword").value;
    const newPassword = document.getElementById("newPassword").value;
    const confirmPassword = document.getElementById("confirmPassword").value;
    
    const userDataObject = {
        firstName:firstName,
        lastName:lastName,
        lineOne:lineOne,
        lineTwo:lineTwo,
        postalCode:postalCode,
        citySelect:citySelect,
        currentPassword:currentPassword,
        newPassword:newPassword,
        confirmPassword:confirmPassword
    };
    
    const userDataJson = JSON.stringify(userDataObject);
    
    const response = await fetch("MyAccount",{
        method:"PUT",
        body:userDataJson,
        headers:{
            "content-type":"application/json"
        }
    });
    
    if (response.ok) {
        
    } else {
    }
}
