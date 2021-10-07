package com.revature.controller;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;

import javax.servlet.http.HttpSession;

import com.revature.model.Reimbursement;
import com.revature.service.ReimbursementService;
import com.revature.controller.EmployeeController;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class ReimbursementController {

	private ReimbursementService reimbursementService;
	
	public ReimbursementController(Javalin app) {
		this.reimbursementService = new ReimbursementService();
		app.routes(() -> {
			path("/reimbursement", () -> {
				//We can do subroutes for reimbursement resources (e.g. /reimbursement/all or /reimbursement/:id
				path("/all", () -> {
					//This is GET handler for the /reimbursement/all endpoint.
					get(findAllReimbursements);
				});
				path("/new", () -> {
					post(saveReimbursement);
				});
				path("/:name", () -> {
					get(findByName);
				});
				path("/approve/:id", () -> {
					post(approve);
				});
				path("/deny/:id", () -> {
					post(deny);
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
	
	private Handler findAllReimbursements = ctx -> {
		
		//To check for the existence of a session:
		HttpSession session = ctx.req.getSession(false);
		
		ctx.json(this.reimbursementService.findAll());
	};
	
	private Handler findByName = ctx -> {
		
		HttpSession session = ctx.req.getSession(false);
		
		ctx.json(this.reimbursementService.findName(ctx.pathParam("name")));
	};
	
	private Handler approve = ctx -> {
		
		HttpSession session = ctx.req.getSession(false);
		
		this.reimbursementService.approve(Integer.parseInt(ctx.pathParam("id")));
	};
	
	private Handler deny = ctx -> {
		
		HttpSession session = ctx.req.getSession(false);
		
		this.reimbursementService.deny(Integer.parseInt(ctx.pathParam("id")));
	};
		
	private Handler saveReimbursement = ctx -> {
		
		/*
		 * You can access input from a form by using the getParameter method
		 * that the context "req" property gives you access to.
		 */
		Reimbursement reimbursement = new Reimbursement(1, 
				Double.parseDouble(ctx.req.getParameter("reimbursement_amount")),
				ctx.req.getParameter("reimbursement_description"),
				ctx.req.getParameter("employee_name"),
				"Pending");
		
		this.reimbursementService.save(reimbursement);
		this.reimbursementService.findAll();
		
		/*
		 * Redirecting to a different view is simple in Javalin. You just
		 * use the "redirect" method that the context gives you access to.
		 */
		ctx.redirect("/home.html");
		
	};
	
	//Additional paths only used during testing
	private Handler pending = ctx -> {
		
		HttpSession session = ctx.req.getSession(false);
		
		this.reimbursementService.pending(Integer.parseInt(ctx.pathParam("id")));
	};
	
	private Handler delete = ctx -> {
		
		HttpSession session = ctx.req.getSession(false);
		
		Reimbursement reimbursement = new Reimbursement(1, 
				Double.parseDouble(ctx.req.getParameter("reimbursement_amount")),
				ctx.req.getParameter("reimbursement_description"),
				ctx.req.getParameter("employee_name"),
				"Pending");
		
		this.reimbursementService.delete(reimbursement);
	};
	
}
