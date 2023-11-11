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

 
    stage('JUNIT / MOCKITO' ) {
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
                sh "docker build -t azizsnoussi/kaddemback ."
            }
        }
    
    
    stage('Docker Login') {
      steps {
        withCredentials([usernamePassword(credentialsId: 'dockercred', usernameVariable: 'DOCKERHUB_USERNAME', passwordVariable: 'DOCKERHUB_PASSWORD')]) {
          sh 'docker login -u azizsnoussi -p $DOCKERHUB_PASSWORD'
        }
      }
    }

    stage('Clean') {
            steps {
                 dir('etudiant-angular') {
                sh 'rm -rf node_modules'
                sh 'npm cache clean --force'
                sh'npm cache clean -f'
                 }
            }
        }

    stage('Install') {
            steps {
                dir('etudiant-angular') {
              sh 'npm install ng2-search-filter @angular/platform-browser/animations --legacy-peer-deps'
                sh 'npm install --legacy-peer-deps'
                 }
            }
        }

    stage('Build') {
            steps {
                dir('etudiant-angular') {
                sh 'ng build --configuration=production --output-path=dist'
              //sh 'ng serve --watch --proxy-config proxy.conf.json'
                echo 'Build stage done'
                }
            }
        }    

    stage('docker compose') {
            steps {
                sh "docker-compose up "
            }
        }
    }

     post {
      success {
        mail to: 'mohamedaziz.snoussi@esprit.tn',
        subject: 'Jenkins Build pipeline: Success',
        body: '''Your pipeline build success.'''
      }
      failure {
        mail to: 'mohamedaziz.snoussi@esprit.tn',
        subject: 'Jenkins Build pipeline: Failure',
        body: '''Your pipeline build failed.'''
      }
    }
}





