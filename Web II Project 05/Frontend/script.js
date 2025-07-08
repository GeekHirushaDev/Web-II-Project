function createNewAccount() {
  // Get the values from the input fields
  const mobile = document.getElementById("mobile").value;
  const firstname = document.getElementById("firstname").value;
  const lastname = document.getElementById("lastname").value;
  const password = document.getElementById("password").value;
  const country = document.getElementById("country").value;

  // Validate the inputs
  // console.log(mobile);
  // console.log(firstname);
  // console.log(lastname);
  // console.log(password);
  // console.log(country);

  // JS Object to hold the data
  const userData = {
    mobile: mobile,
    firstname: firstname,
    lastname: lastname,
    password: password,
    country: country,
  };

  // Convert the object to a JSON string
  const userDataJSON = JSON.stringify(userData);

  // Log the JSON string to the console
  console.log(userDataJSON);

  // add ajax call here to send the data to the server
  const ajax = new XMLHttpRequest();
  ajax.open("POST", "http://localhost:8080/WEB_II_PROJECT_05/CreateNewAccount", true);

  ajax.onreadystatechange = function () {
    if (ajax.readyState === 4) {
      if (ajax.status === 200) {
        alert("Account created successfully!");
      } else {
        alert("Error creating account: " + ajax.status + " " + ajax.statusText);
      }
    }
  };

  ajax.send(userDataJSON);
}

function login() {
  // Get the values from the input fields
  const mobile = document.getElementById("mobile").value;
  const password = document.getElementById("password").value;

  // Validate the inputs
  // console.log(mobile);
  // console.log(password);

  // JS Object to hold the data
  const userData = {
    mobile: mobile,
    password: password,
  };

  // Convert the object to a JSON string
  const userDataJSON = JSON.stringify(userData);

  // Log the JSON string to the console
  console.log(userDataJSON);

  // add ajax call here to send the data to the server
  const ajax = new XMLHttpRequest();
  ajax.open("POST", "http://localhost:8080/WEB_II_PROJECT_05/Login", true);

  ajax.onreadystatechange = function () {
    if (ajax.readyState === 4) {
      if (ajax.status === 200) {
        alert(ajax.responseText);
      } else {
        alert("Error logging in: " + ajax.status + " " + ajax.statusText);
      }
    }
  };

  ajax.send(userDataJSON);
}

function loadUsers() {
  const tbody = document.getElementById("userTableBody");

  let count =1;

  // add ajax call here to get the data from the server
  const ajax = new XMLHttpRequest();

  ajax.onreadystatechange = function () {
    if (ajax.readyState === 4) {
      if (ajax.status === 200) {
        const users = JSON.parse(ajax.responseText);
        users.forEach((u) => {
          tbody.innerHTML+= `<tr>
            <td class="border px-4 py-2">${count++}</td>
            <td class="border px-4 py-2">${u.mobile}</td>
            <td class="border px-4 py-2">${u.firstname}</td>
            <td class="border px-4 py-2">${u.lastname}</td>
            <td class="border px-4 py-2">${u.country}</td>
          </tr>`;
          count++;
        });
      } else {
        alert("Error loading users: " + ajax.status + " " + ajax.statusText);
      }
    }
  };
  
  ajax.open("GET", "http://localhost:8080/WEB_II_PROJECT_05/GetAllUsers", true);
  ajax.send();
}