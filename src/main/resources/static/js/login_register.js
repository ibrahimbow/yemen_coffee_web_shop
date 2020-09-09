
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
                return true;
            } else {
                swal("The Email (" + e + ")  is Exists..!");
                bt.disabled = true;    // Disable the button.
                return false;
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
        } else{

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
                    congratsRegister();
                    return false;
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
function congratsRegister() {
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



// check the input of shipping address - checkout form
// to check if the user already exist but he didn't login and still use existed email
function checkExistUser() {
    var user = document.forms["checkout_form"]["check_exist_user"].value;
    var e = document.forms["checkout_form"]["email"].value;

    if(user==="") {
        if (document.getElementById("email").value !== "") {
            var http = new XMLHttpRequest();
            http.open("Post", "/checkEmailRegister", true);
            http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            var paramz = "param3=" + e;
            http.send(paramz);
            http.onload = function () {
                var s = http.responseText.trim();
                if (s === '' || s === null ) {
                    checkInputShippingAddress();
                    return true;
                } else {
                    swal("The Email (" + e + ")  is Exists..! please Login");
                    return false;
                }

            };

        }
    }else{
        checkInputShippingAddress();
    }
}

// checkout form after checking the user exist
function checkInputShippingAddress() {

    var userNameReg = document.getElementById("fname").value;
    var emailReg = document.getElementById("email").value;
    var  address = document.getElementById("adr").value;
    var country = document.getElementById("country").value;
    var city = document.getElementById("city").value;
    var postcode = document.getElementById("zip").value;
    var cname = document.getElementById("cname").value;
    var ccnum = document.getElementById("ccnum").value;
    var cvv = document.getElementById("cvv").value;

    var checkEmailReg = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;

    // It can not be registered if one of the fields is empty
    if (userNameReg !== "" && emailReg !== ""  && ccnum !== "" && address !== "" && country !== ""  &&  city !== ""
        && postcode !== ""  && cname !== ""  && cvv !== "") {
        if (!checkEmailReg.test(emailReg)) {
            swal("please Type your email correctly..! ");
            return false;
        } else {
            document.forms['checkout_form'].submit();
            return true;
        }
    } else {
        swal("Empty Fields..!");
        return false;
    }
}

