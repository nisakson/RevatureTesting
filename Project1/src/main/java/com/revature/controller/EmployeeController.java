package com.revature.controller;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;

import javax.servlet.http.HttpSession;

import com.revature.model.Employee;
import com.revature.model.Reimbursement;
import com.revature.service.EmployeeService;
import com.revature.service.ReimbursementService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

import java.util.concurrent.TimeUnit;

public class EmployeeController {

	private EmployeeService employeeService;
	public Employee emp;
	
	public EmployeeController(Javalin app) {
		this.employeeService = new EmployeeService();
		app.routes(() -> {
			path("/employee", () -> {
				//We can do subroutes for reimbursement resources (e.g. /reimbursement/all or /reimbursement/:id
				path("/:username", () -> {
					get(login);
				});
				path("/login", () -> {
					post(login2);
				});
			});
		});
	}
	
	/*
	 * Why are we using so many lambda expressions? Javalin makes heavy use of lambda expressions.
	 * The Handler interface is a function interface that we can use to create lambdra expressions
	 * which take in a context that gives us access to our HTTP request and response bodies.
	 */
	
	// This lambda expression writes the list of reimbursements to the response body as JSON.
	
	private Handler login = ctx -> {
		
		//To check for the existence of a session:
		HttpSession session = ctx.req.getSession(false);
		
		emp = this.employeeService.getByUsername(ctx.pathParam("username"));
		ctx.json(emp);
	};
	
	private Handler login2 = ctx -> {
		
		//To check for the existence of a session:
		HttpSession session = ctx.req.getSession(false);
		
		emp = this.employeeService.getByUsername(ctx.req.getParameter("username"));
		
		if (emp.getEmployee_password().equals(ctx.req.getParameter("password")) && emp.getEmployee_role().equals("Employee")) {
			ctx.redirect("/home.html");
		}
		else if (emp.getEmployee_password().equals(ctx.req.getParameter("password")) && emp.getEmployee_role().equals("Manager")) {
			ctx.redirect("/managerhome.html");
		}
		else {
			ctx.redirect("/loginpage.html");
		}
	};
}
