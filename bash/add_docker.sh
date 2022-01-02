#!/bin/bash

function docker() {
  sudo snap install docker
  docker --version
}

docker