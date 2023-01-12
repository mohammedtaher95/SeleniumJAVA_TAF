node {
    def mvnHome
    stage('Get latest Pulls') { // for display purposes
        // Get some code from a GitHub repository
        git 'https://github.com/mohammedtaher95/SeleniumJAVA_TAF.git'
        // Get the Maven tool.
        // ** NOTE: This 'M3' Maven tool must be configured
        // **       in the global configuration.
        mvnHome = tool 'MAVEN_HOME'
    }

    stage('Starting Grid') { // for display purposes
        echo "ENV_TYPE=$Environment_Type" >> src/main/resources/properties/ExecutionPlatform.properties
        echo "CROSS_BROWSER_MODE=$Cross_Browser_Mode" >> src/main/resources/properties/ExecutionPlatform.properties
        echo "EXECUTION_METHOD=$Execution_Method" >> src/main/resources/properties/WebCapabilities.properties
        echo "TARGET_BROWSER_NAME=$Target_Browser_Name" >> src/main/resources/properties/WebCapabilities.properties
            if (isUnix()) {
                sh "docker-compose up -d"
            }
            else
            {
               bat("docker-compose up -d")
            }
    }

    stage('Clean Old Builds') {
            // Run the maven build
            withEnv(["MVN_HOME=$mvnHome"]) {
                if (isUnix()) {
                    sh '"$MVN_HOME/bin/mvn" clean'
                } else {
                    bat(/"%MVN_HOME%\bin\mvn" clean/)
                }
            }
        }
    stage('Run Tests') {
        // Run the maven build
        withEnv(["MVN_HOME=$mvnHome"]) {
            if (isUnix()) {
                sh '"$MVN_HOME/bin/mvn" -Dmaven.test.failure.ignore clean package -X'
            } else {
                bat(/"%MVN_HOME%\bin\mvn" -Dmaven.test.failure.ignore clean package -X/)
            }
        }
    }

    stage('Teardown Grid') { // for display purposes
                if (isUnix()) {
                    sh "docker-compose down"
                }
                else
                {
                   bat("docker-compose down")
                }
        }
    stage('Results') {
        // testng '**/target/surefire-reports/TEST-*.xml'
        // archiveArtifacts 'target/*.jar'
        allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
        step([$class: 'Publisher', reportFilenamePattern: '**/testng-results.xml'])
        
    }
}
