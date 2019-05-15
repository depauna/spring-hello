def dockerhub_username = 'depauna'
def img_name = 'spring-hello'
def img_tag = 'latest'
def namespace = 'depauna'

pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh """
          docker build . -t ${dockerhub_username}/${img_name}:${img_tag}
        """
      }
    }
    stage('Push') {
      steps {
        withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'depauna', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
          sh """
            echo ${WORKSPACE}
            docker login -u ${USERNAME} -p ${PASSWORD}
            docker push ${dockerhub_username}/${img_name}:${img_tag}
            docker logout
          """
        }
      }
    }
    stage('Deploy') {
      steps {
        sh """
          helm upgrade --wait --install -f user30-backend.yaml --namespace user30 --tiller-namespace user30 user30-employee-management-backend ../kubemania/employee-management-backend/
        """
      }
    }
  }
}
