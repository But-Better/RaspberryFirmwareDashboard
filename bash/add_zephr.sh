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