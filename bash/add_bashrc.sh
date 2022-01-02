#!/bin/bash

function exportToBashrc() {
  # shellcheck disable=SC2129
  echo 'export MYSQL_DASHBOARD_DB_NAME=dashboard_db' >>~/.bashrc
  # shellcheck disable=SC2016
  echo 'export MYSQL_DASHBOARD_DB_HOST=jdbc:mysql://localhost:3306/${MYSQL_DB_NAME}' >>~/.bashrc
  echo 'export MYSQL_DASHBOARD_DB_PORT=3306' >>~/.bashrc
  echo 'export MYSQL_DASHBOARD_DB_USERNAME=root' >>~/.bashrc
  echo 'export MYSQL_DASHBOARD_DB_PASSWORD=password' >>~/.bashrc

}
exportToBashrc