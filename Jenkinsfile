pipeline {
    agent any

    tools {
        maven "Maven@latest"
    }

    environment {
        GIT_REPOSITORY = "https://github.com/williamsayo/arithmetic-fx"
        DOCKERHUB_CREDENTIALS_ID = "docker_cred"
        DOCKERHUB_REPOSITORY = "williamsayo/sum-product_fx"
        DOCKER_IMAGE_TAG = "latest"
    }
    stages {

        stage('Checkout') {
            steps {
                git branch: "main", url: "${GIT_REPOSITORY}"
            }
        }

        stage('Check Docker') {
            steps {
                bat 'docker --version'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean package'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Build Docker Image') {
            steps {
                bat "docker build -t ${DOCKERHUB_REPOSITORY}:${DOCKER_IMAGE_TAG} ."
            }
        }

        stage('Push Docker Image to Docker Hub') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', DOCKERHUB_CREDENTIALS_ID) {
                        docker.image("${DOCKERHUB_REPOSITORY}:${DOCKER_IMAGE_TAG}").push()
                    }
                }
            }
        }

        post {
            always {
                junit '**/target/surefire-reports/*.xml'
                jacoco execPattern: '**/target/jacoco.exec'
            }
        }

    }
}