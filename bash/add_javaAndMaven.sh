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