```groovy
pipeline {
    agent any

    tools {
        maven 'MAVEN_HOME'
    }

    stages {
        stage('git repo & clean') {
            steps {
                bat 'if exist maven-main rmdir /s /q maven-main'
                bat 'git clone https://github.com/yogeshtraj/maven-main.git'
                bat 'mvn clean -f maven-main'
            }
        }

        stage('install') {
            steps {
                bat 'mvn install -f maven-main'
            }
        }

        stage('test') {
            steps {
                bat 'mvn test -f maven-main'
            }
        }

        stage('package') {
            steps {
                bat 'mvn package -f maven-main'
            }
        }
    }

    post {
        success {
            emailext(
                to: 'yogeshtraj@gmail.com',
                subject: "SUCCESS: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """The Jenkins pipeline completed successfully.

Job: ${env.JOB_NAME}
Build Number: ${env.BUILD_NUMBER}
Build URL: ${env.BUILD_URL}
"""
            )
        }

        failure {
            emailext(
                to: 'yogeshtraj@gmail.com',
                subject: "FAILURE: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """The Jenkins pipeline failed.

Job: ${env.JOB_NAME}
Build Number: ${env.BUILD_NUMBER}
Build URL: ${env.BUILD_URL}

Please check the Jenkins Console Output for details.
"""
            )
        }
    }
}
```
