
// check in database if there is the same email as the client wants to register in order to avoid duplicates
function check_email() {
    var e = document.forms["myformReg"]["email"].value;
    var bt =  document.getElementById('submit');
    if (document.getElementById("email").value !== "") {
        var http = new XMLHttpRequest();
        http.open("Post", "/checkEmailRegister", true);
        http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        var paramz = "param3=" + e;
        http.send(paramz);
        http.onload = function () {
            var s = http.responseText.trim();
            if (s === '' || s === null) {
                //
                bt.disabled = false;    // Disable the button.
            } else {
                swal("The Email (" + e + ")  is Exists..!");

            }
        };
    }
}


//Register new customer
function Register_new_client() {

    var userNameReg = document.getElementById("username").value;
    var emailReg = document.getElementById("email").value;
    var passwordReg = document.getElementById("password1").value;
    var  address = document.getElementById("address").value;
    var country = document.getElementById("country").value;
    var city = document.getElementById("city").value;
    var postcode = document.getElementById("zipcode").value;

    var checkEmailReg = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;

    var http = new XMLHttpRequest();
    //
    http.onreadystatechange = function () {
        if (http.readyState === 4 || http.status === 200) {
            //
        }
    };

    // It can not be registered if one of the fields is empty
    if (userNameReg !== "" && emailReg !== ""  && passwordReg !== "" && address !== "" && country !== ""  &&  city !== ""
        && postcode !== "") {
        if (!checkEmailReg.test(emailReg)) {
            swal("please Type your email correctly..! ");
            return false;
        } else {
            http.open('POST', '/check_email_register', true);
            http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            // http.send();
            http.send("myuserReg=" + userNameReg +
                "&email=" + emailReg+
                "&password_register=" + passwordReg +
                "&address=" + address +
                "&country=" + country +
                "&city=" + city +
                "&postcode=" + postcode);

            http.onload = function () {
                var s = http.responseText.trim();
                if (s === '' || s === null) {
                    swal("Not Created");
                } else {
                    congratesRegister();

                }
            };

            return false;
        }
    } else {
        swal("Empty Fields..!");
        return false;
    }
}




// alert Register new user
function congratesRegister() {
    swal({
        title: "Good job!",
        text: "Your account is created !",
        icon: "success",
        button: true,
    }).then((result) => {
        if (result.value) {
            //

        } else {
            // window.location.reload();
            window.location.replace("http://localhost:5000/");
            return false;
        }
    });
}

//only except numbers
function isNumber(evt) {
    var iKeyCode = (evt.which) ? evt.which : evt.keyCode
    return !(iKeyCode !== 46 && iKeyCode > 31 && (iKeyCode < 48 || iKeyCode > 57));
}
