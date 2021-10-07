package daotest;

import static org.mockito.Mockito.times;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.hibernate.query.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Transaction;
import org.hibernate.Session;
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
import com.revature.util.HibernateSessionFactory;

public class ReimbursementRepositoryTest {
	

	//My Object Under Test
	@InjectMocks
	private static ReimbursementRepository reimbursementRepository;
	
	private static Session hibernateSession;
	private static Transaction hibernateTx;
	private static CriteriaBuilder hibernateCb;
	private static CriteriaQuery<Reimbursement> hibernateCq;
	private static Root<Reimbursement> hibernateRootEntry;
	private static CriteriaQuery<Reimbursement> hibernateAll;
	private static Query<Reimbursement> hibernateAllQuery;
	
	//What needs to be mocked? We need to mock the RecipeRepository as we don't want
	//any actual RecipeRepository method invocations when we use our RecipeService.
	@Mock
	private static HibernateSessionFactory hibernateSessionFactory;
	
	{
		// We need to initialize our Mockito mocks (or anything that is annotated with
		// Mockito annotations.
		MockitoAnnotations.openMocks(this);
	}
	
	@BeforeClass
	public static void setUp() {
		reimbursementRepository = new ReimbursementRepository();
		
		//Mockito.when(hibernateSessionFactory.getSession()).thenReturn(hibernateSession);
		//Mockito.when(hibernateSession.beginTransaction()).thenReturn(hibernateTx);
		//Mockito.when(hibernateSession.getCriteriaBuilder()).thenReturn(hibernateCb);
		//Mockito.when(hibernateCb.createQuery(Reimbursement.class)).thenReturn(hibernateCq);
		//Mockito.when(hibernateCq.from(Reimbursement.class)).thenReturn(hibernateRootEntry);
		//Mockito.when(hibernateCq.select(hibernateRootEntry)).thenReturn(hibernateAll);
		//Mockito.when(hibernateSession.createQuery(hibernateAll)).thenReturn(hibernateAllQuery);
		//Mockito.when(hibernateAllQuery.getResultList()).thenReturn(
		//		Arrays.asList(
		//				new Reimbursement(1, 1.01, "Desc1", "Name1", "Status1"),
		//				new Reimbursement(2, 2.02, "Desc2", "Name2", "Status2"),
		//				new Reimbursement(3, 3.03, "Desc3", "Name3", "Status3"),
		//				new Reimbursement(4, 4.04, "Desc4", "Name4", "Status4")
		//				)
		//		);
		//Mockito.doNothing().when(hibernateTx).commit();
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
	@Transactional
	public void testFindAll() {
						
		List<Reimbursement> reimbursements = reimbursementRepository.findAll();
		
		Assert.assertNotEquals(0, reimbursements.size());
	}
	
	@Test
	@Transactional
	public void testFindName() {
		String name = "Nick Isakson";
						
		List<Reimbursement> reimbursements = reimbursementRepository.findName(name);
		
		Assert.assertNotEquals(0, reimbursements.size());
	}
	
	@Test
	@Transactional
	public void testSave() {
		Reimbursement test = new Reimbursement(1, 1.01, "d", "n", "s");
						
		reimbursementRepository.save(test);
		
		Assert.assertEquals(test.getEmployee_name(), reimbursementRepository.findName("n").get(0).getEmployee_name());
		
		reimbursementRepository.delete(test);
	}
	
	@Test
	@Transactional
	public void testDeny() {
		int id = reimbursementRepository.findAll().get(0).getReimbursement_id();
		String status = reimbursementRepository.findAll().get(0).getReimbursement_status();	
		
		reimbursementRepository.deny(id);
		
		for (Reimbursement reimbursement : reimbursementRepository.findAll()) {
			if (reimbursement.getReimbursement_id()==id) {
				Assert.assertEquals("Denied", reimbursement.getReimbursement_status());
			}
		}
		
		if (status.equals("Approved")) {
			reimbursementRepository.approve(id);
		}
		else if (status.equals("Denied")) {
			reimbursementRepository.deny(id);
		}
		else if (status.equals("Pending")) {
			reimbursementRepository.pending(id);
		}
	}
	
	@Test
	@Transactional
	public void testApprove() {
		int id = reimbursementRepository.findAll().get(0).getReimbursement_id();
		String status = reimbursementRepository.findAll().get(0).getReimbursement_status();	
						
		reimbursementRepository.approve(id);
		
		for (Reimbursement reimbursement : reimbursementRepository.findAll()) {
			if (reimbursement.getReimbursement_id()==id) {
				Assert.assertEquals("Approved", reimbursement.getReimbursement_status());
			}
		}
		
		if (status.equals("Approved")) {
			reimbursementRepository.approve(id);
		}
		else if (status.equals("Denied")) {
			reimbursementRepository.deny(id);
		}
		else if (status.equals("Pending")) {
			reimbursementRepository.pending(id);
		}
	}
}
