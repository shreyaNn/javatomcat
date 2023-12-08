pipeline {
    agent any
    stages {
        stage('Build Application') {
            steps {
                sh 'mvn -f pom.xml clean package'
            }
            post {
                success {
                    echo "Now Archiving the Artifact"
                    archiveArtifacts artifacts:'**/*.war'
                }
            }
        }

        stage('Test Application') {
            steps {
                echo 'Testing the Application'
            }
        }

        stage('Nexus Deploy') {
            steps {
                echo 'Deploying Artifacts to Nexus'
            }
        }

        stage('Deploy to Staging Enviornment') {
            steps {
                script {
                    deploy adapters: [tomcat9(credentialsId: 'Tomcat-Stage-Crdedentials', path: '', url: 'http://172.31.24.171:8080')], contextPath: '/', onFailure: false, war: '**/*.war'

                }
            }
        }

    }
}