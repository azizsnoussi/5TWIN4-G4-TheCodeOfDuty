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
                sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar -Dmaven.test.skip=true'
            }
        }
	stage('JUNIT / MOCKITO' ) {
            steps {
          
                sh 'mvn test'
            }
        }
    stage('NEXUS') {
            steps {
                sh 'mvn deploy -DskipTests=true'
            }
        }
    }
}





