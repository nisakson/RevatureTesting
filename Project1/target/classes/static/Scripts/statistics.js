/*
    We will be using AJAX to make asynchronous HTTP requests for resources that we have exposed. For instance, one resource that I have exposed is an endpoint for grabbing all of the existing reimbursements in my DB. I want to have access to that data on the frontend so that I can display this information for my end users.
*/


// Isolation (finding an element by its ID). Also note that I'm using "let" to declare variables as it's good practice.

function getStatistics(){
	
	var user = localStorage.getItem("user");
	console.log(user);
	
	let user_hold = document.getElementById("user_name");
	
	user_hold.innerText = user;
	
    let url = 'http://localhost:7000/reimbursement/all';

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
            
            let statistics_container = document.getElementById('statistics_container');
            let mean_expense = document.createElement('h3');
            let mean_expense_value = null;
            let sum = null;
            let most_expensive_employee = document.createElement('h3');
            let most_expensive_employee_value = null;
            let most_expensive_employee_employee = null;
            let count = 0;
            let approved = document.createElement('h3');
            let approved_count = 0;
            let denied = document.createElement('h3');
            let denied_count = 0;
            let pending = document.createElement('h3');
            let pending_count = 0
            var map1 = new Map();
			
            for(let reimbursement of reimbursements){
				if (reimbursement.reimbursement_status=="Approved") {
					approved_count = approved_count + 1;
            		count = count + 1;
            		sum = sum + reimbursement.reimbursement_amount;
            	
            		if (map1.get(reimbursement.employee_name)==null) {
						map1.set(reimbursement.employee_name, reimbursement.reimbursement_amount);
					}
					else {
						map1.set(reimbursement.employee_name, reimbursement.reimbursement_amount + map1.get(reimbursement.employee_name));
					}
				}
				else if (reimbursement.reimbursement_status=="Denied") {
					denied_count = denied_count + 1;
				}
				else if (reimbursement.reimbursement_status=="Pending") {
					pending_count = pending_count + 1;
				}
            }
            
            for (var [key,value] of map1) {
        		 most_expensive_employee_value = (!most_expensive_employee_value || most_expensive_employee_value < value) ? value : most_expensive_employee_value;
      		}
      		for (let [key, value] of map1.entries()) {
   	 			if (value === most_expensive_employee_value)
      				most_expensive_employee_employee = key;
  			}
            mean_expense_value = sum/count;
            
            mean_expense.innerText = "Average Expense Amount: $" + mean_expense_value;
            most_expensive_employee.innerText = "Most Expensive Employee: " + most_expensive_employee_employee + " with a total cost of $" + most_expensive_employee_value;
            approved.innerText = "Approved Reimbursements: " + approved_count;
            denied.innerText = "Denied Reimbursements: " + denied_count;
            pending.innerText = "Pending Reimbursements: " + pending_count;
            
            statistics_container.appendChild(mean_expense);
            statistics_container.appendChild(most_expensive_employee);
            statistics_container.appendChild(approved);
            statistics_container.appendChild(denied);
            statistics_container.appendChild(pending);
        }
    }
    
    // Now let's open our HTTP request. We need to specify an HTTP verb and the URL.
    xhr.open('GET', url); //readyState is 1

    // Now let's send our HTTP request.
    xhr.send(); //readyState is 2
    
}

function approve(id) {
	
	let url = 'http://localhost:7000/reimbursement/approve/';
	url = url + id;

    // Using the ready states of an XMLHttpRequest can guide your AJAX workflow.
    let xhr = new XMLHttpRequest(); //readyState is 0
    console.log(xhr);
    
    xhr.onreadystatechange = function(){
        // If the readyState is 4 and the HTTP status code is 200, I have access to the data I requested when I sent the HTTP request.
        if(xhr.readyState === 4 && xhr.status === 200){
        	window.location.replace("http://localhost:7000/managerhome.html");
		}
	}
    
    // Now let's open our HTTP request. We need to specify an HTTP verb and the URL.
    xhr.open('POST', url); //readyState is 1

    // Now let's send our HTTP request.
    xhr.send(); //readyState is 2
}

function logout() {

    localStorage.setItem("user", null);
    
    window.location.replace("http://localhost:7000/loginpage.html")

}

// Any function you call here is going to be invoked as soon as the window loads.
window.onload = function(){
    this.getStatistics();
}

