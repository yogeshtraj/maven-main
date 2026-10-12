
pipeline {
    agent any

    tools {
        maven 'MAVEN_HOME'
    }

    stages {
        stage('Git Repository & Clean') {
            steps {
                bat 'if exist maven-main rmdir /s /q maven-main'
                bat 'git clone https://github.com/yogeshtraj/maven-main.git'
                bat 'mvn clean -f maven-main'
            }
        }

        stage('Install') {
            steps {
                bat 'mvn install -f maven-main'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test -f maven-main'
            }
        }

        stage('Package') {
            steps {
                bat 'mvn package -f maven-main'
            }
        }

        stage('Build Docker Image') {
            steps {
                bat 'docker version'
                bat 'docker build -t yogeshrajt/maven-main:%BUILD_NUMBER% -t yogeshrajt/maven-main:latest .'
            }
        }

        stage('Push Docker Image to Docker Hub') {
            steps {
                withCredentials([
                    usernamePassword(
                        credentialsId: 'dockerhub-credentials',
                        usernameVariable: 'DOCKERHUB_USER',
                        passwordVariable: 'DOCKERHUB_TOKEN'
                    )
                ]) {
                    bat '''
                        @echo off
                        powershell -NoProfile -ExecutionPolicy Bypass -Command "$env:DOCKERHUB_TOKEN | docker login --username $env:DOCKERHUB_USER --password-stdin"
                    '''

                    bat 'docker push yogeshrajt/maven-main:%BUILD_NUMBER%'
                    bat 'docker push yogeshrajt/maven-main:latest'
                }
            }
        }
    }

    post {
        success {
            emailext(
                to: 'yogeshtraj@gmail.com',
                from: 'yogeshtraj@gmail.com',
                replyTo: 'yogeshtraj@gmail.com',
                subject: "SUCCESS: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """The Jenkins CI/CD pipeline completed successfully.

Job: ${env.JOB_NAME}
Build Number: ${env.BUILD_NUMBER}
Docker Image: yogeshrajt/maven-main:${env.BUILD_NUMBER}
Docker Image: yogeshrajt/maven-main:latest
Build URL: ${env.BUILD_URL}

Maven build, tests, packaging, Docker image creation
and Docker Hub publishing completed successfully."""
            )
        }

        failure {
            emailext(
                to: 'yogeshtraj@gmail.com',
                from: 'yogeshtraj@gmail.com',
                replyTo: 'yogeshtraj@gmail.com',
                subject: "FAILURE: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """The Jenkins CI/CD pipeline failed.

Job: ${env.JOB_NAME}
Build Number: ${env.BUILD_NUMBER}
Build URL: ${env.BUILD_URL}

Please check the Jenkins Console Output for details."""
            )
        }
    }
}
