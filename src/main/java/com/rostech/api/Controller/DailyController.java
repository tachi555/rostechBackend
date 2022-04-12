/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rostech.api.Controller;

import com.rostech.api.Mapper.ArticleMapper;
import com.rostech.api.Mapper.DailyMapper;
import com.rostech.api.Model.Daily;
import com.rostech.api.Model.Greeting;
import com.rostech.api.Model.Material;
import com.rostech.api.Model.Pembelian;
import com.rostech.api.Model.Result;

import java.util.ArrayList;
import java.util.Calendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author PC-Rumah
 */
@RestController
public class DailyController {
    private final String cors = "*";
    private final PlatformTransactionManager transactionManager;
    private final String url = "daily/";
    
    @Autowired
    private final DailyMapper dailyMapper;
    
    @Autowired
    private final ArticleMapper articleMapper;

    private final Logger logger = LogManager.getLogger(GreetingController.class);

    public DailyController(ArticleMapper articleMapper, DailyMapper dailyMapper ,PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
        this.articleMapper = articleMapper;
        this.dailyMapper = dailyMapper;
    }
    
    protected void printStackTrace(Exception e){
        System.out.println("Start logging");
        logger.error(e.getMessage());
        System.out.println("End logging");
    }

    @CrossOrigin(origins = cors)
    @GetMapping(url+"/Greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
            return new Greeting(1, "Hello World 3!");
    }
    
    private int checkLogin(Result result) throws Exception{
        if(null == result || result.getIp().isEmpty() || result.getIp().isBlank() || result.getToken().isEmpty() || result.getToken().isBlank())
        {
            throw new Exception("Please login");
        } else if (1 == this.articleMapper.stillLogin(result.getIp(), result.getToken()).getIsSuccess()){

            return 1;
        } else {
            throw new Exception("Please login");
        }
    }
    
    @Transactional
    @CrossOrigin(origins = cors)
    @PostMapping(url+"/insertDaily")
    public Integer insertDaily(@RequestBody Daily daily) {
        TransactionStatus txStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
        Integer hasil = 0;
        try {
            checkLogin(daily);
            if(null != daily.getTanggal()) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(daily.getTanggal());
                cal.set(Calendar.HOUR_OF_DAY, 0);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0);
                cal.set(Calendar.MILLISECOND, 0);
                daily.setTanggal(cal.getTime());
                daily.setFrom(cal.getTime());
                cal.set(Calendar.HOUR_OF_DAY, 23);
                cal.set(Calendar.MINUTE, 59);
                cal.set(Calendar.SECOND, 59);
                cal.set(Calendar.MILLISECOND, 000);
                daily.setTo(cal.getTime());
	            if(null == this.dailyMapper.checkDaily(daily)) {
	            	hasil = this.dailyMapper.insertDaily(daily);
	            } else {
	            	hasil = this.dailyMapper.updateDaily(daily);
	            }
            }   
            
        } catch(Exception e){
            transactionManager.rollback(txStatus);
            printStackTrace(e);
        }
        return hasil;
    }
    
    @CrossOrigin(origins = cors)
    @PostMapping(url+"/getDailyBetweenDate")
    public ArrayList<Daily> getDailyBetweenDate(@RequestBody Daily daily) {
        ArrayList<Daily> hasil = new ArrayList<>();
        try {
            checkLogin(daily);
            Calendar cal = Calendar.getInstance();
            cal.setTime(daily.getFrom());
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            daily.setFrom(cal.getTime());
            cal.setTime(daily.getTo());
            cal.set(Calendar.HOUR_OF_DAY, 23);
            cal.set(Calendar.MINUTE, 59);
            cal.set(Calendar.SECOND, 59);
            cal.set(Calendar.MILLISECOND, 000);
            daily.setTo(cal.getTime());
            hasil = this.dailyMapper.getDailyBetweenDate(daily);
            
        } catch (Exception ex) {
            printStackTrace(ex);
        }
        return hasil;
    }
    
    @CrossOrigin(origins = cors)
    @PostMapping(url+"/getDailyOnDate")
    public ArrayList<Daily> getDailyOnDate(@RequestBody Daily daily) {
        ArrayList<Daily> hasil = new ArrayList<>();
        try {
            checkLogin(daily);
            Calendar cal = Calendar.getInstance();
            cal.setTime(daily.getTanggal());
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            daily.setFrom(cal.getTime());
            System.out.println(daily.getTanggal());
            cal.set(Calendar.HOUR_OF_DAY, 23);
            cal.set(Calendar.MINUTE, 59);
            cal.set(Calendar.SECOND, 59);
            cal.set(Calendar.MILLISECOND, 000);
            daily.setTo(cal.getTime());
            hasil = this.dailyMapper.getDailyOnDate(daily);
            System.out.println("size = " +hasil.size() +" from = " +daily.getFrom() +" to = " +daily.getTo());
            
        } catch (Exception ex) {
            printStackTrace(ex);
        }
        return hasil;
    }
    
    
    
}
