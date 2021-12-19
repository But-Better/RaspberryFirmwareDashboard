#!/bin/bash

function default_setup() {

  #system update and upgrade
  sudo apt update && sudo apt upgrade -y

  sudo apt install vim

  javaAndMaven

  install_repo

  exportToBashrc

  zephyr
}

function exportToBashrc() {
  # shellcheck disable=SC2129
  echo 'export MYSQL_DB_NAME=dashboard_db' >>~/.bashrc
  # shellcheck disable=SC2016
  echo 'export MYSQL_DB_HOST=jdbc:mysql://localhost:3310/${MYSQL_DB_NAME}' >>~/.bashrc
  echo 'export MYSQL_DB_PORT=3306' >>~/.bashrc
  echo 'export MYSQL_DB_USERNAME=user' >>~/.bashrc
  echo 'export MYSQL_DB_PASSWORD=password' >>~/.bashrc

}

function zephyr() {
  wget https://apt.kitware.com/kitware-archive.sh
  sudo bash kitware-archive.sh

  sudo apt install --no-install-recommends git cmake ninja-build gperf \
    ccache dfu-util device-tree-compiler wget \
    python3-dev python3-pip python3-setuptools python3-tk python3-wheel xz-utils file \
    make gcc gcc-multilib g++-multilib libsdl2-dev

  cmake --version
  python3 --version
  dtc --version

  pip3 install --user -U west
  echo 'export PATH=~/.local/bin:"$PATH"' >>~/.bashrc
  # shellcheck disable=SC1090
  source ~/.bashrc

  west init ~/zephyrproject
  # shellcheck disable=SC2164
  cd ~/zephyrproject
  west update

  west zephyr-export
  pip3 install --user -r ~/zephyrproject/zephyr/scripts/requirements.txt

  # shellcheck disable=SC2164
  cd ~
  wget https://github.com/zephyrproject-rtos/sdk-ng/releases/download/v0.13.1/zephyr-sdk-0.13.1-linux-x86_64-setup.run

  sudo chmod +x zephyr-sdk-0.13.1-linux-x86_64-setup.run

  ./zephyr-sdk-0.13.1-linux-x86_64-setup.run -- -d ~/zephyr-sdk-0.13.1

  sudo cp ~/zephyr-sdk-0.13.1/sysroots/x86_64-pokysdk-linux/usr/share/openocd/contrib/60-openocd.rules /etc/udev/rules.d
  sudo udevadm control --reload
}

function javaAndMaven() {
  #Java installation
  sudo apt install default-jdk -f
  java --version

  sudo apt install maven -f
  mvn --version

  mvn install

  mvn compiler:compile

  sudo apt-get install apache2 -f
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

function install_service() {
  #Create a service after restart or reboot exec ./start.sh

  # shellcheck disable=SC2164
  cd /etc/systemd/system/
  touch dashboard.service

  echo '[Unit]
         Description=RaspberryFirmwareDashboard
         After=network.target
         StartLimitIntervalSec=0
         [Service]
         Type=simple
         Restart=always
         RestartSec=1
         User=spring
         ExecStart=~/RaspberryFirmwareDashboard/./start.sh

         [Install]
         WantedBy=multi-user.target' > dashboard.service
}

# shellcheck disable=SC2170
if [ "$1" = "z" ]; then
  zephyr
else
  default_setup
fi

exit 0
