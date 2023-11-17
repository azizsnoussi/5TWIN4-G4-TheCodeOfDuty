pipeline {
    agent any

    stages {
        stage('Maven Clean') {
            steps {
                sh 'mvn clean'
            }
        }

        stage('Maven Install') {
            steps {
                sh 'mvn install'
            }
        }

        stage('JUNIT / MOCKITO') {
            steps {
                sh 'mvn test'
            }
        }

        stage('MVN SONARQUBE') {
            steps {
                sh 'mvn verify sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar -Dmaven.test.skip=true'
            }
        }

        stage('NEXUS') {
            steps {
                sh 'mvn deploy -DskipTests'
            }
        }

        stage('Build Docker') {
            steps {
                sh "docker build -t yassinenajar/kaddem ."
            }
        }

        stage('Docker Login and Push') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhubid', usernameVariable: 'DOCKERHUB_USERNAME', passwordVariable: 'DOCKERHUB_PASSWORD')]) {
                    sh 'docker login -u $DOCKERHUB_USERNAME -p $DOCKERHUB_PASSWORD'
                    sh 'docker push yassinenajar/kaddem'
                }
            }
        }

        stage('Docker Compose') {
            steps {
                sh "docker-compose up -d "
            }
        }
    }

    post {
        success {
            emailext body: 'Build successful Yassine !',
                     subject: 'Build Success',
                     to: 'yassine.najar@esprit.tn'
        }
        failure {
            emailext body: 'Build failed Yassine !',
                     subject: 'Build Failure',
                     to: 'yassine.najar@esprit.tn'
        }
    }
}
