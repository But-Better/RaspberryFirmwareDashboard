#!/bin/bash

# shellcheck disable=SC2164
cd ~/Documents/RaspberryFirmwareDashboard
git pull

mvn spring-boot:run
