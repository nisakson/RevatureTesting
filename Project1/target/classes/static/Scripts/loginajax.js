/*
    We will be using AJAX to make asynchronous HTTP requests for resources that we have exposed. For instance, one resource that I have exposed is an endpoint for grabbing all of the existing reimbursements in my DB. I want to have access to that data on the frontend so that I can display this information for my end users.
*/


// Isolation (finding an element by its ID). Also note that I'm using "let" to declare variables as it's good practice.

function login(){
	
	var user = JSON.parse(xhr.response);
    
    localStorage.setItem("user", user);
    
}