package com.rostech.api.Mapper;

import com.rostech.api.Model.Article;
import com.rostech.api.Model.Iip;
import com.rostech.api.Model.Ilogin;
import com.rostech.api.Model.Material;
import com.rostech.api.Model.Pembelian;
import com.rostech.api.Model.Stock;
import com.rostech.api.Model.Toko;
import java.util.ArrayList;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ArticleMapper {
    @Select("SELECT * FROM ARTICLES WHERE id = #{id}")
    Article getArticle(@Param("id") Long id);
    
//    select checkLogin('itest','fec2b8ace11d2e8115a9cb8ed56f6ec8')
    @Select("SELECT checkLogin(#{var1}, #{var2}) as role")
    Ilogin checklogin(@Param("var1") String var1, @Param("var2") String var2);

//    @Select("SELECT * , 1 as isSuccess FROM `iip` WHERE `status` like 'aktif' and `ip` like #{ip, jdbcType=VARCHAR} and `token` like #{token, jdbcType=VARCHAR} limit 1")
    @Select("SELECT * , 1 as isSuccess FROM `iip` WHERE `status` like 'aktif' and `token` like trim(#{token, jdbcType=VARCHAR}) limit 1")
    Iip stillLogin(@Param("ip") String ip, @Param("token") String token);

    @Select("SELECT #{token} FROM iip limit 1")
    Iip todoLogin(@Param("token") String token, @Param("nama") String nama, @Param("pass") String pass);

    @Select("select persatuan, CAST(tanggal AS DATETIME) as tanggal from harga where mid = #{mid} order by tanggal desc limit 1;")
    Pembelian getLastPrice(Long mid);
    
    @Insert("INSERT INTO material(nama, type, ukuran, tanggal, jumlah, total, ket) VALUES(#{nama}, #{type}, #{ukuran}, LOCALTIME(), 0, 0, COALESCE(#{ket},''))")
    @Options(useGeneratedKeys = true, keyProperty = "mid")
    Integer insertMaterial(Material material);
    
    @Update("update material set nama = #{nama}, type = #{type}, ukuran = #{ukuran}, tanggal = now(), ket = COALESCE(#{ket},'') where mid = #{mid}")
    Integer editMaterial(Material material);

    @Select("SELECT 1 FROM material where TRIM(concat(nama,' ',type,' ',ukuran)) = TRIM(concat(#{nama},' ',#{type},' ',#{ukuran})) limit 1")
    Integer checkMaterialName(Material material);

    @Select("select count(mid) from material")
    Integer getTotalMaterial();

    @Select("SELECT *, 1 as isSuccess FROM material  order by `nama`")
    ArrayList<Material> getMaterialList();

    @Select("SELECT *, 1 as isSuccess FROM material  order by `nama` limit #{offset}, #{limit}")
    ArrayList<Material> getMaterialListPerPage(Integer offset, Integer limit);

    @Select("SELECT *, 1 as isSuccess FROM stock where id = #{mid} order by tanggal desc limit 1")
    Stock getLatestMaterialById(Long mid);

    @Select("SELECT mid, TRIM(concat(nama,' ',type,' ',ukuran)) as nama FROM material where upper(`nama`) like (#{na, jdbcType=VARCHAR}) order by nama, type, ukuran asc;")
    ArrayList<Material> getComboMaterial(String na);

    @Select("SELECT TRIM(concat(mid,' ',nama,' ',type,' ',ukuran)) as n FROM `material` where upper(TRIM(concat(mid,' ',nama,' ',type,' ',ukuran))) like upper(#{na, jdbcType=VARCHAR}) order by nama, type, ukuran asc limit 20")
    ArrayList<String> getAutoMaterial(String na);
    
    
    @Select("SELECT 1 FROM toko where TRIM(nama) like TRIM(#{nama}) limit 1")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer checkTokoNama(Toko toko);
    
    @Insert("INSERT INTO toko(`nama`,`contact`,`telpon`,`alamat`,`tanggal`,`ket`) VALUES(#{nama}, #{contact}, #{telpon}, #{alamat}, LOCALTIME(), COALESCE(#{ket},''))")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer insertToko(Toko toko);
    
    @Insert("update toko set `nama` = #{nama},`contact`=#{contact},`telpon`=#{telpon},`alamat`=#{alamat},`tanggal`=now(),`ket`=COALESCE(#{ket},'') where id = #{id}")
    Integer editToko(Toko toko);

    @Select("SELECT *, 1 as isSuccess FROM toko order by `nama`")
    ArrayList<Toko> getTokoList();

    @Select("SELECT id, TRIM(concat(nama,' ',contact,' ',telpon,' ',alamat)) as nama FROM toko where upper(`nama`) like upper(#{na, jdbcType=VARCHAR}) order by nama, contact, alamat asc;")
    ArrayList<Toko> getComboToko(String na);

    @Select("SELECT TRIM(concat(id,' ',nama,' ', contact,' ',telpon,' ',alamat)) as n FROM toko where upper(TRIM(concat(id,' ',nama,' ', contact,' ',telpon,' ',alamat))) like upper(#{na, jdbcType=VARCHAR}) order by nama asc, contact asc, alamat asc  limit 20")
    ArrayList<String> getAutoToko(String na);
    
    
    @Select("select h.id, h.mid, h.tid, concat(m.nama,' ',m.type,' ', m.ukuran) as mnama, concat(t.nama) as tnama,\n" +
            "h.persatuan, h.jumlah, h.total, h.tanggal, h.ket\n" +
            "from harga h\n" +
            "left join material m on m.mid = h.mid\n" +
            "left join toko t on t.id = h.tid\n" +
            "order by h.id desc")
    ArrayList<Pembelian> getHarga();
    
    @Select("SELECT a.*, trim(concat(b.nama,' ',b.type,' ',b.ukuran)) as mnama, c.nama as tnama, 1 as isSuccess FROM `harga` a\n" +
            "left join material b on b.mid = a.mid\n" +
            "left join toko c on c.id = a.tid\n" +
            "WHERE a.mid = #{mid} order by a.tanggal desc")
    ArrayList<Pembelian> getHargaByMaterialId(Pembelian pembelian);
    
    
    @Update("UPDATE iip set status = 'nonaktif', tanggal_out = now() where `ip` = #{notes, jdbcType=VARCHAR} and `status` = 'aktif'")
    Integer updateLoginAktif(Ilogin ilogin);
    
    @Update("UPDATE iip set status = 'nonaktif', tanggal_out = now() where `token` = #{token, jdbcType=VARCHAR} and `status` = 'aktif'")
    Integer NonAktifToken(Iip iip);
       
    @Select("select 1 from iip where status like 'aktif' and token like #{token, jdbcType=VARCHAR}")
    Integer checkUseToken(String token);
    
    @Insert("INSERT INTO `iip` (`ip`, `status`, `tanggal`, `token`) VALUES (#{ilogin.notes, jdbcType=VARCHAR}, 'aktif', LOCALTIME(), #{token, jdbcType=VARCHAR})")
    Integer insertLogin(Ilogin ilogin, String token);
        
    @Insert("select inputPembelian(#{mid, jdbcType=VARCHAR}, #{tid, jdbcType=VARCHAR}, #{persatuan, jdbcType=VARCHAR}, #{jumlah, jdbcType=VARCHAR}, #{total, jdbcType=VARCHAR}, #{tanggal, jdbcType=VARCHAR}, #{ket, jdbcType=VARCHAR})")
    Integer inputPembelian(Pembelian pembelian);
        
    @Insert("select COALESCE(s.total,0) from stock s where s.id = #{mid, jdbcType=VARCHAR} order by s.tanggal desc limit 1")
    Integer inputPembelian1(Pembelian pembelian);
        
    @Insert("insert into stock(mid, perubahan, total, tanggal, ket, pembelianId)  values("
            + "#{mid, jdbcType=VARCHAR}, "
            + "#{jumlah, jdbcType=VARCHAR}, "
            + "(#{jumlah, jdbcType=VARCHAR}+(select COALESCE(latestStock(#{mid, jdbcType=VARCHAR}),0))), "
            + "now(), "
            + "#{ket, jdbcType=VARCHAR}, "
            + "#{id});")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer inputStockWithPembelianId(Pembelian pembelian);
        
    @Insert("insert into stock(mid, perubahan, total, tanggal, ket, pembelianId)  values("
            + "#{mid, jdbcType=VARCHAR}, "
            + "-(select jumlah from harga where id = #{id}), "
            + "((select COALESCE(latestStock(#{mid, jdbcType=VARCHAR}),0))-(select jumlah from harga where id = #{id})), "
            + "now(), "
            + "#{ket, jdbcType=VARCHAR}, "
            + "#{id});")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer removeStockWithPembelianId(Pembelian pembelian);
        
    @Insert("insert into harga(mid, tid, tanggal, persatuan, jumlah, total, ket) values(#{mid}, #{tid}, #{tanggal, jdbcType=VARCHAR}, #{persatuan}, #{jumlah}, #{total}, #{ket, jdbcType=VARCHAR})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer inputPembelian3(Pembelian pembelian);
        
    @Insert("insert into stock(mid, perubahan, total, tanggal, ket)  values(#{mid, jdbcType=VARCHAR}, #{perubahan, jdbcType=VARCHAR}, (#{perubahan, jdbcType=VARCHAR}+(select COALESCE(latestStock(#{mid, jdbcType=VARCHAR}),0))), now(), #{ket, jdbcType=VARCHAR});")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer insertStock(Stock stock);

    @Select("select  a.*, concat(b.nama,' ',b.type,' ',b.ukuran) as mnama FROM stock a left join material b on b.mid = a.mid where a.id in (select max(id) from stock group by mid) order by tanggal desc")
    ArrayList<Stock> getStockList();

    @Select("SELECT a.*, concat(b.nama,' ',b.type,' ',b.ukuran) as mnama FROM stock a left join material b on b.mid = a.mid order by `tanggal` desc")
    ArrayList<Stock> getStockHistoryList();

    @Select("SELECT a.*, concat(b.nama,' ',b.type,' ',b.ukuran) as mnama FROM stock a left join material b on b.mid = a.mid where a.mid = #{mid} order by a.`tanggal` desc, a.id desc")
    ArrayList<Stock> getStockHistoryListbyMaterialId(Stock stock);

    @Select("SELECT a.*, concat(b.nama,' ',b.type,' ',b.ukuran) as mnama FROM stock a left join material b on b.mid = a.mid where a.mid = #{mid, jdbcType=VARCHAR} order by a.tanggal desc, a.id desc limit 1")
    Stock getStockLatestStockById(Stock stock);
        
}