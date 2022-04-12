/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rostech.api.Mapper;

import com.rostech.api.Model.Cucian;
import java.util.ArrayList;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

/**
 *
 * @author PC-Rumah
 */
@Mapper
public interface CucianMapper {

    @Insert("INSERT INTO `rostech`.`cucian`\n" +
            "(`tanggal`,`noPlat`,`pencuci`,`typeCucian`,`harga`,`status`,`ket`,`user_id`,`group_id`)\n" +
            "VALUES\n" +
            "(#{tanggal},#{noPlat},#{pencuci},#{typeCucian},#{harga},#{status},#{ket},#{user_id},#{group_id});")
    @Options(useGeneratedKeys=true, keyProperty="id")
    Integer insertCucian(Cucian cucian);

    @Update("UPDATE `rostech`.`cucian`\n" +
            "SET `noPlat` = #{noPlat},`pencuci` = #{pencuci},"+
            "`typeCucian` = #{typeCucian},`harga` = #{harga},`status` = #{status},`ket` = #{ket},"+
            "`user_id` = #{user_id},`group_id` = #{group_id}\n" +
            "WHERE `id` = #{id}")
    Integer updateCucian(Cucian cucian);
    
    @Select("SELECT `id`, `tanggal`,`noPlat`,`pencuci`,`typeCucian`,`harga`,`status`,`ket`,`user_id`,`group_id` "
            + "from `rostech`.`cucian` WHERE  `tanggal` BETWEEN #{from} and #{to} order by `id` asc;")
    ArrayList<Cucian> getCucianOnDate(Cucian cucian);
    
}
