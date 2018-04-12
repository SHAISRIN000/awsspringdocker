# awsspringdocker

Testing
Welcome to the Testing wiki!

docker build -t myspringboot:latest .

docker container run -it -p 8081:8081 myspringboot:latest

docker-machine env - Get the IP address from this

http://192.168.99.100:8081/property

http://containertutorials.com/docker-compose/spring-boot-app.html

Installation instructions on EC2

yum install -y java-1.8.0-openjdk-devel

yum remove java-1.7.0-openjdk

wget http://repos.fedorapeople.org/repos/dchen/apache-maven/epel-apache-maven.repo -O /etc/yum.repos.d/epel-apache-maven.repo

sed -i s/$releasever/6/g /etc/yum.repos.d/epel-apache-maven.repo

yum install -y apache-maven

mvn --version

sudo update-alternatives --config java sudo update-alternatives --config javac

you can use above commands to change java version to java-1.8

yum install -y git

yum install -y docker

service docker start

usermod -a -G docker ec2-user

Close the terminal and restart
