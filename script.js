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
        console.log("Account created successfully:", ajax.responseText);
      } else {
        console.error("Error creating account:", ajax.status, ajax.statusText);
      }
    }
  };

  ajax.send();
}
