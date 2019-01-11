/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  JDM
 * Created: Jan 11, 2019
 */

alter table Division
add created_by date;

alter table Dept
add created_by date;

alter table Division
drop column created_by

alter table Dept
drop column created_by ;

alter table Division
add created_date date;

alter table Dept
add created_date date;

