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

        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("${DOCKERHUB_REPOSITORY}:${DOCKER_IMAGE_TAG}")
                }
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

    }

}