#!/bin/bash

function default_setup() {

  #system update and upgrade
  sudo apt update && sudo apt upgrade -y

  sudo apt install vim

  Bash add_docker.sh

  Bash add_bashrc.sh

  Bash add_javaAndMaven.sh

  install_repo
}

function install_repo() {
  # shellcheck disable=SC2164
  cd ~
  git clone https://github.com/But-Better/RaspberryFirmwareDashboard.git
  # shellcheck disable=SC2164
  cd RaspberryFirmwareDashboard/

  # start web api
  mvn spring-boot:run
}

# shellcheck disable=SC2170
if [ "$1" = "z" ]; then
  Bash add_zephr.sh
else
  default_setup
fi

exit 0
