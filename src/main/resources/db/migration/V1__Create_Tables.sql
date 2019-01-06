/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  JDM
 * Created: Jan 5, 2019
 */

create table division (
    id INT PRIMARY KEY IDENTITY,
	division_code varchar(5) not null,
	division_name varchar(30) not null
)

create table dept (
    id INT PRIMARY KEY IDENTITY,
	dept_code varchar(10) not null,
	dept_name varchar(30) not null,
	division_code varchar(5) not null
)