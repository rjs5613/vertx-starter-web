pipeline {
  agent { label 'master' }
  stages {
    stage('Checkout Code') { // Get code
      steps {
        git 'https://github.com/rjs5613/vertx-starter-web'
      }
    }
    stage('Run Test') {
      steps {
        sh "./gradlew test"
      }
    }
    stage('Build') {
      steps {
        sh "./gradlew build -x test"
      }
    }
    stage('Build Docker') {
      steps {
        print "Implement Docker Build"
      }
    }
    stage('Push Docker') {
      steps {
        print "Push Docker Build"
      }
    }
  }
}
