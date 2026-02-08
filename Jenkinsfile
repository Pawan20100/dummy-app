pipeline {
    agent none

    environment {
        IMAGE = "codekar/dummy-app:${BUILD_NUMBER}"
    }

    stages {

        stage('Checkout') {
            agent any
            steps {
                checkout scm
            }
        }

        stage('Build Jar') {
            agent {
                docker {
                    image 'maven:3.9.9-eclipse-temurin-17'
                }
            }
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Build Image') {
            agent any
            steps {
                sh 'docker build -t $IMAGE .'
            }
        }
    }
}