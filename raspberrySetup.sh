#!/bin/bash

function default_setup(){

 #system update and upgrade
 sudo apt update -f && sudo apt upgrade 

 sudo apt install vim -f

 javaAndMaven

 install_repo 

}

function javaAndMaven(){
 #Java installation
 sudo apt install default-jdk -f
 java --version

 sudo apt install maven -f
 mvn --version

 mvn install

 mvn compiler:compile

 sudo apt-get install apache2 -f
}

function install_repo(){
 # shellcheck disable=SC2164
 cd /home/pi/Documents/
 git clone https://github.com/But-Better/RaspberryFirmwareDashboard.git
 # shellcheck disable=SC2164
 cd RaspberryFirmwareDashboard/
 
 # start web api
 mvn spring-boot:run
}

default_setup

exit 0

