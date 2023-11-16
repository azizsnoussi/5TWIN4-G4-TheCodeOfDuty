pipeline {
    agent any

    stages {
         stage('Display pom.xml') {
            steps {
                script {
                    def pomContent = readFile('pom.xml')
                    echo "Content of pom.xml:\n${pomContent}"
                }
            }
        }

	stage('Maven Clean') {
            steps {
     
                sh 'mvn clean'
            }
        }
        stage('Maven Compile') {
            steps {
     
                sh 'mvn compile'
            }
        }

 
     

	stage('MVN SONARQUBE') {
            steps {
                sh 'mvn verify sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar -Dmaven.test.skip=true'
            }
        }    
        
   
     post {
      success {
        mail to: 'yassine.najar@esprit.tn',
        subject: 'Jenkins Build pipeline: Success',
        body: '''Your pipeline build success.'''
      }
      failure {
        mail to: 'yassine.najar@esprit.tn',
        subject: 'Jenkins Build pipeline: Failure',
        body: '''Your pipeline build failed.'''
      }
    }
}




