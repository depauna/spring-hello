pipeline {
  agent any
  environment {
    dockerhub_username = 'depauna'
    img_name = 'spring-hello'
    img_tag = sh (returnStdout: true, script: 'git log -1 --pretty=%h').trim()
    namespace = 'depauna'
  }
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
          helm upgrade --wait --install -f user30-frontend.yaml --set image.tag=${img_tag} --namespace user30 --tiller-namespace user30 user30-employee-management-frontend ../../kubemania/employee-management-frontend/
        """
      }
    }
  }
}
