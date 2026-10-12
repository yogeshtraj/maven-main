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
}
