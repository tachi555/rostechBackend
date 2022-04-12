/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rostech.api.Controller;

import com.rostech.api.Mapper.ArticleMapper;
import com.rostech.api.Mapper.CucianMapper;
import com.rostech.api.Model.Cucian;
import com.rostech.api.Model.Greeting;
import com.rostech.api.Model.Result;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
public class CucianController {
    private final String cors = "*";
    private final PlatformTransactionManager transactionManager;
    private final String url = "cucian/";
    
    @Autowired
    private final CucianMapper cucianMapper;
    
    @Autowired
    private final ArticleMapper articleMapper;

    private final Logger logger = LogManager.getLogger(GreetingController.class);

    public CucianController(ArticleMapper articleMapper, CucianMapper cucianMapper,PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
        this.cucianMapper = cucianMapper;
        this.articleMapper = articleMapper;
    }
    
    protected void printStackTrace(Exception e){
        System.out.println("Start logging");
        logger.error(e.getMessage());
        System.out.println("End logging");
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

    @CrossOrigin(origins = cors)
    @GetMapping(url+"/GreetingCucian")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
            return new Greeting(1, "Hello World 3!");
    }
    
    @Transactional
    @CrossOrigin(origins = cors)
    @PostMapping(url+"/insertCucian")
    public Integer insertCucian(@RequestBody Cucian cucian) {
        TransactionStatus txStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
        Integer hasil = -1;
        try {
            checkLogin(cucian);
            if(null == cucian.getId() || 0 == cucian.getId()){
                hasil = this.cucianMapper.insertCucian(cucian);
                hasil = Integer.parseInt(cucian.getId().toString());
            } else {
                hasil = this.cucianMapper.updateCucian(cucian);
                hasil = Integer.parseInt(cucian.getId().toString());
            }
            
            
        } catch(Exception e){
            transactionManager.rollback(txStatus);
            printStackTrace(e);
        }
        return hasil;
    }
    
    @CrossOrigin(origins = cors)
    @PostMapping(url+"/getCucianOnDate")
    public ArrayList<Cucian> getCucianOnDate(@RequestBody Cucian cucian) {
        TransactionStatus txStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
        ArrayList<Cucian> hasil = new ArrayList<>();
        try {
            checkLogin(cucian);
            Calendar cal = Calendar.getInstance();
            cal.setTime(cucian.getTanggal());
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            cucian.setTanggal(cal.getTime());
            cucian.setFrom(cal.getTime());
            cal.set(Calendar.HOUR_OF_DAY, 23);
            cal.set(Calendar.MINUTE, 59);
            cal.set(Calendar.SECOND, 59);
            cal.set(Calendar.MILLISECOND, 000);
            cucian.setTo(cal.getTime());
            hasil = this.cucianMapper.getCucianOnDate(cucian);
            
        } catch(Exception e){
            transactionManager.rollback(txStatus);
            printStackTrace(e);
        }
        return hasil;
    }
    
}
