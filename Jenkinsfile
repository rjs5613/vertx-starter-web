pipeline {
  agent { label 'agent2' }
  stages {
    stage('Run Test') {
      steps {
        sh "ls -la"
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
