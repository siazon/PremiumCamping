/**
 * 
 */
 
		        function initBar() {
		            $("#NavBar").load("NavBar.html");
		
		        }
		        initBar();
		        
		        function init() {
		            var countTemp = localStorage.getItem('RegisterTimeOut');
		
		            if (false) {// (countTemp > 0) {
		                settime(countTemp);
		            } else {
		                $("#divSendCode").show();
		                $("#btnSendCode").hide();
		            }
		        }
		        function onblusEmail() {
		
		            FacesContext.getCurrentInstance().addMessage("signupMsg", new FacesMessage("Done"));
		            return;
		            var re = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/
		            var str = document.getElementById("rForm:txtEmail").value;
		            if (re.test(str)) {
		                $("#tEmail").text("")
		            } else {
		                $("#tEmail").text(" (Invalid Email address)")
		            }
		        }
		
		        function onblusPWD() {
		            var str = document.getElementById("rForm:txtPWD").value;
		            var str1 = document.getElementById("rForm:txtPWD1").value;
		            if (str == str1) {
		                $("#tpassword").text("")
		            } else {
		                $("#tpassword").text(" (Password not match)")
		            }
		        }

		        function RegistComplete(xhr, status, args) {
		            console.log('RegistComplete: ', args);
		            if (args.msg != undefined) {
		                if (args.msg == "OK") {
		                    window.location.href = "login.xhtml";
		                }
		            }
		        }
		
		        function handleComplete(xhr, status, args) {
		            console.log('callback: ', args);
		            settime(60)
		        }
		
		        function settime(countdown) {
		            if (countdown == 0) {
		                $("#divSendCode").show();
		                $("#btnSendCode").hide();
		                countdown = 60;
		            } else {
		                $("#divSendCode").hide();
		                $("#btnSendCode").show();
		                $("#btnSendCode").text("Resend(" + countdown + ")");
		                countdown--;
		                localStorage.setItem("RegisterTimeOut", countdown);
		                setTimeout(function () {
		                    settime(countdown)
		                }, 1000)
		            }
			
			        }
			        init();