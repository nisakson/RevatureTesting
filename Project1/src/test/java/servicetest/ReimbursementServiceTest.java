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
import com.revature.repository.ReimbursementRepository;
import com.revature.service.ReimbursementService;

public class ReimbursementServiceTest {
	

	//My Object Under Test
	@InjectMocks
	private static ReimbursementService reimbursementService;
	
	//What needs to be mocked? We need to mock the RecipeRepository as we don't want
	//any actual RecipeRepository method invocations when we use our RecipeService.
	@Mock
	private ReimbursementRepository reimbursementRepository;
	
	{
		// We need to initialize our Mockito mocks (or anything that is annotated with
		// Mockito annotations.
		MockitoAnnotations.openMocks(this);
	}
	
	@BeforeClass
	public static void setUp() {
		reimbursementService = new ReimbursementService();
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
	public void testFindAll() {
		
		/*
		 * We have to tell Mockito what dummy data should be returned when the 
		 * RecipeRepository's findAll would normally be invoked.
		 * 
		 * It seems reasonable to return a small ArrayList of fake recipes.
		 * 
		 * Essentially, the RecipeRepository's findAll method is NOT going to be called.
		 * This dummy data we've specified below will just be returned instead.
		 */
		
		Mockito.when(reimbursementRepository.findAll()).thenReturn(
				Arrays.asList(
						new Reimbursement(1, 1.01, "Desc1", "Name1", "Status1"),
						new Reimbursement(2, 2.02, "Desc2", "Name2", "Status2"),
						new Reimbursement(3, 3.03, "Desc3", "Name3", "Status3"),
						new Reimbursement(4, 4.04, "Desc4", "Name4", "Status4")
						)
				);
						
		List<Reimbursement> reimbursements = reimbursementService.findAll();
		
		Assert.assertEquals("The list size should be 4", 4, reimbursements.size());
	}
	
	@Test
	public void testFindName() {
		
		/*
		 * We have to tell Mockito what dummy data should be returned when the 
		 * RecipeRepository's findAll would normally be invoked.
		 * 
		 * It seems reasonable to return a small ArrayList of fake recipes.
		 * 
		 * Essentially, the RecipeRepository's findAll method is NOT going to be called.
		 * This dummy data we've specified below will just be returned instead.
		 */
		
		Mockito.when(reimbursementRepository.findName("test")).thenReturn(
				Arrays.asList(
						new Reimbursement(1, 1.01, "Desc1", "Name1", "Status1")
						)
				);
						
		List<Reimbursement> reimbursement = reimbursementService.findName("test");
		
		Assert.assertEquals("The list size should be 1", 1, reimbursement.size());
	}
	
	@Test
	public void testSave() {
		
		/*
		 * We have to tell Mockito what dummy data should be returned when the 
		 * RecipeRepository's findAll would normally be invoked.
		 * 
		 * It seems reasonable to return a small ArrayList of fake recipes.
		 * 
		 * Essentially, the RecipeRepository's findAll method is NOT going to be called.
		 * This dummy data we've specified below will just be returned instead.
		 */
	
		Reimbursement test = new Reimbursement(1, 1.01, "Desc1", "Name1", "Status1");
		
		Mockito.doNothing().when(reimbursementRepository).save(test);
		
		reimbursementService.save(test);
		
		Mockito.verify(reimbursementRepository,times(1)).save(test);
	}
	
	@Test
	public void testApprove() {
		
		/*
		 * We have to tell Mockito what dummy data should be returned when the 
		 * RecipeRepository's findAll would normally be invoked.
		 * 
		 * It seems reasonable to return a small ArrayList of fake recipes.
		 * 
		 * Essentially, the RecipeRepository's findAll method is NOT going to be called.
		 * This dummy data we've specified below will just be returned instead.
		 */
	
		int test = 1;
		
		Mockito.doNothing().when(reimbursementRepository).approve(test);
		
		reimbursementService.approve(test);
		
		Mockito.verify(reimbursementRepository,times(1)).approve(test);
	}
	
	@Test
	public void testDeny() {
		
		/*
		 * We have to tell Mockito what dummy data should be returned when the 
		 * RecipeRepository's findAll would normally be invoked.
		 * 
		 * It seems reasonable to return a small ArrayList of fake recipes.
		 * 
		 * Essentially, the RecipeRepository's findAll method is NOT going to be called.
		 * This dummy data we've specified below will just be returned instead.
		 */
	
		int test = 1;
		
		Mockito.doNothing().when(reimbursementRepository).deny(test);
		
		reimbursementService.deny(test);
		
		Mockito.verify(reimbursementRepository,times(1)).deny(test);
	}
}
