/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rostech.api.Mapper;

import com.rostech.api.Model.Iip;
import com.rostech.api.Model.Ilogin;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 *
 * @author PC-Rumah
 */
@Mapper
public interface LoginMapper {
    
//    select checkLogin('itest','fec2b8ace11d2e8115a9cb8ed56f6ec8')
    @Select("SELECT COALESCE(checkLogin(#{var1}, #{var2}),0) as role")
    Ilogin checklogin(@Param("var1") String var1, @Param("var2") String var2);
    
    @Select("SELECT nama, user_id, group_id  FROM ilogin WHERE nama LIKE #{var1} AND pass = #{var2} AND STATUS = 'aktif'")
    Iip getUserData(@Param("var1") String var1, @Param("var2") String var2);

//    @Select("SELECT * , 1 as isSuccess FROM `iip` WHERE `status` like 'aktif' and `ip` like #{ip, jdbcType=VARCHAR} and `token` like #{token, jdbcType=VARCHAR} limit 1")
    @Select("SELECT id, ip, status, tanggal, token, tanggal_out , 1 as isSuccess FROM `iip` WHERE `status` like 'aktif' and `token` like trim(#{token, jdbcType=VARCHAR}) limit 1")
    Iip stillLogin(@Param("ip") String ip, @Param("token") String token);

    @Select("SELECT #{token} FROM iip limit 1")
    Iip todoLogin(@Param("token") String token, @Param("nama") String nama, @Param("pass") String pass);
    
    @Insert("INSERT INTO `iip` (`ip`, `status`, `tanggal`, `token`, `nama`) VALUES (#{ilogin.notes, jdbcType=VARCHAR}, 'aktif', LOCALTIME(), #{token, jdbcType=VARCHAR}, #{ilogin.nama, jdbcType=VARCHAR})")
    Integer insertLogin(Ilogin ilogin, String token);
       
    @Select("select 1 from iip where status like 'aktif' and token like #{token, jdbcType=VARCHAR}")
    Integer checkUseToken(String token);
    
    @Update("UPDATE iip a set a.status = 'nonaktif', a.tanggal_out = now() where a.nama like #{nama, jdbcType=VARCHAR} and a.ip = #{notes, jdbcType=VARCHAR} and a.status = 'aktif'")
    Integer updateLoginAktif(Ilogin ilogin);
    
    @Update("UPDATE iip set status = 'nonaktif', tanggal_out = now() where `token` = #{token, jdbcType=VARCHAR} and `status` = 'aktif'")
    Integer NonAktifToken(Iip iip);
    
}
