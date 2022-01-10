#!/bin/bash

#this file must be made executable (chmod)
#run with sudo ./filename

cd /etc/ssh

currentDir=$(pwd)
echo "moved into $currentDir"

echo "modifying the sshd_config file"

#setting all the algorithms:
echo "#These are the added Configuration lines:" >> sshd_config
echo "ciphers aes256-ctr" >> sshd_config
echo "KexAlgorithms curve25519-sha256" >> sshd_config
echo "HostkeyAlgorithms ssh-ed25519" >> sshd_config
echo "MACs hmac-sha2-512" >> sshd_config
echo "PubkeyAcceptedKeyTypes ssh-ed25519" >> sshd_config

#removing password login
sed -i 's/PasswordAuthentication yes/PasswordAuthentication no/1' sshd_config

#restart ssh
systemctl restart ssh
