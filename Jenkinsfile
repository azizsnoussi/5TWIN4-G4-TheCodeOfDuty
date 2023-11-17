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

        stage('JUNIT / MOCKITO') {
            steps {
                sh 'mvn test'
            }
        }

        /*
        // Commented out the SonarQube stage
        stage('MVN SONARQUBE') {
            steps {
                sh 'mvn verify sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar -Dmaven.test.skip=true'
            }
        }
        */

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

        

    }
}
