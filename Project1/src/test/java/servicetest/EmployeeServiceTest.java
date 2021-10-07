package servicetest;

import static org.mockito.Mockito.times;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.revature.model.Reimbursement;
import com.revature.model.Employee;
import com.revature.repository.EmployeeRepository;
import com.revature.service.EmployeeService;

public class EmployeeServiceTest {
	

	//My Object Under Test
	@InjectMocks
	private static EmployeeService employeeService;
	
	//What needs to be mocked? We need to mock the RecipeRepository as we don't want
	//any actual RecipeRepository method invocations when we use our RecipeService.
	@Mock
	private EmployeeRepository employeeRepository;
	
	{
		// We need to initialize our Mockito mocks (or anything that is annotated with
		// Mockito annotations.
		MockitoAnnotations.openMocks(this);
	}
	
	@BeforeClass
	public static void setUp() {
		employeeService = new EmployeeService();
	}
	
	/*
	 * The point of unit testing is to isolate defects. In essence, if a test reveals a bug,
	 * I should be able to easily pinpoint where that bug is.
	 * 
	 * This is where Mockito comes in. Mockito allows us to better isolate our tests. It
	 * can prevent method calls to other layers (for instance) or return dummy data/canned responses
	 * when certain methods would be called (this is called "stubbing"). This gives us more control.
	 */
	
	@Test
	public void testGetByUsername() {
		
		/*
		 * We have to tell Mockito what dummy data should be returned when the 
		 * RecipeRepository's findAll would normally be invoked.
		 * 
		 * It seems reasonable to return a small ArrayList of fake recipes.
		 * 
		 * Essentially, the RecipeRepository's findAll method is NOT going to be called.
		 * This dummy data we've specified below will just be returned instead.
		 */
		
		Mockito.when(employeeRepository.getByUsername("u")).thenReturn(
				new Employee(1, "n", "u", "p", "r")
				);
						
		Employee emp = employeeService.getByUsername("u");
		
		Assert.assertEquals(emp.getEmployee_id(), 1);
		Assert.assertEquals(emp.getEmployee_name(), "n");
		Assert.assertEquals(emp.getEmployee_username(), "u");
		Assert.assertEquals(emp.getEmployee_password(), "p");
		Assert.assertEquals(emp.getEmployee_role(), "r");
	}
}
