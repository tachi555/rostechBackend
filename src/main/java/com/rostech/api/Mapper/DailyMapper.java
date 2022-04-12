/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rostech.api.Mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.rostech.api.Model.Daily;
import com.rostech.api.Model.Ilogin;
import com.rostech.api.Model.Pembelian;

/**
 *
 * @author PC-Rumah
 */
@Mapper
public interface DailyMapper {
    
	
    @Insert("INSERT INTO `rostech`.`daily_cucian` (`tanggal`, `mobilBesar`, `mobilKecil`, `bodyBesar`, `bodyKecil`, "
    		+ "`karpet`, `upahCuci`, `upahKarpet`, `beliNasi`, `jasaRos`, `jasaAlex`, `sparepart`, "
    		+ "`minuman`, `sewa`, `cicilan`, `lain`, `ket`, `pendapatan`, `pengeluaran`, `total`, `pendapatanCucian`, "
    		+ "`pengeluaranCucian`, `pendapatanTekuk`, `pendapatanPlasma`, `pendapatanLas`, `pendapatanBubut`, `user_id`, `group_id`, `penambahan`) "
    		+ "VALUES (#{tanggal}, #{mobilBesar}, #{mobilKecil}, #{bodyBesar}, #{bodyKecil}, "
    		+ "#{karpet}, #{upahCuci}, #{upahKarpet}, #{beliNasi}, #{jasaRos}, #{jasaAlex}, #{sparepart}, "
    		+ "#{minuman}, #{sewa}, #{cicilan}, #{lain}, #{ket}, #{pendapatan}, #{pengeluaran}, #{total}, #{pendapatanCucian}, "
    		+ "#{pengeluaranCucian}, #{pendapatanTekuk}, #{pendapatanPlasma}, #{pendapatanLas}, #{pendapatanBubut}, #{user_id}, #{group_id}, #{penambahan});")
    Integer insertDaily(Daily daily);

    @Update("UPDATE `rostech`.`daily_cucian`  set `mobilBesar` = #{mobilBesar}, `mobilKecil` = #{mobilKecil}, `bodyBesar` = #{bodyBesar}, `bodyKecil` = #{bodyKecil}, "
    		+ "`karpet` = #{karpet}, `upahCuci` = #{upahCuci}, `upahKarpet` = #{upahKarpet}, `beliNasi` = #{beliNasi}, `jasaRos` = #{jasaRos}, `jasaAlex` = #{jasaAlex}, `sparepart` = #{sparepart}, "
    		+ "`minuman` = #{minuman}, `sewa` = #{sewa}, `cicilan` = #{cicilan}, `lain` = #{lain}, `ket` = #{ket}, `pendapatan` = #{pendapatan}, `pengeluaran` = #{pengeluaran}, `total` = #{total}, "
    		+ "`pendapatanCucian` = #{pendapatanCucian}, `pengeluaranCucian` = #{pengeluaranCucian}, `pendapatanTekuk` = #{pendapatanTekuk}, `pendapatanPlasma` = #{pendapatanPlasma}, "
    		+ "`user_id` = #{user_id}, `group_id` = #{group_id}, "
    		+ "`pendapatanLas` = #{pendapatanLas}, `pendapatanBubut` = #{pendapatanBubut}, `penambahan` = #{penambahan} "
    		+ "where `tanggal` between #{from} and #{to}"
    		+ ";")
    Integer updateDaily(Daily daily);
    
    @Select("select 1 from daily_cucian where tanggal between #{from} and #{to}")
    Integer checkDaily(Daily daily);
    
    @Select("SELECT `id`, `tanggal`, `mobilBesar`, `mobilKecil`, `bodyBesar`, `bodyKecil`, `karpet`, `upahCuci`, `upahKarpet`, `beliNasi`, `jasaRos`, `jasaAlex`, `sparepart`, `minuman`, `sewa`, "
    		+ "`cicilan`, `lain`, `ket`, `pendapatan`, `pengeluaran`, `total`, `pendapatanCucian`, `pengeluaranCucian`, `pendapatanTekuk`, `pendapatanPlasma`, `pendapatanLas`, `pendapatanBubut`, "
    		+ "`user_id`, `group_id`, `penambahan` FROM `rostech`.`daily_cucian` "
    		+ "WHERE  `tanggal` BETWEEN #{from} and #{to} order by `tanggal` asc;")
    ArrayList<Daily> getDailyBetweenDate(Daily daily);
    
    @Select("SELECT `id`, `tanggal`, `mobilBesar`, `mobilKecil`, `bodyBesar`, `bodyKecil`, `karpet`, `upahCuci`, `upahKarpet`, `beliNasi`, `jasaRos`, `jasaAlex`, `sparepart`, `minuman`, `sewa`, "
    		+ "`cicilan`, `lain`, `ket`, `pendapatan`, `pengeluaran`, `total`, `pendapatanCucian`, `pengeluaranCucian`, `pendapatanTekuk`, `pendapatanPlasma`, `pendapatanLas`, `pendapatanBubut`, "
    		+ "`user_id`, `group_id`, `penambahan` FROM `rostech`.`daily_cucian` "
    		+ "WHERE  `tanggal` BETWEEN #{from} and #{to};")
    ArrayList<Daily> getDailyOnDate(Daily daily);
	
}
