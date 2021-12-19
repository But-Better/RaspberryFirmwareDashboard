function exportToBashrc() {
  # shellcheck disable=SC2129
  echo 'export MYSQL_DASHBOARD_DB_NAME=dashboard_db' >>~/.bashrc
  # shellcheck disable=SC2016
  echo 'export MYSQL_DASHBOARD_DB_HOST=jdbc:mysql://localhost:3310/${MYSQL_DB_NAME}' >>~/.bashrc
  echo 'export MYSQL_DASHBOARD_DB_PORT=3310' >>~/.bashrc
  echo 'export MYSQL_DASHBOARD_DB_USERNAME=user' >>~/.bashrc
  echo 'export MYSQL_DASHBOARD_DB_PASSWORD=password' >>~/.bashrc

}