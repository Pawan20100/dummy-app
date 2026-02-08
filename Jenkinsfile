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
		stage('Push Image') {
			agent any
			steps {
				withCredentials([usernamePassword(
					credentialsId: 'dockerhub-creds',
					usernameVariable: 'DOCKER_USER',
					passwordVariable: 'DOCKER_PASS'
				)]) {
					sh '''
					echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin
					docker push $IMAGE
					'''
				}
			}
		}
		stage('Test Kubectl') {
			agent {
				docker {
					image 'bitnami/kubectl:latest'
				}
			}
			steps {
				sh 'kubectl version --client'
			}
		}
    }
}