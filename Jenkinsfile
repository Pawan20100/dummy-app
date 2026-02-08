pipeline {
  agent any

  environment {
    IMAGE = "codekar/dummy-app:${BUILD_NUMBER}"
  }

  stages {

    stage('Build Jar') {
      steps {
        sh 'mvn clean package'
      }
    }

    stage('Build Image') {
      steps {
        sh 'docker build -t $IMAGE .'
      }
    }

    stage('Push Image') {
      steps {
        withCredentials([usernamePassword(
          credentialsId: 'dockerhub-creds',
          usernameVariable: 'codekar',
          passwordVariable: 'DOCKER@Codekar'
        )]) {
          sh '''
          echo $PASS | docker login -u $USER --password-stdin
          docker push $IMAGE
          '''
        }
      }
    }

    stage('Deploy to Kubernetes') {
      steps {
        sh '''
        kubectl set image deployment/dummy-app \
        dummy-app=$IMAGE
        '''
      }
    }
  }
}