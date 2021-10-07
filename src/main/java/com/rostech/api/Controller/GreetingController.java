package com.rostech.api.Controller;

import com.rostech.api.Mapper.ArticleMapper;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rostech.api.Model.Greeting;
import com.rostech.api.Model.Iip;
import com.rostech.api.Model.Ilogin;
import com.rostech.api.Model.Material;
import com.rostech.api.Model.Pembelian;
import com.rostech.api.Model.Result;
import com.rostech.api.Model.Stock;
import com.rostech.api.Model.Toko;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class GreetingController {
//    private final String cors = "54.169.128.87:8080";
    private final String cors = "*";
    private final PlatformTransactionManager transactionManager;

    public GreetingController(ArticleMapper articleMapper ,PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
        this.articleMapper = articleMapper;
    }

    @Autowired
    private final ArticleMapper articleMapper;

    private final Logger logger = LogManager.getLogger(GreetingController.class);

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    
    protected void printStackTrace(Exception e){
//        e.printStackTrace();
        System.out.println("Start logging");
        logger.error(e.getMessage());
        System.out.println("End logging");
//        java.util.logging.Logger.getLogger(GreetingController.class.getName()).log(Level.SEVERE, null, e);
    }

    @CrossOrigin(origins = cors)
    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
            return new Greeting(1, "Hello World!");
    }
    
    @CrossOrigin(origins = cors)
    @PostMapping("/getLastPrice")
    public Pembelian getLastPrice(@RequestBody Material material) {
        Pembelian  hasil = new Pembelian();
        try {
            checkLogin(material);
            hasil = this.articleMapper.getLastPrice(material.getMid());
        } catch (Exception ex) {
            printStackTrace(ex);
        }
        
        return hasil;  
    }
    
    @CrossOrigin(origins = cors)
    @PostMapping("/listmaterial")
    public ArrayList<Material> listmaterial(@RequestBody Material material) {
        ArrayList<Material>  hasil = new ArrayList<>();
        try {
            checkLogin(material);
            hasil = this.articleMapper.getMaterialList();

        } catch (Exception ex) {
            printStackTrace(ex);
        }
        
        return   hasil;  
    }
    
    @CrossOrigin(origins = cors)
    @PostMapping("/getAutoMaterial")
    public ArrayList<String> getAutoMaterial(@RequestBody Material material) {
        ArrayList<String>  hasil = new ArrayList<>();
        try {
            checkLogin(material);
            hasil = this.articleMapper.getAutoMaterial("%"+material.getNama()+"%");
            
        } catch (Exception ex) {
            printStackTrace(ex);
        }
        
        return hasil;  
    }
    
    @CrossOrigin(origins = cors)
    @PostMapping("/getComboMaterial")
    public ArrayList<Material> getComboMaterial(@RequestBody Material material) {
        ArrayList<Material>  hasil = new ArrayList<>();
        try {
            checkLogin(material);
            hasil = this.articleMapper.getComboMaterial("%"+material.getNama()+"%");
            
        } catch (Exception ex) {
            printStackTrace(ex);
        }
        
        return hasil;  
    }
    
    @Transactional
    @CrossOrigin(origins = cors)
    @PostMapping("/insertmaterial")
    public Integer insertmaterial(@RequestBody Material material) {
        TransactionStatus txStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
        Integer hasil = 0;
        try {
            checkLogin(material);
            Integer dupe = this.articleMapper.checkMaterialName(material);
            if(null != dupe){
                hasil = -1;
            } else {
                hasil = this.articleMapper.insertMaterial(material);
            }
        } catch(Exception e){
            transactionManager.rollback(txStatus);
            printStackTrace(e);
        }
        return hasil;
    }
    
    @Transactional
    @CrossOrigin(origins = cors)
    @PostMapping("/editMaterial")
    public Integer editMaterial(@RequestBody Material material) {
        TransactionStatus txStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
        Integer hasil = 0;
        try {
            checkLogin(material);
            hasil = this.articleMapper.editMaterial(material);
        } catch(Exception e){
            transactionManager.rollback(txStatus);
            printStackTrace(e);
        }
        return hasil;
    }

    @CrossOrigin(origins = cors)
    @GetMapping("/getTotalMaterial")
    public Integer getTotalMaterial() {
            return this.articleMapper.getTotalMaterial();
    }

    @CrossOrigin(origins = cors)
    @GetMapping("/getMaterialListPerPage")
    public ArrayList<Material> getMaterialListPerPage(@RequestParam(value = "offset", defaultValue = "0") Integer offset, @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
            return this.articleMapper.getMaterialListPerPage(offset, limit);
    }
    
    @CrossOrigin(origins = cors)
    @PostMapping("/listToko")
    public ArrayList<Toko> listToko(@RequestBody Toko toko) {
        ArrayList<Toko>  hasil = new ArrayList<>();
        try {
            checkLogin(toko);
            hasil = this.articleMapper.getTokoList();
            
        } catch (Exception ex) {
            printStackTrace(ex);
        }
        
        return hasil;
    }
    
    @CrossOrigin(origins = cors)
    @PostMapping("/getComboToko")
    public ArrayList<Toko> getComboToko(@RequestBody Toko toko) {
        ArrayList<Toko>  hasil = new ArrayList<>();
        try {
            checkLogin(toko);
            hasil = this.articleMapper.getComboToko("%"+toko.getNama()+"%");
            
        } catch (Exception ex) {
            printStackTrace(ex);
        }
        
        return hasil;
    }
    
    @CrossOrigin(origins = cors)
    @PostMapping("/getAutoToko")
    public ArrayList<String> getAutoToko(@RequestBody Toko toko) {
        ArrayList<String>  hasil = new ArrayList<>();
        try {
            checkLogin(toko);
            hasil = this.articleMapper.getAutoToko("%"+toko.getNama()+"%");
            
        } catch (Exception ex) {
            printStackTrace(ex);
        }
        
        return hasil;
    }
    
    @Transactional
    @CrossOrigin(origins = cors)
    @PostMapping("/insertToko")
    public Integer insertToko(@RequestBody Toko toko) {
        TransactionStatus txStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
        Integer hasil = 0;
        try{
            checkLogin(toko);
            Integer dupe = this.articleMapper.checkTokoNama(toko);
            if(null != dupe){
                hasil = -1;
            } else {
                hasil = this.articleMapper.insertToko(toko);
            }
        } catch(Exception e){
            transactionManager.rollback(txStatus);
            printStackTrace(e);
        }
        return hasil;
    }
    
    @Transactional
    @CrossOrigin(origins = cors)
    @PostMapping("/editToko")
    public Integer editToko(@RequestBody Toko toko) {
        TransactionStatus txStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
        Integer hasil = 0;
        try{
            checkLogin(toko);
            hasil = this.articleMapper.editToko(toko);
        } catch(Exception e){
            transactionManager.rollback(txStatus);
            printStackTrace(e);
        }
        return hasil;
    }
    
    @CrossOrigin(origins = cors)
    @PostMapping("/getHarga")
    public ArrayList<Pembelian> getHarga(@RequestBody Pembelian harga) {
        ArrayList<Pembelian>  hasil = new ArrayList<>();
        try {
            checkLogin(harga);
            hasil = this.articleMapper.getHarga();
            
        } catch (Exception ex) {
            printStackTrace(ex);
        }
        return hasil;
    }
    
    @CrossOrigin(origins = cors)
    @PostMapping("/getHargaByMaterialId")
    public ArrayList<Pembelian> getHargaByMaterialId(@RequestBody Pembelian harga) {
        ArrayList<Pembelian>  hasil = new ArrayList<>();
        try {
            checkLogin(harga);
            hasil = this.articleMapper.getHargaByMaterialId(harga);
        } catch (Exception ex) {
            printStackTrace(ex);
        }
        return hasil;
    }
    
    @Transactional
    @CrossOrigin(origins = cors)
    @PostMapping("/inputPembelian")
    public Integer inputPembelian(@RequestBody Pembelian pembelian) {
        TransactionStatus txStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
        Integer hasil = 0;
        Integer hasil1 = 0;
        Integer hasil2 = 0;
        Integer hasil3 = 0;
        try{
            if(checkLogin(pembelian) == 2)
            {
                throw new Exception("asdf");
            }
            pembelian.setId(0L);
            pembelian.setMnama("");
            pembelian.setTnama("");
            hasil3 = this.articleMapper.inputPembelian3(pembelian);
            hasil2 = this.articleMapper.inputStockWithPembelianId(pembelian);
            if(hasil2 != 1 || hasil3 != 1){
                transactionManager.rollback(txStatus);
            }
            
        } catch(Exception e){
            transactionManager.rollback(txStatus);
            printStackTrace(e);
        }
        return hasil;
    }
    @Transactional
    @CrossOrigin(origins = cors)
    @PostMapping("/editPembelian")
    public Integer editPembelian(@RequestBody Pembelian pembelian) {
        TransactionStatus txStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
        Integer hasil = 0;
        Integer hasil1 = 0;
        Integer hasil2 = 0;
        Integer hasil3 = 0;
        try{
            if(checkLogin(pembelian) == 2)
            {
                throw new Exception("asdf");
            }
            pembelian.setMnama("");
            pembelian.setTnama("");
            hasil2 = this.articleMapper.removeStockWithPembelianId(pembelian);
            hasil3 = this.articleMapper.inputPembelian3(pembelian);
            hasil2 = this.articleMapper.inputStockWithPembelianId(pembelian);
            if(hasil2 != 1 || hasil3 != 1){
                transactionManager.rollback(txStatus);
            }
            
        } catch(Exception e){
            transactionManager.rollback(txStatus);
            printStackTrace(e);
        }
        return hasil;
    }
    
    @Transactional
    @CrossOrigin(origins = cors)
    @PostMapping("/insertStock")
    public Integer insertStock(@RequestBody Stock stock) {
        TransactionStatus txStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
        Integer hasil = 0;
        try{
            checkLogin(stock);
            hasil = this.articleMapper.insertStock(stock);
        } catch(Exception e){
            transactionManager.rollback(txStatus);
            printStackTrace(e);
        }
        return hasil;
    }
    
    @CrossOrigin(origins = cors)
    @PostMapping("/getStock")
    public ArrayList<Stock> getStock(@RequestBody Stock stock) {
        ArrayList<Stock>  hasil = new ArrayList<>();
        try {
            checkLogin(stock);
            hasil = this.articleMapper.getStockList();
            
        } catch (Exception ex) {
            printStackTrace(ex);
        }
        
        return hasil;
    }
    
    @CrossOrigin(origins = cors)
    @PostMapping("/getStockHistoryList")
    public ArrayList<Stock> getStockHistoryList(@RequestBody Stock stock) {
        ArrayList<Stock>  hasil = new ArrayList<>();
        try {
            checkLogin(stock);
            hasil = this.articleMapper.getStockHistoryList();
            
        } catch (Exception ex) {
            printStackTrace(ex);
        }
        
        return hasil;
    }
    
    @CrossOrigin(origins = cors)
    @PostMapping("/getStockLatestStockById")
    public Stock getStockLatestStockById(@RequestBody Stock stock) {
        Stock  hasil = new Stock();
        try {
            checkLogin(stock);
            hasil = this.articleMapper.getStockLatestStockById(stock);
            
        } catch (Exception ex) {
            printStackTrace(ex);
        }
        
        return hasil;
    }
    
    @CrossOrigin(origins = cors)
    @PostMapping("/getStockByMaterialId")
    public ArrayList<Stock> getStockByMaterialId(@RequestBody Stock stock) {
        ArrayList<Stock>  hasil = new ArrayList<>();
        try {
            checkLogin(stock);
            hasil = this.articleMapper.getStockHistoryListbyMaterialId(stock);
        } catch (Exception ex) {
            printStackTrace(ex);
        }
        return hasil;
    }
    
    @Transactional
    @CrossOrigin(origins = cors)
    @PostMapping("/doLogin")
    public ArrayList<Iip> doLogin(@RequestBody Ilogin ilogin){
        Iip hasil = generateToken();
        Ilogin login = new Ilogin();
        String role = "";
        
        TransactionStatus txStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try{
            if(null != ilogin.getNama() && !ilogin.getNama().isBlank()
                    && null != ilogin.getPass() && !ilogin.getPass().isBlank()){
                login = this.articleMapper.checklogin(ilogin.getNama(), ilogin.getPass());
            }

            if(null == login.getRole() || login.getRole().isEmpty() || login.getRole().isBlank()){
                hasil.setToken("22");
            } else {
                hasil.setRole(login.getRole());
                this.articleMapper.updateLoginAktif(ilogin);
                this.articleMapper.insertLogin(ilogin, hasil.getToken());
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
    @PostMapping("/doLogout")
    public ArrayList<Ilogin> doLogout(@RequestBody Ilogin ilogin){
        Iip hasil = generateToken();
        Ilogin login = new Ilogin();
        String role = "";
        
        TransactionStatus txStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try{
            if(null != ilogin.getNama() && !ilogin.getNama().isBlank()
                    && null != ilogin.getPass() && !ilogin.getPass().isBlank()){
                login = this.articleMapper.checklogin(ilogin.getNama(), ilogin.getPass());
            }
            
            this.articleMapper.updateLoginAktif(ilogin);

        } catch(Exception e){
            transactionManager.rollback(txStatus);
            printStackTrace(e);
        }
        
        ArrayList ar = new ArrayList();
        ar.add(ilogin);
        
        return ar;
    }
    
    @CrossOrigin(origins = cors)
    @PostMapping("/stillLogin")
    public Iip stillLogin(@RequestBody Iip iip){
        Iip hasil = new Iip();
        
        try{
            if(null == iip){
                throw new Exception("Please login");
            }
            hasil = this.articleMapper.stillLogin(iip.getIp(), iip.getToken());
            
            Calendar cal1 = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(hasil.getTanggal());
            if(ChronoUnit.HOURS.between(cal2.toInstant(),cal1.toInstant())>16){
                this.articleMapper.NonAktifToken(hasil);
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
        } else if (1 == this.articleMapper.stillLogin(result.getIp(), result.getToken()).getIsSuccess()){

            return 1;
        } else {
            throw new Exception("Please login");
        }
    }
    
    public Iip generateToken(){
        SecureRandom sr = new SecureRandom();
        MessageDigest md;
        String token = "";
        Iip hasil = new Iip();
        hasil.setIsSuccess(0);
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

                Integer use = articleMapper.checkUseToken(sb.toString());
                if(null== use){
                    repeat = false;
                }
            }
            
        } catch (NoSuchAlgorithmException ex) {
            java.util.logging.Logger.getLogger(GreetingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        hasil.setToken(sb.toString());
        hasil.setIsSuccess(1);
        
        return hasil;
    }
}