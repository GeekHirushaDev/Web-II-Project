function loadData() {
    getUserData();
    getCityData();
}

async function getUserData() {
    const response = await fetch("MyAccount");
    if (response.ok) {
        const json = await response.json();
        document.getElementById("username").innerHTML = `Hello, ${json.firstName} ${json.lastName}`;
        document.getElementById("since").innerHTML = `Smart Trade Member Since ${json.since}`;
        document.getElementById("firstName").value = json.firstName;
        document.getElementById("lastName").value = json.lastName;
        document.getElementById("currentPassword").value = json.password;

        if (json.hasOwnProperty("addressList") && json.addressList !== undefined) {
            let email;
            let lineOne;
            let lineTwo;
            let city;
            let postalCode;
            let cityId;
            const addressUL = document.getElementById("addressUL");
            json.addressList.forEach(address => {
                email = address.user.email;
                lineOne = address.lineOne;
                lineTwo = address.lineTwo;
                city = address.city.name;
                postalCode = address.postalCode;
                cityId = address.city.id;
                const line = document.createElement("li");
                line.innerHTML = lineOne + ",<br/>" +
                        lineTwo + ",<br/>" +
                        city + "<br/>" +
                        postalCode;
                addressUL.appendChild(line);
            });
            document.getElementById("addName").innerHTML = `Name: ${json.firstName} ${json.lastName}`;
            document.getElementById("addEmail").innerHTML = `Email: ${email}`;
            document.getElementById("contact").innerHTML = `Phone: 011-2215453`;

            document.getElementById("lineOne").value = lineOne;
            document.getElementById("lineTwo").value = lineTwo;
            document.getElementById("postalCode").value = postalCode;
            document.getElementById("citySelect").value = parseInt(cityId);
        }
    }

}

async function getCityData() {
    const response = await fetch("CityData");
    if (response.ok) {
        const json = await response.json();
        const citySelect = document.getElementById("citySelect");
        json.forEach(city => {
            let option = document.createElement("option");
            option.innerHTML = city.name;
            option.value = city.id;
            citySelect.appendChild(option);
        });

    }
}

async function saveChanges() {


    const firstName = document.getElementById("firstName").value;
    const lastName = document.getElementById("lastName").value;
    const lineOne = document.getElementById("lineOne").value;
    const lineTwo = document.getElementById("lineTwo").value;
    const postalCode = document.getElementById("postalCode").value;
    const cityId = document.getElementById("citySelect").value;
    const currentPassword = document.getElementById("currentPassword").value;
    const newPassword = document.getElementById("newPassword").value;
    const confirmPassword = document.getElementById("confirmPassword").value;

    const userDataObject = {
        firstName: firstName,
        lastName: lastName,
        lineOne: lineOne,
        lineTwo: lineTwo,
        postalCode: postalCode,
        cityId: cityId,
        currentPassword: currentPassword,
        newPassword: newPassword,
        confirmPassword: confirmPassword
    };

    const userDataJSON = JSON.stringify(userDataObject);

    const response = await fetch("MyAccount", {
        method: "PUT",
        headers: {
            "Content-Type": "application/json"
        },
        body: userDataJSON
    });
    if (response.ok) {
        const json = await response.json();
        if (json.status) {
            getUserData();
        } else {
            document.getElementById("message").innerHTML = json.message;
        }
    } else {
        document.getElementById("message").innerHTML = "Profile details update failed!";
    }
}