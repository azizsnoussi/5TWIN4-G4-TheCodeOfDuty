pipeline {
    agent any

    stages {
         stage('Display pom.xml') {
            steps {
                // Read and display the content of the pom.xml file
                script {
                    def pomContent = readFile('pom.xml')
                    echo "Content of pom.xml:\n${pomContent}"
                }
            }
        }

        stage('MVN SONARQUBE') {
            steps {
                sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar -Dmaven.test.skip=true -Dsonar.exclusions=**/*.java';'
            }
        }

        stage('Maven Compile') {
            steps {
     
                sh 'mvn compile'
            }
        }

        stage('Maven Clean') {
            steps {
     
                sh 'mvn clean'
            }
        }
    }
}





