package com.rostech.api.Mapper;

import com.rostech.api.Model.Article;
import com.rostech.api.Model.Iip;
import com.rostech.api.Model.Ilogin;
import com.rostech.api.Model.Material;
import com.rostech.api.Model.Pembelian;
import com.rostech.api.Model.Stock;
import com.rostech.api.Model.Toko;
import java.util.ArrayList;
import org.apache.ibatis.annotations.Delete;
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

//    @Select("SELECT * , 1 as isSuccess FROM `iip` WHERE `status` like 'aktif' and `ip` like #{ip, jdbcType=VARCHAR} and `token` like #{token, jdbcType=VARCHAR} limit 1")
    @Select("SELECT id, ip, status, tanggal, token, tanggal_out , 1 as isSuccess FROM `iip` WHERE `status` like 'aktif' and `token` like trim(#{token, jdbcType=VARCHAR}) limit 1")
    Iip stillLogin(@Param("ip") String ip, @Param("token") String token);

    @Select("select persatuan, CAST(tanggal AS DATETIME) as tanggal from harga where mid = #{mid} order by tanggal desc limit 1;")
    Pembelian getLastPrice(Long mid);
    
    @Insert("INSERT INTO material(mnama, type, ukuran, tanggal, jumlah, total, ket, nama, user_id, group_id) VALUES(#{mnama}, #{type}, #{ukuran}, LOCALTIME(), 0, 0, COALESCE(#{ket},''),#{nama},#{user_id},#{group_id})")
    @Options(useGeneratedKeys = true, keyProperty = "mid")
    Integer insertMaterial(Material material);
    
    @Update("update material set mnama = #{mnama}, type = #{type}, ukuran = #{ukuran}, tanggal = now(), ket = COALESCE(#{ket},'') where mid = #{mid}")
    Integer editMaterial(Material material);

    @Select("SELECT 1 FROM material where TRIM(concat(mnama,' ',type,' ',ukuran)) = TRIM(concat(#{mnama},' ',#{type},' ',#{ukuran})) limit 1")
    Integer checkMaterialName(Material material);

    @Select("select count(mid) from material")
    Integer getTotalMaterial();

    @Select("SELECT *, 1 as isSuccess FROM material where upper(mnama) like upper(#{mnama, jdbcType=VARCHAR}) order by `mnama`")
    ArrayList<Material> getMaterialList(String mnama);

    @Select("SELECT *, 1 as isSuccess FROM material  order by `mnama` limit #{offset}, #{limit}")
    ArrayList<Material> getMaterialListPerPage(Integer offset, Integer limit);

    @Select("SELECT *, 1 as isSuccess FROM stock where id = #{mid} order by tanggal desc limit 1")
    Stock getLatestMaterialById(Long mid);

    @Select("SELECT mid, TRIM(concat(mnama,' ',type,' ',ukuran)) as nama FROM material where upper(`mnama`) like (#{na, jdbcType=VARCHAR}) order by mnama, type, ukuran asc;")
    ArrayList<Material> getComboMaterial(String na);

    @Select("SELECT TRIM(concat(mid,' ',mnama,' ',type,' ',ukuran)) as nama FROM `material` where upper(TRIM(concat(mid,' ',mnama,' ',type,' ',ukuran))) like upper(#{na, jdbcType=VARCHAR}) order by mnama, type, ukuran asc limit 20")
    ArrayList<String> getAutoMaterial(String na);
    
    
    @Select("SELECT 1 FROM toko where TRIM(tnama) like TRIM(#{tnama}) limit 1")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer checkTokoNama(Toko toko);
    
    @Insert("INSERT INTO toko(`tnama`,`contact`,`telpon`,`alamat`,`tanggal`,`ket`,nama,user_id,group_id) VALUES(#{tnama}, #{contact}, #{telpon}, #{alamat}, LOCALTIME(), COALESCE(#{ket},''),#{nama},#{user_id},#{group_id})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer insertToko(Toko toko);
    
    @Insert("update toko set `tnama` = #{tnama},`contact`=#{contact},`telpon`=#{telpon},`alamat`=#{alamat},`tanggal`=now(),`ket`=COALESCE(#{ket},'') where id = #{id}")
    Integer editToko(Toko toko);

    @Select("SELECT *, 1 as isSuccess FROM toko where upper(tnama) like upper(#{tnama, jdbcType=VARCHAR}) order by `tnama`")
    ArrayList<Toko> getTokoList(String tnama);

    @Select("SELECT id, TRIM(concat(tnama,' ',contact,' ',telpon,' ',alamat)) as nama FROM toko where upper(`tnama`) like upper(#{na, jdbcType=VARCHAR}) order by tnama, contact, alamat asc;")
    ArrayList<Toko> getComboToko(String na);

    @Select("SELECT TRIM(concat(id,' ',tnama,' ', contact,' ',telpon,' ',alamat)) as n FROM toko where upper(TRIM(concat(id,' ',tnama,' ', contact,' ',telpon,' ',alamat))) like upper(#{na, jdbcType=VARCHAR}) order by tnama asc, contact asc, alamat asc  limit 20")
    ArrayList<String> getAutoToko(String na);
    
    
    @Select("select h.id, h.mid, h.tid, concat(m.mnama,' ',m.type,' ', m.ukuran) as mnama, concat(t.tnama) as tnama,\n" +
            "h.persatuan, h.jumlah, h.total, h.tanggal, h.ket\n" +
            "from harga h\n" +
            "left join material m on m.mid = h.mid\n" +
            "left join toko t on t.id = h.tid\n" +
            "where upper(`mnama`) like (#{na, jdbcType=VARCHAR})"+
            "order by h.id desc")
    ArrayList<Pembelian> getHarga(String mnama);
    
    @Select("SELECT a.*, trim(concat(b.mnama,' ',b.type,' ',b.ukuran)) as mnama, c.tnama as tnama, 1 as isSuccess FROM `harga` a\n" +
            "left join material b on b.mid = a.mid\n" +
            "left join toko c on c.id = a.tid\n" +
            "WHERE a.mid = #{mid} order by a.tanggal desc")
    ArrayList<Pembelian> getHargaByMaterialId(Pembelian pembelian);
            
    @Insert("select inputPembelian(#{mid, jdbcType=VARCHAR}, #{tid, jdbcType=VARCHAR}, #{persatuan, jdbcType=VARCHAR}, #{jumlah, jdbcType=VARCHAR}, #{total, jdbcType=VARCHAR}, #{tanggal, jdbcType=VARCHAR}, #{ket, jdbcType=VARCHAR})")
    Integer inputPembelian(Pembelian pembelian);
        
    @Insert("select COALESCE(s.total,0) from stock s where s.id = #{mid, jdbcType=VARCHAR} order by s.tanggal desc limit 1")
    Integer inputPembelian1(Pembelian pembelian);
        
    @Insert("insert into stock(mid, perubahan, total, tanggal, ket, pembelianId,nama,user_id,group_id)  values("
            + "#{mid, jdbcType=VARCHAR}, "
            + "#{jumlah, jdbcType=VARCHAR}, "
            + "(#{jumlah, jdbcType=VARCHAR}+(select COALESCE(latestStock(#{mid, jdbcType=VARCHAR}),0))), "
            + "now(), "
            + "#{ket, jdbcType=VARCHAR}, "
            + "#{id},#{nama},#{user_id},#{group_id});")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer inputStockWithPembelianId(Pembelian pembelian);
        
    @Insert("insert into stock(mid, perubahan, total, tanggal, ket, pembelianId, nama, user_id, group_id)  values("
            + "#{mid, jdbcType=VARCHAR}, "
            + "-(select jumlah from harga where id = #{id}), "
            + "((select COALESCE(latestStock(#{mid, jdbcType=VARCHAR}),0))-(select jumlah from harga where id = #{id})), "
            + "now(), "
            + "#{ket, jdbcType=VARCHAR}, "
            + "#{id},"
            + "#{nama},#{user_id},#{group_id});")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer removeStockWithPembelianId(Pembelian pembelian);
    
    @Delete("Delete from harga where id = #{id}")
    Integer removePembelianWithPembelianId(Pembelian pembelian);
        
    @Insert("insert into harga(mid, tid, tanggal, persatuan, jumlah, total, ket,nama,user_id,group_id) values(#{mid}, #{tid}, #{tanggal, jdbcType=VARCHAR}, #{persatuan}, #{jumlah}, #{total}, #{ket, jdbcType=VARCHAR},#{nama},#{user_id},#{group_id})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer inputPembelian3(Pembelian pembelian);
        
    @Insert("insert into stock(mid, perubahan, total, tanggal, ket,nama,user_id,group_id)  values(#{mid, jdbcType=VARCHAR}, #{perubahan, jdbcType=VARCHAR}, (#{perubahan, jdbcType=VARCHAR}+(select COALESCE(latestStock(#{mid, jdbcType=VARCHAR}),0))), now(), #{ket, jdbcType=VARCHAR},#{nama},#{user_id},#{group_id});")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer insertStock(Stock stock);

    @Select("select  a.*, concat(b.mnama,' ',b.type,' ',b.ukuran) as mnama FROM stock a join material b on b.mid = a.mid and upper(mnama) like upper(#{mnama, jdbcType=VARCHAR}) where a.id in (select max(id) from stock group by mid) order by tanggal desc")
    ArrayList<Stock> getStockList(String mnama);

    @Select("SELECT a.*, concat(b.mnama,' ',b.type,' ',b.ukuran) as mnama FROM stock a join material b on b.mid = a.mid and upper(mnama) like upper(#{mnama, jdbcType=VARCHAR}) order by `tanggal` desc, a.id desc")
    ArrayList<Stock> getStockHistoryList(String mnama);

    @Select("SELECT a.*, concat(b.mnama,' ',b.type,' ',b.ukuran) as mnama FROM stock a left join material b on b.mid = a.mid where a.mid = #{mid} order by a.`tanggal` desc, a.id desc")
    ArrayList<Stock> getStockHistoryListbyMaterialId(Stock stock);

    @Select("SELECT a.*, concat(b.mnama,' ',b.type,' ',b.ukuran) as mnama FROM stock a left join material b on b.mid = a.mid where a.mid = #{mid, jdbcType=VARCHAR} order by a.tanggal desc, a.id desc limit 1")
    Stock getStockLatestStockById(Stock stock);
    
    @Select("SELECT a.id, COALESCE(b.mnama,' ',b.type,' ',b.ukuran) as mnama, " +
            "COALESCE(c.tnama,' ',c.contact,' ',c.telpon,' ',c.alamat) as tnama, " +
            "a.tanggal, a.persatuan, a.jumlah, a.total, a.ket " +
            "FROM harga a " +
            "join material b on b.mid = a.mid " +
            "join toko c on c.id = a.tid " +
            "where a.tanggal BETWEEN #{from, jdbcType=TIMESTAMP} and #{to, jdbcType=TIMESTAMP} " +
            "order by a.tanggal asc")
    ArrayList<Pembelian> getPembelianBetweenDate(Pembelian Pembelian);
    
    @Select("SELECT sum(a.total) as total " +
            "FROM harga a " +
            "where a.tanggal BETWEEN #{from, jdbcType=TIMESTAMP} and #{to, jdbcType=TIMESTAMP} ")
    ArrayList<Pembelian> getTotalPembelianBetweenDate(Pembelian Pembelian);
        
}