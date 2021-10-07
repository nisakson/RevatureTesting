/*
    We will be using AJAX to make asynchronous HTTP requests for resources that we have exposed. For instance, one resource that I have exposed is an endpoint for grabbing all of the existing reimbursements in my DB. I want to have access to that data on the frontend so that I can display this information for my end users.
*/


// Isolation (finding an element by its ID). Also note that I'm using "let" to declare variables as it's good practice.

function getAllReimbursements(){
	
	var user = localStorage.getItem("user");
	console.log(user);
	
	let user_hold = document.getElementById("user_name");
	
	user_hold.innerText = user;
	
	document.getElementById("user_input").value = user;
	
    let url = 'http://localhost:7000/reimbursement/';
    
    url = url + user_hold.innerText;
    

    // Using the ready states of an XMLHttpRequest can guide your AJAX workflow.
    let xhr = new XMLHttpRequest(); //readyState is 0
    console.log(xhr);


    //While the readyState is 3 or 4, we're just waiting around for the response. As such, we need to determine what will happen when we do receive a response.

    /*
        Each time the readyState changes, this callback we define will be invoked.
    */
    xhr.onreadystatechange = function(){
        // If the readyState is 4 and the HTTP status code is 200, I have access to the data I requested when I sent the HTTP request.
        if(xhr.readyState === 4 && xhr.status === 200){
            // My first order of business is to access the data itself. Note that the data comes to us as JSON but that we want to be able to use the data as a JavaScript object.
            let reimbursements = JSON.parse(xhr.response);

            console.log(reimbursements);
            
            /*
                We need to iterate over this array of reimbursement objects in order to make a div for each reimbursement. We can use a for loop to accomplish this.

                Here we have used an enhanced for loop syntax. Please note that you can also us the "in" keyword in a JS loop, but that "in" is used to iterate over the properties of an object.
            */
			
            for(let reimbursement of reimbursements){
                // The first step is to decide where you want to add a new element. In our case, we want to add a new reimbursement_div to the element which has the id "reimbursement_container". That means that we want to isolate the reimbursement_container div.

            let reimbursement_container = document.getElementById('reimbursement_container');

            // In order to add a new reimbursement_div to the DOM, we have to create it. Fortunately, there is a JS function which allows us to create a new element. You just pass in the name of the element that you'd like to create:

            let new_div = document.createElement('div');
            new_div.className = "reimbursement_div";
            new_div.name = reimbursement.reimbursement_description;

            // Unfortunately, you are going to have to set the content for h3, p and of course the needed attributes for elements that require them (e.g. img have "src" attributes).

            // Since the innerText and src should be dynamically provided by the user, we are going to refactor this code to pull that information from the input boxes the users put this information inside of. In order to do that, we have to isolate/grab the input boxes in order to grab the data that has been entered.

			let new_h32 = document.createElement('h3');
			new_h32.innerText = reimbursement.reimbursement_id;
			
            let new_h3 = document.createElement('h3');
            new_h3.innerText = "Employee: " + reimbursement.employee_name;

            let new_para = document.createElement('p');
			new_para.innerText = "Amount: $" + reimbursement.reimbursement_amount;

            let new_para2 = document.createElement('p');
            new_para2.innerText = "Description: " + reimbursement.reimbursement_description;
            
            let new_para3 = document.createElement('p');
            new_para3.innerText = "Status: " + reimbursement.reimbursement_status;

            // We should also specify that the h3, p, img, and ol are children of the new div we're creating:

            new_div.appendChild(new_h32);
            new_div.appendChild(new_h3);
            new_div.appendChild(new_para);
            new_div.appendChild(new_para2);
            new_div.appendChild(new_para3);

            // Now that we've created that div and all of its child elements, let's FINALLY append the new div we've created to the existing reimbursement_container on our web page.

            reimbursement_container.appendChild(new_div);
            }
            console.log(user_hold.innerText);
        }
    }
    
    // Now let's open our HTTP request. We need to specify an HTTP verb and the URL.
    xhr.open('GET', url); //readyState is 1

    // Now let's send our HTTP request.
    xhr.send(); //readyState is 2
    
}

function logout() {

    localStorage.setItem("user", null);
    
    window.location.replace("http://localhost:7000/loginpage.html");

}

// Any function you call here is going to be invoked as soon as the window loads.
window.onload = function(){
    this.getAllReimbursements();
}