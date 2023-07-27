pipeline {
    agent any
    stages(Build Application) {
        steps {
            sh 'mvn -f pom.xml clean package'
        }
        post {
            success {
                echo "Now Archiving the Artifact"
                        archiveArtifacts artifacts: '**/*.war'
            }
        }
    }
}