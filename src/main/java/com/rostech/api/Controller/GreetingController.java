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
//    private final String url = "api/";
    private final String url = "";

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
    @GetMapping(url+"/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
            return new Greeting(1, "Hello World!");
    }
    
    @CrossOrigin(origins = cors)
    @PostMapping(url+"/getLastPrice")
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
    @PostMapping(url+"/listmaterial")
    public ArrayList<Material> listmaterial(@RequestBody Material material) {
        ArrayList<Material>  hasil = new ArrayList<>();
        try {
            checkLogin(material);
            hasil = this.articleMapper.getMaterialList(material.getMnama());

        } catch (Exception ex) {
            printStackTrace(ex);
        }
        
        return   hasil;  
    }
    
    @CrossOrigin(origins = cors)
    @PostMapping(url+"/getAutoMaterial")
    public ArrayList<String> getAutoMaterial(@RequestBody Material material) {
        ArrayList<String>  hasil = new ArrayList<>();
        try {
            checkLogin(material);
            hasil = this.articleMapper.getAutoMaterial("%"+material.getMnama()+"%");
        } catch (Exception ex) {
            printStackTrace(ex);
        }
        
        return hasil;  
    }
    
    @CrossOrigin(origins = cors)
    @PostMapping(url+"/getComboMaterial")
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
    @PostMapping(url+"/insertmaterial")
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
    @PostMapping(url+"/editMaterial")
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
    @GetMapping(url+"/getTotalMaterial")
    public Integer getTotalMaterial() {
            return this.articleMapper.getTotalMaterial();
    }

    @CrossOrigin(origins = cors)
    @GetMapping(url+"/getMaterialListPerPage")
    public ArrayList<Material> getMaterialListPerPage(@RequestParam(value = "offset", defaultValue = "0") Integer offset, @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
            return this.articleMapper.getMaterialListPerPage(offset, limit);
    }
    
    @CrossOrigin(origins = cors)
    @PostMapping(url+"/listToko")
    public ArrayList<Toko> listToko(@RequestBody Toko toko) {
        ArrayList<Toko>  hasil = new ArrayList<>();
        try {
            checkLogin(toko);
            hasil = this.articleMapper.getTokoList(toko.getTnama());
            
        } catch (Exception ex) {
            printStackTrace(ex);
        }
        
        return hasil;
    }
    
    @CrossOrigin(origins = cors)
    @PostMapping(url+"/getComboToko")
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
    @PostMapping(url+"/getAutoToko")
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
    @PostMapping(url+"/insertToko")
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
    @PostMapping(url+"/editToko")
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
    @PostMapping(url+"/getHarga")
    public ArrayList<Pembelian> getHarga(@RequestBody Pembelian harga) {
        ArrayList<Pembelian>  hasil = new ArrayList<>();
        try {
            checkLogin(harga);
            hasil = this.articleMapper.getHarga(harga.getMnama());
            
        } catch (Exception ex) {
            printStackTrace(ex);
        }
        return hasil;
    }
    
    @CrossOrigin(origins = cors)
    @PostMapping(url+"/getPembelianBetweenDate")
    public ArrayList<Pembelian> getPembelianBetweenDate(@RequestBody Pembelian pembelian) {
        ArrayList<Pembelian> hasil = new ArrayList<>();
        try {
            checkLogin(pembelian);
            Calendar cal = Calendar.getInstance();
            cal.setTime(pembelian.getFrom());
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            pembelian.setFrom(cal.getTime());
            cal.setTime(pembelian.getTo());
            cal.set(Calendar.HOUR_OF_DAY, 23);
            cal.set(Calendar.MINUTE, 59);
            cal.set(Calendar.SECOND, 59);
            cal.set(Calendar.MILLISECOND, 999);
            pembelian.setTo(cal.getTime());
            hasil = this.articleMapper.getPembelianBetweenDate(pembelian);
            
        } catch (Exception ex) {
            printStackTrace(ex);
        }
        return hasil;
    }
    
    @CrossOrigin(origins = cors)
    @PostMapping(url+"/getTotalPembelianBetweenDate")
    public ArrayList<Pembelian> getTotalPembelianBetweenDate(@RequestBody Pembelian pembelian) {
        ArrayList<Pembelian> hasil = new ArrayList<>();
        try {
            checkLogin(pembelian);
            Calendar cal = Calendar.getInstance();
            cal.setTime(pembelian.getFrom());
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            pembelian.setFrom(cal.getTime());
            cal.setTime(pembelian.getTo());
            cal.set(Calendar.HOUR_OF_DAY, 23);
            cal.set(Calendar.MINUTE, 59);
            cal.set(Calendar.SECOND, 59);
            cal.set(Calendar.MILLISECOND, 999);
            pembelian.setTo(cal.getTime());
            hasil = this.articleMapper.getTotalPembelianBetweenDate(pembelian);
            
        } catch (Exception ex) {
            printStackTrace(ex);
        }
        return hasil;
    }
    
    @CrossOrigin(origins = cors)
    @PostMapping(url+"/getTotalPembelianCurrentMonth")
    public ArrayList<Pembelian> getTotalPembelianCurrentMonth(@RequestBody Pembelian pembelian) {
        ArrayList<Pembelian> hasil = new ArrayList<>();
        try {
            checkLogin(pembelian);
            Calendar from = Calendar.getInstance();
            from.set(from.get(Calendar.YEAR), from.get(Calendar.MONTH), 1,0,0,0);
            Calendar to = Calendar.getInstance();
            to.set(to.get(Calendar.YEAR), to.get(Calendar.MONTH), to.getActualMaximum(Calendar.DATE),23,59,59);
            pembelian.setFrom(Calendar.getInstance().getTime());
            pembelian.setFrom(from.getTime());
            pembelian.setTo(to.getTime());
            hasil = this.articleMapper.getTotalPembelianBetweenDate(pembelian);
            if(hasil.size()>0){
                hasil.get(0).setFrom(from.getTime());
                hasil.get(0).setTo(to.getTime());
            }
            System.out.println("from : " +from.getTime().toString() + " to : " +to.getTime().toString());
        } catch (Exception ex) {
            printStackTrace(ex);
        }
        return hasil;
    }
    
    @CrossOrigin(origins = cors)
    @PostMapping(url+"/getHargaByMaterialId")
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
    @PostMapping(url+"/inputPembelian")
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
    @PostMapping(url+"/editPembelian")
    public Integer editPembelian(@RequestBody Pembelian pembelian) {
        TransactionStatus txStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
        Integer hasil = 0;
        Integer hasil1 = 0;
        Integer hasil2 = 0;
        Integer hasil3 = 0;
        Integer hasil4 = 0;
        try{
            if(checkLogin(pembelian) == 2)
            {
                throw new Exception("asdf");
            }
            pembelian.setMnama("");
            pembelian.setTnama("");
            System.out.println("1");
            hasil1 = this.articleMapper.removeStockWithPembelianId(pembelian);
            System.out.println("2");
            hasil4 = this.articleMapper.removeStockWithPembelianId(pembelian);
            System.out.println("4");
            hasil3 = this.articleMapper.inputPembelian3(pembelian);
            System.out.println("3");
            hasil2 = this.articleMapper.inputStockWithPembelianId(pembelian);
            if(hasil1 != 1 || hasil2 != 1 || hasil3 != 1){
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
    @PostMapping(url+"/insertStock")
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
    @PostMapping(url+"/getStock")
    public ArrayList<Stock> getStock(@RequestBody Stock stock) {
        ArrayList<Stock>  hasil = new ArrayList<>();
        try {
            checkLogin(stock);
            hasil = this.articleMapper.getStockList(stock.getMnama());
            
        } catch (Exception ex) {
            printStackTrace(ex);
        }
        
        return hasil;
    }
    
    @CrossOrigin(origins = cors)
    @PostMapping(url+"/getStockHistoryList")
    public ArrayList<Stock> getStockHistoryList(@RequestBody Stock stock) {
        ArrayList<Stock>  hasil = new ArrayList<>();
        try {
            checkLogin(stock);
            hasil = this.articleMapper.getStockHistoryList(stock.getMnama());
            
        } catch (Exception ex) {
            printStackTrace(ex);
        }
        
        return hasil;
    }
    
    @CrossOrigin(origins = cors)
    @PostMapping(url+"/getStockLatestStockById")
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
    @PostMapping(url+"/getStockByMaterialId")
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
}