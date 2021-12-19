#!/bin/bash

function default_setup() {

  #system update and upgrade
  sudo apt update && sudo apt upgrade -y

  sudo apt install vim

  docker

  exportToBashrc

  javaAndMaven

  install_repo

  zephyr
}

function install_repo() {
  # shellcheck disable=SC2164
  cd /home/pi/Documents/
  git clone https://github.com/But-Better/RaspberryFirmwareDashboard.git
  # shellcheck disable=SC2164
  cd RaspberryFirmwareDashboard/

  # start web api
  mvn spring-boot:run
}

# shellcheck disable=SC2170
if [ "$1" = "z" ]; then
  zephyr
else
  default_setup
fi

exit 0
