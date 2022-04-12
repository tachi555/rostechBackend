/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rostech.api.Controller;

import com.rostech.api.Mapper.ArticleMapper;
import com.rostech.api.Mapper.LoginMapper;
import com.rostech.api.Model.Greeting;
import com.rostech.api.Model.Iip;
import com.rostech.api.Model.Ilogin;
import com.rostech.api.Model.Result;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
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
public class LoginController {
    private final String cors = "*";
    private final PlatformTransactionManager transactionManager;
    private final String url = "";

    @Autowired
    private final ArticleMapper articleMapper;

    @Autowired
    private final LoginMapper loginMapper;

    private final Logger logger = LogManager.getLogger(GreetingController.class);

    public LoginController(ArticleMapper articleMapper, LoginMapper loginMapper ,PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
        this.articleMapper = articleMapper;
        this.loginMapper = loginMapper;
    }
    
    protected void printStackTrace(Exception e){
        System.out.println("Start logging");
        logger.error(e.getMessage());
        System.out.println("End logging");
    }

    @CrossOrigin(origins = cors)
    @GetMapping(url+"/loginGreeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
            return new Greeting(1, "Hello World 3!");
    }
    
    @Transactional
    @CrossOrigin(origins = cors)
    @PostMapping(url+"/doLogin")
    public ArrayList<Iip> doLogin(@RequestBody Ilogin ilogin){
        Iip hasil = new Iip();
        Ilogin login = new Ilogin();
        String role = "";
        
        TransactionStatus txStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try{
            if(null != ilogin.getNama() && !ilogin.getNama().isBlank()
                    && null != ilogin.getPass() && !ilogin.getPass().isBlank()){
                login = this.loginMapper.checklogin(ilogin.getNama(), ilogin.getPass());
            }

            if(null == login.getRole() || login.getRole().isEmpty() || login.getRole().isBlank() || login.getRole().equalsIgnoreCase("0")){
                hasil.setToken("22");
            } else {
                hasil = this.loginMapper.getUserData(ilogin.getNama(), ilogin.getPass());
                hasil.setRole(login.getRole());
                hasil.setToken(generateToken());
                this.loginMapper.updateLoginAktif(ilogin);
                this.loginMapper.insertLogin(ilogin, hasil.getToken());
                hasil.setIsSuccess(1);
            }
        } catch(Exception e){
            transactionManager.rollback(txStatus);
            printStackTrace(e);
        }
        
        ArrayList ar = new ArrayList();
        ar.add(hasil);
        
        return ar;
    }
    
    @Transactional
    @CrossOrigin(origins = cors)
    @PostMapping(url+"/doLogout")
    public ArrayList<Ilogin> doLogout(@RequestBody Ilogin ilogin){
        Iip hasil = new Iip();
        Ilogin login = new Ilogin();
        String role = "";
        
        TransactionStatus txStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try{
            if(null != ilogin.getNama() && !ilogin.getNama().isBlank()
                    && null != ilogin.getPass() && !ilogin.getPass().isBlank()){
                login = this.loginMapper.checklogin(ilogin.getNama(), ilogin.getPass());
            }
            
            this.loginMapper.updateLoginAktif(ilogin);

        } catch(Exception e){
            transactionManager.rollback(txStatus);
            printStackTrace(e);
        }
        
        ArrayList ar = new ArrayList();
        ar.add(ilogin);
        
        return ar;
    }
    
    @CrossOrigin(origins = cors)
    @PostMapping(url+"/stillLogin")
    public Iip stillLogin(@RequestBody Iip iip){
        Iip hasil = new Iip();
        
        try{
            if(null == iip){
                throw new Exception("Please login");
            }
            hasil = this.loginMapper.stillLogin(iip.getIp(), iip.getToken());
            
            Calendar cal1 = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(hasil.getTanggal());
            if(ChronoUnit.HOURS.between(cal2.toInstant(),cal1.toInstant())>16){
                this.loginMapper.NonAktifToken(hasil);
                hasil.setToken(null);
                throw new Exception("Please login cause duration = " +ChronoUnit.DAYS.between(cal2.toInstant(),cal1.toInstant()));
            }
        } catch(Exception e){
            printStackTrace(e);
        }
        return hasil;
    }
    
    private int checkLogin(Result result) throws Exception{
        if(null == result || result.getIp().isEmpty() || result.getIp().isBlank() || result.getToken().isEmpty() || result.getToken().isBlank())
        {
            throw new Exception("Please login");
        } else if (1 == this.loginMapper.stillLogin(result.getIp(), result.getToken()).getIsSuccess()){

            return 1;
        } else {
            throw new Exception("Please login");
        }
    }
    
    public String generateToken(){
        SecureRandom sr = new SecureRandom();
        MessageDigest md;
        String token = "";
//        Iip hasil = new Iip();
//        hasil.setIsSuccess(0);
        StringBuilder sb = new StringBuilder();
        boolean repeat = true;
                
        try {
            while(repeat){
                System.out.println("Getting Token");    
                md = MessageDigest.getInstance("MD5");
                md.update(sr.generateSeed(32));
                byte[] b = md.digest();
                sb = new StringBuilder();

                for (int i = 0; i < b.length; i++)
                    sb.append(Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1));

                Integer use = loginMapper.checkUseToken(sb.toString());
                if(null== use){
                    repeat = false;
                }
            }
            
        } catch (NoSuchAlgorithmException ex) {
            java.util.logging.Logger.getLogger(GreetingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
//        hasil.setToken(sb.toString());
//        hasil.setIsSuccess(1);
        
        return sb.toString();
    }
    
}
