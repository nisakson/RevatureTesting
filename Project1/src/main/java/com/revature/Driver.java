package com.revature;

import javax.servlet.http.HttpSession;

import com.revature.controller.EmployeeController;
import com.revature.controller.ReimbursementController;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class Driver {

	public static void main(String...main) {
		
		//I'm starting my server on port 7000.
		Javalin app = Javalin.create().start(7000);
		
		app.post("/login", ctx -> {
			//If user credentials are correct, grant an HttpSession:
			ctx.req.getSession();
		});
		
		app.get("/logout", ctx -> {
			//If you pass in "false", an existing session is checked for.
			HttpSession session = ctx.req.getSession(false);
			if(session != null) session.invalidate();
		});
		
		app.after(ctx -> {
			ctx.res.addHeader("Access-Control-Allow-Origin", "null");
		});
		
		app.config.addStaticFiles("/static", Location.CLASSPATH);
		
		ReimbursementController reimbursementController = new ReimbursementController(app);
		EmployeeController employeeController = new EmployeeController(app);
	}
}
