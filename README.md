# RaspberryFirmwareDashboard

it's a DASHBOARD

```bash

#For loading and properly executing the Configuration File follow these steps:

#First connection to pi:

  ssh -o HostkeyAlgorithms=ssh-ed25519 ubuntu@IPofPi
  >yes
  >ubuntu (is the default password)
  >enter new password of your choice

#Create a pair of keys:

  ssh-keygen -t ed25519
  >no location
  >no passphrase
  keys will be found in ~/.ssh/id_ed25519 and  ~/.ssh/id_ed25519.pub

#Copy the public key to the Raspberry Pi:

  ssh-copy-id -i ~/.ssh/id_ed25519.pub ubuntu@IPofPi
  >enter the new password you chose earlier
  
#Copy the config file to the pi  

  cd <directoryOfConfigFile>
  sftp ubuntu@IPofPi
  >put piConfiguratorFile.sh
  
#Log into the Pi and execute the script
ssh ubuntu@IPofPi
sudo ./piConfiguratorFile.sh

```
