package com.camping.Account;

import java.util.ArrayList;
import java.util.List;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Details;
import com.paypal.api.payments.Item;
import com.paypal.api.payments.ItemList;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
/*
 * author: Aleksandra Marjanovic
 * A00303157
 */
public class PaymentServices {
	private static final String CLIENT_ID = "Aaa7h-3TDYdAzGjGvpeKgOVn8S5IpD8HGhYplLgJcEsTPD2lHbTeKcABg8NKbHRhQprteI4RxaykMgGK";
	private static final String CLIENT_SECRET = "EH5TxPWVHe6yNNInEMaXBbFYFpdPnhagmyaUyxwNpdVq4DKMnZJQp4XuKCUvIzIEoVxvk0EAfFHNGR4x";
	private static final String MODE = "sandbox";
	

    public String authorizePayment(OrderDetailsBean orderDetail)        
            throws PayPalRESTException {       
 
        Payer payer = getPayerInformation();
        RedirectUrls redirectUrls = getRedirectURLs(orderDetail.getPackageId());
        List<Transaction> listTransaction = getTransactionInformation(orderDetail);
         
        Payment requestPayment = new Payment();
        requestPayment.setTransactions(listTransaction);
        requestPayment.setRedirectUrls(redirectUrls);
        requestPayment.setPayer(payer);
        requestPayment.setIntent("authorize");
 
        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
 
        Payment approvedPayment = requestPayment.create(apiContext);
 
        return getApprovalLink(approvedPayment);
 
    }
     
    private Payer getPayerInformation() {
        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");
         
        PayerInfo payerInfo = new PayerInfo();
        payerInfo.setFirstName("John")
                 .setLastName("Doe")
                 .setEmail("sb-g2gop18944149@personal.example.com");
         
        payer.setPayerInfo(payerInfo);
         
        return payer;
    }
     
    private RedirectUrls getRedirectURLs(String reservationNumber) {
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("http://localhost:8080/PremiumCamping/cancel.xhtml");
        redirectUrls.setReturnUrl("http://localhost:8080/PremiumCamping/confirm.xhtml?reservationNumber=" + reservationNumber);  
        return redirectUrls;
    }
     
    private List<Transaction> getTransactionInformation(OrderDetailsBean orderDetail) {
        Details details = new Details();
       
     
        Amount amount = new Amount();
        amount.setCurrency("EUR");
        amount.setTotal(orderDetail.getTotal());
        amount.setDetails(details);
     
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription(orderDetail.getPackageName());
         
        ItemList itemList = new ItemList();
        List<Item> items = new ArrayList<Item>();
         
        Item item = new Item();
        item.setCurrency("EUR");
        item.setName(orderDetail.getPackageName());
        item.setPrice(orderDetail.getTotal());
        item.setQuantity("1");
        
    
        items.add(item);
        itemList.setItems(items);
        transaction.setItemList(itemList);
     
        List<Transaction> listTransaction = new ArrayList<Transaction>();
        listTransaction.add(transaction);  
         
        return listTransaction;
    }
     
    private String getApprovalLink(Payment approvedPayment) {
        List<Links> links = approvedPayment.getLinks();
        String approvalLink = null;
         
        for (Links link : links) {
            if (link.getRel().equalsIgnoreCase("approval_url")) {
                approvalLink = link.getHref();
                break;
            }
        }      
         
        return approvalLink;
    }
    public Payment getPaymentDetails(String paymentId) throws PayPalRESTException {
        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
        return Payment.get(apiContext, paymentId);
    }
    public Payment executePayment(String paymentId, String payerId)
            throws PayPalRESTException {
        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);
     
        Payment payment = new Payment().setId(paymentId);
     
        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
     
        return payment.execute(apiContext, paymentExecution);
    }
	
}
