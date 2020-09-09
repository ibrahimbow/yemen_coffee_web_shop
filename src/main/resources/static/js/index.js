// Select all links with hashes
$('a[href*="#"]')
	// Remove links that don't actually link to anything
	.not('[href="#"]')
	.not('[href="#0"]')
	.click(function(event) {
		// On-page links
		if (
			location.pathname.replace(/^\//, '') === this.pathname.replace(/^\//, '')
			&&
			location.hostname === this.hostname
		) {
			// Figure out element to scroll to
			var target = $(this.hash);
			target = target.length ? target : $('[name=' + this.hash.slice(1) + ']');
			// Does a scroll target exist?
			if (target.length) {
				// Only prevent default if animation is actually gonna happen
				event.preventDefault();
				$('html, body').animate({
					scrollTop: target.offset().top
				}, 1000, function() {
					// Callback after animation
					// Must change focus!
					var $target = $(target);
					$target.focus();
					if ($target.is(":focus")) { // Checking if the target was focused
						return false;
					} else {
						$target.attr('tabindex','-1'); // Adding tabindex for elements not focusable
						$target.focus(); // Set focus again
					};
				});
			}
		}
	});



// this function is to hid the list of account if its not logged in yet
function show_logout(){
	if(document.getElementById("user_id").value !== "") {
		// document.getElementById("ok").innerText="show";
		document.getElementById("logout").style.display='block';
	}else {
		// document.getElementById("ok").innerText = "hid";
		document.getElementById("logout").style.display='none';
	}
}


// this function to hid the point above the cart bag
function show_cart_items(){
	if(document.getElementById("quantity_items").value !== "") {
		document.getElementById("quantity_notification").style.display='block';
		document.getElementById("cart_items_list").style.display='block';
	}else {
		document.getElementById("quantity_notification").style.display='none';
		document.getElementById("cart_items_list").style.display='none';
	}
}








// check the input of Contact From

function checkSendingForm() {
	var email_user = document.forms["contact-form"]["email"].value;

	var user_name = document.getElementById("name").value;
	// var email_user = document.getElementById("email").value;
	var subj = document.getElementById("subject").value;
	var msg = document.getElementById("message").value;


	var checkEmailReg = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;

	// It can not be registered if one of the fields is empty
	if (user_name !== "" && email_user !== "" && subj !== ""  && msg !== "") {
		if (!checkEmailReg.test(email_user)) {
			swal("please Type your email correctly..! ");
			return false;
		} else {
			congratsSendEmail();
			return false;
		}
	} else {
		swal("Empty Fields..!");
		return false;
	}
}


//Check email of subscribers if it already existed
function show_exist_email() {
	var email = document.forms["subscr"]["emails"].value;
	var bt =  document.getElementById('button1');
	// It can not be registered if one of the fields is empty
	if (document.getElementById("emails").value !== "") {
		var http = new XMLHttpRequest();
		http.open("Post", "/checkEmailSubs", true);
		http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		var param = "param2=" + email;
		http.send(param);
		http.onload = function () {
			var s = http.responseText.trim();
			if (s === '' || s === null) {
				//
				bt.disabled = false;    // Enable the button.
				return true;
			} else {
				swal("The Email (" + email + ")  is Exists..!");
				bt.disabled = true; // Disable the button.
				return false;
			}
		};
	}
}


// add email to the subscribe table in database
function add_email_subscribe() {

		var email_sub = document.forms["subscr"]["emails"].value;
		var checkEmailSub = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;

		// It can not be registered if one of the fields is empty
		if (email_sub !== "") {

			if (!checkEmailSub.test(email_sub)) {
				swal("please Type your email correctly..! ");
				return false;
			} else {
				congratsSubscribe();
				return false;
			}
		} else {
			swal("Empty Fields..!");
			return false;
		}

}

//subscribe congrats
function congratsSubscribe() {
	swal({
		title: "Good job!",
		text: "You have subscribed !",
		icon: "success",
		button: true,
	}).then((result) => {
		if (result.value) {
			//
		} else {
			document.forms['subscr'].submit();
			return false;
		}
	});
}



function congratsSendEmail() {
	swal({
		title: "Good job!",
		text: "you already add new product ! ",
		icon: "success",
		button: true,
	}).then((result) => {
		if (result.value) {
			//
		} else {
			document.forms['contact-form'].submit();
			return false;
		}
	});
}


//add new product check it out the empty fields
function addNewProductAdmin() {

	var imageId = document.getElementById("imageId").value;
	var productName = document.getElementById("productName").value;
	var  description = document.getElementById("description").value;
	var amount = document.getElementById("amount").value;
	var price = document.getElementById("price").value;

	// It can not be added new product if one of the fields is empty
	if ( productName !== ""  && description !== "" && amount !== "" && price !== "") {

		if (imageId === "") {
			swal("please add photo of the product..! ");
			return false;
		} else {
			congratsAddNewProduct();
			return true;
		}

	} else {
		swal("Empty Fields..!");
		return false;
	}
}

function congratsAddNewProduct() {
	swal({
		title: "Good job!",
		text: "You have added New Product !",
		icon: "success",
		button: true,
	}).then((result) => {
		if (result.value) {
			//

		} else {
			document.forms['addNewProduct'].submit();
			return false;
		}
	});
}
