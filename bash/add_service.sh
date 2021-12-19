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
         WantedBy=multi-user.target' >dashboard.service
}