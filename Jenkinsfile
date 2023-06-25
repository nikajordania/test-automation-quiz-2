pipeline {
  agent any
  stages {
    stage('Checkout') {
      steps {
        git(branch: 'master', url: 'https://github.com/nikajordania/test-automation-quiz-2.git')
      }
    }

    stage('Run Maven Project') {
      parallel {
        stage('Run Maven Project') {
          steps {
            bat 'mvn clean test'
          }
        }

        stage('Get Maven Version') {
          steps {
            bat 'mvn --version'
          }
        }

      }
    }

  }
}