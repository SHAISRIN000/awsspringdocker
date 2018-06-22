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

sudo wget http://repos.fedorapeople.org/repos/dchen/apache-maven/epel-apache-maven.repo -O /etc/yum.repos.d/epel-apache-maven.repo
sudo sed -i s/\$releasever/6/g /etc/yum.repos.d/epel-apache-maven.repo
sudo yum install -y apache-maven
mvn --version

yum install -y apache-maven

mvn --version

sudo update-alternatives --config java sudo update-alternatives --config javac

you can use above commands to change java version to java-1.8

yum install -y git

yum install -y docker

service docker start

usermod -a -G docker ec2-user

Close the terminal and restart


cp /home/ec2-user/environment/target/DSALRest-0.0.1-SNAPSHOT.jar .
 docker container -d -name=newsample -p 8082:8080 sshaik/dockersample:latest 
 mv DSALRest-0.0.1-SNAPSHOT.jar  app.jar
 docker build -t sshaik/dockersample .
 docker container run -it -p 8080:8080 sshaik/dockersample:latest


Setting up the AWS access 
https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/setup-credentials.html

AWS credentials on container
docker container run -it -e AWS_ACCESS_KEY_ID=XXX -e AWS_SECRET_ACCESS_KEY=XXX -p 8080:8080 sshaik/dsalv2


localstack installation
On windows the localstack is not working so do this.

cd C:\Users\SOHA\AppData\Local\Programs\Python\Python36-32\Scripts
py localstack start

make install
yum groupinstall "Development Tools"
yum groupinstall "Development Libraries"


pip install --user localstack

install npm without sudo

DEBUG=1 localstack start

