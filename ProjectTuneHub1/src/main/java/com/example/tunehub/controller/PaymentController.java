package com.example.tunehub.controller;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.tunehub.entity.User;
import com.example.tunehub.service.UserService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;

import jakarta.servlet.http.HttpSession;

@Controller
public class PaymentController {
	@Autowired
	UserService userService;
	
	

	@GetMapping("/pay")
	public String pay() {
		return "pay";
	}

	@SuppressWarnings("finally")
	@PostMapping("/createOrder")
	@ResponseBody
	public String createOrder(HttpSession session) {

		int  amount  = 1;
		Order order=null;
		try {
			RazorpayClient razorpay=new RazorpayClient("rzp_test_6kkEchuogR2ws4"
					, "U7aKGcd7KphTrPVbF1Gat2TY");

			JSONObject orderRequest = new JSONObject();
			orderRequest.put("amount", amount*100); // amount in the smallest currency unit
			orderRequest.put("currency", "INR");
			orderRequest.put("receipt", "order_rcptid_11");

			order = razorpay.orders.create(orderRequest);

//			

		} catch (RazorpayException e) {
			e.printStackTrace();
		}
		finally {
			return order.toString();
		}
	}
	
	@PostMapping("/verify")
	@ResponseBody
	public boolean verifyPayment(@RequestParam  String orderId, @RequestParam String paymentId,
											@RequestParam String signature) {
	    try {
	        // Initialize Razorpay client with your API key and secret
	        RazorpayClient razorpayClient = new RazorpayClient("rzp_test_6kkEchuogR2ws4", 
	        								"U7aKGcd7KphTrPVbF1Gat2TY");
	        // Create a signature verification data string
	        String verificationData = orderId + "|" + paymentId;

	        // Use Razorpay's utility function to verify the signature
	        boolean isValidSignature = Utils.verifySignature(verificationData, signature, 
	        													"U7aKGcd7KphTrPVbF1Gat2TY");

	        return isValidSignature;
	    } catch (RazorpayException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	@GetMapping("/payment-sucess")
	public String getMethodName( HttpSession session) {
			String mail =  (String) session.getAttribute("email");

			User user = userService.getUser(mail);
			user.setIspremium(true);
			userService.updateUser(user);
		return "customerhome";
	}
	@GetMapping("/payment-failure")
	public String paymentFailure() {
		return "customerhome";
	}
	
}
	