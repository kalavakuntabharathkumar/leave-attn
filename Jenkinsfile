pipeline {
 agent any
 stages {
  stage('Test'){steps{bat 'mvn test'}}
  stage('Package'){steps{bat 'mvn package -DskipTests'}}
  stage('Docker'){steps{bat 'docker build -t employee-leave-attendance .'}}
 }
}
