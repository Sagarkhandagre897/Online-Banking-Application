package com.Controller;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.Dao.AccountDao;
import com.Dao.PaymentHistoryDao;
import com.Dao.TransactionHistoryDao;

import Entities.Account;
import Entities.PaymentHistory;
import Entities.TransactionHistory;
import Entities.User;

@Controller
@RequestMapping("/app")
public class AppController {

	@Autowired
	private AccountDao accountDao;
	
	@Autowired
	private PaymentHistoryDao paymentHistoryDao;
	@Autowired
	private TransactionHistoryDao transactionHistoryDao;
	
	@GetMapping("/dashboard")
    public ModelAndView getDashboard(HttpSession session){
        ModelAndView getDashboardPage = new ModelAndView("dashboard");

        // Get the details of the logged i user:
       User  user = (User)session.getAttribute("user");

       // Get The Accounts Of The Logged In User:
       List<Account> getUserAccounts = accountDao.getUserAccountsById(user.getUser_id());

       BigDecimal totalAccountsBalance = new BigDecimal(0);
       
       // Get Balance:
       for(Account account : getUserAccounts) {
    	   
    	totalAccountsBalance  = account.getBalance();
       
       }
       
       // Set Objects:
       getDashboardPage.addObject("userAccounts", getUserAccounts);
       getDashboardPage.addObject("totalBalance", totalAccountsBalance);
       
        return getDashboardPage;
    }
	
	 @GetMapping("/payment_history")
	    public ModelAndView getPaymentHistory(HttpSession session){
	        // Set View:
	        ModelAndView getPaymentHistoryPage = new ModelAndView("paymentHistory");

	        // Get Logged In User:\
	        User user = (User) session.getAttribute("user");

	        // Get Payment History / Records:
	        List<PaymentHistory> userPaymentHistory = paymentHistoryDao.getPaymentRecordsById(user.getUser_id());

	        getPaymentHistoryPage.addObject("payment_history", userPaymentHistory);

	        return getPaymentHistoryPage;

	    }


	    @GetMapping("/transact_history")
	    public ModelAndView getTransactHistory(HttpSession session){
	        // Set View:
	        ModelAndView getTransactHistoryPage = new ModelAndView("transactHistory");

	        
	        User user = (User) session.getAttribute("user");

	       
	        List<TransactionHistory> userTransactHistory = transactionHistoryDao.getTransactionRecordsById(user.getUser_id());

	        getTransactHistoryPage.addObject("transact_history", userTransactHistory);

	        return getTransactHistoryPage;

	    }
}
